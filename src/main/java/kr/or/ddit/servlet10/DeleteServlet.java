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

        // JSON인지 확인하기 415오류 써보기
        String contentType = req.getContentType();
        if (contentType == null || !contentType.contains("application/json")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "지원하지 않는 미디어 타입임");
            return;
        }

        // 오브젝트매퍼써서 JSON 데이터 파싱하기
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(req.getInputStream(), Map.class);
        List<String> filenames = (List<String>) data.get("filenames");

        //400오류 만들기
        if (filenames == null || filenames.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "삭제할 파일명이 없음");
            return;
        }

        // 파일 삭제하기
        for (String filename : filenames) {
            // 파일명 검증 (경로 조작 방지) 이건 지피티가 보완해줌
            if (filename.contains("..") || filename.contains("/") || filename.contains("\\")) {
                continue; // 부적절한 파일명은 무시
            }
            Path filePath = saveDirPath.resolve(filename);
            File file = filePath.toFile();
            // isDirectory를 썼으니 이번에는 isFile일때로해보기
            if (file.exists() && file.isFile()) {
                Files.delete(filePath);
            }
        }
    }
}