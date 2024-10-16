<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
	Http(Hyper Text Transfer Protocol) : 웹상의 클라이언트와 서버가 메세지를 교환할때 사용되는 패키징 방식
	
	Http request packaging: 클라이언트가 서버에게 전송하는 우편물 ==> HttpServletRequest로 캡슐화함
	1. Request Line : URL(수신자), http method로 요청의 목적과 패키징 방식을 추가로 표현.
		url : <%=request.getRequestURL()%>
		uri : <%=request.getRequestURI()%>, 클라이언트사이드 절대 경로 형태
		servlet path : <%=request.getServletPath()%>, 서버사이드 절대 경로 형태

		URL(Uniform Resource Locator) : ex) 교보문구 4층 소설 코너의 두번째 책장의 세번째 줄의 네번째 칸의 왼쪽에서 세번째 책.
		URI(Uniform Resource Identifier) : 자원을 식별하기 위한 식별자의 총칭.
		URN(Uniform Resource Name) : ex) 소년이 온다
		URC(Uniform Resource Content) : ex) 저자 : 한강, 출판사 : 창비, 노벨문학상 작가의 작품
		URL과 URI의 차이점은
		URL은 URI의 식별하기위한 방법중 하나인데 URL은 그 자원의 위치를 특정하는 방식

		request method : <%=request.getMethod()%>

		url 형태
			protocol[scheme]://IP[Domain-host]:port/pathname
			ex) https://www.example.com:443/depth1/depth2/filename.exp?k1=v1&k2=v2
			origin(protocol+host+post)/resource path+query string
		http method
			GET (R) : 서버의 자원 조회 src, href 속성에 의해 발생하는 요청
			POST (C) : 서버의 새로운 자원 등록
			PUT / PATCH (U) : 서버의 자원을 갱신/수정
			DELETE (D) : 서버의 자원을 삭제
			OPTIONS : 서버의 상황을 확인하기 위한 preFlight 요청에 사용됨.
			HEAD : 응답데이터에 content body가 없는 응답을 수신하기 위한 요청에 사용됨.
			TRACE : 제한된 환경에서 서버를 트래킹할 목적의 요청에 사용됨

			테스트 자원의 url 설계 - RestFull URI 방식의 설계
			/person (GET) : 전체 조회
			/person/p001 (GET) : 단건(person) 조회
			/person (POST) : 새로운 person 등록
			/person/p001 (PUT): p001 하나의 정보 수정
			/person/p001 (DELETE) : p001 삭제

			<button class="test-btn" data-method="get">GET 요청</button>
			<button class="test-btn" data-method="post">POST 요청</button>
			<button class="test-btn" data-method="put">PUT 요청</button>
			<button class="test-btn" data-method="delete">DELETE 요청</button>
			<button class="test-btn" data-method="head">HEAD 요청</button>
			<button class="test-btn" data-method="options">OPTIONS 요청</button>

	2. Request Header : 클라리언트와 요청에 대한 정보를 수식할 수 있는 메타데이터(name/String value)
		1) Accept-* : 응답을 조건을 설정할 때 활용됨.
			Accept-Language : 응답 메세지를 구성할 언어(Locale)
			Accept-Encoding : 응답 메세지의 압축 형식
	 		Accept : 응답 메세지의 종류
		2) Content-* : request body가 있고, body를 통해 전송되는 메세지가 있을 때,
						해당 메세지의 종류(Content_Type)나 길이(Content_Length)를 표현할 수 있음.
		3) User-Agent : 클라이언트의 사용기기와 브라우저의 종류(렌더링 엔진등)
		<a href="<%=request.getContextPath()%>/03/requestHeader.jsp">request header</a>

	3. Request Body(message body, content body) : 클라이언트가 서버로 전송할 메세지
		1) 파라미터(문자열로 전송) 형태의 form-data : application/x-www-form-urlencoded
		2) 멀티파트 형태의 form-data : multipart/form-data
		3) json[xml] payload : application/json[xml]
		<a href=<%=request.getContextPath()%>"requestData.jsp">request data</a>
</pre>
<a href="">GET 요청</a>
<form enctype="application/x-www-form-urlencoded" method="post">
	<button type="submit">POST 전송</button>
</form>
<button type="button" id="putbtn">PUT 전송</button>
<script type="text/javascript">
	putbtn.addEventListener("click", () => {
		fetch("", {method:"put"});
	})

	document.querySelector("pre").addEventListener("click", (e) => {
		if (!e.target.classList.contains("test-btn")) return false;
		let method = e.target.dataset.method;
		let url = "<%=request.getContextPath()%>/person";
		fetch(url, {method:method})
	})
</script>
<%--<img src="<%=request.getContextPath() %>/resources/images/cat1.jpg" />--%>
</body>
</html>