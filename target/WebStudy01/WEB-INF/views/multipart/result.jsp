<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
    <meta charset="UTF-8"/>
</head>
<body data-context-path="<%=request.getContextPath()%>">
<h4>저장된 파일 : <%=request.getAttribute("saveFilePath")%></h4>
<h4>저장된 파일 : <%=request.getAttribute("fileName")%></h4>

<select name="saveFile" onchange="this.form.requestSubmit()">
    <option value="">파일리스트</option>

</select>

<script>
    const fnInit = async () => {
        // <body data-context-path="<request.getContextPath()%>"> 이게원래 context-path
        // - 하이픈이 붙혀져있는게 나중에 dataset에붙히면 카멜케이스로 바뀐다!!
        const contextPath = document.body.dataset.contextPath
        const select = document.querySelector('[name="saveFile"]');
        let resp = await fetch(`\${contextPath}/multipart/fileList`, {
            headers : {
                "accept" : "application/json"
            }
        })
        if(resp.ok){
            let list = await resp.json();
            select.innerHTML += list.map(name => `<option>\${name}</option>`).join("\n");
        }
    }
    document.addEventListener("DOMContentLoaded", fnInit);
</script>
</body>
</html>
