<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>loginForm.jsp</title>
</head>
<body>
<h4>Http 인증 시스템</h4>
<form action="j_security_check" method="post">
    <pre>
        <input type="text" name="j_username" placeholder="username">
        <input type="password" name="j_password" placeholder="password">
        <button type="submit">로그인</button>
    </pre>
</form>
</body>
</html>
