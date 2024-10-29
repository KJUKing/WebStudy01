package kr.or.ddit.props.controller;

import kr.or.ddit.props.PersonVO;
import kr.or.ddit.props.service.PersonService;
import kr.or.ddit.props.service.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 다건조회 : /props/personList.do(GET)
 * 단건조회 : /props/personDetail.do?who=a001(GET)
 * 등록 : /props/personInsert.do(GET, POST)
 * 수정 : /props/personUpdate.do?who=a001(GET, POST)
 * 삭제 : /props/personDelete.do?who=a001(GET)
 *
 * HCLC - High Cohesion Low Coupling
 */
@WebServlet("/props/personList.do")
public class PersonListController extends HttpServlet {
    private PersonService service = new PersonServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PersonVO> personList = service.readPersonList();

        req.setAttribute("list", personList);

        String view = "/WEB-INF/views/props/personList.jsp";
        req.getRequestDispatcher(view).forward(req, resp);
    }
}
