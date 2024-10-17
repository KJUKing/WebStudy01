<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/jsonAndRest.jsp</title>

</head>
<body>
<h4>JSON (JavaScript Object Notation)</h4>
<pre>
    : 경량의 데이터 교환 방식(메세지 교환을 위해 표현 할 수 있는 방식)

    : 데이터 표현 방식이 서로 다른 이기종 시스템간의 메세지를 교환하기 위해 필요한 공통 메세지 표현 방식
            => XML, JSON

    JSON syntax
    1. value : null, true/false, number, string(""), object, array
    2. object : { "propname" : value, ... }
    3. array : [value, ... ]

    REST 구조에 활용됨.
        : 네트워크에서 식별가능한 자원을 메세지 형태로 교환하는 구조에 대한 포괄적인 표현.
    REST 구성요소
    1.URI (자원의 식별성)
    2.Method (자원에 대한 행위)
    3.Content-Type (자원의 종류를 표현하는 메타데이터)
    4.JSON, XML Data(표현 가능한 자원의 상태)

    RESTFul(Rest 스럽다) : Rest구조의 일반적 특성에 부합하는 시스템에 대한 수식어
    1. C/S시스템
        Front-end(client) : javascript (JSON)
            JSON
        Back-end(server) : java (Gson, javaScript)

        native -> json(xml) : marshalling -> 0101001(stream) : serialization
        0101010(stream) -> json(xml) : deSerialization -> native : unMarshalling
    2. 자원에 대한 유일성이 부여된 식별 체계 (Uniform interface)
    3. Stateless : 헤더와 토큰 기반의 인증시스템
    4. Cachable : 필요한 경우 캐싱 구조를 활용할 수 있음.
    5. Layered system

    RestFul URI(URL)
        ex) /members/new POST
            /member/memberInsert.do
            /members GET
            /member/memberList.do
            /members/a001 GET
            /member/memberView.do?memId=a001
            /members/a001/profile
            Content-Type:image/jpeg
            /member/a001/profile.jpg

</pre>

<script type="text/javascript">

    let nativeTarget = {
        prop1: "value1",
        prop2: 23,
        prop3: false,
        prop4: {
            innerProp1: "innerValue"
        },
        prop5: [1, 2, 3]
    };
    console.log("원본 객체 :\n", nativeTarget);

    let json = JSON.stringify(nativeTarget);
    console.log("마샬링된 JSON :\n", json);

    let unMarshallingObj = JSON.parse(json);
    console.log("언마샬링된 객체 :\n", unMarshallingObj);

</script>
</body>
</html>