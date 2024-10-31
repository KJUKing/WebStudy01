/**
 *
 */


document.addEventListener("DOMContentLoaded", async ()=>{
    let resp = await fetch("../calendar/ui-data");
    let {zones} = await resp.json();
    for(let key in zones){
        let value = zones[key];
        zone.insertAdjacentHTML("beforeend", `<option value='${key}'>${value}</option>`);
    }

    zone.addEventListener("change", ({target})=>{
        let zoneId = target.value;
        document.querySelectorAll("[data-wl-url]").forEach(area=>{
            let url = area.dataset.wlUrl;
            area._watch.url = `${url}?zone=${zoneId}`;
        });
    });

    let nodeList = document.querySelectorAll(".has-watch");

    const stopBtn = document.getElementById("stop-btn");
    const resumeBtn = document.getElementById("resume-btn");

////	1. ClientWatch 저장용 배열 생성
//	let watchs = [];
////	2. 배열에 인스턴스 저장
//	watchs.push(new ClientWatch(clientArea1));
//	watchs.push(new ClientWatch(clientArea2));
//	watchs.push(new ClientWatch(clientArea3));

//	3. STOP 버튼 클릭시 배열에 저장된 인스턴스들을 대상으로 stopWatch 호출
    stopBtn.addEventListener("click", (e)=>{
//		for(let w of watchs){
//			w.stopWatch();
//		}
        nodeList.forEach(area=>area._watch.stopWatch());
        stopBtn.classList.toggle("invisible");
        resumeBtn.classList.toggle("invisible");
    });
//	4. RESUME 버튼 클릭시, 모든 시계는 재가동.
    resumeBtn.addEventListener("click", ()=>{
        nodeList.forEach(area=>area._watch.startWatch());
        stopBtn.classList.toggle("invisible");
        resumeBtn.classList.toggle("invisible");
    });
});






