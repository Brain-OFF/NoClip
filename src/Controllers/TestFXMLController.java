/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Coach;
import Services.CoachService;
import Utils.MyDB;
import java.awt.event.MouseEvent;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author xDrais
 */
public class TestFXMLController implements Initializable {


    @FXML
    private TextField tcoachnom;
    @FXML
    private TextField tcoachprenom;
    @FXML
    private TextField tcoachrank;
    @FXML
    private TextField tcoachcategorie;
    @FXML
    private Button addcoach;


    @FXML
    private Button deletecoach;
    @FXML
    private TableView<Coach> tableviewcoach;
    @FXML
    private TableColumn<Coach, Integer> idcoach;
    @FXML
    private TableColumn<Coach, String> firstnamecoach;
    @FXML
    private TableColumn<Coach, String> lastnamecoach;
    @FXML
    private TableColumn<Coach, String> categoriecoach;
    @FXML
    private TableColumn<Coach,Integer > rankcoach;
int id_selected;
    /**
     * Initializes the controller class.
     */

    
      ObservableList <Coach> list = FXCollections.observableArrayList();
    @FXML
    private Button modifiercoach;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
         try{
             
                
        Connection con = MyDB.getInstance().getCon();
        ResultSet rst = con.createStatement().executeQuery("SELECT * FROM `coach`");
        while(rst.next()){
        list.add(new Coach(rst.getInt(1),rst.getInt(4),rst.getString(2),rst.getString(3),rst.getString(5)));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(TestFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }           
            idcoach.setCellValueFactory(new PropertyValueFactory<Coach,Integer>("id"));
            firstnamecoach.setCellValueFactory(new PropertyValueFactory<Coach,String>("name"));
            lastnamecoach.setCellValueFactory(new PropertyValueFactory<Coach,String>("lastname"));   
            categoriecoach.setCellValueFactory(new PropertyValueFactory<Coach,String>("categorie"));
            rankcoach.setCellValueFactory(new PropertyValueFactory<Coach,Integer>("rank"));
            tableviewcoach.setItems(list);  
    }   
    

    @FXML
    private void ajouter(ActionEvent event) {
        CoachService cs = new CoachService();
        
        
//         String b = "^[0-9]+[0-9]$";
//                       Pattern emapat = Pattern.compile(b, Pattern.CASE_INSENSITIVE);
//                        Matcher m = emapat.matcher(tcoachrank.getText());
//                        boolean bb;
//                       bb=m.matches();
//                       System.out.println(bb);
                       
         if(tcoachnom.getText().length()!=0  && tcoachprenom.getText().length()!=0 && tcoachcategorie.getText().length()!=0 && tcoachrank.getText().length()!=0 && Integer.parseInt(tcoachrank.getText()) < 101 && Integer.parseInt(tcoachrank.getText()) > 0 ) {
        try {
            Coach Game = new Coach(Integer.parseInt(tcoachrank.getText()),tcoachnom.getText(),tcoachprenom.getText(),tcoachcategorie.getText());
            cs.ajouter(Game);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        
         alert.setContentText("coach ajoutée");
         alert.show();
          list.clear();
            try{
        Connection con = MyDB.getInstance().getCon();
        ResultSet rst = con.createStatement().executeQuery("SELECT * FROM `coach`");
        while(rst.next()){
        list.add(new Coach(rst.getInt(1),rst.getInt(4),rst.getString(2),rst.getString(3),rst.getString(5)));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(TestFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }   
              idcoach.setCellValueFactory(new PropertyValueFactory<Coach,Integer>("id"));
            firstnamecoach.setCellValueFactory(new PropertyValueFactory<Coach,String>("name"));
            lastnamecoach.setCellValueFactory(new PropertyValueFactory<Coach,String>("lastname"));   
            categoriecoach.setCellValueFactory(new PropertyValueFactory<Coach,String>("categorie"));
            rankcoach.setCellValueFactory(new PropertyValueFactory<Coach,Integer>("rank"));
            tableviewcoach.setItems(list);
    }
         else if(Integer.parseInt(tcoachrank.getText()) < 0 || Integer.parseInt(tcoachrank.getText()) > 100)
                 {
                     JOptionPane.showMessageDialog(null, "error le rank doit etre entre 0 et 100");
                     
                 }
       
             
         else
          JOptionPane.showMessageDialog(null, "error un champ est vide");
    }

    
    
    Connection con;
    Statement s;
    PreparedStatement pst;
    int myIndex;
    int id;
    
    
    
    
    @FXML
    private void Delete(javafx.event.ActionEvent event)throws IOException {
        
         CoachService cs = new CoachService();
         
         System.out.println(tableviewcoach.getSelectionModel().getSelectedItem().getId()+"");
        try {
            
           cs.Delete(tableviewcoach.getSelectionModel().getSelectedItem().getId());
           JOptionPane.showMessageDialog(null, "Data avec succée!");
            Coach selectedItem = tableviewcoach.getSelectionModel().getSelectedItem();
            tableviewcoach.getItems().remove(selectedItem);       
        } 
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
       }
    }
    @FXML
    private void modifierT(javafx.event.ActionEvent event) {
        int index = id_selected;
                        CoachService C = new CoachService();
                 
                     
           Coach c=new Coach(Integer.parseInt(tcoachrank.getText()),tcoachnom.getText().toString(),tcoachprenom.getText().toString(),tcoachcategorie.getText().toString());
                     if(tcoachnom.getText().length()!=0 && tcoachprenom.getText().length()!=0 && tcoachcategorie.getText().length()!=0 && Integer.parseInt(tcoachrank.getText()) > 0 && Integer.parseInt(tcoachrank.getText()) < 101 && tcoachrank.getText().length() !=0   ){
        try {
            C.modifier(c, index);
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(TestFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        
         alert.setContentText("coach modifier");
         alert.show();
         list.clear();
          try{
        Connection con = MyDB.getInstance().getCon();
        ResultSet rst = con.createStatement().executeQuery("SELECT * FROM `coach`");
        while(rst.next()){
        list.add(new Coach(rst.getInt(1),rst.getInt(4),rst.getString(2),rst.getString(3),rst.getString(5)));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(TestFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }   idcoach.setCellValueFactory(new PropertyValueFactory<Coach,Integer>("id"));
            firstnamecoach.setCellValueFactory(new PropertyValueFactory<Coach,String>("name"));
            lastnamecoach.setCellValueFactory(new PropertyValueFactory<Coach,String>("lastname"));   
            categoriecoach.setCellValueFactory(new PropertyValueFactory<Coach,String>("categorie"));
            rankcoach.setCellValueFactory(new PropertyValueFactory<Coach,Integer>("rank"));
            tableviewcoach.setItems(list);
    }
    else if(Integer.parseInt(tcoachrank.getText()) < 0 || Integer.parseInt(tcoachrank.getText()) > 100)
                 {
                     JOptionPane.showMessageDialog(null, "error le rank doit etre entre 0 et 100");
                     
                 }
         else
          JOptionPane.showMessageDialog(null, "error un champ est vide");
                         }
    
   
    private Stage stage;
 private Scene scene;
 private Parent root;
    Statement stm;
    private void Trait_inc(javafx.event.ActionEvent event) throws IOException {
        
 Parent root = FXMLLoader.load(getClass().getResource("/Gui/CoachFXML.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
        
        
    }

 
    @FXML
    private void tableview_clicked(javafx.scene.input.MouseEvent event) {
        System.out.println("Clicked on " + tableviewcoach.getSelectionModel().getSelectedItem().getId()); 
                deletecoach.setDisable(false);
                modifiercoach.setDisable(false);
                 int index = tableviewcoach.getSelectionModel().getSelectedItem().getId();
                        
             System.out.println(tableviewcoach.getSelectionModel().getSelectedItem().getId());

            tcoachnom.setText(tableviewcoach.getSelectionModel().getSelectedItem().getName());
            tcoachprenom.setText(tableviewcoach.getSelectionModel().getSelectedItem().getLastname());
            tcoachcategorie.setText(tableviewcoach.getSelectionModel().getSelectedItem().getCategorie());
          Integer tt=tableviewcoach.getSelectionModel().getSelectedItem().getRank();
          tcoachrank.setText(tt.toString());
            
           
            
            id_selected=tableviewcoach.getSelectionModel().getSelectedItem().getId();
    }

  
}
