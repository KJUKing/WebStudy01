package kr.or.ddit.servlet02;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class ImageStreamingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // classpath resource를 web resource로 서비스하고싶어함.

        String imageFileName = req.getParameter("image");
        if (imageFileName == null || imageFileName.trim().length() == 0) {
            throw new ServletException("Please input image file name");
        }
        System.out.println("Requested image: " + imageFileName);

        if (imageFileName == null || imageFileName.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Image parameter is missing");
            return;
        }

        ServletContext application = getServletContext();
        String mime = application.getMimeType(imageFileName);
        System.out.println("MIME type: " + mime);

        if (mime == null) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Unsupported image type");
            return;
        }

        resp.setContentType(mime);

        String imageLogicalPath = "/kr/or/ddit/images/" + imageFileName;
        InputStream is = this.getClass().getResourceAsStream(imageLogicalPath);
        if (is == null) {
            throw new ServletException("Could not find image logical path: " + imageLogicalPath);
        }
        System.out.println("Image path: " + imageLogicalPath);

        OutputStream out = resp.getOutputStream();
        byte[] buffer = new byte[1024];
        int cnt = -1;

        try {
            while ((cnt = is.read(buffer)) != -1) { // EOF를 만나기 전까지 반복
                out.write(buffer, 0, cnt);
            }
        } finally {
            is.close();
            out.close();
        }
    }
}

