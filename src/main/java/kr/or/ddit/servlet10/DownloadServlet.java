package kr.or.ddit.servlet10;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


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
 * 1. charset 설정
 * 2. filename 파라미터 확보
 * 3. 누락여부 체크 - 누락시 400
 * 4. 파일 존재 여부 체크 - 없을시 404
 * 5. 다운로드가 될 수 있도록 헤더 설정(Content-Dispotision)
 * 6. 입/출력 스트림 확보
 * 7.
 */
@WebServlet("/multipart/download.do")
public class DownloadServlet extends HttpServlet {

    private Path saveDir = Paths.get("D:/multipartDir/saveDir");
    private static ServletContext application;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        application = getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String filename = req.getParameter("filename");
        if (filename == null || filename.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Path filePath = saveDir.resolve(filename);
        File file = filePath.toFile();
        if (!file.exists()) {
            // 파일이 존재하지 않거나, 파일이 아니라 디렉토리일 경우 404 에러 반환
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "파일을 찾을 수 없습니다.");
            return;
        }

        if (file.isDirectory()) {
            resp.sendError(400, "폴더는 다운로드안된다");
            return;
        }


        resp.setContentType(getServletContext().getMimeType(file.getName()));
        String encodedFilename = URLEncoder.encode(filename, "UTF-8").replace("+", " ");
        resp.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\"", encodedFilename));

        String mime = Optional.ofNullable(application.getMimeType(filename))
                        .orElse("application/octet-stream");
        resp.setContentType(mime);

        //파일 크기 알려주는거임
        resp.setContentLengthLong(file.length());

        // InputStream으로 파일 읽고 OutputStream으로 클라이언트로 전송
        try (OutputStream os = resp.getOutputStream()) {
            Files.copy(filePath, os);
        }
    }
    
}
