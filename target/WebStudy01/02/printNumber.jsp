<%@ page import="java.util.Optional" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%!
    //4개의 scope를 이용해 전역 멤버를 통해 데이터 공유가 불가능한 상황을 해결할 수 있음
    String pattern = "<h4>%d</h4>";
    StringBuffer h4Number =  new StringBuffer();
    private String printNumber(int i, int j) {
        for (i = 1; i <= j; i++) {
            h4Number.append(String.format(pattern, i));
        }
        return h4Number.toString();
    }
%>

<%
    Integer min = Optional.ofNullable(request.getParameter("min"))
            .filter(mp -> mp.matches("\\d+"))
            .map(Integer::parseInt)
            .orElse(1);
    Integer max = Optional.ofNullable(request.getParameter("max"))
            .filter(mp -> mp.matches("\\d+"))
            .map(Integer::parseInt)
            .orElse(10);
%>
<body>
<form method="post">
    <input type="number" name="min" placeholder="최소숫자" value="<%= min%>">
    <input type="number" name="max" placeholder="최대숫자"value="<%=max%>">
    <button type="submit">전송</button>
</form>
<%-- action, src, href : url 실행 속성이 생략된 경우, location.href값이 반영됨--%>
<%=printNumber(min, max)%>

<hr />
<%
    for (int i=1;i<=10;i++){
        h4Number.append(String.format(pattern, i));
    }
%>
<hr />
<%
    for (int i=1;i<=10;i++){
        out.print(String.format(pattern, i));
    }
    %>
<hr />
<%
		for (int i=1;i<=10;i++){
			out.print("<h4>"+i+"</h4>");
		}
	%>
</body>
</html>
