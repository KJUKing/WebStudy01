package kr.or.ddit.servlet04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;

/**
 * Html페이지의 렌더링 방식
 * 1. SSR방식 ServerSideRendering : 최종 UI 구성하는 전체 HTML 소스를 서버에서 생성해서 한번의 응답으로 전송하는 형태
 * 2. CSR방식 ClientSideRendering : 최초의 응답으로 html 소스의 일부 템플릿을 전송하고,
 *                                  한번 이상의 데이터에 대한 응답으로 전송된 데이터를
 *                                  클라이언트 측에서 한번 이상 추가 렌더링을 하는 형태
 */

@WebServlet("/csr/case2")
public class ClientSideRenderingDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ZoneId timeZone = ZoneId.systemDefault();

        String information = timeZone.toString();

        String pattern = "{\"tzId\":\"%s\"}";
        resp.setContentType("application/json;charset=UTF-8");

        try (PrintWriter out = resp.getWriter();) {
            out.println(String.format(pattern, information));
        }
    }
}
