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
public class publicationService {
    private Connection connection;

    public publicationService(Connection connection) {
        this.connection = connection;
    }

    public publicationService() {
        this.connection = MyConnection.getInstance().getCnx();
    }
public void create(int postId, String description, int nbLikes,int nbComments, int userId) {
    try {
        // Create a SQL statement to insert data into the "publications" table
        String sql = "INSERT INTO publication (idPost, description, nbLikes, nbComments, id_user) VALUES (?, ?, ?, ?,?)";

        // Prepare the SQL statement with the given parameters
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, postId);
        statement.setString(2, description);
        statement.setInt(3, nbLikes);
        statement.setInt(4, nbComments);
        statement.setInt(5, userId);

        // Execute the SQL statement and insert the publication into the database
        statement.executeUpdate();
        
        System.out.println("Publication created successfully!");
    } catch (SQLException e) {
        System.out.println("Error creating publication: " + e.getMessage());
    }
}
public void displayPublications() {
    try {
        // Create a SQL statement to select all publications
        String sql = "SELECT * FROM publication";

        // Create a statement object to execute the SQL query
        Statement statement = connection.createStatement();

        // Execute the SQL statement and get the result set of publications
        ResultSet resultSet = statement.executeQuery(sql);
        
        // Print each publication's details to the console
        while (resultSet.next()) {
            int id = resultSet.getInt("idPost");
            String description = resultSet.getString("description");
            int nbLikes = resultSet.getInt("nbLikes");
            int nbComments = resultSet.getInt("nbComments");
            int userId = resultSet.getInt("id_user");
            
            System.out.println("ID: " + id);
            System.out.println("Description: " + description);
            System.out.println("Number of likes: " + nbLikes);
            System.out.println("Number of comments: " + nbComments);
            System.out.println("User ID: " + userId);
            System.out.println();
        }
    } catch (SQLException e) {
        System.out.println("Error displaying publications: " + e.getMessage());
    }
}
public void getPublicationById(int publicationId) {
    try {
        // Create a SQL statement to retrieve a publication by its ID
        String sql = "SELECT * FROM publication WHERE idPost = ?";

        // Prepare the SQL statement with the given parameter
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, publicationId);

        // Execute the SQL statement and retrieve the publication
        ResultSet result = statement.executeQuery();

        // Check if a publication was found
        if (result.next()) {
            // Print the publication's details
            System.out.println("Publication ID: " + result.getInt("idPost"));
            System.out.println("Description: " + result.getString("description"));
            System.out.println("Number of likes: " + result.getInt("nbLikes"));
            System.out.println("Number of comments: " + result.getInt("nbComment"));
            System.out.println("User ID: " + result.getInt("id_user"));
        } else {
            System.out.println("Publication not found.");
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving publication: " + e.getMessage());
    }
}

public void update(int publicationId, String newDescription) {
    try {
        // Create a SQL statement to update the description of a publication
        String sql = "UPDATE publication SET description = ? WHERE idPost = ?";

        // Prepare the SQL statement with the given parameters
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, newDescription);
        statement.setInt(2, publicationId);

        // Execute the SQL statement and update the publication's description
        int rowsAffected = statement.executeUpdate();

        // Check if the publication was updated
        if (rowsAffected > 0) {
            System.out.println("Publication description updated successfully!");
        } else {
            System.out.println("Publication not found.");
        }
    } catch (SQLException e) {
        System.out.println("Error updating publication description: " + e.getMessage());
    }
}


public void delete(int publicationId) {
    try {
        // Create a SQL statement to delete a publication by its ID
        String sql = "DELETE FROM publication WHERE idPost = ?";

        // Prepare the SQL statement with the given parameter
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, publicationId);

        // Execute the SQL statement and delete the publication from the database
        int rowsDeleted = statement.executeUpdate();
        
        if (rowsDeleted > 0) {
            System.out.println("Publication deleted successfully!");
        } else {
            System.out.println("No publication found with ID " + publicationId);
        }
    } catch (SQLException e) {
        System.out.println("Error deleting publication: " + e.getMessage());
    }
}
public void addLike(int publicationId) {
    try {
        // Create a SQL statement to update the number of likes for a publication
        String sql = "UPDATE publication SET nbLikes = nbLikes + 1 WHERE idPost = ?";

        // Prepare the SQL statement with the given parameter
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, publicationId);

        // Execute the SQL statement and update the publication's number of likes
        int rowsUpdated = statement.executeUpdate();
        
        // Check if the update was successful
        if (rowsUpdated > 0) {
            System.out.println("Like added successfully!");
        } else {
            System.out.println("Publication not found.");
        }
    } catch (SQLException e) {
        System.out.println("Error adding like: " + e.getMessage());
    }
}
public void addComment(int publicationId) {
    try {
        // Create a SQL statement to update the number of likes for a publication
        String sql = "UPDATE publication SET nbComments = nbComments + 1 WHERE idPost = ?";

        // Prepare the SQL statement with the given parameter
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, publicationId);

        // Execute the SQL statement and update the publication's number of likes
        int rowsUpdated = statement.executeUpdate();
        
        // Check if the update was successful
        if (rowsUpdated > 0) {
            System.out.println("Comment added successfully!");
        } else {
            System.out.println("Publication not found.");
        }
    } catch (SQLException e) {
        System.out.println("Error adding Comments: " + e.getMessage());
    }
}
public void removeLike(int publicationId) {
    try {
        // Create a SQL statement to update the number of likes for a publication
        String sql = "UPDATE publication SET nbLikes = nbLikes - 1 WHERE idPost = ? AND nbLikes > 0";

        // Prepare the SQL statement with the given parameter
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, publicationId);

        // Execute the SQL statement and update the publication's number of likes
        int rowsUpdated = statement.executeUpdate();
        
        // Check if the update was successful
        if (rowsUpdated > 0) {
            System.out.println("Like removed successfully!");
        } else {
            System.out.println("Publication not found or no likes to remove.");
        }
    } catch (SQLException e) {
        System.out.println("Error removing like: " + e.getMessage());
    }
}
public void removeComment(int publicationId) {
    try {
        // Create a SQL statement to update the number of likes for a publication
        String sql = "UPDATE publication SET nbComments = nbComments - 1 WHERE idPost = ? AND nbComments > 0";

        // Prepare the SQL statement with the given parameter
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, publicationId);

        // Execute the SQL statement and update the publication's number of likes
        int rowsUpdated = statement.executeUpdate();
        
        // Check if the update was successful
        if (rowsUpdated > 0) {
            System.out.println("Comments removed successfully!");
        } else {
            System.out.println("Publication not found or no likes to remove.");
        }
    } catch (SQLException e) {
        System.out.println("Error removing Comments: " + e.getMessage());
    }
}
public void getCommentCount(int publicationId) {
    try {
        // Create a SQL statement to retrieve the number of comments for a publication
        String sql = "SELECT COUNT(*) AS comment_count FROM commentaire WHERE idPost = ?";

        // Prepare the SQL statement with the given parameter
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, publicationId);
        
        // Execute the SQL statement and retrieve the comment count
        ResultSet result = statement.executeQuery();

        // Print the comment count
        if (result.next()) {
            int commentCount = result.getInt("comment_count");
            System.out.println("Publication " + publicationId + " has " + commentCount + " comment(s).");
        } else {
            System.out.println("Publication not found.");
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving comment count: " + e.getMessage());
    }
}





}