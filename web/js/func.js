let insc = document.getElementById("insc");
let conn = document.getElementById("conn");
let snd = document.getElementById("snd1");
let d = document.getElementById("first1");
let a = document.getElementById("first2");
let cls = document.getElementById("cls");
insc.addEventListener("click", ()=>{
    snd.style.marginLeft= "0px";
    d.style.transform = "rotateY(180deg)";
    a.style.transform = "rotateY(0deg)";
});
conn.addEventListener("click", () => {
    snd.style.marginLeft = "440px";
    d.style.transform = "rotateY(0deg)";
    a.style.transform = "rotateY(180deg)";
});
cls.addEventListener("click", () => {
    ansbox.style.marginTop = "-80%";
});