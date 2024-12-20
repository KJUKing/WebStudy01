<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" buffer="8kb" autoFlush="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>06/cacheControl.jsp</title>
</head>
<body>
    <h4>캐시 제어</h4>
<pre>
    Http의 특성
    Connect-less : response가 수신되면, connection은 close됨
    State-less : request/response 객체는 삭제됨.

    1. Request Dispatcher : 최초로 발생한 요청이 이동하는(흐름이 제어되는 구조에서) 그대로 유지되는 방식
        1) forward : 요청과 응답에 대한 처리자가 분리되는 구조 --> Model2 아키텍처에서 사용됨
        2) include : 하나의 응답 페이지를 구성하기위한 책임이 두개 이상의 객체로 분리된 구조.

    <%--
        //RequestDispatcher rd = request.getRequestDispatcher("/06/dest.jsp");
        RequestDispatcher rd = application.getRequestDispatcher("/06/dest.jsp");
        rd.forward(request, response);

//        rd.include(request, response);
    --%>
    <%
        request.setAttribute("reqeustAttr", "요청 속성");
        session.setAttribute("sessionAttr", "세션 속성");
        application.setAttribute("applicationAttr", "어플리케이션 속성");
    %>
<%--    <jsp:forward page="/06/dest.jsp" />--%>
<%--    <jsp:include page="/06/dest.jsp" />--%>
    2. Redirect : 자원의 위치를 재지정하는 경우 PostRedirectGet 패턴의 구조에서 활용됨
        1)최초의 요청 발생
        2)body가 없는 응답이 전송(307/302, Location 헤더 포함)
        --> Connection close, StateLess
        3) Location 헤더 방향으로 완전히 새로운 요청이 전송
        4) 최종 응답 전송
    <%
        String location = request.getContextPath() +"/06/dest.jsp";
        response.sendRedirect(location);
    %>

</pre>
</body>
</html>