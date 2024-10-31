/**
 *
 */
document.addEventListener("DOMContentLoaded", () => {
    const zoneArea = document.getElementById("zone-area")
    fetch("../../../csr/case2")
        .then(resp => {
            if (resp.ok) {
                return resp.json();
            } else {
                throw new Error(`에러 발생 : ${resp.status}`);
            }
        }).then(({tzId}) => zoneArea.innerHTML = tzId)
        // .catch(error => console.log(error));
        .catch(console.log());
});