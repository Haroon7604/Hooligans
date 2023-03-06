/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import utile.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Publication;

/**
 *
 * @author mouhi
 */
public class commentaireService {
    private Connection connection;

    public commentaireService(Connection connection) {
        this.connection = connection;
    }

    public commentaireService() {
        this.connection = MyConnection.getInstance().getCnx();
    }
public void create(int idcomment, String description, int iduser, int idpublication) {
    try {
        // Create a SQL statement to insert data into the "commentaires" table
        String sql = "INSERT INTO commentaire (id_comment , description, id_user, id_publication) VALUES (?, ?, ?, ?)";

        // Prepare the SQL statement with the given parameters
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idcomment);
        statement.setString(2, description);
        statement.setInt(3, iduser);
        statement.setInt(4, idpublication);

        // Execute the SQL statement and insert the commentaire into the database
        statement.executeUpdate();
        
        System.out.println("Commentaire created successfully!");
    } catch (SQLException e) {
        System.out.println("Error creating commentaire: " + e.getMessage());
    }
}

    public void update(int idcomment, int idpublication, String description) {
    try {
        // Create a SQL statement to update the description and number of likes of a commentaire
        String sql = "update commentaire SET description = ?  where id_comment = ? AND id_publication = ?" ;

        // Prepare the SQL statement with the given parameters
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, description);
        statement.setInt(2, idcomment);
        statement.setInt(3,idpublication );
        

        // Execute the SQL statement and update the commentaire in the database
        int rowsUpdated = statement.executeUpdate();
        
        if (rowsUpdated > 0) {
            System.out.println("Commentaire updated successfully!");
        } else {
            System.out.println("No commentaire found with ID ");
        }
    } catch (SQLException e) {
        System.out.println("Error updating commentaire: " + e.getMessage());
    }
}

public void delete(int commentId, int publicationId) {
    try {
        // Create a SQL statement to delete a comment
        String sql = "DELETE FROM commentaire WHERE id_comment  = ? AND id_publication = ?";

        // Prepare the SQL statement with the given parameters
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, commentId);
        statement.setInt(2, publicationId);

        // Execute the SQL statement and delete the comment
        int rowsAffected = statement.executeUpdate();

        // Check if the comment was deleted
        if (rowsAffected > 0) {
            System.out.println("Comment deleted successfully!");
        } else {
            System.out.println("Comment not found.");
        }
    } catch (SQLException e) {
        System.out.println("Error deleting comment: " + e.getMessage());
    }
}


public void displayComments(int publicationId) {
    try {
        // Create a SQL statement to select all comments associated with a publication
        String sql = "SELECT * FROM commentaire WHERE id_publication = ?";

        // Prepare the SQL statement with the given parameter
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, publicationId);

        // Execute the SQL statement and get the result set of comments
        ResultSet resultSet = statement.executeQuery();
        
        // Print each comment's details to the console
        while (resultSet.next()) {
            int id = resultSet.getInt("id_comment");
            String description = resultSet.getString("description");
            int userId = resultSet.getInt("id_user");
            
            System.out.println("ID: " + id);
            System.out.println("Description: " + description);
            System.out.println("User ID: " + userId);
            System.out.println();
        }
    } catch (SQLException e) {
        System.out.println("Error displaying comments: " + e.getMessage());
    }
}

}