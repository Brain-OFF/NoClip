/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author month
 */
public class GestionpromosController implements Initializable {

    @FXML
    private AnchorPane scaff;
    @FXML
    private TextField tnom;
    @FXML
    private TableView<?> tvgames;
    @FXML
    private TableColumn<?, ?> idg;
    @FXML
    private TableColumn<?, ?> nomg;
    @FXML
    private TableColumn<?, ?> descg;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private DatePicker date;
    @FXML
    private TextField pourc;
    @FXML
    private TableColumn<?, ?> nomg1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterc(ActionEvent event) {
    }

    @FXML
    private void tableview_clickedc(MouseEvent event) {
    }

    @FXML
    private void modifierc(ActionEvent event) {
    }

    @FXML
    private void deletec(ActionEvent event) {
    }
    
}
