<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Optional" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script>
        function myFunction() {
            const input = document.getElementsByName("operand");
            input.addEventListener("change", function (e) {

            });
        }

    </script>
</head>
<body>

<%!
    long factorial(int num, StringBuffer expr) {
        if (num <= 0) {
            throw new IllegalArgumentException("num must be greater than 0");
        }
        if (num == 1) {
            expr.append(num);
            return 1;
        } else {
            expr.append(num + " * ");
            return num * factorial(num - 1, expr);
        }
    }
%>
<%

    String opParam = request.getParameter("operand");
    if(opParam != null && !opParam.trim().isEmpty()){
    StringBuffer expr = new StringBuffer();

    Integer num2 = Optional.of(opParam)
            .filter(op -> op.matches("\\d+"))
            .map(Integer::parseInt)
            .orElseThrow(() -> new IllegalArgumentException("필수 파라미터 누락 혹은 잘못된 데이터 입력"));
%>
<%=num2%>! =<%=factorial(num2, expr)%>
<%=expr %>
<%
    }
%>

<br/>
<br/>

<form method="post" enctype="application/x-www-form-urlencoded">
    <input type="number" name="operand" value="<%=opParam%>" required onchange="this.form.requestSubmit();"/>
</form>
</body>
</html>