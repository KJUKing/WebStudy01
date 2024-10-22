package kr.or.ddit.serlvet11;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 1. 서비스 설계
 * <p>
 * 1) head 메소드 : no-op handler
 * 2) get 메소드 : 자원 서비스
 * - JSON, HTML 컨텐츠로 서비스
 * 3) post 메소드 : 자원 수신
 * - JSON payload, request parameter, multipart컨텐츠 수신
 * - 수신한 자원에는 필수 데이터(data3)가 포함되어있는지 검증함
 */
@WebServlet("/status/send-and-receieve")
public class ResponseStatusCaseServlet extends HttpServlet {
    Map<String, Object> model = new HashMap<String, Object>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        model.put("data1", "DATA1");
        model.put("data2", "DATA2");
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Dummy Content"); // response body 를 통해 전송될까?
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String authorization = req.getHeader("authorization");
        if (authorization == null) {
            resp.setHeader("Www-Authenticate", "basic");
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String userToken = authorization.split("\\s+")[1];
        userToken = new String(Base64.getDecoder().decode(userToken));
        System.out.printf("사용자 식별 정보 : %s\n", userToken);

        if (!userToken.startsWith("admin")) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "관리자만 접근 할 수 있음");
            return;
        }


        String accept = Optional.ofNullable( req.getHeader("accept"))
                .map(String::toLowerCase)
                .orElse("");
        String content = null;
        String contentType = null;
        if (accept.contains("json")) {
            contentType = "application/json;charset=UTF-8";
            content = new ObjectMapper().writeValueAsString(model);
        } else if (accept.contains("xml")) {
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "xml서비스는 서비스 불가임");
            return;
        } else {
            contentType = "text/html;charset=UTF-8";
            content = String.format("<h4>%s</h4>", model.toString());
        }
        resp.setContentType(contentType);
        try (
                PrintWriter out = resp.getWriter();
                ){
            out.print(content);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String contentType = Optional.ofNullable(req.getContentType())
                .map(String::toLowerCase)
                .orElse("");

        Map<String, ?> requestContent = null;
        if (contentType.contains("json")) {
            requestContent = new ObjectMapper().readValue(req.getInputStream(), HashMap.class);
        } else if (contentType.contains("xml")) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "xml 페이로드는 수신 할 수 없음");
            return;
        } else {
            requestContent = req.getParameterMap();
        }
        Object data3 = requestContent.get("data3");
        if (data3 == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효성 검증 통과못함");
            return;
        } else {
            model.put("data3", data3);
            // PRG패턴사용
            resp.sendRedirect(req.getContextPath() + "/status/send-and-receieve");
        }

    }
}
