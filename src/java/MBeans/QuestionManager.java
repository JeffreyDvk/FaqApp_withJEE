/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MBeans;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author __Root0__
 */
public class QuestionManager {
    public final DatabaseConnect db;
    private Statement statement;
    private ResultSet ans;
    private List<Question> questions;
    private Connection connexion;
    
    public final List<Question> getAllQuestions(){
        questions = new ArrayList<Question>();
        try{
            ans = statement.executeQuery("SELECT * FROM question;");
            while(ans.next()){
                String id = ans.getString("id");
                String categorie = ans.getString("categorie");
                String question = ans.getString("question");
                String response = ans.getString("response");
                boolean status =  ans.getString("status").equals("1");
                boolean inFaq = ans.getString("inFaq").equals("1");
                String asker = ans.getString("asker");
                Question q = new Question(id, categorie, question, response, status, inFaq, asker);
                questions.add(q);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        finally{
            try{
                if(ans != null)
                    ans.close();
            }catch(SQLException e){
                
            }  
        }
        return questions;
    } 
    public int getQuestionIndex(String id){
        for(int i=0;i<questions.size();i++){
            if(questions.get(i).getId().equals(id))
                return i;
        }
        return -1;
    }
    public QuestionManager(){
        db = new DatabaseConnect();
        connexion = db.getConnection();
        try{
            statement  = connexion.createStatement();
        }catch(SQLException e){
            
        }
        ans = null;
        List<Question> allQuestions = getAllQuestions();
    }
    public boolean searchQuestion(String id){
        for(int i=0;i<questions.size();i++){
            if(questions.get(i).getId().equals(id))
                return true;
        }
        return false;
    }
    public boolean addQuestion(String categorie,String question,boolean status,boolean inFaq,String asker) {
        try{
            String req = "INSERT INTO question (categorie,question,status,inFaq,asker) VALUES (?,?,?,?,?);";   
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, categorie);
            ps.setString(2, question);
            ps.setBoolean(3, status);
            ps.setBoolean(4, inFaq);
            ps.setString(5, asker);
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            System.out.println(e);
        }
        questions = getAllQuestions();
        return false; 
    }
    public boolean deleteQuestion(String id){
        if(searchQuestion(id)){
            try{
                String req = "DELETE FROM question WHERE id= ?;" ;   
                PreparedStatement ps = connexion.prepareStatement(req);
                ps.setInt(1, Integer.parseInt(id));
                ps.execute();
                questions.remove(getQuestionIndex(id));
                return true;
            }catch(SQLException e){
                System.out.println(e);
            }
        }
        return false; 
    }
    public boolean modifyQuestionInfo(String id,String categorie,String question){
        if(searchQuestion(id)){
            try{
                String req = "UPDATE question set categorie=? , question=? WHERE id=?;";   
                PreparedStatement ps = connexion.prepareStatement(req);
                ps.setString(1, categorie);
                ps.setString(2, question);
                ps.setInt(3, Integer.parseInt(id));
                ps.execute();
                int idx = getQuestionIndex(id);
                questions.get(idx).setCategorie(categorie);
                questions.get(idx).setQuestion(question);
                return true;
            }catch(SQLException e){
                System.out.println(e);
            }
        }
        return false;     
    }
    public boolean setQuestionResponse(String id,String response){
        if(searchQuestion(id)){
            try{
                String req = "UPDATE question set response=?  WHERE id=?;";   
                PreparedStatement ps = connexion.prepareStatement(req);
                ps.setString(1, response);
                ps.setInt(2, Integer.parseInt(id));
                ps.execute();
                int idx = getQuestionIndex(id);
                questions.get(idx).setResponse(response);
                return true;
            }catch(SQLException e){
                System.out.println(e);
            }
        }
        return false;
    }

    public boolean modifyQuestionState(String id,boolean status,boolean inFaq){
        if(searchQuestion(id)){
            try{
                String req = "UPDATE question set status=? , inFaq=? WHERE id=?;";   
                PreparedStatement ps = connexion.prepareStatement(req);
                ps.setBoolean(1, status);
                ps.setBoolean(2, inFaq);
                ps.setInt(3, Integer.parseInt(id));
                ps.execute();
                int idx = getQuestionIndex(id);
                questions.get(idx).setStatus(status);
                questions.get(idx).setInFaq(inFaq);
                return true;
            }catch(SQLException e){
                System.out.println(e);
            }
        }
        return false; 
    }
    
    
    public List<Question> getUserQuestions(String email){
        List<Question> userQuestions = new ArrayList<Question>();
        for(int i=0;i<questions.size();i++){
            if(questions.get(i).getAsker().equals(email))
                userQuestions.add(questions.get(i));
        }
        return userQuestions;
    }
    
    public List<Question> getInFaqQuestion(){
        List<Question> inFaqs = new ArrayList<Question>();
        for(int i=0;i<questions.size();i++){
            if(questions.get(i).isInFaq())
                inFaqs.add(questions.get(i));
        }
        return inFaqs;
    }
    public List<Question> getInWait(){
        List<Question> noAnswer = new ArrayList<>();
        for(int i=0;i<questions.size();i++){
            if(!questions.get(i).isAnswered())
                noAnswer.add(questions.get(i));
        }
        return noAnswer;
    }
    public List<Question> getNonInFaq(){
        List<Question> nonInFaqs = new ArrayList<>();
        for(int i=0;i<questions.size();i++){
            if(!questions.get(i).isInFaq() && questions.get(i).isAnswered())
                nonInFaqs.add(questions.get(i));
        }
        return nonInFaqs;
    }

    public List<Question> getQuestions() {
        return questions;
    }
    
    public void closer(){
        try{
            if(statement!=null){
                statement.close();
            }
            if(connexion!=null){
                connexion.close();
            }
            db.closeConnect();
        }catch(SQLException e){
            
        }
        
    }
}
