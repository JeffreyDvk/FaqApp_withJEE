let ansbox = document.getElementById("ansbox");

let inp = document.getElementById("qu");




function openForm(id) {
    let ansbox = document.getElementById("ansbox");
    let inp = document.getElementById("qu");
    ansbox.style.marginTop = "0px";
    let q = document.getElementById(id+"-q");
    inp.setAttribute("value", q.innerHTML);
    let c = document.getElementById(id+"-c");
    let cat = document.getElementById("cat-"+c.getAttribute("m"));
    let id = document.getElementById("id-conteneur");
    id.setAttribute("value",c.getAttribute("qid"));
    cat.setAttribute("selected", true);
    
}
