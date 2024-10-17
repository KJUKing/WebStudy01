<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
</head>
<body>
<form id="facForm" method="post">
    <input type="number" name="operand" required onchange="this.form.requestSubmit();"/>
</form>
form태그는 submit 이벤트에서 동기 요청을 발생시킨다
<%--공유자원을 쓰려면 rock이라는게필요함--%>

<div id="resultArea">

</div>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/app/factorial/case5Form.js">
	
</script>
</body>
</html>


