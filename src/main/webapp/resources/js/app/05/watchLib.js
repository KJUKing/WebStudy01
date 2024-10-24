
//
// 클라이언트 시계
// 1. 시계 출력 엘리먼트
// 2. 시간을 갱신할 수 있는 작업 (1초마다 갱신)
// 3. 시간을 멈출수있는 기능
// 4. 재가동 기능
class ClientWatch{
    constructor(area) {
        if (!area) {
            throw new Error("시계 출력 엘리먼트가 필요함.")
        }
        this.area = area;
        area._watch = this;
        this.startWatch();
    }
    startWatch(){
        this.intervalId = setInterval(() => {
            this.area.innerHTML = new Date();
        },1000);
    }
    stopWatch() {
        clearInterval(this.intervalId);
    }

}

class ServerWatch extends ClientWatch{

    constructor(area, url) {
        super(area);
        if (!url) {
            throw new Error("주소가 없다.");
        }
        this.url = url;
    }

    startWatch(){
        this.intervalId = setInterval( async () => {
            let resp = await fetch(this.url);
            let timeText = await resp.text();
            this.area.innerHTML = timeText;
        },1000)
    }
}

document.addEventListener("DOMContentLoaded", () => {
    let nodeList = document.querySelectorAll(".has-watch");
    nodeList.forEach(area => {
        if (area.dataset.wlUrl) {
            new ServerWatch(area, area.dataset.wlUrl);
        } else {
            new ClientWatch(area);
        }

    });
})