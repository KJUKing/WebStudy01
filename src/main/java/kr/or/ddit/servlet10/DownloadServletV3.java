package kr.or.ddit.servlet10;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;


/**
 * saveDir 내의 파일의 목록 중 하나를 선택하고, 다운로드 처리함
 * ** 서비스 설계
 * 1. get 요청 처리
 * 2. 선택한 파일명은 "filename" 파라미터로 전송됨.
 * 3. 파일명 누락된 경우, 400 전송
 * 4. 해당 파일이 없는 경우, 404 전송
 * 5. Stream copy
 *      - 다운로드 처리될 수 있는 헤더 결정
 */

/**
 * 내생각
 * jsp 리스트에서 이미지글 리스트중에서하나 선택
 * 선택과동시에 클릭 이벤트 발생
 * 이벤트 발생후 동기든 비동기든  filename파라미터를 전송
 * 서블릿에서 받음 get으로 받고나서 여기서 유효성검증 및 파일 위치 검증
 * 내가 inputStream으로 받아옴과동시에 outputStream으로 보내는 동시작업을 진행희망
 * <div id="result-area"></div> 여기다가 출력이되는데
 * 딱 하나만 다운되어야하니까 div result-area id태그의 e.target을 붙히는건어떨까 생각함
 *
 */
//@WebServlet("/multipart/download.do")
public class DownloadServletV3 extends HttpServlet {

    private static final String saveDir = "D:/multipartDir/saveDir";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String filename = req.getParameter("filename");
        if (filename == null || filename.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        File file = new File(saveDir, filename);
        if (!file.exists() || file.isDirectory()) {
            // 파일이 존재하지 않거나, 파일이 아니라 디렉토리일 경우 404 에러 반환
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "파일을 찾을 수 없습니다.");
            return;
        }


        resp.setContentType(getServletContext().getMimeType(file.getName()));
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(filename, "UTF-8") + "\"");
        resp.setContentLengthLong(file.length());

        // InputStream으로 파일 읽고 OutputStream으로 클라이언트로 전송
        try (InputStream in = new FileInputStream(file);
             OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
    
}
