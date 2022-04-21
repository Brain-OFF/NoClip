/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Categorie;
import Entities.News;
import Services.CategorieService;
import Services.NewsService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class CategorieController implements Initializable {

    @FXML
    private TextField tnom;
    @FXML
    private TableView<Categorie> listcat;
     @FXML
    private TextField tid;
 
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button dell;
    @FXML
    private TableColumn<Categorie, Integer> Tid;
    @FXML
    private TableColumn<Categorie, String> Tnom;
    @FXML
    private Label idd;
    @FXML
    private Label nomm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            CategorieService cs = new CategorieService();
            List CategorieList= cs.afficher();
            ObservableList list = FXCollections.observableArrayList(CategorieList);
           listcat.setItems(list);
            Tid.setCellValueFactory(new PropertyValueFactory<>("id"));
            Tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
           
        } catch (SQLException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
       
    
        
                   
            
            CategorieService ps = new CategorieService();
            
            
            Categorie p = new Categorie( tnom.getText());
            
            
         if (tnom.getText().isEmpty())
         {
           Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("verifier les champs");
            alert.show();
        }
         
         else {

            

    ps.ajouter(p);
    
    
    
    
    
    
    
    Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Succes");
    alert.setContentText("categorie ajoutee");
    alert.show();
    
    tnom.setText("");
    tid.setText("");
    
    
    
    

CategorieService cs = new CategorieService();
List  Categorie = cs.afficher();
ObservableList list = FXCollections.observableArrayList(Categorie);
listcat.setItems(list);
Tid.setCellValueFactory(new PropertyValueFactory<>("id"));
Tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         
                   }        
    }
        
        
        
        
    
       

  
        
        
        
        
        
        
        

    

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        
         
      CategorieService ps = new CategorieService();

       Categorie p = new Categorie(tnom.getText().toString());
       try {
        
            ps.modifier(p, listcat.getSelectionModel().getSelectedItem().getId());
            
            CategorieService cs = new CategorieService();
            List Categorie = cs.afficher();
        ObservableList list = FXCollections.observableArrayList(Categorie);
        listcat.setItems(list);
       
            Tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
          
       
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("Categorie modifier");
            alert.show();
            
            tnom.setText("");
          
               
            
            
        
         
        
    }
          catch (Exception e) {
JOptionPane.showMessageDialog(null, "error2 : "+e.getMessage());
        }
    }

    @FXML
    private void suprimer(ActionEvent event) {
        try{
            CategorieService cs = new CategorieService();
        cs.Delete(listcat.getSelectionModel().getSelectedItem().getId());
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"error : "+e.getMessage());
        }
        try{
            CategorieService cs = new CategorieService();
            List Categorie = cs.afficher();
        ObservableList list = FXCollections.observableArrayList(Categorie);
        listcat.setItems(list);
        Tid.setCellValueFactory(new PropertyValueFactory<>("id"));
            Tnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
           
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
    }

    @FXML
    private void on_clickeed(MouseEvent event) {
        
           update.setDisable(false);
        dell.setDisable(false);
       
            tnom.setText(listcat.getSelectionModel().getSelectedItem().getNom());
//            tdate.setDate(listn.getSelectionModel().getSelectedItem().getDate());
            tid.setText(listcat.getSelectionModel().getSelectedItem().getId()+"");
            
}
    
    
}
