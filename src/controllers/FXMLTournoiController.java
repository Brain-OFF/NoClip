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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pidev3a37.fmx;

/**
 * FXML Controller class
 *
 * @author Taha
 */
public class FXMLTournoiController implements Initializable {
    ObservableList<Tournoi> data = FXCollections.observableArrayList();


    @FXML
    private TextField nom;
    @FXML
    private DatePicker dateT;
    ObservableList<String> options = 
    FXCollections.observableArrayList(
        "RPG",
        "MMORPG",
        "MOBA",
         "Battle Royale",
         "Beat Them All",
         "survival Horror",
         "RTS"
    );
    @FXML
    private ComboBox<String> cathegorie;
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
    int id_selected;

    /**
     * Initializes the controller class.
     */
    ObservableList <Tournoi> list = FXCollections.observableArrayList();
    @FXML
    private Button delete;
    @FXML
    private Button updateT;
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        try{
                cathegorie.setItems(options);

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
   
    @FXML
    private void AddT(javafx.event.ActionEvent event) {
          PersonneService T = new PersonneService();
         Tournoi T1 = new Tournoi(nom.getText(),dateT.getValue().toString(),cathegorie.getValue().toString(),Discription.getText());
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

    @FXML
    private void modifierT(javafx.event.ActionEvent event) {
        int index = id_selected;
                        PersonneService T = new PersonneService();

           Tournoi t=new Tournoi(nom.getText().toString(),dateT.getValue().toString(),cathegorie.getValue().toString(),Discription.getText().toString());
        try {
            T.modifier(t, index);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    /*public void clickItem(MouseEvent event) {
    tvTour.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("Clicked on " + (tvTour.getSelectionModel().getSelectedCells().get(0)).getColumn());        
        }
    });
}*/
    


    @FXML
    private void tableview_clicked(MouseEvent event) {
                System.out.println("Clicked on " + tvTour.getSelectionModel().getSelectedItem().getId()); 
                delete.setDisable(false);
                updateT.setDisable(false);
                 int index = tvTour.getSelectionModel().getSelectedItem().getId();
                        PersonneService T = new PersonneService();
             System.out.println(tvTour.getSelectionModel().getSelectedItem().getId());

            nom.setText(tvTour.getSelectionModel().getSelectedItem().getName());
            Discription.setText(tvTour.getSelectionModel().getSelectedItem().getDiscription());
            cathegorie.setValue(tvTour.getSelectionModel().getSelectedItem().getCathegorie());
            id_selected=tvTour.getSelectionModel().getSelectedItem().getId();
    }
   
     }
    

