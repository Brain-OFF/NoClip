/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.User;
import Entities.loggedUser;
import Services.UserService;
import java.io.PrintWriter;
import java.io.StringWriter;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 *
 * @author WALID
 */
public class ProfileController implements Initializable {

    @FXML
    private TextArea bio;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private Label idlabel;
    UserService ServU=new UserService();
    User Current_user=new User();
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loggedUser holder = loggedUser.get_instace();
        Current_user= loggedUser.get_instace().getUser();
        idlabel.setText("ID : "+Current_user.getId());
        username.setText(Current_user.getUsername());
        email.setText(Current_user.getEmail());
        bio.setText(Current_user.getBio());
    }    

    @FXML
    private void submit(ActionEvent event) {
        if ((username.getText().length()<=0||username.getText().length()>25)||(email.getText().length()<=0||email.getText().length()>50)||
                (password.getText().length()<=0||password.getText().length()>50))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot Perform Add");
            alert.setContentText("Please fill all the the fields before adding");
            alert.showAndWait();
        }
        else
        {
            boolean success = false;
            ServU=new UserService();
            try{
            User U=Current_user;
            U.setUsername(username.getText().toString());
            U.setEmail(email.getText().toString());
            U.setBio(bio.getText().toString());
            U.setPassword(password.getText().toString());
            Current_user=ServU.modifier(U, Current_user.getId());
            loggedUser.get_instace().setUser(Current_user);
            success=true;
            }
            catch (SQLException ex)
            {
                /*Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
            alert.setHeaderText("SQL Exception");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();*/
                exceptionerror(ex);
            }
            if (success)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("User Modified");
                alert.setContentText("User Has Been Updated");
                alert.showAndWait();
            }
            
        }
    }

    
}
