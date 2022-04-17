/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Tournoi;
import Services.IService;
import Services.PersonneService;
import Utils.MyDB;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pidev3a37.fmx;

/**
 * FXML Controller class
 *
 * @author Taha
 */
public class FXMLTournoiController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private DatePicker dateT;
    @FXML
    private TextField cathegorie;
    @FXML
    private TextArea Discription;
    @FXML
    private Button AddT;
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
    @FXML
    private Button delete;
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
        }            idT.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id"));
            nomT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("name"));
            dateTo.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("dateT"));   
            cathT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("cathegorie"));
            discT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("discription"));
            tvTour.setItems(list);
    }   
    

    @FXML
    private void AddT(javafx.event.ActionEvent event) {
          PersonneService T = new PersonneService();
         Tournoi T1 = new Tournoi(nom.getText(),dateT.getValue().toString(),cathegorie.getText(),Discription.getText());
          try {
            T.ajouter(T1);
            tvTour.setItems(list);   
          } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        
         alert.setContentText("game ajoutée");
         alert.show();
         list.clear();
          try{
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `tournoi`");
        while(rs.next()){
        list.add(new Tournoi(rs.getInt(1),rs.getString("nom"),rs.getString("date"),rs.getString("cathegorie"),rs.getString("discription")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }            idT.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id"));
            nomT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("name"));
            dateTo.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("dateT"));   
            cathT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("cathegorie"));
            discT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("discription"));
            tvTour.setItems(list);

    }

    Connection con;
    Statement s;
    PreparedStatement pst;
    int myIndex;
    int id;

    @FXML
    private void deleteT(javafx.event.ActionEvent event)throws IOException {    
                PersonneService T = new PersonneService();


                             System.out.println(tvTour.getSelectionModel().getSelectedItem().getId()+"");
       try{
                                       T.Delete(tvTour.getSelectionModel().getSelectedItem().getId());
                                       
                                       
            JOptionPane.showMessageDialog(null, "Data telah terhapus");
 Tournoi selectedItem = tvTour.getSelectionModel().getSelectedItem();
    tvTour.getItems().remove(selectedItem);       
       } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error"+e.getMessage());

        }
    }

           
    
     }
    

