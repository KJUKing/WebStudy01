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
 * ** SSR 방식에서 전체 HTML 컨텐츠를 생성하는 책임의 분리 구조
 * 1. Model1 : request - process - reponse 3단계가 모두 하나의 서블릿이나 jsp로 처리되는 구조(책임 분리가 안된 상태)
 * 2. Model2 : request - process 단계를 컨트롤러로 표현하는 서블릿이 담당하고,
 *             response를 생성하고 전송하는 view 형태를 템플릿 엔진으로 처리하는 구조.
 *
 *
 */

@WebServlet("/ssr/case3")
public class Model2ServerSideRenderingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ZoneId timeZone = ZoneId.systemDefault();

        String information = timeZone.toString();

        req.setAttribute("tzId", information);
        req.getRequestDispatcher("/WEB-INF/views/ssr/case3.jsp").forward(req, resp);
    }
}
