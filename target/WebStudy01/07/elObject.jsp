<%@ page import="kr.or.ddit.calc.CalculateVO" %>
<%@ page import="kr.or.ddit.calc.OperatorType" %>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>07/elObject.jsp</title>
</head>
<body>
<h4>07/elDesc.jsp</h4>
<h4> EL에서 객체 사용 방법</h4>
<pre>
    <%
        CalculateVO cal = new CalculateVO();
        cal.setLeft(2);
        cal.setRight(3);
        cal.setOperator(OperatorType.PLUS);

        CalculateVO cal2 = new CalculateVO();
        cal2.setLeft(2);
        cal2.setRight(3);
        cal2.setOperator(OperatorType.PLUS);

        request.setAttribute("calAttr", cal);
    %>

    <%= cal.getLeft() +""+ cal.getOperator().getSign() +""+ cal.getRight()%>=<%=cal.getResult()%>
    ${calAttr.left} ${calAttr.operator.sign} ${calAttr.right} = ${calAttr.result}

    <%=cal.getExpression()%>
    ${calAttr.expression}
    ${calAttr['expression']}

</pre>
<hr />
<pre>
    <%
        List<String> list = Arrays.asList("listValue1", "listValue2");
        request.setAttribute("list", list);
        String[] array = new String[]{"arrayValue1", "arrayValue2"};
        request.setAttribute("array", array);

        Set<String> set = new HashSet<>();
        request.setAttribute("set", set);
        set.add("setValue1");
        set.add("setValue2");
        set.add("setValue2");

        Map<String, Object> map = new LinkedHashMap<>();
        request.setAttribute("map", map);
        map.put("key1", "mapValue1");
        map.put("key2", "mapValue2");
        map.put("key-3", "mapValue3");
        map.put("key 4", "mapValue4");
    %>
    ${list[3]}, ${array[3]}
    ${set}
    ${map.get("key2")}, ${map["key2"]}, ${map.key2}
    ${map.key-3}, ${map["key-3"]}
    \${map.key 4}, ${map["key 4"]}

</pre>
<hr/>
<pre>
    EL 기본객체(11)
    1. scope객체 : requestScope, sessionScope, applicationScope, pageScope
    2. pageContext :
        ${pageContext.request.contextPath}, ${pageContext.servletContext.contextPath}
        ${pageContext.session.id}, ${pageContext.session.creationTime}
    6. 요청 파라미터 객체 : param, paramValues
    7. 요청 헤더 객체 : header, headerValues
    8. 쿠키 객체 : cookie
    9. 컨텍스트 파라미터 객체 : initParam



</pre>
</body>
<script>

</script>
</html>
