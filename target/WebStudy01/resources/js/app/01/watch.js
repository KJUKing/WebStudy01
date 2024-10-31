const clientarea = document.getElementById("clientarea");
const serverarea = document.querySelector("#serverarea");
const controlBtns = document.querySelectorAll(".control");

const watchJob = async () => {
    clientarea.innerHTML = new Date();
    //fetch가 못받는 상황대비해서 try catch문 한번 더감싸기
    try {
        let resp = await fetch("../../../now2.do");
        if (resp.ok) {
            let jsonObj = await resp.json();  // 서버에서 받은 데이터를 파싱
            serverarea.innerHTML = jsonObj.now;  // 서버의 현재 시간을 표시(innerHTML)
        } else {
            let errMsg = await resp.text();
            serverarea.innerHTML = errMsg;
            controlBtns.forEach(b => {
                if (b.dataset['role' ==="stop"])
                    b.click
            })//연산배열구조, 연관배열구조
        }
    } catch (error) {
        serverarea.innerHTML = error;
    }
}

let intervalId = null;

const watchStart = (btn) => {
    if (!intervalId) {  // intervalId가 null일 때만 실행
        intervalId = setInterval(watchJob, 1000);  // 1초마다 watchJob 실행
    }
}

const watchStop = (btn) => {
    if (intervalId) {
        clearInterval(intervalId);  // interval을 멈추고
        intervalId = null;  // intervalId 초기화
    }
}

controlBtns.forEach((btn, index) => {
    btn.addEventListener("click", ({ target }) => {
        let role = target.dataset.role;
        controlBtns.forEach(b => b.classList.toggle("d-none"));  // 버튼 토글
        if (role === "start") {
            watchStart(target);
        } else if (role === "stop") {
            watchStop(target);
        }
    });
});
