<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="MBeans.*"%>
<div id="action">
    <i class="fa fa-close closeIt text-white"></i>
    <p><a class="btn btn-info" id="mix">Retirer de la FAQ<i class="fa fa-check ml-2"></i></a></p>
    <p><a class="btn btn-success" id="mod" onclick="openFormN('id')"  >Modifier<i class="fa fa-gear ml-2"></i></a></p>
    <p><a class="btn btn-danger" id="sup">Supprimer<i class="fa fa-close ml-2"></i></a></p>
</div>
<div id="gestBox">
    <div id="ansbox" class="text-center">
       <i id="cls" class="fa fa-close"></i>
        <form action="../response" method="POST" >
            <input name="id-conteneur" id="id-conteneur" type="text" hidden>
            <label for="cat" class="mt-4">Réaffecter la catégorie de la préocupation :</label><br>
            <select id="cat" class="categories" name="categories">
                <option id="cat-1">Commande de nourriture</option>
                <option id="cat-2" >Réservation de table</option>
                <option id="cat-3">Livraison à domicile</option>
                <option id="cat-4">Pâtisserie</option>
                <option id="cat-5">Service traiteur</option>
                <option id="cat-6">Devenir partenaire</option>
                <option selected>Non sélectionner</option>
            </select><br>
            <label for="qu">Question de l'utilisateur : </label><br>
            <input name="question" id="qu" type="text"><br>
            <label for="pre">Entrer votre réponse à la préocupation ici : </label><br>
            <textarea id="pre" name="reponse" class="preocupation" placeholder="Entrer votre préocupation ici"></textarea><br>
            <button class="btn btn-dark">Répondre</button>
        </form>
    </div>
    <div>
        <h3>Liste des questions-réponses sur la FAQ</h3>
        <div class="all">
            <%  QuestionManager qm = new QuestionManager();
                    String data = MPrinter.forGestInFaq(qm.getInFaqQuestion()); %>
                <%= data %>
        </div>
        <h3 class=" ">Liste des questions-réponses restantes</h3>
        <div class="all">
            <%=MPrinter.forNonFaq(qm.getNonInFaq()) %>
        </div>
    </div>
</div>
<script type="text/javascript" src="../js/oc.js"></script>
