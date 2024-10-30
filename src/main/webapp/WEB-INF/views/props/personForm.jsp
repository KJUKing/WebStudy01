<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
<style>
        form {
            display: flex;
            flex-direction: column;
            width: 300px; /* 폼의 너비 설정 */
            margin: 0 auto; /* 가운데 정렬 */
        }

        input, button {
            margin-bottom: 10px; /* 각 입력 필드와 버튼 간격 */
            padding: 8px;
            font-size: 16px;
        }

        button {
            cursor: pointer;
        }
    </style>
</head>
<body>
<h4>등록 폼</h4>
<form method="post" enctype="application/x-www-form-urlencoded">
    <input type="text" name="id" placeholder="id" required/>
    <input type="text" name="name" placeholder="이름" required/>
    <input type="radio" name="gender" value="M" checked />남
    <input type="radio" name="gender" value="F"/>여
    <input type="number" name="age" placeholder="나이" required/>
    <textarea name="addr" placeholder="주소" required></textarea>
    <button type="submit">전송</button>
</form>
</body>
</html>
