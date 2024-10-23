package kr.or.ddit.servlet10;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

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
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/multipart/fileList")
public class SaveDirFileListServlet extends HttpServlet {


    private Path saveDirPath = Paths.get("D:/multipartDir/saveDir");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        resp.setContentType("application/json;charset=utf-8");
        List<Path> children = new ArrayList<>();
        Files.walkFileTree(saveDirPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                children.add(file);
                return super.visitFile(file, attrs);
            }
        });
//        File folder = new File(saveDirPath.toString());
//        String[] list = folder.list();


        //기가막힌 스트림
        String[] list = children.stream()
                .map(p -> p.getFileName().toString())
                .toArray(String[]::new);
        try (
                PrintWriter out = resp.getWriter();
                ) {
            new ObjectMapper().writeValue(out, list);

        }
//        req.setAttribute("list", list);
//        req.setAttribute("saveDirPath", saveDirPath.toString());

//        req.getRequestDispatcher("/WEB-INF/views/multipart/result.jsp").forward(req, resp);


    }

}
