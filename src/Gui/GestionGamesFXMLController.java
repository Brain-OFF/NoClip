/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Games;
import Services.GamesService;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author month
 */
public class GestionGamesFXMLController implements Initializable {

    @FXML
    private TextField tnom;
    @FXML
    private TextField tdesc;
    @FXML
    private TextField tprix;
    @FXML
    private TextField timg;
    @FXML
    private Label lbaff;
   
    @FXML
    private Button listg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        GamesService gs = new GamesService();
        Games Game = new Games(Float.parseFloat(tprix.getText()),tnom.getText(),tdesc.getText(),timg.getText());
        try {
            gs.ajouter(Game);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        
         alert.setContentText("game ajoutée");
         alert.show();
    }

    @FXML
    private void afficher(ActionEvent event) {
        GamesService gs = new GamesService();
        try {
            lbaff.setText(gs.afficher().toString());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
    
}
