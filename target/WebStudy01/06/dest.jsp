<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


최종 도착지(B) - 화면 일부를 구성할 때(페이지의 모듈)

<pre>
<%
    out.println(request.getAttribute("reqeustAttr"));
   out.println( session.getAttribute("sessionAttr"));
    out.println( application.getAttribute("applicationAttr"));
%>

</pre>
