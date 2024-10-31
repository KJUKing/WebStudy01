<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<h1>PART2에서 만든 컨텐츠</h1>
<h4 class="text-primary"><%=request.getParameter("param")%>--(D, part2)</h4>
<h4 >${model},${sessionScope['new-menu']}--(D, part2)</h4>
<ul>
    <%
        String menuName = (String) session.getAttribute("new-menu");
        Map<String, String> paramMap = (Map<String, String>) request.getAttribute("model");
        if (paramMap != null) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                boolean isNew = entry.getKey().equals(menuName);
    %>
    <li <%=isNew ? "id='new-menu'" : ""%>><%= entry.getKey() %> : <%= entry.getValue() %></li>
    <%
            }
        }
        //새로운 메뉴에만 new -menu를 붙히는 방법을모르겠구나
    %>
</ul>
<form method="post">
    <input type="text" name="key" />
    <input type="text" name="value" />
    <button type="submit" class="btn btn-primary">전송</button>
</form>