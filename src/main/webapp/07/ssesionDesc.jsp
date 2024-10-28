<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>07/implicitObject.jsp</title>
</head>
<body>
<h4>07/ssesionDesc.jsp</h4>
<h4> Http Session </h4>
<pre>
세션 생명주기
        1.생성시점(시작) : 세션 트래킹 모드로 전달되는 세션 아이디가 없는 요청이 발생했을때

            ==> 세션의 식별자로 ID를 부여받음 <%=session.getId()%>
            <%=new Date(session.getCreationTime()) %>
            ==> response에 ID가 포함되어 클라이언트 쪽으로 전송됨
            ==> 클라이언트가 ID를 자기 저장소에 저장함.
            ==> 다음번 요청 헤더ID가 포함되어 서버쪽으로 재전송됨
            session 내의 마지막 요청 시점 : <%=new Date(session.getLastAccessedTime())%>
            session tracking mode : 클라이언트와 서버가 세션 아이디를 서로 교환하는 방법
            1) COOKIE(***)
            2) URL <a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">현재 페이지로 다시 요청 전송</a>
            3) SSL(Secure Socket Layer) : C/S 사이의 메세지를 암호화하는 방식

        소멸시점(종료)
            1) timeout(<%=session.getMaxInactiveInterval()%>) 이내에 새로운 요청이 전송되지 않는 경우.
                    - cookie제거
                    - 브라우저 완전 종료
            2) 직접적인 로그아웃 <%-- <%session.invalidate();%>--%>
</pre>



</body>

</html>
