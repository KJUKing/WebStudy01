<%@ page import="java.net.URLEncoder" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>HttpServletResponse 객체의 활용 (http response packaging)</h4>
<pre>
	response : 서버가 클라이언트로 전송하는 메세지 전체를 캡슐화한 객체
	1. Response Line :
		Status Code (상태 코드) - 요청 처리의 결과를 표현할 수 있는 세자리 숫자 코드
		1) 1xx : ING...
			HTTP (CONNECT LESS, STATELESS)
			CONNECT LESS : 연결이 의도적으로 수립되지도 않고, 종료되지도 않는 구조. EX) HTTP
							실시간 양방향 통신이 불가능한 구조 --> Long Polling, Web Socket(Connect-full, 101번 상태 코드로 표현)
			CONNECT FULL : 연결(CONNECTION)을 필요에 따라 의도적으로 수립하고 종료할 수 있는 방식 EX) DB프로그래밍

		2) 2xx : OK (정상 처리)
		3) 3xx : 요청 처리는 아직 완료되지 않았고, 클라이언트의 추가 액션이 필요함을 표현. 그래서 RESPONSE BODY가 없음
			304 : NOT MODIFIED, 캐싱된 정적 자원은 최종버전이니까 캐싱 자원을 사용하라는 표현
			307/302/301 : MOVED, 최종자원의 위치가 변경되었음에 대한 표현
							"LOCATION(변경된 위치)" 응답 헤더와 함께 전송됨
		4) 4xx : Failure, 클라이언트 오류
			400 : BAD REQUEST, request가 유효성 검증을 통과하지 못한 경우 사용
					ex) 필수 파라미터 누락, 요청 데이터가 길이 허용치를 벗어난 경우
						요청에 포함된 데이터의 타입이 잘못도니 경우 등에 사용
			401/403 : 401(UNAUTHORIZED, 인증 전) 403(FORBIDDEN 권한 없음)
			404 : NOT Found
			405 : METHOD-NOT-ALLOWED 클라이언트의 request method를 처리할수없음
			406 : NOT-ACCEPTABLE 응답 컨텐츠를 협상하는 조건으로 사용되는 "ACCEPT" 헤더로 결정된,
								컨텐츠의 종류를 서버가 처리 할 수 없을때 표현
			415 : UnSupported-Media-Type 클라이언트가 전송한 BODY컨텐츠를 서버에서 파싱할 수 없을 때 표현
			<a href="status400.jsp">400번대 테스트 페이지</a>
		5) 5xx : Failure, 서버측 오류, 500(Internal Server Error 서버의 정보 노출 제한)
	2. Response Header : setContentType, setContentLength, setHeader(name, value)
		1) Content-Type, Content-Length(공통 헤더) : body의 content에 대한 메타 데이터
			Content-Type(body content 종류)
			Content-Length(body content 길이)

		2) Content-Disposition(공통 헤더)
			request header : method="post", content-type="multipart/form-data"
							body의 부분집합(part) 하나에 대한 메타데이터로 사용됨.
							ex)
							문자 기반 파트 Content-Disposition : form-data; name="파트명"
							파일 기반 파트 Content-Disposition : form-data; name="파트명"; filename="파일명"
			response header ==>
				Content-Disposition
				- inline(default) : 브라우저의 창 내부에서 웹 페이지의 형태로 컨텐츠 소비.
				- attachment : 다운로드 받고 저장하라 저장명은 filename 지시자로 결정
						filename 내에 특수문자나 공백이 포함된다면, url encoding 방식이나 replace구조가 필요함
			<%
				String filename = "더미 1.html";
				filename = URLEncoder.encode(filename, "UTF-8").replace("+" , " ");
				response.setHeader("Content-Disposition", "attachment; filename=\""+filename+"\"");
			%>
		3) Refresh(response전용 헤더)
		4) Cache-Control(response)
		5) Location(response)
	3. Responsw Body(Message Body, Content Body)
		servlet : response.getWriter(), response.getOutputStream()
		jsp : 표현식 , out 객체

</pre>
<form method="post" enctype="multipart/form-data">
	<input type="text" name="param1"/>
	<input type="file" name="uploadFile"/>
	<button type="submit">전송</button>
</form>
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