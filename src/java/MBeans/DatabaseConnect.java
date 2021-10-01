/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MBeans;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author __Root0__
 */
public class DatabaseConnect {

    private final String url = "jdbc:mysql://localhost";
    private final String port = ":3306";
    private final String database = "/javaproject?useUnicode=true&characterEncoding=UTF-8";
    private final String user = "root";
    private final String pass = "";
    private Connection connexion=null;
    
    public DatabaseConnect() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(url + port + database,user,pass);
        }catch(ClassNotFoundException e){
            connexion = null;
        }catch(SQLException a){
            connexion = null;
        }
    }
    
    public void closeConnect(){
        try{
            connexion.close();
        }catch(SQLException e){
            
        }
    }
    public Connection getConnection(){
        return this.connexion;
    }
}
