package kr.or.ddit.props.controller;

import kr.or.ddit.props.PersonVO;
import kr.or.ddit.props.exception.PersonNotFoundException;
import kr.or.ddit.props.service.PersonService;
import kr.or.ddit.props.service.PersonServiceImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/props/personDetail.do")
public class PersonDetailController extends HttpServlet {

    private PersonService service = PersonServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("who");

        if (StringUtils.isBlank(id)) {
            resp.sendError(400, "필수 파라미터 누락");
        }
        try {
            PersonVO person = service.readPerson(id);
            req.setAttribute("person", person);
            req.getRequestDispatcher("/WEB-INF/views/props/personDetail.jsp").forward(req, resp);
        } catch (PersonNotFoundException e) {
            resp.sendError(404, e.getMessage());
        }






    }
}
