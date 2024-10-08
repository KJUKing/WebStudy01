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


@WebServlet("/image/csr/fileList")
public class ClientSideRenderingImageFileListJsonController extends HttpServlet {

    private File folder;
    private ServletContext application;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        application = getServletContext();
        folder = Optional.ofNullable(application.getInitParameter("imagefolderQN"))
                .map(qn -> this.getClass().getResource(qn))
                .map(url -> url.getFile())
                .map(rp -> new File(rp))
                .orElseThrow(() -> new ServletException("폴더가 없음"));
        System.out.println(folder.getAbsolutePath());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        data -> information -> content
        String[] fileNames = folder.list((d, n) -> Optional.ofNullable(application.getMimeType(n))
                                                            .map(m -> m.startsWith("image/"))
                                                            .orElse(false)
        );

//        sugar syntax : 코드의 간결성을 지향하는 경향
        String pattern = "\"%s\"";

        String elements = Arrays.stream(fileNames)
                .map(n -> String.format(pattern, n))
                .collect(Collectors.joining(","));

        StringBuffer json = new StringBuffer();

        json.append("[");
        json.append(elements);
        json.append("]");

        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().print(json);
    }
}
