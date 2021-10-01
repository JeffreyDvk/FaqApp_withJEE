let ansbox = document.getElementById("ansbox");

function closeError(){
    let error = document.getElementById("error-div");
    error.style.marginLeft = "100%";
}
function topicClick(id){
    let arrow = document.getElementById(id+"-a");
    let sons = document.getElementsByClassName(id);
    if(arrow.style.transform === "rotateX(180deg)"){
        arrow.style.transform = "rotateX(0deg)";
    }else{
        arrow.style.transform = "rotateX(180deg)";
    }
    Array.from(sons).forEach(function (element) {
        if (element.style.display === "block") {
            element.style.display = "none";
        } else {
            element.style.display = "block";
        }
    });
}
function answerClick(id){
    attr1 = "m"+id;
    let sons = document.getElementsByClassName(attr1);
    Array.from(sons).forEach(function (element) {
        if (element.style.display === "block") {
            element.style.display = "none";
        } else {
            element.style.display = "block";
        }
    });
}
function openForm(id) {  
    let inp = document.getElementById("qu");
    let em = document.getElementById("em");
    ansbox.style.marginTop = "0px";
    let q = document.getElementById(id+"-q");
    inp.setAttribute("value", q.innerHTML);
    let c = document.getElementById(id+"-c");
    let cat = document.getElementById("cat-"+c.getAttribute("m"));
    cat.setAttribute("selected", true);
    let ids = document.getElementById("id-conteneur");
    ids.setAttribute("value",c.getAttribute("qid"));
    em.setAttribute("value",c.getAttribute("eid"));
}
function actionR(id) {
    let r = document.getElementById(id+"-r");
    let a = document.getElementById(id+"-a");
    
    if (r.style.display === "block") {
        r.style.display = "none";
        a.style.transform = "rotateX(0deg)";
    } else {
        r.style.display = "block";
        a.style.transform = "rotateX(180deg)";
    }
};


let deco = document.getElementsByClassName("deco");
let deconnexion = document.getElementById("deconnection");
Array.from(deco).forEach(function (element) {
    element.addEventListener("click", () => {
        if (deconnexion.style.marginTop === "40vh") {
            deconnexion.style.marginTop = "-40vh";
        } else {
            deconnexion.style.marginTop = "40vh";
        }
    });
});

function displayIt(id){
    let cl = document.getElementById(id);
    let al = document.getElementById(id+"-a");
    if(cl.style.display === "block"){
        cl.style.display = "none";
        al.style.transform = "rotateX(0deg)";
    }else{
        cl.style.display = "block";
        al.style.transform = "rotateX(180deg)";
    }
}
let box = document.getElementById("action");
function gestMenu(id){
    let modify = document.getElementById("mod");
    let first = document.getElementById("mix");
    let sup = document.getElementById("sup");
    let info = document.getElementById("r"+id).getAttribute("tid");
    if(info==="1"){
        first.innerHTML = "Ajouter à la FAQ";
        first.setAttribute("href","../addFAQ?id="+id);
    }else{
        first.innerHTML = "Retirer de la FAQ";
        first.setAttribute("href","../outFAQ?id="+id);
    }
    sup.setAttribute("href","../suppression?id="+id);
    modify.setAttribute("onclick","openFormN('"+id+"')");
    box.style.top = "200px";
}
let close = document.getElementsByClassName("closeIt");
Array.from(close).forEach(function (element) {
    element.addEventListener('click', () => {
        box.style.top = "-400px";
    });
});
let cls = document.getElementById("cls");
cls.addEventListener("click", () => {
    ansbox.style.marginTop = "-80%";
});
function openFormN(id) {
    let c = document.getElementById(id+"-c");
    let inp = document.getElementById("qu");
    let pre = document.getElementById("pre");
    let q = document.getElementById(id+"-q");
    let aa = document.getElementById("r"+id);
    let cat = document.getElementById("cat-"+c.getAttribute("cid"));
    let ids = document.getElementById("id-conteneur");
    ansbox.style.marginTop = "0px";
    inp.setAttribute("value", q.innerHTML);
    pre.innerHTML = +aa.innerHTML;
    cat.setAttribute("selected", true);
    ids.setAttribute("value",c.getAttribute("qid"));
}