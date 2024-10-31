<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body data-context-path="<%=request.getContextPath()%>">
<button id="btn404">404 에러 확인</button>
<button id="btnHead">head 요청 전송</button>
<button id="btn200_get_json">get 요청 전송(JSON)</button>
<button id="btn200_get_html">get 요청 전송(HTML)</button>
<button id="btn406">get 요청 전송(XML)-406 에러확인</button>
<button id="btn200_post_parameter">POST parameter 전송</button>
<button id="btn200_post_json">POST JSON 전송</button>
<button id="btn415">POST XML 전송, 415에러</button>
<button id="btn400">POST JSON 전송(필수데이터 누락 400)</button>
<div id="resultArea"></div>
<script>
    const contextPath = document.body.dataset.contextPath;
    const baseURL = `\${contextPath}/status/send-and-receieve`;
    btn404.onclick = async function () {
        let resp = await fetch("dummyErrorUrl");
        let message = await resp.text();
        resultArea.innerHTML = message;
    }
    btnHead.onclick = async function () {
        let resp = await fetch(baseURL, {
            method :"head"
        });
        let message = await resp.text();
        resultArea.innerHTML = message;
    };

    btn200_get_json.onclick = async function () {
        let resp = await fetch(baseURL, {
            headers: {
                accept: "application/json"
            }
        });
        let message = await resp.text();
        resultArea.innerHTML = message;
    };

    btn200_get_html.onclick = async function () {
        let resp = await fetch(baseURL);
        let message = await resp.text();
        resultArea.innerHTML = message;
    };

    btn406.onclick = async function () {
        let resp = await fetch(baseURL, {
            headers: {
                accept: "application/xml"
            }
        });
        let message = await resp.text();
        resultArea.innerHTML = message;
    };
    btn200_post_parameter.onclick = async function () {
        let formData = new FormData();
        formData.append("data3", "DATA3");
        new URLSearchParams(formData);
        let resp = await fetch(baseURL, {
            method: "post",
            body:new URLSearchParams(formData)
        });
        let message = await resp.text();
        resultArea.innerHTML = message;
    };

    btn200_post_json.onclick = async function () {
        let nativeTarget = {
            data3 : "DATA3"
        };
        let resp = await fetch(baseURL, {
            method: "post",
            headers : {
                "content-type" : "application/json"
            },
            body:JSON.stringify(nativeTarget)
        });
        let message = await resp.text();
        resultArea.innerHTML = message;
    };

    btn415.onclick = async function () {
        let nativeTarget = {
            data3 : "DATA3"
        };
        let data = JSON.stringify(nativeTarget);
        console.log(data)
        let resp = await fetch(baseURL, {
            method: "post",
            headers : {
                "content-type" : "application/xml"
            },
            body:"<root><data3>data3</data3></root>"
        });
        let message = await resp.text();
        resultArea.innerHTML = message;
    };

    btn400.onclick = async function () {
        let nativeTarget = {
            data33 : "DATA3"
        };
        let resp = await fetch(baseURL, {
            method: "post",
            headers : {
                "content-type" : "application/json"
            },
            body:JSON.stringify(nativeTarget)
        });
        let message = await resp.text();
        resultArea.innerHTML = message;
    };
</script>
</body>
</html>