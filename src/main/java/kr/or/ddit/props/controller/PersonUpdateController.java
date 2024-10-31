package kr.or.ddit.props.controller;

import com.fasterxml.jackson.databind.util.BeanUtil;
import kr.or.ddit.props.PersonVO;
import kr.or.ddit.props.service.PersonService;
import kr.or.ddit.props.service.PersonServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/props/personUpdate.do")
public class PersonUpdateController extends HttpServlet {


    private PersonService service = PersonServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        System.out.println("addr확인 = " + req.getParameter("addr"));

        PersonVO person = new PersonVO();

        try {
            BeanUtils.populate(person, req.getParameterMap());
        }catch (IllegalAccessException | InvocationTargetException e) {
            throw new ServletException(e);
        }
        Map<String, String> errors = new HashMap<String, String>();
        validate(person, errors);
        boolean valid = errors.isEmpty();

        if (valid) {
            boolean result = service.modifyPerson(person);
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
