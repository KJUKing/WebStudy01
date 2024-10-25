<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>PART2에서 만든 컨텐츠</h1>
<h4 class="text-primary"><%=request.getParameter("param")%>--(D, part2)</h4>
<h4 ><%=request.getAttribute("model")%>--(D, part2)</h4>

<form method="post">
    <input type="text" name="key" />
    <input type="text" name="value" />
    <button type="submit" class="btn btn-primary">전송</button>
</form>