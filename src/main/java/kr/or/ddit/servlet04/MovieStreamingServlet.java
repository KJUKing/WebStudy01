package kr.or.ddit.servlet04;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Optional;

@WebServlet(value = "/movie/streaming.hw", loadOnStartup = 2)
public class MovieStreamingServlet extends HttpServlet {
    private File folder;
	private ServletContext application;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        application = getServletContext();
        folder = Optional.ofNullable(application.getInitParameter("moviefolder"))
//                .map(qn -> this.getClass().getResource(qn))
//                .map(url -> url.getFile())
                .map(rp -> new File(rp))
                .orElseThrow(() -> new ServletException("폴더가 없음"));
        System.out.println(folder.getAbsolutePath());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File videoFile = Optional.ofNullable(req.getParameter("video"))
                .map(p -> new File(folder, p))
                .filter(f-> f.exists())
                .orElseThrow(() -> new ServletException("필수 파라미터가 누락됨"));
        resp.setContentType(application.getMimeType(videoFile.getName()));
        try ( // try with resourse구문
              // Closable 객체 선언하는것
            OutputStream os = resp.getOutputStream();
        ) {
            Files.copy(videoFile.toPath(), os);
        }
    }
}
