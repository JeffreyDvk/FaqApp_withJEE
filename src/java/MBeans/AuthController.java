/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MBeans;
import java.security.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author __Root0__
 */
public class AuthController {
    public final DatabaseConnect db;
    private Statement statement;
    private ResultSet ans;
    private Connection connexion;
    
    
    public AuthController() {
        db = new DatabaseConnect();
        connexion = db.getConnection();
        try{
            statement = connexion.createStatement();
        }catch(SQLException e){
            System.out.println(e);
        }
        
    }
    
    public boolean searchUser(String email,String password){
        String req="SELECT COUNT(*) AS count FROM user WHERE email=? AND password=?;";
        try{
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, email);
            ps.setString(2, hash(password));
            ans = ps.executeQuery();
            while(ans.next()){
                return ans.getInt("count")!=0;
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return true;
    }
    public boolean searchCopyUser(String email){
        String req="SELECT COUNT(*) AS count FROM user WHERE email=?;";
        try{
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, email);
            ans = ps.executeQuery();
            while(ans.next()){
                return ans.getInt("count")!=0;
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return true;
    }
    public boolean insertUser(String email,String password,boolean status){
        if(searchCopyUser(email)){
            return false;
        }
        String req="INSERT INTO user VALUES (?,?,?);";
        try{
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, email);
            ps.setString(2, hash(password));
            ps.setBoolean(3, status);
            ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return true;
    }
    public User ConnectUser(String email){ 
        String req="SELECT email,status FROM user WHERE email=? ;";
        try{
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, email);
            ans = ps.executeQuery();
            while(ans.next()){
                return new User(ans.getString("email"), ans.getBoolean("status"));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    public String hash(String password){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            System.out.println(e);
        }
        return generatedPassword;

    }

}
