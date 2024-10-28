<%@ page import="java.security.Principal" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>loginForm.jsp</title>
</head>
<body>
<h4>웰컴 페이지</h4>
현재 사용자가 인증된 상태라면 신원 정보를 출력하고,
인증되지 않은 사용자에게는 로그인 페이지 링크를 출력함.

<%
    Principal userPrincipal = request.getUserPrincipal();
    if (userPrincipal == null) {
%>
<a href="${pageContext.request.contextPath}/login/loginForm.jsp">로그인</a>
<%
} else {
//        response.sendRedirect(request.getContextPath()+"/login/loginForm.jsp");
%>
<h4>현재 사용자 : <%=userPrincipal %>
</h4>
<form id="logoutForm" method="post" action="<%=request.getContextPath()%>/login/logout.do"></form>
<a href="javascript:logoutForm.requestSubmit()">로그아웃</a>
<%
    }
%>

</body>

</html>
