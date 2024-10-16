<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<h4> 자원의 분류</h4>
<h4><%= application.getContextPath() %></h4>
<h4>contextParam1 : <%= application.getInitParameter("contextParam1")%></h4>

<pre>
    1. 자원의 생성 시점과 서비스 대상에 따른 분류
        1) 정적 자원 : 서버가 구동되기 전에 이미 생성되어 있는 파일, 파일 자체가
                        서비스 대상이 되는 컨텐츠
            ex) *.html, *.js, *.css, *.pdf
        2) 동적 자원 : 클라이언트의 요청에 의해 특정 어플리케이션이 실행되는 시점에
                        실행의 결과를 서비스의 대상으로.
            MIME 텍스트 : 실행결과로 동적 생성되는 컨텐츠의 종류를 표현할 수 있는 문자열
                            maintype/subtype;charset=UTF-8
            ex) /now1.do 요청에 의해 ServletTimeServlet이 실행되고
                그결과로 컨텐츠가 생성됨(text/html)
                /now2.do 요청에 의해 ServerTimeForJsonServlet이 실행되고
                그 결과로 컨텐츠가 생성됨(application/json)

    2. 자원의 위치와 접근 방법에 따른 분류
		1) File System resource : 파일 시스템상의 절대 경로(물리 경로)를통해 자원을 식별
			ex) D:\00.medias\movies\file_example_MP4_1280-10MG.mp4

		2) Classpath resource : classpath이후의 전체경로를 의미하는 qualified name(논리 경로) 의 형태로 자원을 식별
			ex) /WebStudy01/src/main/resources/kr/or/ddit/images/cute1.PNG
				/kr/or/ddit/image.cute1
                kr.or.ddit.servlet01.HelloServlet

		3) Web resource :네트워크상에서 url(논리경로)을 통해 식별할 수 있는 자원
            ex) http://localhost/Webstudy01/resources/images/cat1.jpg
		
</pre>
</body>
</html>