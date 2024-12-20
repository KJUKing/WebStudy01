<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/multipartDesc.jsp</title>

</head>
<body>
<h4> 멀티파트 컨텐츠</h4>
<pre>
    MIME : multipart/* - 한번의 전송으로 여러 종류의 컨텐츠를 전송해야하는 경우,
                          body 영역을 여러개의 부분집합으로 분리하여 전송하는 구조
                        ex) multipart/form-data(폼데이터의 네임태그의 갯수에따라 파트가 나뉜다)
                            multipart/mixed()

        1. Front-End
            1) 동기 요청
                - form ui 구성(method="post" enctype="multipart/form-data")
                    : form 내부의 name속성(part name)을 가진 입력 태그로 body part가 형성됨.
            2) 비동기 요청
                - fetch 함수 사용
                    (method="post")
                    (body=new FormData(form))
                    (content-type 헤더를 생략하면, boundary 속성을 가진 multipart/form-data헤더가 자동으로 설정됨.)

        ====== 전송되는 multipart는 각 파트가 독립적인 헤더(Content-Disposition)를 가지고, name 속성에 의해 식별성이 부여된다. ======

        2. Back-End
            1) post 요청 핸들링
            2) Part API 사용.
            3) multipart-config 설정
            4) 문자기반의 파트 : parameter로 핸들링.
               파일기반의 파트 : 업로드받고,  영구 저장소에 저장하기 위한 처리

</pre>
<form action="<%=request.getContextPath()%>/multipart/sendFileAndText" method="post" enctype="multipart/form-data" id="file-form">
<%--    x-www-form-urlencoded이란 인코딩된 문자열이라는뜻임--%>
<%--    multipart 는 이제부터 바디의 파트를 나누겠다고선언한거임--%>
    <input type="text" name="param1" />

    <select name="param2">
        <option label="TEXT1" value="VALUE1"></option>
        <option label="TEXT2" value="VALUE2"></option>
    </select>

    <input type="number" name="param3" />
<%--    여기까지는 전부 전송데이터가 문자열기반이다--%>
    <input type="file" name="uploadFile" />
    <button type="submit">전송</button>
</form>




<%--파일리스트--%>
<div id="result-area"></div>

<%--삭제버튼--%>
<button id="delete-button">삭제</button>
<script>
    document.addEventListener("DOMContentLoaded", async () => {
        const fileForm = document.getElementById("file-form");
        const resultArea = document.getElementById("result-area");
        const deleteButton = document.getElementById("delete-button");

        // 체크박스 추가 및 파일 목록 렌더링 함수
        const fnListup = list => {
            let html = list.map(n =>
            `<p><input type="checkbox" name="files" value="\${n}"><a href="<%=request.getContextPath()%>/multipart/download.do?filename=\${encodeURIComponent(n)}">\${n}</a></p>`).join("\n");
            resultArea.innerHTML = html;
        };


        let resp = await fetch("<%=request.getContextPath()%>/multipart/fileList");
        let list = await resp.json();
        fnListup(list);

        fileForm.addEventListener("submit", (e) => {
            e.preventDefault();
            const form = e.target;
            let url = form.action;
            let method = "post";
            let headers = {
                "accept" : "application/json"
            }
            let body = new FormData(form);
            fetch(url, {
                method: method,
                headers: headers,
                body: body
            })
                .then(resp => resp.json())
                .then(fnListup)
                .catch(console.error)
                .finally(() => fileForm.reset());
        })


        deleteButton.addEventListener("click", () => {
            const checkboxes = document.querySelectorAll('input[name="files"]:checked');
            const filenames = Array.from(checkboxes)
                                    .map(cb => cb.value);

            if (filenames.length == 0) {
                alert("삭제할 파일을 선택하세요.");
                return;
            }

            let url = "<%=request.getContextPath()%>/multipart/delete.do";
            let method = "post";
            let headers = {
                "Content-Type": "application/json",
                "accept": "application/json"
            };
            let body = JSON.stringify({filenames});
            fetch(url, {
                method: method,
                headers: headers,
                body: body
            })
                .then(resp => resp.json())
                .then(response => {
                    if (response.status === "success") {
                        // 삭제 성공 후 파일 목록 재요청
                        return fetch("<%=request.getContextPath()%>/multipart/fileList")
                            .then(resp => resp.json())
                            .then(fnListup);
                    } else {
                        throw new Error("삭제 실패");
                    }
                })
                .catch(console.error);
        });
    })
</script>
</body>
</html>