/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.News;
import Entities.Personne;
import Services.CategorieService;

import Services.NewsService;
import Services.PersonneService;
import Utils.MyDB;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class News2Controller implements Initializable {

    @FXML
    private TextField tnom;
    private TextField tprenom;
    @FXML
    private DatePicker tdate;
    @FXML
    private TextField tjeu;
    @FXML
    private TextField categorie_id;
    @FXML
    private TextField timed;
    private Label LListeP;
    @FXML
    private TableColumn<News, Integer> Tid;
    @FXML
    private TableColumn<News, Integer> Tcat;
    @FXML
    private TableColumn<News, String> Ttitre;
    @FXML
    private TableColumn<News, Date> Tdate;
    @FXML
    private TableColumn<News, String> Ttext;
    @FXML
    private TableColumn<News, String> Tjeu;
    
    private Button modifier;
    private Button suprimer;
    @FXML
    private TableView<News> listn;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private ChoiceBox<String> tchois;
    ObservableList<String> listl=FXCollections.observableArrayList();

    public News2Controller() {
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        
          try {
              Connection con = MyDB.getInstance().getCon();
              ResultSet rs1 = con.createStatement().executeQuery("SELECT nom FROM categorie");
        ResultSet rs3 = con.createStatement().executeQuery("SELECT nom,id FROM categorie");
;
while(rs1.next()){
        listl.add(rs1.getString("nom"));
        }
           ObservableList<String> options = 
    listl;
        tchois.setValue("");
                tchois.setItems(options);
            NewsService cs = new NewsService();
            List NewsList= cs.afficher();
            ObservableList list = FXCollections.observableArrayList(NewsList);
           listn.setItems(list);
            Tid.setCellValueFactory(new PropertyValueFactory<>("id"));
            Tcat.setCellValueFactory(new PropertyValueFactory<>("Categorie_id"));
            Ttitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            Tdate.setCellValueFactory(new PropertyValueFactory<>("Date"));
            Ttext.setCellValueFactory(new PropertyValueFactory<>("Text"));
            Tjeu.setCellValueFactory(new PropertyValueFactory<>("jeu"));
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(News2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    
        @FXML
    private void table_view_clicked(MouseEvent event) {
        update.setDisable(false);
        delete.setDisable(false);
       
            tnom.setText(listn.getSelectionModel().getSelectedItem().getTitre());
//            tdate.setDate(listn.getSelectionModel().getSelectedItem().getDate());
            timed.setText(listn.getSelectionModel().getSelectedItem().getText()+"");
            tjeu.setText(listn.getSelectionModel().getSelectedItem().getJeu());
//            tfprix.setText(listeq.getSelectionModel().getSelectedItem().getPrix_e()+"");
//            tfdescription.setText(listeq.getSelectionModel().getSelectedItem().getDescription_e()+"");
//       
       
          
       
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        if (tnom.getText().isEmpty())
         {
           Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("verifier les champs");
            alert.show();
        }
        else{
       
        int k=0;
         int c=0;
      NewsService ps = new NewsService();
      try {
         
  Connection con = MyDB.getInstance().getCon();
        
        ResultSet rs1 = con.createStatement().executeQuery("Select * from categorie WHERE nom = '"+ tchois.getValue() +"'");
       
         while(rs1.next()){  c=  rs1.getInt(1);}

     
    } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }

       
            
        News p = new News(c,Date.valueOf(tdate.getValue()),tnom.getText(), timed.getText(), tjeu.getText());
       
//         else{
        
//         if (tnom.equals("")&&tnom.equals("   ")&&tdate.equals("")&&tdate.equals("   ")&&timed.equals("")&&timed.equals("   ")&&tjeu.equals("")&&tjeu.equals("   ")) 
//         {
//           Alert alert= new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("ERROR");
//            alert.setContentText("verifier les champs");
//            alert.show();
//        } 
//         else {
        
        
      
            ps.ajouter(p);
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("News ajoutee");
            alert.show();
            
            tnom.setText("");
            timed.setText("");
             tjeu.setText("");
             categorie_id.setText("");
             

            
     
      NewsService cs = new NewsService();
List  News = cs.afficher();
ObservableList list = FXCollections.observableArrayList(News);
listn.setItems(list);
Tid.setCellValueFactory(new PropertyValueFactory<>("id"));
Ttitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
Tdate.setCellValueFactory(new PropertyValueFactory<>("date"));
Ttext.setCellValueFactory(new PropertyValueFactory<>("text"));
Tjeu.setCellValueFactory(new PropertyValueFactory<>("text"));
         
         
                   }
        
        }
         
    

   
   

    @FXML
    private void modifier(ActionEvent event) {
           NewsService ps = new NewsService();

       News p = new News(tnom.getText().toString(),Date.valueOf(tdate.getValue()) ,timed.getText().toString(),tjeu.getText().toString());
        try {
            ps.modifier(p, listn.getSelectionModel().getSelectedItem().getId());
            try{
            NewsService cs = new NewsService();
            List News = cs.afficher();
        ObservableList list = FXCollections.observableArrayList(News);
        listn.setItems(list);
        Tid.setCellValueFactory(new PropertyValueFactory<>("id"));
            Tcat.setCellValueFactory(new PropertyValueFactory<>("Categorie_id"));
            Ttitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            Tdate.setCellValueFactory(new PropertyValueFactory<>("Date"));
            Ttext.setCellValueFactory(new PropertyValueFactory<>("Text"));
            Tjeu.setCellValueFactory(new PropertyValueFactory<>("jeu"));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("News modifier");
            alert.show();
            
             tnom.setText("");
            timed.setText("");
             tjeu.setText("");
             categorie_id.setText("");
             
            
            
        } catch (Exception e) {
JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
        
        
        
        
        
        
    }

    @FXML
    private void suprimer(ActionEvent event) {
        try{
            NewsService cs = new NewsService();
        cs.Delete(listn.getSelectionModel().getSelectedItem().getId());
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"error : "+e.getMessage());
        }
        try{
            NewsService cs = new NewsService();
            List News = cs.afficher();
        ObservableList list = FXCollections.observableArrayList(News);
        listn.setItems(list);
        Tid.setCellValueFactory(new PropertyValueFactory<>("id"));
            Tcat.setCellValueFactory(new PropertyValueFactory<>("Categorie_id"));
            Ttitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
            Tdate.setCellValueFactory(new PropertyValueFactory<>("Date"));
            Ttext.setCellValueFactory(new PropertyValueFactory<>("Text"));
            Tjeu.setCellValueFactory(new PropertyValueFactory<>("jeu"));
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
        
        
    }


    
    }
    

