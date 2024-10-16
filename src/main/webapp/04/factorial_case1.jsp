<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

<%!

    // call by value
    // call by reference
    long reFactorial(int num, StringBuffer expr) {
        if (num <= 0) {
            throw new IllegalArgumentException("num must be greater than 0");
        }
        if (num ==1) {
            expr.append(num);
            return 1;
        } else {
            expr.append(num + " * ");
            return num * reFactorial(num - 1, expr);
        }
    }

%>
<%
int sum = 1;

for (int i = 10; i >= 1; i--) {
	sum += sum * i;
    }

    int num1 = 10;
    StringBuffer expr = new StringBuffer();
%>


10! =<%=sum %>

10! =<%=reFactorial(num1, expr)%>
<br/>
<br/>
    <%=expr %>
</head>
<body>
</body>
</html>