<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="MBeans.*"%>
<div class="" id="faq-content">
    <div>
        <h2>QUESTIONS FREQUEMMENT POSEES</h2>
        <div id="content" class="pb-2">
            <div id="questions-div">
                <%  QuestionManager qm = new QuestionManager();
                    String data = MPrinter.forFaq(qm.getInFaqQuestion()); %>
                <%= data %>
            </div>
        </div>
                <c:if test='${empty sessionScope["anonymous"]}'>
                <h2 class="mb-4">Mon historique de questions</h2>
                <div id="answered">
                    <%  User user = (User)session.getAttribute("user");
                        data = MPrinter.forUser(qm.getUserQuestions(user.getEmail())); %>
                        <%= data.equals("") ? "<h4 class=\"text-center\">Vous n'avez poser aucune question</h4>" : data %>
                </div>
                <h2>Vous n'avez pas trouvé satisfaction à votre préocupation ?<br>Envoyez nous votre préocupation</h2>
                <div id="chatbox" class="text-center">
                    <form action="../postQuestion" method="POST" >
                        <label for="cat">Choisissez la catégorie de votre préocupation :</label><br>
                        <select id="cat" class="categories" name="categories">
                            <option>Commande de nourriture</option>
                            <option>Réservation de table</option>
                            <option>Livraison à domicile</option>
                            <option>Pâtisserie</option>
                            <option>Service traiteur</option>
                            <option>Devenir partenaire</option>
                            <option selected>Non sélectionner</option>
                        </select><br>
                        <label for="pre">Entrer votre préocupation ici : </label><br>
                        <textarea id="pre" class="preocupation" name="question" placeholder="Entrer votre préocupation ici"></textarea><br>
                        <button href="#" class="btn btn-dark">Envoyer</button>
                    </form>
                </div>
            </c:if>
        
    </div>
</div>
<script type="text/javascript" src="../js/oc.js"></script>