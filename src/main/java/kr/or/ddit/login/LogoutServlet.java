package kr.or.ddit.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 *
 * 1.로그아웃 처리(doPost)에서
 * 2.로그아웃 성공 후 웰컴 페이지로 이동 redirect
 * 3.
 */
@WebServlet("/login/logout.do")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        if (session.isNew()) {
            resp.sendError(400, "현재 요청이 최초 요청일수가없음");
            return;
        }
        //삭제하기
        req.removeAttribute("j_username");

        req.getSession().invalidate();

        resp.sendRedirect(req.getContextPath());
    }
}
