<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>07/implicitObject.jsp</title>
</head>
<body>
<h4>07/elDesc.jsp</h4>
<h4> EL(Expression Language) </h4>
<pre>

    : scope를 통해 공유되고 있는 attribute 데이터를 브라우저 상에 출력하기 위한 언어로만 사용됨. ->
    .jsp - \${속성이름} EL
    .js - \${aa} template
    .xml - \${aa} placeholder
    : 표현식의 대체용으로 사용됨.
    <%
        String data = "DATA";
        request.setAttribute("data", data);
        request.setAttribute("attr", "요청속성");
        session.setAttribute("attr", "세션 속성");
        application.setAttribute("attr", "어플리케이션 속성");
    %>
    표현식 : <%=data%>, EL : ${data }
    <%=request.getAttribute("attr")%> ${requestScope.attr}
    <%=session.getAttribute("attr")%> ${sessionScope.attr }
    <%=application.getAttribute("attr")%> ${applicationScope.attr }

    EL 연산자
    산술연산자 : + (산술연산자로만 사용됨.), 연산자 우선순위를 연산자로 결정됨.
        <%
            request.setAttribute("a-1", 34);
            %>

            <%= ((Integer) request.getAttribute("a-1")) +3 %>, ${requestScope['a-1'] + 3}
        ${3+4}, ${"3"+4} , ${"3"+"4"} , ${"3"+a}, ${a1+a2}

        ${4/3}
    논리연산자
        &&(and), ||(or), !(not)
        &nbsp; &lt;
        ${true and true}, ${dummy and true}
    비교연산자 : &gt; (gt), &lt; (lt), &gt;= (ge), &lt;= (le), != (ne), == (eq)
            ${3 lt 4}, ${not (3 ge 4)}

    단항연산자 : ++(+, =), --(-, =) 3버전은 지원안되고 그이후버전은 지원한다
                empty(비어있는지 확인), not empty(공백이 아님을 확인)
                <%
                    request.setAttribute("dummy1", null);
                    request.setAttribute("dummy2", "");
                    request.setAttribute("dummy3", "   ");
                    request.setAttribute("dummy4", new ArrayList());
                    request.setAttribute("dummy5", new String[]{"element1"});
                %>
                dummy1 : ${empty dummy1}
                dummy2 : ${empty dummy2}
                dummy3 : ${empty dummy3}
                dummy4 : ${empty dummy4}
                dummy5 : ${empty dummy5}
    삼항연산자 : 조건식 ? 참표현식 : 거짓표현식
        ${empty dummy6 ? "dummy6은 없음" : "dummy6은 있음"}

    </pre>
</body>
<script>
    `\${변수명}`
</script>
</html>
