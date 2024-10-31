<%@ page import="java.util.Arrays" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>07/jstlDesc.jsp</title>
</head>
<body>
<h4>Jsp Standard Tag Library</h4>
<h4> EL에서 객체 사용 방법</h4>
<pre>
    : 커스텀 태그들 중 많이 활용되는 것들을 모아놓은 라이브러리
    &lt;prefix:tag-name attibute-name="attrbute-value"&gt;

    1. jar파일을 빌드패스에 추가.
    2. 태그 라이브러리 로딩필요,
        : taglib 지시자 사용(uri="태그 종류 결정", prefix="커스텀 태그 접두어")
    3. core(c) 태그 사용
        1) 데이터 핸들링. : set, remove
            <c:set var="attr" value="attr_value" scope="page"/>
            ${attr}, ${pageScope.attr}
            <c:set var="attrClone" value="${attr}"/>
            ${attrClone}
            <c:remove var="attrClone" scope="page"/>
            --> ${attrClone}
        2) 조건문
            - 단일 조건문 : if
        <c:if test="${3 lt 5}">
            3은 5보다 적습니다.
        </c:if>
        <c:if test="${3 ge 5}">
            3은 5보다 크거나 같다(거짓말)
        </c:if>
        <c:set var="dummy" value="   "/>
        <c:if test="${empty dummy}">
            dummy 속성은 없다
        </c:if>
        <c:if test="${not empty dummy}">
            dummy 속성은 있다(${dummy})
        </c:if>

            - 다중 조건문 : choose when otherwise
        <c:choose>
            <c:when test="${empty dummy}">
                dummy 없다
            </c:when>
            <c:otherwise>
                dummy 있다(${dummy})
            </c:otherwise>
        </c:choose>
        <a href="?sample1=value1&sample2=342">현재 페이지를 대상으로 쿼리 스트링 전달</a>
        <%=request.getParameter("sample1")%>, ${param.sample1}

        3) 반복문 forEach, forTokens
            forEach
                for(선언절; 조건절; 증감절)
        <c:forEach var="i" begin="1" step="2" end="10">
            <c:if test="${i %2 eq 1}">
                ${i}번째 반복
            </c:if>
        </c:forEach>
                for(선언절 : 집합 객체)
        <c:set var="targetList" value='<%=Arrays.asList("value1", "value2")%>'></c:set>
        <c:forEach items="${targetList}" var="element" varStatus="lts">
            ${lts.first ? "시작" : ""}

            ${element}. index : ${lts.index}, ${lts.count}

            ${lts.last ? "종료" : ""}
        </c:forEach>
        forTokens
            <c:forTokens items="민재가 방에 들어간다" delims=" " var="token">
                ${token}
            </c:forTokens>

            <c:forTokens items="100,200,300" delims="," var="number">
                ${number * 10}
            </c:forTokens>




</pre>
<div>
    1. sample2 파라미터가 있는지 확인
     : 없는 경우 "파라미터 없음" 출력
    2. 짝/홀 여부 확인하고,
        "342는 짝수임" 메세지 출력

    <c:set var="sample2" value="${param.sample2}"/>
    <c:if test="${not empty sample2}">
        파라미터 있다
        <c:if test="${sample2 % 2 eq 0}">
            ${sample2}는 짝수임
        </c:if>
        <c:if test="${not (sample2 % 2 eq 0)}">
            ${sample2}는 짝수아님
        </c:if>
    </c:if>
    <c:if test="${empty sample2}">
        파라미터 없다
    </c:if>

</div>
</body>
<script>

</script>
</html>