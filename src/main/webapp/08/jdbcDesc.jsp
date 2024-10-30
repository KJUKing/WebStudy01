<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="kr.or.ddit.db.ConnectionFactory" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>/08/jdbcDesc.jsp</title>
</head>
<body>
<h4>07/elDesc.jsp</h4>
<h4> JDBC (Java DataBase Connectivity</h4>
<pre>
    JDBC 프로그래밍 단계
    java.sql 패키지의 언티페이스 구현체 : 드라이버
    <%
        try (
//        2. Connection 생성
                Connection conn = ConnectionFactory.getConnection();
//        3. 쿼리 객체 생성
                Statement stmt = conn.createStatement();
        ) {
//        4. 쿼리 실행
            String sql = "select MEM_ID, MEM_NAME from Member";
            ResultSet rs = stmt.executeQuery(sql); // 조회 결과는 포인터를 가진 커서 형태이다.

//        5. 쿼리 결과 집합 핸들링

            Map<String, String> resultMap = new HashMap<String, String>();
            request.setAttribute("resultMap", resultMap);
            while (rs.next()) {
                resultMap.put(rs.getString("MEM_ID"), rs.getString("MEM_NAME"));
            }
        }
    %>
    6. 사용한 자원 release (close)

</pre>
${resultMap.size()}
<c:set var="members" value="${resultMap}" />
<table>
    <thead>
    <tr>
        <th>아이디</th>
        <th>이름</th>
    </tr>
    </thead>
    <tbody>
        <c:if test="${not empty members}">
            <c:forEach items="${members}" var="mem">
                <tr>
                    <td>${mem.key}</td>
                    <td><a href="detail.jsp?who=${mem.key}'or'1'='1">${mem.value}</a></td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty members}"/>
    </tbody>
</table>
</body>
</html>