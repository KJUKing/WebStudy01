package kr.or.ddit.servlet08;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/factorial/case3")
public class FactorialControllerServlet_case3 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/factorial/case3Form.jsp");
        rd.forward(req, resp);
        //디스패처는 서버안에서만 이동하는것임 **중요**
        //디스패처는 위임구조를 만들때 사용한다
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String opParam = req.getParameter("operand");
        if (opParam != null && !opParam.trim().isEmpty()) {
            Integer num2 = Optional.of(opParam)
                    .filter(op -> op.matches("\\d+"))
                    .map(Integer::parseInt)
                    .orElseThrow(() -> new IllegalArgumentException("필수 파라미터 누락 혹은 잘못된 데이터 입력"));

            FactorialVO facVO = new FactorialVO();
            facVO.setOperand(num2);

            req.setAttribute("facVO", facVO);

            req.getRequestDispatcher("/WEB-INF/views/factorial/case3Result.jsp").forward(req, resp);
        }
    }

    
}

