package MBeans;



/**
 *
 * @author __Root0__
 */

public class Question {

    
    private String id;
    private String categorie;
    private String question;
    private String response;
    private boolean status;
    private boolean inFaq;
    private String asker;

    public Question(){
        
    }
    
    public String getId() {
        return id;
    }
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    public String getCategorie() {
        return categorie;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isAnswered() {
        return status;
    }
    
    public boolean isInFaq() {
        return inFaq;
    }

    public String getAsker() {
        return asker;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setInFaq(boolean inFaq) {
        this.inFaq = inFaq;
    }

    public void setAsker(String asker) {
        this.asker = asker;
    }
    
    public Question(String id, String categorie, String question, String response, boolean status, boolean inFaq, String asker) {
        this.id = id;
        this.categorie = categorie;
        this.question = question;
        this.response = response;
        this.status = status;
        this.inFaq = inFaq;
        this.asker = asker;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", categorie=" + categorie + ", question=" + question + ", response=" + response + ", status=" + status + ", inFaq=" + inFaq + ", asker=" + asker + '}';
    }

    
    
}
