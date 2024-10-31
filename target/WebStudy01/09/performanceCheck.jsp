<%@ page import="kr.or.ddit.db.ConnectionFactory" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="kr.or.ddit.db.ConnectionFactoryCP" %><%--
  Created by IntelliJ IDEA.
  User: PC-14
  Date: 2024-10-31
  Time: 오후 1:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>09/performanceCheck.jsp</title>
</head>
<body>
<h4> 어플리케이션의 성능 체크</h4>
<pre>
    String(상수) vs StringBuffer(객체)

    전체 소요 시간 : processing time + Latency time
    <%
        //        case 1: Member 테이블에서 a001 사용자의 이름과 휴대폰 번호 조회 (1) : 192ms, 9ms ==> 187ms, 0ms
//        case 2: Member 테이블에서 a001 사용자의 이름과 휴대폰 번호 조회 (100): 881ms, 590ms
//        case 3: Member 테이블에서 a001 사용자의 이름과 휴대폰 번호 조회 (1), 출력(100) 174ms, 23ms
        long start = System.currentTimeMillis();

        for (int i = 1; i <= 100; i++) {
            try (
                    Connection conn = ConnectionFactoryCP.getConnection();
                    Statement stmt = conn.createStatement();
            ) {

                String sql = "SELECT MEM_NAME, MEM_HP FROM MEMBER WHERE MEM_ID = 'a001'";
                ResultSet rs = stmt.executeQuery(sql);
                Map<String, String> result = new HashMap<String, String>();
                if (rs.next()) {
                    result.put("memName", rs.getString(1));
                    result.put("memHp", rs.getString(2));
                }
                out.println(result);

            }
        }
        long end = System.currentTimeMillis();

    %>
    소요시간 : <%=end - start%> ms

</pre>
</body>
</html>
