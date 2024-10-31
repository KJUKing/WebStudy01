/**
 * 
 */

 facForm.addEventListener("submit", (e) => {
     e.preventDefault(); // submit이벤트의 동기 요청을 중단한다.
     // request Line : url, method
     const form = e.target;
     let url = form.action;
     let options = {};
     let method = form.method;
     options.method = method;
     // request header : content-type, accept
     let headers = {
         accept : "application/json"
     }
     options.headers = headers;
     //k=v&k=v&k=v
     let formData = new FormData(form);
     let queryString = new URLSearchParams(formData).toString();
     if (method == "get") {
        // request line 에 query String 추가 k=v&k=v&k=v
         url = `${url}?${queryString}`;
     } else {
         headers["content-type"] = form.enctype;
         // -을 쓸수가없어서 . dot notation을 쓸수가없다 그래서
         // 연관 배열구조를 사용해야한다 그게 [""]이구조이다.
         // request body : (only post) << 근데 이것들은 form이 이미 다갖고있다.
         //k=v&k=v&k=v
         options.body = queryString;
     }

     fetch(url, options) //resolve와 reject 함수로 이후 이벤트에대한 처리 함수를 미리 예약한다.
         .then(resp => {
             if (resp.ok) {
                 return resp.json();
             } else {
                 throw new Error(`응답 실패 , ${resp.status}`);
             }
         }).then(({operand, expression, result})=>resultArea.innerHTML = ` ${operand} ! = ${expression} = ${result} `)
         .catch(console.log);

 })