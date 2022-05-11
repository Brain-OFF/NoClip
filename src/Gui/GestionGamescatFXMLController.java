/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Games;
import Entities.Gamescat;
import Services.GamesService;
import Services.GamescatService;
import Utils.MyDB;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author month
 */
public class GestionGamescatFXMLController implements Initializable {

    @FXML
    private TextField tnom;
    @FXML
    private TextField tdesc;
   
   
    
    @FXML
    private AnchorPane scaff;
    @FXML
    private TableColumn<Gamescat, Integer> idg;
    @FXML
    private TableColumn<Gamescat, String> nomg;

    @FXML
    private TableColumn<Gamescat, String> descg;
    ObservableList <Gamescat> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Gamescat> tvgames;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    int id_selected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
              
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `Gamescat`");
        while(rs.next()){
        list.add(new Gamescat(rs.getInt(1),rs.getString("nom"),rs.getString("description")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(GestionGamescatFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }           
            idg.setCellValueFactory(new PropertyValueFactory<Gamescat,Integer>("id"));
            nomg.setCellValueFactory(new PropertyValueFactory<Gamescat,String>("nom"));
            descg.setCellValueFactory(new PropertyValueFactory<Gamescat,String>("description"));   
            
            
            tvgames.setItems(list);
}    

    @FXML
    private void ajouterc(ActionEvent event) {
         int a=0;
         String g=tnom.getText();
        GamescatService gs = new GamescatService();
        if(tnom.getText().length()!=0 && tdesc.getText().length()!=0  ){
            Connection conxi = MyDB.getInstance().getCon();
            try {
                
                ResultSet rs = conxi.createStatement().executeQuery("SELECT nom FROM `Gamescat`");
                 while(rs.next()){
        if(rs.getString("nom").equals(g)){ a=1;}
        }
            } catch (SQLException ex) {
                Logger.getLogger(GestionGamesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        if(a ==0){
        Gamescat Game = new Gamescat(tnom.getText(),tdesc.getText());
        try {
            gs.ajouter(Game);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        
         alert.setContentText("gamecat ajoutée");
         alert.show();
           list.clear();
          try{
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `Gamescat`");
        while(rs.next()){
        list.add(new Gamescat(rs.getInt(1),rs.getString("nom"),rs.getString("description")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(GestionGamescatFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }          
          idg.setCellValueFactory(new PropertyValueFactory<Gamescat,Integer>("id"));
            nomg.setCellValueFactory(new PropertyValueFactory<Gamescat,String>("nom"));
            descg.setCellValueFactory(new PropertyValueFactory<Gamescat,String>("description"));   
            
            tvgames.setItems(list);
        }else  {JOptionPane.showMessageDialog(null, "erreur nom deja existant");}
        }
        else{
          JOptionPane.showMessageDialog(null, "error un champ est vide");}
    }
    @FXML
    private void tableview_clickedc(MouseEvent event) {
                 
                delete.setDisable(false);
                update.setDisable(false);
         int index = tvgames.getSelectionModel().getSelectedItem().getId();
            tnom.setText(tvgames.getSelectionModel().getSelectedItem().getNom());
            tdesc.setText(tvgames.getSelectionModel().getSelectedItem().getDescription());
            
             id_selected=tvgames.getSelectionModel().getSelectedItem().getId();
    }
    private Stage stage;
 private Scene scene;
 private Parent root;
    Statement stm;
   
    Connection con;
    Statement s;
    PreparedStatement pst;
    int myIndex;
    int id;
       @FXML
    private void deletec(javafx.event.ActionEvent event)throws IOException {    
                GamesService T = new GamesService();


                             System.out.println(tvgames.getSelectionModel().getSelectedItem().getId()+"");
       try{
                                       T.Delete(tvgames.getSelectionModel().getSelectedItem().getId());
                                       
                                       
            JOptionPane.showMessageDialog(null, "Data avec succée!");
            Gamescat selectedItem = tvgames.getSelectionModel().getSelectedItem();
            tvgames.getItems().remove(selectedItem);       
       } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error"+e.getMessage());

        }
    }

    @FXML
    private void modifierc(javafx.event.ActionEvent event) {
        int index = id_selected;
                        GamescatService T = new GamescatService();
           Gamescat t=new Gamescat(tnom.getText(),tdesc.getText());
                     if(nomg.getText().length()!=0 && descg.getText().length()!=0 ){
        try {
            T.modifier(t, index);
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionGamescatFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        
         alert.setContentText("Games modifier");
         alert.show();
         list.clear();
          try{
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `Gamescat`");
        while(rs.next()){
        list.add(new Gamescat(rs.getInt(1),rs.getString("nom"),rs.getString("description")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(GestionGamescatFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }          
          idg.setCellValueFactory(new PropertyValueFactory<Gamescat,Integer>("id"));
            nomg.setCellValueFactory(new PropertyValueFactory<Gamescat,String>("nom"));
            descg.setCellValueFactory(new PropertyValueFactory<Gamescat,String>("description"));   
            
            tvgames.setItems(list);
    }
    else
                                     JOptionPane.showMessageDialog(null, "error un champ est vide");

                         }
    
}
