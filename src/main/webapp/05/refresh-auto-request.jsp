<%@ page import="java.time.LocalDateTime" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<%--    <meta http-equiv="refresh" content="5, url=https://www.naver.com"/>--%>
    <title>Insert title here</title>
</head>
<body>
<h4>Refreah header의 사용</h4>
<pre>
    : 주기(second)적으로 서버에 대한 요청을 전송할 때 활용됨.
        화면 전체를 refresh하기 위해 동기 요청을 전송함
        ex) refresh=10, url=외부사이트 : 일정 시간이 지연된 후, 외부사이트로 redirection 할 수 있음.

    서버 사이드 처리 : refresh헤더 사용
    클라이언트 사이드 처리 : html meta 태그, JS스케쥴링 함수

<%--    <%--%>
<%--        response.setHeader("refresh", "5,url=https://www.naver.com");--%>
<%--    %>--%>
    현재 서버의 시간 : <span id="server-area"></span>

</pre>
<span class="has-watch">

</span>
<script src="<%=request.getContextPath()%>/resource/js/app/05/watchLib.js"></script>
<script>
    document.addEventListener("DOMContentLoaded", async () => {
        setTimeout(() => {
            // location.reload();
            location.href = "https://www.naver.com";
        }, 50000);
        const serverArea = document.getElementById("server-area");

        let resp = await fetch("getServerTime.jsp");
        let timeText = await resp.text();
        serverArea.innerHTML = timeText;
    })



</script>
</body>
</html>