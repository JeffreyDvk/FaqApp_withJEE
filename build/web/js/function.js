let telements = document.getElementsByClassName("topic");
let qelements = document.getElementsByClassName("question");
let toggleButton = document.getElementById("toggle-button");
let menu = document.getElementById("menu-bar");


let tfunc = function () {
    let attr1 = "t" + this.getAttribute("id");
    let attr2 = attr1 + "-a";
    let qelements = document.getElementsByClassName(attr1);
    let arr = document.getElementById(attr1 + "-a");
    if ( arr.style.transform === "rotateX(0deg)") {
        arr.style.transform = "rotateX(180deg)";
    }else{
        arr.style.transform = "rotateX(0deg)";
    }
    Array.from(qelements).forEach(function (element) {
        if (element.style.display === "block") {
            element.style.display = "none";
        } else {
            element.style.display = "block";
        }
    });
};
Array.from(telements).forEach(function (element) {
    element.addEventListener('click', tfunc);
});


toggleButton.addEventListener("click", () => {
    if (menu.style.marginLeft === "0px") {
        menu.style.marginLeft = "-200px";
    }else {
        menu.style.marginLeft = "0px";
    }
});
window.addEventListener('resize',()=>{
    if(window.innerWidth >= 860){
        menu.style.marginLeft = "0px";
    }
});


