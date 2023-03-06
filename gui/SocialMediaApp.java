package gui;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SocialMediaApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        
        // Header
        Label titleLabel = new Label("Social Media");
        titleLabel.setStyle("-fx-font-size: 24pt; -fx-padding: 10px;");
        root.setTop(titleLabel);
        
        // Sidebar
        Button profileButton = new Button("My Profile");
        Button friendsButton = new Button("My Friends");
        VBox sidebar = new VBox(profileButton, friendsButton);
        sidebar.setSpacing(10);
        sidebar.setPadding(new Insets(10));
        root.setLeft(sidebar);
        
        // Main content
        GridPane contentGrid = new GridPane();
        contentGrid.setAlignment(Pos.TOP_CENTER);
        contentGrid.setHgap(10);
        contentGrid.setVgap(10);
        contentGrid.setPadding(new Insets(10));
        
        // Text input for creating a new publication
        TextArea newPublicationInput = new TextArea();
        newPublicationInput.setPromptText("Write a new publication...");
        newPublicationInput.setPrefRowCount(4);
        contentGrid.add(newPublicationInput, 0, 0, 2, 1);
        
        // Button for creating a new publication
        Button newPublicationButton = new Button("Publish");
        contentGrid.add(newPublicationButton, 1, 1);
        
        // Label for displaying the number of likes
        Label likesLabel = new Label("0 Likes");
        contentGrid.add(likesLabel, 0, 2);
        
        // Button for adding a like
        Button likeButton = new Button("Like");
        contentGrid.add(likeButton, 1, 2);
        
        // Text input for adding a new comment
        TextArea newCommentInput = new TextArea();
        newCommentInput.setPromptText("Add a comment...");
        newCommentInput.setPrefRowCount(2);
        contentGrid.add(newCommentInput, 0, 3, 2, 1);
        
        // Button for adding a new comment
        Button newCommentButton = new Button("Comment");
        contentGrid.add(newCommentButton, 1, 4);
        
        // Add the content grid to the center of the root pane
        root.setCenter(contentGrid);
        
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("Social Media");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
