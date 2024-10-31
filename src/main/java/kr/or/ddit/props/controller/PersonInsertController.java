package kr.or.ddit.props.controller;

import kr.or.ddit.props.PersonVO;
import kr.or.ddit.props.service.PersonService;
import kr.or.ddit.props.service.PersonServiceImpl;
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

/**
 * 새로운 person을 등록하고 나면,
 * 갱신된 list를 조회함.
 * 직전에 새로 등록된 person의 tr태그에 배경색을 입힐것.
 *
 */
@WebServlet("/props/personInsert.do")
public class PersonInsertController extends HttpServlet {

    private PersonService service = PersonServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/props/personForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        System.out.println("addr확인 = " + req.getParameter("addr"));

        PersonVO person = new PersonVO();
        person.setId(req.getParameter("id"));
        person.setName(req.getParameter("name"));
        person.setGender(req.getParameter("gender"));
        person.setAge(req.getParameter("age"));
        person.setAddress(req.getParameter("address"));

        Map<String, String> errors = new HashMap<String, String>();
        validate(person, errors);
        boolean valid = errors.isEmpty();

        if (valid) {
            boolean result = service.createPerson(person);
            if (result) {
                HttpSession session = req.getSession();
                session.setAttribute("id", req.getParameter("id"));
                resp.sendRedirect(req.getContextPath() + "/props/personList.do");
            } else {
                resp.sendError(500, "서버문제있음");
            }
        } else {
            resp.sendError(400, errors.toString());

        }
    }

    private void validate(PersonVO person, Map<String, String> errors) {
        boolean valid = true;

        if (StringUtils.isBlank(person.getId())) {
            valid = false;
            errors.put("id", "아이디 누락");
        }
        if (StringUtils.isBlank(person.getName())) {
            valid = false;
            errors.put("name", "이름 누락");
        }
        if (StringUtils.isBlank(person.getGender())) {
            valid = false;
            errors.put("gender", "성별 누락");
        }
        if (StringUtils.isBlank(person.getAge())) {
            valid = false;
            errors.put("age", "나이 누락");
        }
        if (StringUtils.isBlank(person.getAddress())) {
            valid = false;
            errors.put("address", "주소 누락");
        }
    }

}


