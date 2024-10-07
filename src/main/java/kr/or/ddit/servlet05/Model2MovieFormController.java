package kr.or.ddit.servlet05;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(value = "/movie/model2/formUI.hw", loadOnStartup = 1)
public class Model2MovieFormController extends HttpServlet {

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
//        data -> information -> content
        String[] fileNames = folder.list((d, n) -> Optional.ofNullable(application.getMimeType(n))
                                                            .map(m -> m.startsWith("video/"))
                                                            .orElse(false)
        );

//        sugar syntax : 코드의 간결성을 지향하는 경향
        String pattern = "<option>%s</option>";

        String options = Arrays.stream(fileNames)
                .map(n -> String.format(pattern, n))
                .collect(Collectors.joining("\n"));

        req.setAttribute("options", options);

        req.getRequestDispatcher("/WEB-INF/views/movies/formUI.jsp").forward(req, resp);

    }
}
