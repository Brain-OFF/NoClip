/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Tournoi;
import Utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Taha
 */
public class TdeleteController implements Initializable {

   @FXML
    private TableColumn<Tournoi, Integer> idT;
    @FXML
    private TableColumn<Tournoi, String> nomT;
    @FXML
    private TableColumn<Tournoi, String> cathT;
    @FXML
    private TableColumn<Tournoi, String> discT;
    @FXML
    private TableView<Tournoi> tvTour;
    @FXML
    private TableColumn<Tournoi, String> dateTo;

    /**
     * Initializes the controller class.
     */
        ObservableList <Tournoi> list = FXCollections.observableArrayList();        

        ObservableList<Integer> option = FXCollections.observableArrayList();
    @FXML
    private ComboBox iddelete = new ComboBox(option);
    @FXML
    private Button actionD;
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        try{
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `tournoi`");
        while(rs.next()){
        list.add(new Tournoi(rs.getInt(1),rs.getString("nom"),rs.getString("date"),rs.getString("cathegorie"),rs.getString("discription")));
        }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }           
            idT.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id"));
            nomT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("name"));
            dateTo.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("dateT"));   
            cathT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("cathegorie"));
            discT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("discription"));
            tvTour.setItems(list);
    }   
  
    public void fillcombo(){
    }
    
    @FXML
    private void clean(ActionEvent event) {
        
    }
    
}
