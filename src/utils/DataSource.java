package utils;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSource {
    
   private String url="jdbc:mysql://localhost:3306/driftrace";
   private String login="root";
   private String pwd="root";
   private Connection cnx;
   private static DataSource instance;

    private DataSource() {
       try {
           cnx=DriverManager.getConnection(url, login, pwd);
           System.out.println("Connexion etablie");
       } catch (SQLException ex) {
           Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
   
    
    public static DataSource getInstance(){
        if(instance==null)
           instance=new DataSource();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
   
    
    
}