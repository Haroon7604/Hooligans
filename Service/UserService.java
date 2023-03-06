package service;

import models.role;
import models.user;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utile.MyConnection;

/**
 *
 * @author 21694
 */
public class UserService implements IService<user>{
    private String requete;
    private final Connection conn;

    public UserService() {
        conn=MyConnection.getInstance().getCnx();
    }

    
    @Override
    public void insert(user t){
        String requete="insert into user  (nom,prenom,mdp,email,num_tel,cin,quota,id_role,img,etat) values (?,?,?,?,?,?,?,?,?,?) ";
        try {
            PreparedStatement usr=conn.prepareStatement(requete);
            usr.setString(1, t.getNom());
            usr.setString(2, t.getPrenom());
            usr.setString(3, t.getMdp());
            usr.setString(4, t.getEmail());
            usr.setInt(5, t.getNum_tel());
            usr.setInt(6, t.getCin());
            usr.setInt(7, t.getQuota());
            usr.setInt(8, t.id_role);
       FileInputStream fis1 = new FileInputStream(t.getImg());
             byte[] imageData1 = new byte[fis1.available()];
             fis1.read(imageData1);
             fis1.close();
            
             usr.setBytes(9, imageData1);
            usr.setInt(10, 1);
            usr.executeUpdate();
        } 
        
        
        catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void updateban(user t,int etat_user) {
        try {
          PreparedStatement ps = conn.prepareStatement("UPDATE user SET etat= ? WHERE id_user = ?");
ps.setInt(2,t.getId_user());

            ps.setInt(1, etat_user);

            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
      public user readByEmailrecruteur(String email) {
    user t = null;
      requete = "SELECT user.*, role.id_role FROM user INNER JOIN role ON user.id_role = user.id_role where email= ?" ;
    try (PreparedStatement stmt = conn.prepareStatement(requete)) {
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            role r = new role();
            r.setId_role(rs.getInt("id_role"));
           
             t = new user(rs.getInt("id_user"), rs.getString("nom"),rs.getString("prenom"),rs.getString("mdp"),rs.getString("email"),rs.getInt("num_tel"),rs.getInt("cin"),rs.getInt("quota"),rs.getInt("id_role"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return t;
}

   public void delete(int id) {
   
    String requete = "DELETE FROM user WHERE id_user = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(requete);
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    @Override
    public void update(user t) {
        
          try {
            PreparedStatement ps = conn.prepareStatement("UPDATE user SET nom = ?, prenom = ? ,mdp = ?,email = ? ,num_tel = ?,cin = ?, quota = ? WHERE id_user = ?");
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setString(3, t.getMdp());
            ps.setString(4, t.getEmail());
            ps.setInt(5, t.getNum_tel());
            ps.setInt(6, t.getCin());
            ps.setInt(7, t.getQuota());
            ps.setInt(8, t.getId_user());
              
            ps.setInt(10, t.getEtat());
              
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
    }

    @Override
    public List<user> readAll() {
         String requete = "SELECT user.*, role.id_role, role.type FROM user INNER JOIN role ON user.id_role = role.id_role";
    List<user> list = new ArrayList<>();
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            role r = new role();
            r.setId_role(rs.getInt("id_role"));
            r.setType(rs.getString("type"));
            user t;
            t = new user(rs.getInt("id_user"), rs.getString("nom"),rs.getString("prenom"),rs.getString("mdp"),rs.getString("email"),rs.getInt("num_tel"),rs.getInt("cin"),rs.getInt("quota"),rs.getInt("id_role"));
            list.add(t);
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}


    

    @Override
    public user readById(int id_user) {
       String requete;
    requete = "SELECT user.*, role.id_role, role.type FROM user INNER JOIN role ON user.id_role = user.id_role where id_user=" + id_user;

    user t = null;
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            role r = new role();
            r.setId_role(rs.getInt("id_role"));
            r.setType(rs.getString("type"));
           
            t = new user(rs.getInt("id_user"), rs.getString("nom"),rs.getString("prenom"),rs.getString("mdp"),rs.getString("email"),rs.getInt("num_tel"),rs.getInt("cin"),rs.getInt("quota"),rs.getInt("id_role"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }

    return t;
    }

    @Override
    public void delete(user t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void update1(user t,int id_role) {
        try {
          PreparedStatement ps = conn.prepareStatement("UPDATE user SET id_role= ? WHERE id_user = ?");
ps.setInt(2,t.getId_user());
//            ps.setString(2, t.getPrenom());
//            ps.setInt(3, t.getNum_tel());
//            ps.setString(4, t.getAdresse());
//            ps.setString(5, t.getCentre_intere());
//            ps.setString(6, t.getAdresse_entreprise());
//            ps.setString(7, t.getNom_entreprise()); 
            ps.setInt(1, id_role);
//            ps.setString(9, t.getEmail());
//            ps.setString(10,t.getMdp()); 
//            ps.setInt(11, t.getAge()); 
//            ps.setInt(12, t.getNote());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }
    
    public boolean checkEmailExists(String email) {
    Connection cnx = MyConnection.getInstance().getCnx();
    boolean result = false;

    try {
        String req = "SELECT * FROM user WHERE email = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1, email);
        ResultSet rs = st.executeQuery();
        result = rs.next();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    return result;
}


    
    
    
    }