/**
 * 
 */

 facForm.addEventListener("submit", (e) => {
     e.preventDefault(); // submit이벤트의 동기 요청을 중단한다.
     // request Line : url, method
     const form = e.target;
     let url = form.action;
     let options = {};
     let method = "post";
     options.method = method;
     // request header : content-type, accept
     let headers = {
         accept : "application/json",
         "content-type" : "application/json"
     }
     options.headers = headers;
     // request body : {"operand" : 5}
     let formData = new FormData(form)
     let nativeTarget = {
         operand: parseInt(formData.get("operand"))
     }

     options.body = JSON.stringify(nativeTarget);

     fetch(url, options) //resolve와 reject 함수로 이후 이벤트에대한 처리 함수를 미리 예약한다.
         .then(resp => {
             if (resp.ok) {
                 return resp.json();
             } else {
                 throw new Error(`응답 실패 , ${resp.status}`);
             }
         })
         .then(({operand, expression, result}) => resultArea.innerHTML = `${operand} ! =${expression} = ${result}`)
         .catch(console.log);

 })