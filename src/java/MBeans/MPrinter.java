/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MBeans;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author __Root0__
 */

public class MPrinter {
    private static final String[] TOPICS = {"Commande de nourriture","Réservation de table","Livraison à domicile","Pâtisserie personnalisée","Service traiteur","Devenir partenaire"};
    
    private static List<List<Question>> placer(List<List<Question>> all,Question question,int i){
        List<Question> garbage = all.get(i);
        garbage.add(question);
        all.set(i, garbage);
        return all;
    }
    private static List<List<Question>> classify(List<Question> questions){
        List<List<Question>> all = new ArrayList<>();
        for(int i=0;i<6;i++){
            all.add(new ArrayList<>());
        }
        for(int i=0;i<questions.size();i++){
            String categorie = questions.get(i).getCategorie();
            if(categorie.equals("Commande de nourriture")) {
                    placer(all,questions.get(i),0);
            }else if(categorie.equals("Réservation de table")) {
                    placer(all,questions.get(i),1);
            }else if(categorie.equals("Livraison à domicile")) {
                    placer(all,questions.get(i),2);
            }else if(categorie.equals("Pâtisserie personnalisée")) {
                    placer(all,questions.get(i),3);
            }else if(categorie.equals("Service traiteur")) {
                    placer(all,questions.get(i),4);
            }else if(categorie.equals("Devenir partenaire")) {
                    placer(all,questions.get(i),5);
            }
        }
        return all;
    }
    public static String forFaq(List<Question> questions){
        String data = "";
        List<List<Question>> all = classify(questions);
        for(int i=0;i<all.size();i++){
            data += "<div>"
                    + "<a class=\"topic\" id=\""+String.valueOf(i+1)+"\" onclick=\"topicClick('t"+String.valueOf(i+1)+"')\" >"
                    +TOPICS[i]+"<i id=\"t"+String.valueOf(i+1)+"-a\" class=\"fa fa-caret-down ml-4\"></i>"
                    + "</a>";
            List<Question> liste = all.get(i);
            for(int j=0;j<liste.size();j++){
                Question q = liste.get(j);
                data += "<div class=\"t"+String.valueOf(i+1)+"\">";
                String id="q"+String.valueOf(i+1)+String.valueOf(j+1);
                data += "<a onclick=\"actionR('"+id+"')\" class=\"question t"+String.valueOf(i+1)+"-q\""
                        + "id=\"t"+String.valueOf(i+1)+"-q"+String.valueOf(j+1)+"\">"
                        + q.getQuestion()
                        + "<i id=\"q"+String.valueOf(i+1)+String.valueOf(j+1)+"-a\" class=\"fa fa-caret-down ml-4\"></i>"
                        + "</a><br>";
                if(!q.getResponse().equals("")){
                    data += "<p id=\""+id+"-r\">";
                    data += q.getResponse();
                    data += "</p>";
                }
                data += "</div>";
            }     
            data += "</div>";
        }
        return data;
    }
    public static String forUser(List<Question> questions){
        String data = "";
        List<List<Question>> all = classify(questions);
        for(int i=0;i<all.size();i++){
            List<Question> liste = all.get(i);
            for(int j=0;j<liste.size();j++){
                Question q = liste.get(j);
                data += "<div style=\" font-size : 15px;\">";
                String sup = q.isAnswered() ? "" : "<sup><i class=\"fa fa-star text-danger mr-2\"></i></sup>";
                String id="m"+String.valueOf(i+1)+String.valueOf(j+1);
                data += "<a onclick=\"actionR('"+id+"')\" class=\"question\" id=\""+id+"\">"+sup+q.getCategorie()+" : "+q.getQuestion()+"<i id=\""+id+"-a\"class=\"fa fa-caret-down ml-4\"></i></a>";
                if(!q.getResponse().equals("")){
                    data += "<p id=\""+id+"-r\">";
                    data += q.getResponse();
                    data += "</p>";
                }
                data += "</div>";
            }     
        }
        return data;
    }
    public static String forMessagerie(List<Question> questions){
        String data = "";
        List<List<Question>> all = classify(questions);
        for(int i=0;i<all.size();i++){
            List<Question> liste = all.get(i);
            for(int j=0;j<liste.size();j++){
                Question q = liste.get(j);
                data += "<div><div class=\"right-side\">";
                data += "<p>Par : "+q.getAsker()+"</p>";
                String id ="r"+ String.valueOf(i+1) + String.valueOf(j+1);
                data += "<p id=\""+id+"\"><span eid=\""+q.getAsker()+"\" qid=\""+q.getId()+"\" m=\""+String.valueOf(i+1)+"\" id=\""+id+"-c\">"+q.getCategorie()+"</span> - <span id=\""+id+"-q\">"+q.getQuestion()+"</p>";
                data += "</div>";
                data += "<div class=\"left-side\">";
                data += "<a class=\"ans btn btn-success mr-4\"  onclick=\"openForm('"+id+"')\">Répondre<i class=\"fa fa-check ml-2\"></i></a>";
                data += "<a href=\"../suppression?id="+q.getId()+"\" class=\"deny btn btn-danger\">Supprimer<i class=\"fa fa-close ml-2\"></i></a>";
                data += "</div></div>";
            }     
        }
        return data;
    }
    public static String forNonFaq(List<Question> questions){
       String data = "";
       List<List<Question>> all = classify(questions);
       for(int i=0;i<all.size();i++){
           List<Question> liste = all.get(i);
           for(int j=0;j<liste.size();j++){
               Question q = liste.get(j);
               data += "<div class=\"questions\">";
               data += "<div class=\"info\" >";
               data += "<i class=\"opGest fa fa-ellipsis-h mr-4 text-danger\" onclick=\"gestMenu('"+q.getId()+"')\"></i>" ;
               data += "<i id=\"r"+q.getId()+"-a\" onclick=\"displayIt('r"+q.getId()+"')\" class=\"clickIt fa fa-caret-down mr-4\"></i>";
               data += "<a><span cid=\""+String.valueOf(i+1)+"\" id=\""+q.getId()+"-c\">"+q.getCategorie()+"</span> - <span id=\""+q.getId()+"-q\">"+q.getQuestion()+"</a>";
               data += "<p id=\"r"+q.getId()+"\" tid=\"1\">";
               data += q.getResponse();
               data += "</p></div></div>";
           }     
       }
       return data;
    }
    public static String forGestInFaq(List<Question> questions){
       String data = "";
       List<List<Question>> all = classify(questions);
       for(int i=0;i<all.size();i++){
           List<Question> liste = all.get(i);
           for(int j=0;j<liste.size();j++){
               Question q = liste.get(j);
               data += "<div class=\"questions\">";
               data += "<div class=\"info\" >";
               data += "<i class=\"opGest fa fa-ellipsis-h mr-4 text-danger\" onclick=\"gestMenu('"+q.getId()+"')\"></i>" ;
               data += "<i id=\"r"+q.getId()+"-a\" onclick=\"displayIt('r"+q.getId()+"')\" class=\"clickIt fa fa-caret-down mr-4\"></i>";
               data += "<a><span cid=\""+String.valueOf(i+1)+"\" id=\""+q.getId()+"-c\">"+q.getCategorie()+"</span> - <span id=\""+q.getId()+"-q\">"+q.getQuestion()+"</a>";
               data += "<p id=\"r"+q.getId()+"\" tid=\"2\">";
               data += q.getResponse();
               data += "</p></div></div>";
           }     
       }
       return data;
    }
}
