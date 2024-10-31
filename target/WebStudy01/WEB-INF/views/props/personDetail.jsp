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

<form action="<%=request.getContextPath()%>/props/personUpdate.do" method="post" enctype="application/x-www-form-urlencoded">
<table>
    <thead>
    <tr>
        <th>아이디</th>
        <th>이름</th>
        <th>성별</th>
        <th>나이</th>
        <th>주소</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><input type="text" name="id" value="${person.id}" readonly/></td>
        <td><input type="text" name="name" value="${person.name}"/></td>
        <td><input type="text" name="gender" value="${person.gender}"/></td>
        <td><input type="number" name="age" value="${person.age}"/></td>
        <td><input type="text" name="address" value="${person.address}"/></td>
    </tr>
    <tr>
        <button type="submit" colspan="5">수정하기</button>
        <button type="button" colspan="5">삭제</button>
    </tr>
    </tbody>
</table>
</form>
</body>
</html>
