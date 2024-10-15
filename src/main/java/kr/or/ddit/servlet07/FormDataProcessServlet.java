package kr.or.ddit.servlet07;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;

@WebServlet("/request/parameters")
public class FormDataProcessServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.printf("현재 요청의 메소드 : %s\n", req.getMethod());
        getParametersCase1(req, resp);
        getParametersCase2(req);
        getParametersCase3(req);
    }

    /**
     * getParameterMap
     * @param req
     */
//    private void getParametersCase7(HttpServletRequest req) {
//        Map<String, String[]> parameterMap = req.getParameterMap();
//        parameterMap.entrySet().stream().for
//    }


    /**
     * getParameterMap
     * @param req
     */
    private void getParametersCase6(HttpServletRequest req) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        parameterMap.forEach((k, v) ->  System.out.printf(pattern, k, Arrays.toString(v)));
    }

    /**
     * getParameterMap
     * @param req
     */
    private void getParametersCase5(HttpServletRequest req) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        for( Entry<String, String[]> entry : parameterMap.entrySet()){
            String name = entry.getKey();
            String[] values = entry.getValue();
        }
    }

    /**
     * getParameterMap
     * @param req
     */
    private void getParametersCase4(HttpServletRequest req) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (String name : parameterMap.keySet()) {
            String[] values = req.getParameterValues(name);
            System.out.printf(pattern, name, Arrays.toString(values));
        }
    }

    /**
     * getParameterMap
     * @param req
     */
    private void getParametersCase3(HttpServletRequest req) {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Iterator<String> parameterNames = parameterMap.keySet().iterator();
        while (parameterNames.hasNext()) {
            String name = (String) parameterNames.next();
            String[] values = req.getParameterValues(name);
            System.out.printf(pattern, name, Arrays.toString(values));
        }

    }

    /**
     * getParameternames와 getParameter. getParameterVlaues
     * @param req
     */
    private void getParametersCase2(HttpServletRequest req) {
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = (String) parameterNames.nextElement();
            String[] values = req.getParameterValues(name);
            System.out.printf(pattern, name, Arrays.toString(values));
        }

    }

    private String pattern = "%s : %s\n";

    /**
     * getParameter, getParameterValues
     *
     * @param req
     */
    private void getParametersCase1(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try{

        //필수 파라미터임
        int param1 = Optional.ofNullable(req.getParameter("param1"))
                .filter(p1 -> p1.matches("\\d{1,4}"))
                .map(Integer::parseInt)//메소드 레퍼런스
                .orElseThrow(() -> new IllegalArgumentException(String.format("필수 파라미터 누락 혹은 잘못된 데이터 입력")));
        System.out.printf(pattern, "param1", param1);

        LocalDate param2 = Optional.ofNullable(req.getParameter("param2"))
                .filter(p2 -> p2.matches("\\d{4}-\\d{2}-\\d{2}"))
//                .map(p2->LocalDate.parse(p2))//메소드 레퍼런스
                .map(LocalDate::parse)
                .orElseThrow(() -> new IllegalArgumentException(String.format("필수 파라미터 누락 혹은 잘못된 데이터 입력")));
        System.out.printf(pattern, "param2", param2);

        LocalDateTime param3 = Optional.ofNullable(req.getParameter("param3"))
                .map(LocalDateTime::parse)
                .orElseThrow(() -> new IllegalArgumentException(String.format("필수 파라미터 누락 혹은 잘못된 데이터 입력")));
        System.out.printf(pattern, "param3", param3);

        String param4 = Optional.of(req.getParameter("param4"))
                .get();
        System.out.printf(pattern, "param4", param4);

        String[] param5 = Optional.of(req.getParameterValues("param5"))
                        .get();
        System.out.printf(pattern, "param5", Arrays.toString(param5));

        String param6 = Optional.of(req.getParameter("param6"))
                .get();
        System.out.printf(pattern, "param6", param6);

        String param7 = Optional.of(req.getParameter("param7"))
                .get();
        System.out.printf(pattern, "param7", param7);

        String[] param8 = Optional.of(req.getParameterValues("param8"))
                        .get();
        System.out.printf(pattern, "param8", Arrays.toString(param8));
        }catch (RuntimeException e){// try end
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
