/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Ban;
import Entities.History;
import Services.ServiceBan;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author WALID
 */
public class HistoryController implements Initializable {

    @FXML
    private TableColumn<History, String> action;
    @FXML
    private TableColumn<History, String> user_banned;
    @FXML
    private TableColumn<History, String> date_deb;
    @FXML
    private TableColumn<History, String> date_fin;
    ServiceBan Sb=new ServiceBan();
    @FXML
    private TableView<History> table;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<History> listban;
        try {
            listban = FXCollections.observableArrayList(Sb.gethistorytable());
       
   
               action.setCellValueFactory(new PropertyValueFactory<History,String>("type"));
            user_banned.setCellValueFactory(new PropertyValueFactory<History,String>("id_user"));
            date_deb.setCellValueFactory(new PropertyValueFactory<History,String>("Date_debut"));   
            date_fin.setCellValueFactory(new PropertyValueFactory<History,String>("Date"));
            table.setItems(listban);
             } catch (SQLException ex) {
            Logger.getLogger(HistoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
