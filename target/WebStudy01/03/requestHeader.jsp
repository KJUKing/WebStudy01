<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<h4> request header</h4>
<form method="post">
    <input type="text" name="key1" value="value1">
    <input type="text" name="key2" value="한글값">
    <button type="submit">POST 전송</button>
</form>
<pre>
    accept : <%=request.getHeader("Accept")%>
    accept-encoding : <%=request.getHeader("Accept-Encoding")%>
    accept-language : <%=request.getHeader("Accept-Language")%>
    content-type : <%=request.getHeader("Content-Type")%>
    content-Length : <%=request.getHeader("Content-Length")%>
</pre>
<table>
    <thead>
        <tr>
            <th>헤더이름</th>
            <th>헤더값</th>
        </tr>
    </thead>
    <tbody>
        <% Enumeration<String> headerNames = request.getHeaderNames();%>
        <%    while (headerNames.hasMoreElements()) {
                   String header = headerNames.nextElement();
                    String value = request.getHeader(header);



                System.out.println("header = " + header);
                System.out.println("value = " + value);
            }%>
                </th>
<%--            Collection : List, Set, Map--%>
<%--            Collection view : 순차접근이 불가능한 컬렉션을 순차적이 접근방법을 정의하고 있는 객체(Iterator, Enumeration)--%>

    </tbody>
</table>
</body>
</html>