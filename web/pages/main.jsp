<%@page import="java.beans.Beans"%>
<%@page import="MBeans.*"%>
<%  boolean anonymous = session.getAttribute("anonymous")!= null ? true : false;
    if(anonymous){
        
    }else{
    User usr = (User)session.getAttribute("user");
    if(usr==null){
        session.setAttribute("error", 5);
        response.sendRedirect("/ProjetJEE/index.jsp");
        return;
    }else{
        if(usr.getStatus()){
            response.sendRedirect("/ProjetJEE/pages/admin.jsp");
            return;
        }
    }}
%>
<%
    String error = "";
    int type=0;
    if((Boolean)session.getAttribute("ok")!=null){
        error = "Question posée avec succès";
        type = 1;
        session.removeAttribute("ok");
    }
    else {
        error = ErrorManager.getErrorType((Integer)session.getAttribute("error"));
        error = error == null ? "" : error;
        session.removeAttribute("error");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width , initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="../bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../font/css/font-awesome.css">
        <link rel="stylesheet" type="text/css" href="../css/main.css">
        <link rel="stylesheet" type="text/css" href="../css/styleaccueil.css">
        <link rel="stylesheet" type="text/css" href="../css/apropos.css">
    </head>
    <style>
        #error-div{
            position: fixed;
            width : 50%;
            padding: 20px 10px;
            left : <%= error.equals("") ? "100%" : "25%"%>;
            top : 10%;
            background-color: <%= type==1 ? "rgba(55,193,63,1)" :  "rgba(223,57,49,1)" %>;
            z-index: 5000;
            font-weight: bold;
            border-radius: 10px;
            transition: 1s;
        }
        #error-div > i{
            cursor : pointer;
            position: absolute;
            right : 5px;
            top : 5px;
        }
    </style>
    <body>
        <div class="alert text-center text-white" id="error-div">
            <i class="fa fa-close" onclick="closeError()"></i>
            <%= error %>
        </div>
        <div id="top-nav">
            <h3>
                <a href="#" class="nav-link">
                    Claris's
                </a>
            </h3>
            <a id="toggle-button">
                <i class="fa fa-align-justify"></i>
            </a>
        </div>
        <div onclick="" id="deconnection" class="text-center">
            <i class="fa fa-warning text-warning"></i>
            <p>Souhaitez-vous vraiment vous déconnecter ?</p>
            <div>
                <a href="/ProjetJEE/deconnection" class="text-success">Oui</a>
                <a class="deco text-danger">Non</a>
            </div>
        </div>
        <div id="menu-bar" class="p-3">
            <h3><a href="#" class="nav-link">Claris's</a></h3>
            <div id="menu-list">
                <a href="main.jsp?pages=accueil" class="nav-link">Accueil</a>
                <a href="main.jsp?pages=about" class="nav-link">A propos</a>
                <a href="main.jsp?pages=faq" class="nav-link">FAQ</a>
                <a class="deco nav-link">Déconnexion</a>
            </div>
            <div id="social">
                <a href="#" class="logo" ><i class="fa fa-facebook"></i></a>
                <a href="#" class="logo" ><i class="fa fa-instagram"></i></a>
                <a href="#" class="logo" ><i class="fa fa-twitter"></i></a>
            </div>
        </div>
        <div class="" id="main-content">
            <c:choose>
                <c:when test="${param.pages == 'accueil'}">
                    <%@include file="accueil.jsp" %>
                </c:when>
                <c:when test="${param.pages == 'about'}">
                    <%@include file="about.jsp" %>
                </c:when>
                <c:when test="${param.pages == 'faq'}">
                    <%@include file="faq.jsp" %>
                </c:when>
                <c:otherwise>
                    <%@include file="accueil.jsp" %>
                </c:otherwise>
            </c:choose>
        </div>

        <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="../js/oc.js"></script>
        <script type="text/javascript" src="../bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>