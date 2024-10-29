<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%--${list}--%>

<table>
    <thead>
    <tr>
        <th>이름</th>
        <th>성별</th>
        <th>나이</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${not empty list}">
        <c:forEach items="${list}" var="person">
            <tr>
                <td>
                    -->
                    <c:url value="/props/personDetail.do" var="detailUrl">
                        <c:param name="who" value="${person.id}"/>
                    </c:url>

                    <a href="${detailUrl }">${person.name}</a>
                </td>
                <td>${person.gender}</td>
                <td>${person.age}</td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${empty list}">
        <tr>
            <td colspan="3">등록된 person이 없음</td>
        </tr>
    </c:if>

    </tbody>
</table>

</body>
</html>
