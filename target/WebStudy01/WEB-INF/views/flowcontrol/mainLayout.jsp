<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href=<%=request.getContextPath()%>"/resources/bootstrap-5.3.3-dist/bootstrap-5.3.3-dist/css/bootstrap.min.css" />
<style type="text/css">
    #new-menu {
        background-color: yellow;
    }
</style>
</head>
<body>
<h4>최종 응답 페이지</h4>
<h4 class="text-primary"><%=request.getParameter("param")%>--(B)</h4>
<div class="container">
    <div id="part1" class="row bg-primary">
        <jsp:include page="/WEB-INF/views/flowcontrol/fragments/part1.jsp"/>
    </div>

    <div id="part2" class="row bg-warning">
        <jsp:include page="/WEB-INF/views/flowcontrol/fragments/part2.jsp"/>
    </div>
</div>

<script type="text/javascript" src=<%=request.getContextPath()%>"/resources/bootstrap-5.3.3-dist/bootstrap-5.3.3-dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>