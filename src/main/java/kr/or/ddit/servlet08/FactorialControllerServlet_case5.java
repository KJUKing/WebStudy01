package kr.or.ddit.servlet08;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/factorial/case5")
public class FactorialControllerServlet_case5 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/factorial/case5Form.jsp").forward(req, resp);
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

            String accept = req.getHeader("accept");
            resp.setContentType(accept);

            ObjectMapper mapper = new ObjectMapper();
            try(
                PrintWriter out = resp.getWriter();
            ){
                mapper.writeValue(out, facVO);
            }

        }
    }


}

