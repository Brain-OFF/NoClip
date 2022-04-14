/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Coach;
import Services.CoachService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author xDrais
 */
public class TestFXMLController implements Initializable {

    @FXML
    private TextField tnom;
    @FXML
    private TextField tprenom;
    @FXML
    private Button ajouter;
    @FXML
    private TextField trank;
    @FXML
    private TextField tcategorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }   
    
    private void ajout(ActionEvent event)
   
    {
        CoachService cs = new CoachService();
        Coach c = new Coach(Integer.parseInt(trank.getText()),tnom.getText(),tprenom.getText(),tcategorie.getText());
        try {
            cs.ajouter(c);
        } catch (SQLException ex) {
            Logger.getLogger(TestFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        alert.setContentText("Personne ajout");
        alert.show();
        tnom.setText("");
        tprenom.setText("");
    }
    
    
}

//Date.valueof(DatePicker.getvalue());