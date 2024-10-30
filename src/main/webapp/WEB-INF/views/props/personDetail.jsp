<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>

    </style>
</head>
<body>
<h4>회원 상세</h4>

<c:set var="person" value="${requestScope.person}"/>

<table>
    <thead>
    <tr>
        <th>이름</th>
        <th>성별</th>
        <th>나이</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${person.name}</td>
        <td>${person.gender}</td>
        <td>${person.age}</td>
    </tr>
    </tbody>
</table>

</body>
</html>
