<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Arrays" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<h4> request Data.jsp</h4>
<a href="?key1=value1&key2=value2">GET 전송</a>
<form method="post" enctype="application/x-www-form-urlencoded">
    <input type="text" name="key3" value="value3"/>
    <input type="text" name="key3" value="value4"/>
    <button type="submit">POST전송</button>
</form>
<pre>
    1. query string : request line을 통해 전달되는 문자열 집합.
    <%= request.getQueryString()%>

    2. body content
        1) 파라미터 form-data : application/x-www-form-urlencoded
            ==> 요청 파라미터로 통일화된 방식으로 확보
            String getParameter(name);
            String[] getParameterValues(name); - 동일한 이름으로 전달되는 여러값들을 반환.
            Map&lt;String, String[]&gt; getParameterMap(); : 파라미터 전체가 관리되는 map 반환
            Enumeration&lt;String&gt; getParameterNames();
        2) 멀티파트 form0data
        3) json[xml] payload
</pre>
<pre>
    key1 : <%= request.getParameter("key1")%>
    key2 : <%= request.getParameter("key2")%>
    key3 : <%= Arrays.toString(request.getParameterValues("key3"))%>
</pre>
</body>
</html>