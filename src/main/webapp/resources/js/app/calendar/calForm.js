const makeOptions = (parent, assosiativeArray) => {
    for (let pn in assosiativeArray) {
        let pv = assosiativeArray[pn];
        parent.insertAdjacentHTML("beforeend", `<option value='${pn}'>${pv}</option>`)

        //calForm.locale.innerHTML += `<option value='${pn}'>${pv}</option>`;
        //이것도 가능한데 성능이나 이벤트 리스너 유지가 중요한 경우 저게 더 좋다고함
    }
}
const fnInit = () => {
    let url = "../calendar/ui-data";
    //restful스러운 url는 카멜표기법 안쓴다 대신하이픈쓰고 전부 소문자
    fetch(url, {
        headers: {
            accept: "application/json"
        }
    }).then(resp => resp.json())
        .then(({months, locales, zones}) => {
            // for (let m of months) {
            //
            // }
            // for(let idx in months){
            //     months[idx]
            // }
            calForm.month.innerHTML = months.map((m, i) => `<option value="${i + 1}">${m}</option>`).join("\n");

            makeOptions(calForm.locale, locales);
            makeOptions(calForm.zone, zones);

            //현재날짜구하기
            const today = new Date();
            calForm.year.value = today.getFullYear();
            calForm.month.value = today.getMonth() + 1;
            calForm.locale.value = navigator.language;

            calForm.requestSubmit();
        })
        .catch(console.error)
        .finally(() => {
            console.log("최종 파이널리 실행.");
        })

    calForm.addEventListener("change", (e) => {
        // console.log("=============>", e.target);
        calForm.requestSubmit();

        //중요 !! 버블링이되더라도 target은 바뀌지않는다
    })
    calForm.addEventListener("submit", async (e) => {
        e.preventDefault();
        const form = e.target;
        let url = form.action;
        let method = form.method;
        let headers = {
            accept: "text/html",
        };
        let formData = new FormData(form);
        let queryString = new URLSearchParams(formData).toString();
        url = `${url}?${queryString}`;
        try {
            let resp = await fetch(url, {
                method: method,
                headers: headers
            });
            if (resp.ok) {
                let calHTML = await resp.text();
                calArea.replaceChildren("");
                calArea.insertAdjacentHTML("afterbegin", calHTML);
            } else {
                throw new Error(`처리 실패 : ${resp.status}`);
            }
        } catch (error) {
            console.error(error);
        }
    });// submit handler끝나기
    // 소비형이다

    calArea.addEventListener("click", e => {
        if (! e.target.classList.contains('link-a')) return false;
        let a = e.target;
        calForm.year.value = a.dataset.year // 1번째방법
        calForm.month.value = a.dataset['month']; // 2번째방법
        calForm.requestSubmit();
    });
}// fn INit끝나기
document.addEventListener("DOMContentLoaded", fnInit);