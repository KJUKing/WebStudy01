/**
 *
 *
 */

document.addEventListener("DOMContentLoaded", () => {
    const select = document.querySelector("[name='video']");
    fetch("../../../movie/csr/fileList")
        .then(resp => resp.json())
        .then(array => select.innerHTML = array.map(name => `<option>${name}</option>`).join("\n"))
        .catch(console.log);
})