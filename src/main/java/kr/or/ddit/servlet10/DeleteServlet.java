package kr.or.ddit.servlet10;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

@WebServlet("/multipart/delete.do")
public class DeleteServlet extends HttpServlet {

    private Path saveDirPath = Paths.get("D:/multipartDir/saveDir");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 요청이 JSON인지 확인 (415 Unsupported Media Type)
        String contentType = req.getContentType();
        if (contentType == null || !contentType.contains("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "지원하지 않는 미디어 타입입니다.");
            return;
        }

        // ObjectMapper를 사용하여 JSON 데이터 파싱
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(req.getInputStream(), Map.class);
        List<String> filenames = (List<String>) data.get("filenames");

        // 파일명이 없는 경우 400 Bad Request
        if (filenames == null || filenames.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "삭제할 파일명이 없습니다.");
            return;
        }

        // 파일 삭제하기
        try {
            for (String filename : filenames) {
                // 파일명 검증 (경로 조작 방지) 지피티피셜
                if (filename.contains("..") || filename.contains("/") || filename.contains("\\")) {
                    continue; // 부적절한 파일명은 무시
                }
                //코드 재활용
                Path filePath = saveDirPath.resolve(filename);
                File file = filePath.toFile();
                if (file.exists() && file.isFile()) {
                    Files.delete(filePath);
                }
            }
            // json fetch결과값을 넘겨줘야하기때문
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            // 500오류발생하기
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "파일 삭제 중 오류 발생");
            e.printStackTrace();
        }
    }
}
