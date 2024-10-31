package kr.or.ddit.login;

import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.vo.MemberVO;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login/loginCheck.do")
public class loginCheckServlet extends HttpServlet {
    private AuthenticateService service = new AuthenticateServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        MemberVO inputData = new MemberVO();
        inputData.setMemId(req.getParameter("memId"));
        inputData.setMemPass(req.getParameter("memPass"));


        String dest = null;
        Map<String, String> errors = new HashMap<>();
        HttpSession session = req.getSession();
        req.getSession().setAttribute("errors", errors);
        validate(inputData, errors);
        if (errors.isEmpty()) {
            MemberVO authMember = service.authenticate(inputData);
            if (authMember != null) {
                session.setAttribute("authMember", authMember);
                dest = "/";
            } else {
                session.setAttribute("message", "로그인 실패, 아이디나 비밀번호가 틀림");
                dest = "/login/loginForm.jsp";
            }
        } else {
            dest = "/login/loginForm.jsp";
        }

        resp.sendRedirect(req.getContextPath() + dest);

    }

    private void validate(MemberVO inputData, Map<String, String> errors) {


        if (StringUtils.isBlank(inputData.getMemId())) {
            errors.put("memId", "아이디 누락");
        }

        if (StringUtils.isBlank(inputData.getMemPass())) {
            errors.put("memPass", "비밀번호 누락");
        }
    }
}
