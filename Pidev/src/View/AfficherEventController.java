/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import interfaces.InterfaceCRUD;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Event;
import services.Services_event;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AfficherEventController implements Initializable {
InterfaceCRUD Service_event = new Services_event();
    @FXML
    private ListView<Event> Afficher;
    @FXML
    private Button AfficherButton;
    @FXML
    private Button AjouterButton;
    @FXML
    private Button ModifierButton;
    @FXML
    private Button SupprimerButton;
    
  //  Event e=(Event) Service_event.readById(19);
    //@FXML
    //private ImageView brasomek;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        getAllEvent();
//        URL imageUrl;
//        imageUrl = new URL("http://localhost/"+e.getImage());
//        System.out.println(imageUrl);
//        Image images = new Image(imageUrl.toString());
//        brasomek.setImage(images);
        
        // TODO
   
    } 
    public void getAllEvent(){
        ObservableList<Event> Events = FXCollections.observableArrayList(Service_event.readAll());
        Afficher.setItems(Events);
         
         
          Afficher.setCellFactory(param -> new ListCell<Event>() {
            private final ImageView imageView = new ImageView();
            private final Text nom = new Text();
            private final Text dated = new Text();
            private final Text datef = new Text();
            private final Text lieu = new Text();
            private final Text type = new Text();
            private final Text prix = new Text();
            
            
            private final HBox hbox = new HBox(100,imageView,nom,lieu,type,dated,datef,prix);
            //private final HBox hbox2 = new HBox(200,imageView,nom,adresse,type,etat);
            
            {
                imageView.setFitWidth(75);
                imageView.setFitHeight(75);
            }

            @Override
            protected void updateItem(Event item, boolean empty) {
                URL imageUrl;
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    try {
                         nom.setText(item.getNom_event());
                         lieu.setText(item.getLieu_event());
    type.setText(item.getType_event());
    dated.setText(item.getDate_debut().toLocalDate().toString());
    dated.setText(item.getDate_debut().toLocalDate().toString());
    prix.setText(item.getPrix().toString());
                      //  System.out.println(item.getImage());
                        imageUrl = new URL("http://localhost/img/"+item.getImage());
                        //System.out.println(imagehttp://localhost/images/Url);
                        Image images = new Image(imageUrl.toString());
                        imageView.setImage(images);
                        setText(null);
                        setGraphic(hbox);
                    } catch (MalformedURLException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
    }

    @FXML
    private void Affichet_Event(ActionEvent event) {
         getAllEvent();
    }

    @FXML
    private void Ajouter_Event(ActionEvent event) {
          
    try {
        //bonPlanService.update(bonPlanService.readById(selectedId));
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./AjouterEvent.fxml"));
        Parent view_2=loader.load();
        
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AfficherEventController.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    }

    @FXML
    private void Delete_Event(ActionEvent event) {
         int selectedId= Afficher.getSelectionModel().getSelectedItem().getId_e();
        Service_event.delete(selectedId);
        Affichet_Event(event);
    }

    @FXML
    private void modifier_Event(ActionEvent event) {
    try {
        Event selectedEvent=Afficher.getSelectionModel().getSelectedItem();
        
        //bonPlanService.update(bonPlanService.readById(selectedId));
        FXMLLoader loader= new FXMLLoader(getClass().getResource("./ModifierEvent.fxml"));
        Parent view_2=loader.load();
        ModifierEventController ModifierEventController=loader.getController();
        ModifierEventController.getEvent(selectedEvent);
        ModifierEventController.e=selectedEvent;
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AfficherEventController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        }
    }
    

