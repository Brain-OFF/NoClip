/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.loggedUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WALID
 */
public class FrontMenuController implements Initializable {

    @FXML
    private Label Username;
    @FXML
    private Button admin;

     public void exceptionerror(Exception e)
    {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception Dialog");
            alert.setHeaderText("Exception Encountered");
            alert.setContentText("Exception: "+e.getClass());
            Exception ex = e;
            // Create expandable Exception.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("The exception stacktrace was:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);
            alert.showAndWait();
    }
     loggedUser holder;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         holder = loggedUser.get_instace();
        Username.setText("Bienvenue "+holder.getUser().getUsername()+"#"+holder.getUser().getId());
        if (holder.getUser().getStatus().equals("admin"))
        {
            admin.setVisible(true);
            admin.setDisable(false);
        }
    }    

    @FXML
    private void profile(ActionEvent event) throws IOException {
        StackPane secondaryLayout = new StackPane();
                           Parent bans = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/Profile.fxml"));
				Scene secondScene = new Scene(bans);
                                secondScene.getStylesheets().add("/dark-theme.css");
				Stage newWindow = new Stage();
				newWindow.setTitle("Profile");
				newWindow.setScene(secondScene);
				newWindow.show();
    }

    @FXML
    private void logout(ActionEvent event) {
        loggedUser holder = loggedUser.get_instace();      
                holder.setUser(null);
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                try {
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/Login.fxml"));
                    System.out.println("user being logged :"+holder.getUser());
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    scene.getStylesheets().add("/dark-theme.css");
                    stage.getIcons().add(new javafx.scene.image.Image("4.png"));
                    stage.show();
                    } catch (IOException e) {
                           exceptionerror(e);
                    }
    }

    @FXML
    private void Tournois(ActionEvent event) throws IOException {
        StackPane secondaryLayout = new StackPane();
                           Parent bans = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/FXMLADD_inc.fxml"));
				Scene secondScene = new Scene(bans);
                                secondScene.getStylesheets().add("/dark-theme.css");
				Stage newWindow = new Stage();
				newWindow.setTitle("Tournois");
				newWindow.setScene(secondScene);
				newWindow.show();
    }

    @FXML
    private void coaching(ActionEvent event) throws IOException {
        StackPane secondaryLayout = new StackPane();
                           Parent bans = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/CoachFrontFXML.fxml"));
				Scene secondScene = new Scene(bans);
                                secondScene.getStylesheets().add("/dark-theme.css");
				Stage newWindow = new Stage();
				newWindow.setTitle("Coaching");
				newWindow.setScene(secondScene);
				newWindow.show();
    }

    @FXML
    private void gogames(ActionEvent event) {
    }

    @FXML
    private void gonews(ActionEvent event) {
    }

    @FXML
    private void go_to_dash(ActionEvent event) throws IOException {
        if (!holder.getUser().getStatus().equals("admin"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hehe");
                alert.setHeaderText("You sneaky fucking bastard");
                alert.setContentText("eat shit cuck you fucking piece of shit");
                alert.showAndWait();
        }
        else
        {
             StackPane secondaryLayout = new StackPane();
                           Parent bans = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/Dashbiard.fxml"));
				Scene secondScene = new Scene(bans);
                                secondScene.getStylesheets().add("/dark-theme.css");
				Stage newWindow = new Stage();
				newWindow.setTitle("Dashboard");
				newWindow.setScene(secondScene);
				newWindow.show();
        }
    }
    
}
