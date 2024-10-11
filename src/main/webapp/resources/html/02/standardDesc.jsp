<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
<pre>
why : 서블릿 스펙의 단점을 보완하고, Model2 아키텍처를 적용할때
    view레이어를 구성할 수 있는 템플릿 엔진이 활용됨
    서비스 컨텐츠가 text기반의 마임을 갖는 경우, 템플릿 엔진들(jsp, thymeleaf, velocity, freemarker)이 활용됨
    템플릿 엔진이란 ? 템플릿과 데이터의 결합자
JSP(Java Server Page) ? 자바기반의 서블릿 스펙에서 파생된 서버사이드 템플릿 엔진
    3가지 언어로 축약한다면?

HOW : 정적인 템플릿 요소를 제외한 동적 요소를 작성하는 방법
    1. 정적 요소(front-end) : 일반 텍스트, HTML, CSS, JavaScript
    2. 동적 요소(back-end :
        1) scriptlet : _jspService 메소드의 지역코드로 전홚되는 자바 코드
                        해당 메소드에 지역 변수 형태로 선언된 기본 객체 사용 가능
                        <% //자바코드 지역코드
                            String data = "나도 데이터";
//                            return;
                        %>
        2) expression : out.write(출력값)으로 전환됨
                        <%= "출력할문장"%>
        3) directive :  실제 로직에는 영향이 없는 설정값을 변경할때 활용됨(지시자) 속성으로 설정 변경
            page     (required) : 웹 페이지의 설정에 영향
            include  (Optional) : 정적 내포
            taglib   (optional) : custom tag 사용시 필요
                        <%@ page buffer="8kb" %>
        4) declaration : 클래스 차원의 전역 코드로 전환되는 자바 코드

                         <%! //전역코드
                            String data = "데이터";
//                            return; 메소드가없음
                         %>
        5) comment : <%-- 주석 --%>
            - back-end comment(***)지향 : java, jsp
            - front-end comment(XXX)지양 : html, css, js

        JSP 컨테이너의 역할 단계
        - jsp템플릿 파일을 대상으로 최초의 요청이 발생하면, 템플릿 소스를 파싱하여 java코드로 정의함(.java 서블릿작성)
        - compile (.class 바이트 코드 생성)
        - 싱글톤 인스턴스 생성
        - _jspService 메소드를 실행하여 요청과 응답을 처리함
        한글자 수정

        6) EL(expression language)
        7) Custom tag(JSTL)
</pre>
</body>
</html>