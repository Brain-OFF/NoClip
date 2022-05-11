/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Categorie;
import Entities.News;
import Entities.Personne;
import Services.CategorieService;

import Services.NewsService;
import Utils.MyDB;
import Utils.SendEmail;
//import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import java.awt.Insets;
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
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.xml.soap.Node;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class News2Controller implements Initializable {
     ObservableList<News> list = FXCollections.observableArrayList();
   private Parent root ;
   private Stage stage ;
    private Scene scene ;
   

    @FXML
    private TextField tnom;
    private TextField tprenom;
    @FXML
    private DatePicker tdate;
    @FXML
    private TextField tjeu;
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
//    @FXML
//    private Rating rate;
    @FXML
    private Label msg;
    private GridPane grid;
  
//    public void afficher2()
//    {
//         try {
//              Connection con = MyDB.getInstance().getCon();
//              ResultSet rs1 = con.createStatement().executeQuery("SELECT nom FROM categorie");
//        ResultSet rs3 = con.createStatement().executeQuery("SELECT nom,id FROM categorie");
//;
//while(rs1.next()){
//        listl.add(rs1.getString("nom"));
//        }
//           ObservableList<String> options = 
//    listl;
//        tchois.setValue("");
//                tchois.setItems(options);
//            NewsService cs = new NewsService();
//            List NewsList= cs.afficher();
//            ObservableList list = FXCollections.observableArrayList(NewsList);
//           listn.setItems(list);
//            Tid.setCellValueFactory(new PropertyValueFactory<>("id"));
//            Tcat.setCellValueFactory(new PropertyValueFactory<>("Categorie_id"));
//            Ttitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
//            Tdate.setCellValueFactory(new PropertyValueFactory<>("Date"));
//            Ttext.setCellValueFactory(new PropertyValueFactory<>("Text"));
//            Tjeu.setCellValueFactory(new PropertyValueFactory<>("jeu"));
//            // TODO
//        } catch (SQLException ex) {
//            Logger.getLogger(News2Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public News2Controller() {
        
        
        
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //
//          try {
//              Connection con = MyDB.getInstance().getCon();
//              ResultSet rs1 = con.createStatement().executeQuery("SELECT nom FROM categorie");
//        ResultSet rs3 = con.createStatement().executeQuery("SELECT nom,id FROM categorie");
//;
//while(rs1.next()){
//        listl.add(rs1.getString("nom"));
//        }
//           ObservableList<String> options = 
//    listl;
//        tchois.setValue("");
//                tchois.setItems(options);

// rate.ratingProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//            msg.setText("Rating :- "+newValue);
//        });
        
    
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
         afficherCommentaireDesc();
// TODO

// TODO
        } catch (SQLException ex) {
            Logger.getLogger(News2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    private void afficherCommentaireDesc() {
       CategorieService cs = new CategorieService();
        List<Categorie> fcts = cs.listerCat();
        ObservableList<String> descComm = FXCollections.observableArrayList();
        for(int i = 0; i<fcts.size();i++){
            descComm.add(fcts.get(i).getNom());
        }
       tchois.setItems(descComm); //pour remplir le combo box   
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
//        grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
              

               
       
          
       
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
         NewsService ps = new NewsService();
         CategorieService ks = new CategorieService();
         

        News p = new News( ks.getIdCommentaire(tchois.getValue()) ,tnom.getText(),Date.valueOf(tdate.getValue()),timed.getText(),tjeu.getText());
        try {
            
            ps.ajouter(p);
            SendEmail email = new SendEmail();

            email.mail("nouha.benslimen@esprit.tn");
            try{
             
            NewsService cs = new NewsService();
            List News = cs.afficher();
        ObservableList list = FXCollections.observableArrayList(News);
        listn.setItems(list);
        Tid.setCellValueFactory(new PropertyValueFactory<>("id"));
        Ttitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        Tdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        Tjeu.setCellValueFactory(new PropertyValueFactory<>("jeu"));
        
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setContentText("News ajoutee");
            alert.show();
            
            tnom.setText("");
            tjeu.setText("");
            timed.setText("");
            
          
            
            
        } catch (Exception e) {
JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
        
//       
//        
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

    @FXML
    private void Search(KeyEvent event) {
         
        
        
        
        
    }

    @FXML
    private void meteo(MouseEvent event) throws IOException {
StackPane secondaryLayout = new StackPane();
                           Parent bans = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/meteo.fxml"));
				Scene secondScene = new Scene(bans);
                                secondScene.getStylesheets().add("/dark-theme.css");
				Stage newWindow = new Stage();
				newWindow.setTitle("Meteo");
				newWindow.setScene(secondScene);
				newWindow.show();
    }

    @FXML
    private void categorie(MouseEvent event) {
    }

    @FXML
    private void statestique(MouseEvent event) {
    }
    
    
    }
    

