<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="java.sql.*" %>
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
        String memId = request.getParameter("who");
        if (StringUtils.isBlank(memId)) {
            response.sendError(400, "필수 파라미터 누락임");
            return;
        }

        Class.forName("oracle.jdbc.driver.OracleDriver");
        String sql = "select MEM_ID, MEM_NAME, MEM_HP, MEM_ADD1 from Member where MEM_ID = ?";
        try (
//        2. Connection 생성
                Connection conn = ConnectionFactory.getConnection();
//        3. 쿼리 객체 생성
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
//        4. 쿼리 실행
            stmt.setString(1, memId);
            ResultSet rs = stmt.executeQuery(); // 조회 결과는 포인터를 가진 커서 형태이다.

//        5. 쿼리 결과 집합 핸들링

            Map<String, String> resultMap = new HashMap<String, String>();
            request.setAttribute("resultMap", resultMap);
            if(rs.next()) {
                resultMap.put("MEM_ID", rs.getString("MEM_ID"));
                resultMap.put("MEM_NAME", rs.getString("MEM_NAME"));
                resultMap.put("MEM_HP", rs.getString("MEM_HP"));
                resultMap.put("MEM_ADD1", rs.getString("MEM_ADD1"));

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
        <th>키</th>
        <th>밸류</th>
    </tr>
    </thead>
    <tbody>
        <c:if test="${not empty members}">
            <c:forEach items="${members}" var="mem">
                <tr>
                    <td>${mem.key}</td>
                    <td>${mem.value}</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty members}"/>
    </tbody>
</table>
</body>
</html>