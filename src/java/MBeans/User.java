/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MBeans;

/**
 *
 * @author __Root0__
 */
public class User {
    private final String email;
    private boolean status;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }
    
    public User(String email, boolean status) {
        this.email = email;
        this.status = status;
    }
    
}
