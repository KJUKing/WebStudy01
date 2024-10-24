// 1. 서버 시계
// 2. 시간 출력 엘리먼트
// 3. 서버의 갱신되는 시간 자원을 반복적으로 출력하기 위한 작업
// 4. 시계 멈춤 기능
// 5. 재가동 기능

//
// 1. 돔컨텐트 로디드 완료
// 2. 캘린더데이터 갖고오는 비동기요청
// 3. 월 로케일 타임존중에 타임존만 갖고온다
// 4. 그 리스트중에 체인지이벤트로

const makeOptions = (parent, assosiativeArray) => {
    for (let pn in assosiativeArray) {
        let pv = assosiativeArray[pn];
        parent.insertAdjacentHTML("beforeend", `<option value='${pn}'>${pv}</option>`)
    }
}

document.addEventListener("DOMContentLoaded", () => {

    let nodeList = document.querySelectorAll(".has-watch");
    const stopBtn = document.getElementById("stop-btn");
    const resumBtn = document.getElementById("resume-btn");
    const zone = document.getElementById("zone");


    //1. ClientWatch 배열을 생성
    // let watchs = [];


    //3. 스탑버튼 눌렀을때 배열에 저장된 인스턴스 대상으로 stopWatch호추
    //4.
    stopBtn.addEventListener("click", (e) => {
        // for (let w of watchs) {
        //     w.stopWatch();
        // }
        nodeList.forEach(area => area._watch.stopWatch())
        stopBtn.classList.toggle("invisible")
        resumBtn.classList.toggle("invisible")

    })

    //4. RESUME버튼 클릭시 모든 시계는 재가동
    resumBtn.addEventListener("click", (e) => {

        // watchs.forEach((w) => {
        //     w.startWatch();
        // })
        nodeList.forEach(area=>area._watch.startWatch())
        resumBtn.classList.toggle("invisible")
        stopBtn.classList.toggle("invisible")

    })

    fetch("../calendar/ui-data", {
        headers: {
            accept: "application/json"
        }
    }).then(resp => resp.json())
        .then(({zones}) => {
            makeOptions(zone, zones);
        })
        .catch(console.error);

    zone.addEventListener("change", async ({target}) => {
        e.preventDefault();

        // 이렇게 해도됨 let zoneValue = zone.value; // 선택한 나라의 옵션
        let zondId = target.value;

        document.querySelectorAll("[data-wl-url]").forEach(area => {
            let url = area.dataset.wlUrl;
            console.log(url);
            area._watch.url = `${url}?{zone=${zondId}`;//이렇게해야 인스턴스가 특정이된다.
        });

    });
})