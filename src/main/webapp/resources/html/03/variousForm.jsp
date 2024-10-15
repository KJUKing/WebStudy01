<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/variousForm.jsp</title>

</head>
<body>
<form action="<%=request.getContextPath()%>/request/parameters" method="get" enctype="application/x-www-form-urlencoded">
    <pre>
        <input type="number" name="param1" placeholder="숫자"/>
        <input type="date" name="param2" placeholder="날짜"/>
        <input type="datetime-local" name="param3" placeholder="날짜+시간"/>
        <input type="radio" name="param4" value="RADIO1"/>
        <input type="radio" name="param4" value="RADIO2"/>
        <input type="checkbox" name="param5">
        <input type="checkbox" name="param5">
        <input type="checkbox" name="param5">
        <input type="text" name="param6"/>

        <select id="param7" name="param7">
            <option value>선택</option>
            <option value="v1">text1</option>
            <option value="v2">text2</option>
            <option value="v3">text3</option>
        </select>
        <select name="param8" multiple>
            <option value="v1" label="text1"/>
            <option value="v2" label="text2"/>
            <option value="v3" label="text3"/>
        </select>
                <button type="submit">전송</button>
    </pre>
</form>
</body>
</html>