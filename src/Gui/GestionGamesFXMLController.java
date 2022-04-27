/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Games;
import Services.GamesService;
import Utils.MyDB;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

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
    private AnchorPane scaff;
    @FXML
    private TableColumn<Games, Integer> idg;
    @FXML
    private TableColumn<Games, String> nomg;
    @FXML
    private TableColumn<Games, Float> prixg;
    @FXML
    private TableColumn<Games, String> descg;
    ObservableList <Games> list = FXCollections.observableArrayList();
     ObservableList <String> list1 = FXCollections.observableArrayList();
      ObservableList <Integer> list2 = FXCollections.observableArrayList();
    @FXML
    private TableView<Games> tvgames;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    int id_selected;
    @FXML
    private ComboBox<String> categorie;
    @FXML
    private TextField ftr;
    @FXML
    private Button recherchelIv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
             
              
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `Games`");
        ResultSet rs1 = con.createStatement().executeQuery("SELECT nom FROM `Gamescat`");
        ResultSet rs3 = con.createStatement().executeQuery("SELECT nom,id FROM `Gamescat`");
        while(rs.next()){
        list.add(new Games(rs.getInt(1),rs.getFloat("prix"),rs.getString("name"),rs.getString("descreption"),rs.getString("img")));
        }
         while(rs1.next()){
        list1.add(rs1.getString("nom"));
        }
           ObservableList<String> options = 
    list1;
        categorie.setValue("");
                categorie.setItems(options);
        } catch (SQLException ex) {
            Logger.getLogger(GestionGamesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }           
            idg.setCellValueFactory(new PropertyValueFactory<Games,Integer>("id"));
            nomg.setCellValueFactory(new PropertyValueFactory<Games,String>("name"));
            descg.setCellValueFactory(new PropertyValueFactory<Games,String>("descreption"));   
            prixg.setCellValueFactory(new PropertyValueFactory<Games,Float>("prix"));
            
            tvgames.setItems(list);
}    

    @FXML
    private void ajouter(ActionEvent event) {
        int a=0;
        GamesService gs = new GamesService();
        String g=tnom.getText();
        //Games Game = new Games(Float.parseFloat(tprix.getText()),tnom.getText(),tdesc.getText(),timg.getText());
        if(tnom.getText().length()!=0 && tdesc.getText().length()!=0 && tprix.getText().length()!=0  && timg.getText().length()!=0){
            Connection conxi = MyDB.getInstance().getCon();
            try {
                
                ResultSet rs = conxi.createStatement().executeQuery("SELECT name FROM `Games`");
                 while(rs.next()){
        if(rs.getString("name").equals(g)){ a=1;}
        }
            } catch (SQLException ex) {
                Logger.getLogger(GestionGamesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        if(a ==0){
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
            list.clear();
          try{
                Connection conx = MyDB.getInstance().getCon();
                String l=categorie.getValue().toString();
//             ResultSet rs3 = con.createStatement().executeQuery("Select id from Gamescat WHERE nom = "+l);
//             int d=rs3.getInt("id");
//              System.out.println(d);
              System.out.println(categorie.getValue());
            gs.ajouterjoint(categorie.getValue());
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `Games`");
        while(rs.next()){
        list.add(new Games(rs.getInt(1),rs.getFloat("prix"),rs.getString("name"),rs.getString("descreption"),rs.getString("img")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(GestionGamesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }          
          idg.setCellValueFactory(new PropertyValueFactory<Games,Integer>("id"));
            nomg.setCellValueFactory(new PropertyValueFactory<Games,String>("name"));
            descg.setCellValueFactory(new PropertyValueFactory<Games,String>("descreption"));   
            prixg.setCellValueFactory(new PropertyValueFactory<Games,Float>("prix"));
            tvgames.setItems(list);
    
     }else  {JOptionPane.showMessageDialog(null, "erreur nom deja existant");}
        }
        else{
          JOptionPane.showMessageDialog(null, "error un champ est vide");}
         
    }
     @FXML
    private void chercherUser(KeyEvent event) {
     GamesService rs = new GamesService();
      List<Games> listuser;
        String tchoix=ftr.getText();
        try{
            int nchoix = Integer.parseInt(tchoix);
            listuser = rs.cherchejeu(nchoix);
        } catch (NumberFormatException e) {
            listuser = rs.cherchejeu(tchoix);
        }
          ObservableList<Games> items =FXCollections.observableArrayList();
       for(Games r : listuser) {
            
            items.add(r);
        }
                
            idg.setCellValueFactory(new PropertyValueFactory<Games,Integer>("id"));
            nomg.setCellValueFactory(new PropertyValueFactory<Games,String>("name"));
            descg.setCellValueFactory(new PropertyValueFactory<Games,String>("descreption"));   
            prixg.setCellValueFactory(new PropertyValueFactory<Games,Float>("prix"));
            
        tvgames.setItems(items);
    }
    @FXML
    private void tableview_clicked(MouseEvent event) {
                 
                delete.setDisable(false);
                update.setDisable(false);
         int index = tvgames.getSelectionModel().getSelectedItem().getId();
            tnom.setText(tvgames.getSelectionModel().getSelectedItem().getName());
            tdesc.setText(tvgames.getSelectionModel().getSelectedItem().getDescreption());
            tprix.setText(tvgames.getSelectionModel().getSelectedItem().getPrix()+"");
            timg.setText(tvgames.getSelectionModel().getSelectedItem().getImg());
             id_selected=tvgames.getSelectionModel().getSelectedItem().getId();
    }
    private Stage stage;
 private Scene scene;
 private Parent root;
    Statement stm;
    private void Trait_inc(javafx.event.ActionEvent event) throws IOException {
        
 Parent root = FXMLLoader.load(getClass().getResource("/Gui/FXMLIncription.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
        
        
    }
    Connection con;
    Statement s;
    PreparedStatement pst;
    int myIndex;
    int id;
       @FXML
    private void deleteT(javafx.event.ActionEvent event)throws IOException {    
                GamesService T = new GamesService();


                             System.out.println(tvgames.getSelectionModel().getSelectedItem().getId()+"");
       try{
                                       T.Delete(tvgames.getSelectionModel().getSelectedItem().getId());
                                       
                                       
            JOptionPane.showMessageDialog(null, "Data avec succée!");
            Games selectedItem = tvgames.getSelectionModel().getSelectedItem();
            tvgames.getItems().remove(selectedItem);       
       } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error"+e.getMessage());

        }
    }

    @FXML
    private void modifierT(javafx.event.ActionEvent event) {
        int index = id_selected;
                        GamesService T = new GamesService();
           Games t=new Games(Float.parseFloat(tprix.getText()),tnom.getText(),tdesc.getText(),timg.getText());
                     if(nomg.getText().length()!=0 && descg.getText().length()!=0 ){
        try {
            T.modifier(t, index);
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionGamesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        
         alert.setContentText("Games modifier");
         alert.show();
         list.clear();
          try{
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `Games`");
        while(rs.next()){
        list.add(new Games(rs.getInt(1),rs.getFloat("prix"),rs.getString("name"),rs.getString("descreption"),rs.getString("img")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(GestionGamesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }          
          idg.setCellValueFactory(new PropertyValueFactory<Games,Integer>("id"));
            nomg.setCellValueFactory(new PropertyValueFactory<Games,String>("name"));
            descg.setCellValueFactory(new PropertyValueFactory<Games,String>("descreption"));   
            prixg.setCellValueFactory(new PropertyValueFactory<Games,Float>("prix"));
            tvgames.setItems(list);
    }
    else
                                     JOptionPane.showMessageDialog(null, "error un champ est vide");

                         }
    
}
