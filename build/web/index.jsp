<%@page import="MBeans.*"%>
<%@page import="java.beans.Beans"%>
<%
    User usr = (User)session.getAttribute("user");
    if(usr!=null){
        if(usr.getStatus()){
            response.sendRedirect("/ProjetJEE/pages/admin.jsp");
            return;
        }else{
            response.sendRedirect("/ProjetJEE/pages/main.jsp");
            return;
        }
    }
    String error = "";
    int type=0;
    if((Boolean)session.getAttribute("ok")!=null){
        error = "Inscription effectuée avec succès";
        type = 1;
        session.removeAttribute("ok");
    }
    else {
        
        if((Integer)session.getAttribute("error")!=null){
            if((Integer)session.getAttribute("error")==1){
                error = "Un ou plusieurs champs ne sont pas remplies";
            }else if((Integer)session.getAttribute("error")==2){
                error = "Mot de passe non-identique";
            }else if((Integer)session.getAttribute("error")==3){
                error = "Il existe déjà un utilisateur possédant ce email";
            }else if((Integer)session.getAttribute("error")==4){
                error = "Email/Mot de passe incorrect";
            }else if((Integer)session.getAttribute("error")==5){
                error = "Un problème est survenue";
            }else if((Integer)session.getAttribute("error")==6){
                error = "Vous avez été déconnecté d'urgence";
            }
        }
    }
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width , initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="font/css/font-awesome.css">
        <link rel="stylesheet" type="text/css" href="css/connect.css">
    </head>
    <style>
        #error-div{
            display : <%= !error.equals("") ? "block" : "none" %>;
            position: absolute;
            width : 60%;
            padding: 10px;
            left : 20%;
            top : 10px;
            background-color: <%= type==1 ? "rgba(55,193,63,0.8)" :  "rgba(223,57,49,0.8)" %>; 
        }
        #snd1{
            <%= error.equals("") && type==0 ? "margin-left: 440px;" : "" %>
        }
    </style>
    <body>
        <div class="alert text-center text-white" id="error-div">
            <%= error %>
        </div>
        <div id="main-div">
            <div id="first1">
                <p id="logo"><a href="index.jsp">Claris's</a></p>
                <h3>Se connecter à Clari's Web</h3>
                <div id="form-connect">
                    <form action="connect" method="POST" autocomplete="off">
                        <label class="f-label" for="email">
                            <i class="fa fa-envelope text-white"></i>
                        </label>
                        <input class="f-input" type="email" name="email" id="email" placeholder="Entrer votre email ici ..." required=""><br>
                        <label class="f-label" for="pass">
                            <i class="fa fa-lock text-white px-1"></i>
                        </label>
                        <input class="f-input" type="password" name="password" id="password" placeholder="Entrer votre mot de passe ici ..." required="">
                        <div class="my-2">
                            <a href="#" class="">Mot de passe oublié ?</a><br>
                        </div>
                        <input class="btn btn-success" type="submit" value="Se connecter">
                    </form>
                    <div style="margin:40px 0px -40px 0px;">
                        <h4>Vous êtes nouveau ?</h4>
                        <a href="#" id="insc">Inscrivez-vous</a><br>
                        <a href="anonymous">Connexion anonyme</a>
                    </div>
                </div>
            </div>
            <div id="first2">
                <p id="logo2"><a href="pages/admin.jsp">Claris's</a></p>
                <h3 id="more">S'inscrire à Clari's Web</h3>
                <!--<h5>Veuiller fournir les informations ci-contre</h5>-->
                <div id="form-insc">
                    <form action="inscription" method="POST" autocomplete="off">
                        <label class="f-label" for="email-insc">
                            <i class="fa fa-envelope text-white"></i>
                        </label>
                        <input class="f-input" name="email" type="email" id="email-insc" placeholder="Entrer votre email ici ..." required=""><br>
                        <label class="f-label"  for="pass-insc">
                            <i class="fa fa-lock text-white px-1"></i>
                        </label>
                        <input class="f-input" name="pass1" type="password" id="pass-insc" placeholder="Entrer votre mot de passe ici ..." required=""><br>
                        <label class="f-label"  for="conf">
                            <i class="fa fa-lock text-white px-1"></i>
                        </label>
                        <input class="f-input" name="pass2" type="password" id="conf" placeholder="Confirmer votre mot de passe ici ..." required><br>
                        <input class="btn btn-success" type="submit" value="S'inscrire">
                    </form>
                    <div style="margin:40px 0px -40px 0px;">
                        <h5>Vous avez déjà un compte ?</h5>
                        <a href="#" id="conn">Connectez-vous</a>
                    </div>
                </div>
            </div>
            <div id="snd1">
                
            </div>
        </div>
        <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="js/popper.min.js"></script>
        <script type="text/javascript" src="js/func.js"></script>
        <script type="text/javascript" src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>