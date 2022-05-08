/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Services.UserService;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 *
 * @author WALID
 */
public class Reset_PassController implements Initializable {

    @FXML
    private TextField email_field;
    @FXML
    private Label success;
    @FXML
    private Label fail;
    UserService Us=new UserService();
    /**
     * Initializes the controller class.
     */
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void send_m(ActionEvent event) {
        String response_reset="";
        try{
        response_reset=Us.Reset_pass(email_field.getText().toString());
        }
        catch (Exception e)
        {
            exceptionerror( e);
        }
            
        if (response_reset.equals("1"))
        {
            fail.setVisible(false);
            success.setVisible(true);
        }
        else
        {
            fail.setVisible(true);
            success.setVisible(false);
        }
    }
    
}
