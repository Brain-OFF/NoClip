/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;



import controllers.*;
import pidev3a37.test1FXMain;
import Entities.Commande;
import Services.GamesService;
import Services.ServiceComImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author MSI
 */
public class IndexController implements Initializable {

    @FXML
    private TableView<Commande> commandes;
    @FXML
    private TableColumn<Commande, String> nom;
    
     @FXML
    private TableColumn<Commande, String> prenom;
     @FXML
    private TableColumn<Commande, String> adressecomplet;
     @FXML
    private TableColumn<Commande, String> telephone;
     @FXML
    private TableColumn<Commande, String> email;
    @FXML
    private TextField tfrecherche;
    private Commande selectedCommande;
    @FXML
    private Button EventsBack;
    @FXML
    private Button CatComEventsBack;
    @FXML
    private Button Commandes;
    @FXML
    private Button CommandeProduct;
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     GamesService sr = new GamesService();
        try {
            sr.deleteCartf();
        } catch (SQLException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServiceComImpl service = new ServiceComImpl();
        ObservableList OL = FXCollections.observableArrayList(service.afficher());
        commandes.setItems(OL);
      
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adressecomplet.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("numtelephone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
    }    

    @FXML
    private void SelectItem(MouseEvent event) {
                selectedCommande = commandes.getSelectionModel().getSelectedItem();
 System.out.println("Clicked : "+commandes.getSelectionModel().getSelectedItem().getId());         
            nom.setText(commandes.getSelectionModel().getSelectedItem().getNom());
            prenom.setText(commandes.getSelectionModel().getSelectedItem().getPrenom()+"");
            adressecomplet.setText(commandes.getSelectionModel().getSelectedItem().getAdresse());
            telephone.setText(commandes.getSelectionModel().getSelectedItem().getNumtelephone());
            email.setText(commandes.getSelectionModel().getSelectedItem().getEmail());
    }


    @FXML
    private void NewCommande(ActionEvent event) throws IOException {
         formController a = new formController();
        formController.setCom(null);
        Parent root = FXMLLoader.load(getClass().getResource("/Gui/form.fxml"));
            Scene scene = new Scene(root);
            test1FXMain.pStage.setScene(scene);
            test1FXMain.pStage.show();
    }

   
  /*  private void Delete(ActionEvent event) {
        int reply = JOptionPane.showConfirmDialog(null, "Do you want delete this item?", "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            ServiceComImpl service = new ServiceComImpl();
            service.supprimer(selectedCommande);
            this.initialize(null, null);
            JOptionPane.showMessageDialog(null, "Commande supprim?? !");
        }
    }*/
    @FXML
   private void Delete(ActionEvent event) throws IOException{
          try{
           ServiceComImpl cs = new ServiceComImpl();
        cs.Delete(commandes.getSelectionModel().getSelectedItem().getId());
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"error : "+e.getMessage());
        }
        try{
           ServiceComImpl cs = new ServiceComImpl();
            List Commande = cs.afficher();
        ObservableList list = FXCollections.observableArrayList(Commande);
        commandes.setItems(list);
       nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adressecomplet.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("numtelephone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        }   
        
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "error : "+e.getMessage());
        }
            
            
    }



    @FXML
    private void Edit(ActionEvent event) throws IOException {
      //    if (selectedCommande.getId() != 0) {
            formController af = new formController();
           
            formController.setCom(selectedCommande);
            Parent root = FXMLLoader.load(getClass().getResource("Gui/form.fxml"));
            Scene scene = new Scene(root);
            test1FXMain.pStage.setScene(scene);
            test1FXMain.pStage.show();
        //}
    }

    @FXML
    private void Search(KeyEvent event) {
         String rech=tfrecherche.getText();
        
        ServiceComImpl service = new ServiceComImpl();
        ObservableList OL = FXCollections.observableArrayList(service.cherchecom(rech));
        commandes.setItems(OL);
    }

  /*  @FXML
    private void showProductCategories(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/productcategory/index.fxml"));
            Scene scene = new Scene(root);
            MainGUI.pStage.setScene(scene);
            MainGUI.pStage.show();
    }*/

    @FXML
    private void showCommande(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Gui/index.fxml"));
            Scene scene = new Scene(root);
            test1FXMain.pStage.setScene(scene);
            test1FXMain.pStage.show();
    }

    @FXML
    private void ShowEvents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/event/EventsBack.fxml"));
            Scene scene = new Scene(root);
            test1FXMain.pStage.setScene(scene);
            test1FXMain.pStage.show();
    }

    @FXML
    private void ShowCategoryEvent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/event/Category Event Back.fxml"));
            Scene scene = new Scene(root);
            test1FXMain.pStage.setScene(scene);
            test1FXMain.pStage.show();
    }
    
}
