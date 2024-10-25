package kr.or.ddit.flowcontrol.mbti;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * RestFul URI
 *  /mbti (GET)
 *  /mbti/enfj (GET) : path variable 형태로 데이터를 수신하기도 함.
 *  /mbti (POST)
 *  /mbti/entj (DELETE)
 *
 */
@WebServlet("/mbti/*")
@MultipartConfig
public class MbtiServlet extends HttpServlet{
    private Map<String, String>  mbtiMap;
    private ServletContext application;
    private Path mbtiFolderPath;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mbtiMap = new LinkedHashMap<>();
        mbtiMap.put("istj", "1. ISTJ 소금형");
        mbtiMap.put("isfj", "2. ISFJ 권력형");
        mbtiMap.put("infj", "3. INFJ 예언자형");
        mbtiMap.put("intj", "4. INTJ 과학자형");
        mbtiMap.put("istp", "5. ISTP 백과사전형");
        mbtiMap.put("isfp", "6. ISFP 성인군자형");
        mbtiMap.put("infp", "7. INFP 잔다르크형");
        mbtiMap.put("intp", "8. INTP 아이디어형");
        mbtiMap.put("estp", "9. ESTP 활동가형");
        mbtiMap.put("esfp", "10. ESFP 사교형");
        mbtiMap.put("enfp", "11. ENFP 스파크형");
        mbtiMap.put("entp", "12. ENTP 발명가형");
        mbtiMap.put("estj", "13. ESTJ 사업가형");
        mbtiMap.put("esfj", "14. ESFJ 친선도모형");
        mbtiMap.put("enfj", "15. ENFJ 언변능숙형");

        application = getServletContext();
        String realPath = application.getRealPath("/WEB-INF/views/mbti/mbtitypes");
        mbtiFolderPath = Paths.get(realPath);
    }

    private void singleMbti(String type, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 3. type 파라미터가 유효성 검사
        if(type == null || type.trim().isEmpty()) {
            resp.sendError(400);
            return;
        }

        // 4. type에 대한 파일이 있는지 확인 ?????

        // 5. type에 해당하는 파일에 접근하기.
        req.getRequestDispatcher("/WEB-INF/views/mbti/mbtitypes/"+type+".html").forward(req, resp);
    }

    private void allMbti(HttpServletResponse resp) throws IOException{
        resp.setContentType("application/json;charset=UTF-8");
        try(
                OutputStream os = resp.getOutputStream();
        ){
            new ObjectMapper().writeValue(os, mbtiMap);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 한글 깨짐처리
        req.setCharacterEncoding("UTF-8");
        // 2. 파라미터 받기
        String requestURI = req.getRequestURI();
        Pattern regex = Pattern.compile(req.getContextPath()+"/mbti/([a-z]{4})");
        Matcher matcher = regex.matcher(requestURI);
        if(matcher.find()) {
            String type = matcher.group(1);
            singleMbti(type, req, resp);
        }else {
            allMbti(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 특수문자에 대한 디코딩 설정
        req.setCharacterEncoding("UTF-8");
//		2. 문자기반의 파트는 parameter 로 수신
        String mbtiType = req.getParameter("mbtiType");
        String mbtiDesc = req.getParameter("mbtiDesc");
//		3. 파일기반의 파트는 Part 로 수신
        Part mbtiFile = req.getPart("mbtiFile");
//		4. 검증(필수 데이터 가정)
        boolean valid = true;
        Map<String, String> errors = new HashMap<>();
        if(StringUtils.isBlank(mbtiType)) {
            valid = false;
            errors.put("mbtiType", "유형 데이터 누락");
        }
        if(StringUtils.isBlank(mbtiDesc)) {
            valid = false;
            errors.put("mbtiDesc", "유형설명 데이터 누락");
        }
        if(mbtiFile==null || StringUtils.isBlank(mbtiFile.getSubmittedFileName())) {
            valid = false;
            errors.put("mbtiFile", "컨텐츠 파일 누락");
        }else if(!mbtiFile.getContentType().contains("html")) {
            valid = false;
            errors.put("mbtiFile", "컨텐츠 파일은 html 만 허용함.");
        }
        if(valid) {
//		6. 새로운 MBTI 유형 등록
//		   1) mbtiMap 에 엔트리 추가
            mbtiMap.put(mbtiType, mbtiDesc);
//		   2) 컨텐츠 파일 저장
            Path filePath = mbtiFolderPath.resolve(mbtiType+".html");
            mbtiFile.write(filePath.toString());
//		7. redirect 로 /mbti (GET) : P-R-G pattern
            resp.sendRedirect(req.getContextPath() + "/mbti");
        }else {
//		5. 검증 실패 : 400 에러 전송
            resp.sendError(400, errors.toString());
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        Pattern regex = Pattern.compile(req.getContextPath()+"/mbti/([a-z]{4})");
        Matcher matcher = regex.matcher(requestURI);
        if(matcher.find()) {
            String type = matcher.group(1);
            mbtiMap.remove(type);
            Path delFilePath = mbtiFolderPath.resolve(type+".html");
            boolean success = Files.deleteIfExists(delFilePath);
            Map<String, Object> target = Collections.singletonMap("deleted", success ? 1 : 0);
            resp.setContentType("application/json;charset=UTF-8");
            try(
                    OutputStream os = resp.getOutputStream();
            ){
                new ObjectMapper().writeValue(os, target);
            }
        }else {
            resp.sendError(405, "전체 삭제는 지원하지 않음.");
        }
    }
}











