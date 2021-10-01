<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="MBeans.*"%>
<div id="messages">
   <div id="ansbox" class="text-center">
       <i id="cls" class="fa fa-close"></i>
        <form action="../response" method="POST" >
            <input name="id-conteneur" id="id-conteneur" type="text" hidden>
            <label for="cat">Réaffecter la catégorie de la préocupation :</label><br>
            <select id="cat" class="categories" name="categories">
                <option id="cat-1">Commande de nourriture</option>
                <option id="cat-2" >Réservation de table</option>
                <option id="cat-3">Livraison à domicile</option>
                <option id="cat-4">Pâtisserie</option>
                <option id="cat-5">Service traiteur</option>
                <option id="cat-6">Devenir partenaire</option>
                <option selected>Non sélectionner</option>
            </select><br>
            <label for="em">Auteur : </label><br>
            <input name="email" id="em" type="text"><br>
            <label for="qu">Question de l'utilisateur : </label><br>
            <input name="question" id="qu" type="text"><br>
            <label for="pre">Entrer votre réponse à la préocupation ici : </label><br>
            <textarea id="pre" name="reponse" class="preocupation" placeholder="Entrer votre préocupation ici"></textarea><br>
            <button class="btn btn-dark">Répondre</button>
        </form>
    </div>
    <div>
        <h3>Liste des message en attente de réponse</h3>
        <%  QuestionManager qm = new QuestionManager();
            String data = MPrinter.forMessagerie(qm.getInWait()); %>
        <%= data.equals("") ? "<h3 class=\"text-center\">Aucun message en attente</div>" : data %>
    </div>
</div>
<script type="text/javascript" src="../js/oc.js"></script>
