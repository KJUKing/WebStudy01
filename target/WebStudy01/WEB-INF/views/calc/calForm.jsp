<%@ page import="kr.or.ddit.calc.OperatorType" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form name="calForm" method="post">
    <input type="text" name="left" placeholder="좌항피연산자"  />
    <select name="operator" >
        <option value="">연산자</option>
        <%=
            Arrays.stream(OperatorType.values())
                    .map(o -> String.format("<option value='%s'>%c</option>", o.name(), o.getSign()))
                    .collect(Collectors.joining("\n"))
        %>
    </select>
    <input type="text" name="right" placeholder="우항피연산자" />
    <button type="submit">=</button>
</form>
<div id="result-area"></div>
<script>
    const calForm = document.calForm;
    const resultArea = document.getElementById("result-area");
    const fnValidate = (form) => {
        let inValid = false;
        form.querySelectorAll("[name]").forEach(ipt => {
            // if(!ipt.value) valid = false;
            inValid = !ipt.value;
        });
        return !inValid;
    }

    calForm.addEventListener("submit", async (e) => {
        e.preventDefault();
        let valid = fnValidate(e.target);
        if (!valid) {
            alert("유효성 검증 통과 못함");
            return false;
        }
        let resp = await fetch("", {
            method: "post",
            headers:{
                accept: "application/json"
            }, body: new FormData(calForm)
        });
        let calVO = await resp.json();
        resultArea.innerHTML = calVO.expression;
    });

</script>
</body>
</html>


