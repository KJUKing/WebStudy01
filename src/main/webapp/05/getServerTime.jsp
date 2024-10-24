<%@ page import="java.time.LocalDateTime" %><%@ page import="java.time.ZonedDateTime"%><%@ page import="java.util.Optional"%><%@ page import="java.time.ZoneId"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    ZoneId zoneId = Optional.ofNullable(request.getParameter("zone"))
                .map(zp -> ZoneId.of(zp))
                .orElse(ZoneId.systemDefault());
%>
<%=ZonedDateTime.now(zoneId)%>
