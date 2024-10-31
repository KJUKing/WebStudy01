<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>07/implicitObject.jsp</title>
</head>
<body>
<h4>JSP스펙에서 제공되는 기본 객체의 종류</h4>
<pre>

<%--    state less--%>
    request(HttpServletRequest) : 클라이언트와 클라이언트로부터 발생한 요청에 대한 정보를 가진 객체
    response(HttpServletResponse) : 서버에서 클라이언트로 전송되는 응답에 대한 정보를 가진 객체

    ***
<%--    state full--%>
    session(HttpSession) : 하나의 클라이언트로부터 발생한 모든 세션 정보를 가진 객체
        Connect-Full (DB) : 클라이언트와 서버사이의 유일한 연결 통로
        Connect-Less (Web) : 클라이언트가 어플리케이션을 사용하기 시작한 시점부터 종료 이벤트를 발생 시켰을때까지의 기간
        <a href="ssesionDesc.jsp">Http Session</a>
    application(ServletContext) : 서버와 현재 컨텍스트에 대한 정보를 가진 객체 (동일 컨텍트스 내에서는 싱글톤 형태로 관리됨)
        서버 버전 정보 : <%=application.getServerInfo()%>
        서블릿 버전 정보 : <%=application.getMajorVersion()%>.<%=application.getMinorVersion()%>
        Mime type 조회 : <%=application.getMimeType("test.jpg")%>
        Real Path : <%=application.getRealPath("/08/test.html")%>
        context Path : <%=application.getContextPath()%>
    pageContext(PageContext) : jsp와 관련된 모든 정보를 가진 객체로, 나머지 모든 기본 객체를 소유하고 있음.
    <%
        request.setAttribute("requestAttr", "요청 속성");
        pageContext.setAttribute("requestAttr", "요청 속성", PageContext.REQUEST_SCOPE);

    %>
<%--    <%=request.getAttribute("requestAttr")%>--%>
    <%=pageContext.getAttribute("requestAttr", PageContext.REQUEST_SCOPE)%>
    <%
        pageContext.removeAttribute("requestAttr", PageContext.REQUEST_SCOPE);
    %>
    지운 이후 : <%=request.getAttribute("requestAttr")%>


</pre>

<h4> Scope </h4>
<pre>
    : 데이터 저장공간(Map&lt;String, Object&gt;), 해당 공간에 저장된 데이터를 attribute(name/value)이라고함

    request scope : request 객체와 생명주기가 동일한 map
    session scope : session 객체와 생명주기가 동일한 map
    application scope : servlet context객체와 생명주기가 동일한 map

    setAttribute(name, value), Object getAttribute(name), removeAttribute(name)
</pre>
</body>
</html>
