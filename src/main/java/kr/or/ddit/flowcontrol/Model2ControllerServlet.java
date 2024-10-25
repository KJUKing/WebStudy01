package kr.or.ddit.flowcontrol;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/flowcontrol")
public class Model2ControllerServlet extends HttpServlet {
    private Map<String, Object> recipe = new HashMap<>();
    {
        recipe.put("kimchi", "김치찌개");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("model", recipe);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/flowcontrol/mainLayout.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("key");
        String text = req.getParameter("value");
        boolean valid = true;
        Map<String, String> errors = new HashMap<>();

        if (name == null || name.trim().isEmpty()) {
            valid = false;
            errors.put("key", "레시피 네임 누락");
        }
        if (text == null || text.trim().isEmpty()) {
            valid = false;
            errors.put("value", "레시피 설명 누락");
        }

        if (valid) {
            recipe.put(name, text);
//            Post-Redirection-Get
            resp.sendRedirect(req.getContextPath()+"/flowcontrol");
        } else {
            resp.sendError(400, errors.toString());
        }

    }
}
