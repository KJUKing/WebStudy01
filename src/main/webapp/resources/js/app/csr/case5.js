/**
 *
 *
 */

document.addEventListener("DOMContentLoaded", () => {
    const selectMovie = document.querySelector("[name='video']");
    const selectImage = document.querySelector("[name='image']");
    const forms = document.querySelectorAll("form");

    fetch("../../../movie/csr/fileList")
        .then(resp => resp.json())
        .then(array => selectMovie.insertAdjacentHTML( "beforeend",
            array.map(name => `<option>${name}</option>`)
                .join("\n")))
        .catch(console.log);

    fetch("../../../image/csr/fileList")
        .then(resp => resp.json())
        .then(array => selectImage.innerHTML += array.map(name => `<option>${name}</option>`).join("\n"))
        .catch(console.log);

    let renderer= {
        image:(src, parent) => {
            parent.innerHTML = `<img src ="${src}"/>`
        },
        movie:(src, parent) => {
            parent.innerHTML = `<video src= "${src}" autoplay controls/>`
        }
    }

    forms.forEach(f => {
        f.addEventListener("submit", e => {
            e.preventDefault();
            let action = e.target.action; // e.target은 f와같음
            let formData = new FormData(f);
            let queryString = new URLSearchParams(formData);
            let src = `${action}?${queryString}`;
            console.log(`${action}?${queryString}`);
            // http://localhost/WebStudy01/movie/streaming.hw?video=sample-mp4-file.mp4
            // http://localhost/WebStudy01/image/streaming.hw?image=cute3.JPG
            let role = f.dataset['role'];
            let targetArea = f.dataset['targetArea'];
            let parent = document.querySelector(targetArea);
            renderer[role](src, parent);
        })
    });
})