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
public class ErrorManager {
    public static String getErrorType(Integer m_error){
        String error = "";
        if(m_error!=null){
            if(m_error==1){
                error = "Un ou plusieurs champs ne sont pas remplies";
            }else if(m_error==2){
                error = "Mot de passe non-identique";
            }else if(m_error==3){
                error = "Il existe déjà un utilisateur possédant ce email";
            }else if(m_error==4){
                error = "Email/Mot de passe incorrect";
            }else if(m_error==5){
                error = "Un problème est survenue";
            }else if(m_error==6){
                error = "Vous avez été déconnecté d'urgence";
            }
        }
        return null;
    }
}
