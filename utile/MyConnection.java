package utile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azizh
 */
public class MyConnection {
    //DB PARAM
    static final String URL ="jdbc:mysql://127.0.0.1:3306/pi";
    static final String USER ="root";
    static final String PASSWORD ="";
    //var
    private Connection cnx;
    static MyConnection instance;
    
    public MyConnection(){
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion établie avec succes" );
            
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Echec de connexion" );
        }
    }
    
    public Connection getCnx() {
        return cnx;
    }
    
     public static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();
        
        return instance;
    }
}
