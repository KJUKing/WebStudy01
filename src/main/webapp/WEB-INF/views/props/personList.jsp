<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- 세션에서 id 값 가져오기 --%>
<c:set var="sessionId" value="${sessionScope.id}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        #new
    </style>
</head>
<body>
<%--${list}--%>
<a href="<c:url value='/props/personInsert.do'/>">신규등록</a>

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
            <tr style="background-color: <c:if test='${person.id == sessionId}'>#D3D3D3</c:if>;">
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
