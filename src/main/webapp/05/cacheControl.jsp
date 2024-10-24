<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>05/cacheControl.jsp</title>
</head>
<body>
<h4>캐시 제어</h4>
<pre>
	: 네트워크상에서 발생하는 지연시간으로 인한 부하를 줄이기 위해 최소한의 정책으로 저장하는 데이터.
	캐시 데이터 제어 헤더
	1. Cache-Control(Http 1.1) : 캐싱 데이터 저장 여부
	2. Pragma(Http 1.0) : 캐싱 데이터 저장 여부

		no-store : 캐시 데이터를 저장하지 않을때.
		no-cache : 이전에 저장된 캐시 데이터를 바로 사용하지 않고, 확인 후 사용하도록 유도할때.
		private : 사용자의 로컬에 캐시 데이터를 저장함.
		public : 프록시 서버에 캐시 데이터를 저장함.
		max-age : 캐시 데이터 만료 시한 설정. 초단위 설정.

	3. Expires : 캐시 데이터 만료 시한 설정, 70.1.1.00:00 이후의 밀리 세컨드로 만료 시점 설정.
	<%
        response.setHeader("Cache-Control", "no-store");
        response.addHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-store");
        response.addHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
</pre>
<button id="load-btn">UI 데이터 로드</button>
<script>
    const loadBtn = document.getElementById("load-btn");
    loadBtn.addEventListener("click", async ()=>{
        let resp = await fetch("<%=request.getContextPath()%>/calendar/ui-data", {
            //cache:"default"|"no-store"|"no-cache"
        });
        let json = await resp.json();
        console.log(json);
    })
</script>
</body>
</html>









