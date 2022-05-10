/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Games;
import Entities.Panier;
import Entities.games_user;
import Services.GamesService;
import Tools.Constants;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author month
 */

public class AffichepanierController implements Initializable {
    private VBox mainVBox;
     GamesService ps=new GamesService();
    Entities.loggedUser holder = Entities.loggedUser.get_instace();
     ObservableList <Panier> list = FXCollections.observableArrayList();
    @FXML
    private Text topText;
    @FXML
    private VBox mainVBox1;
    @FXML
    private Text total;
    @FXML
    private Button passcom;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<Games> listProd = ps.ShowProduit();
        Collections.reverse(listProd);
          Connection con = MyDB.getInstance().getCon();
         boolean exist=false;
        if (!listProd.isEmpty()) {
            for (Games abo : listProd) {
                 try {
                  exist=false;   
            
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `cart`");
     
                 
            
            while(rs.next())
            {
                
                list.add(new Panier(rs.getInt(1),rs.getInt(2)));
                
                   for(Panier r : list) {
                       
            if (r.getIdProduit()==abo.getId() )
            {exist=true;}
//            
            
        }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelpController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 if(exist==true){
                mainVBox1.getChildren().add(makeProdModel(abo));}
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox1.getChildren().add(stackPane);
        }
    }    
    public Parent makeProdModel( Games abo )  {
         
        Connection con = MyDB.getInstance().getCon();
        
        Parent parent = null;
//////        float k=0;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_MODEL_PROD)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            
            ((Text) innerContainer.lookup("#createdAtText")).setText("Nom Produit : " + abo.getName());
            ((Text) innerContainer.lookup("#desctxt")).setText("Description : " + abo.getDescreption());
            
            
            ((Text) innerContainer.lookup("#prixtxt")).setText("prix : " + abo.getPrix());
            ((Text) innerContainer.lookup("#idgames")).setText( abo.getId()+"");
      
           

            
            ((Pane) innerContainer.lookup("#supppane")).setVisible(false);
            
              
           
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    private void refresh(ActionEvent event) {
                 Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
         try {
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/Affichepanier.fxml"));
                    System.out.println("user being logged :"+holder.getUser());
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    scene.getStylesheets().add("/dark-theme.css");
                 
                    stage.show();
                    } catch (IOException e) {
                           System.out.println(e);;
                    }
    }

    @FXML
    private void passercommande(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
         try {
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/form.fxml"));
                    System.out.println("user being logged :"+holder.getUser());
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    scene.getStylesheets().add("/dark-theme.css");
                 
                    stage.show();
                    } catch (IOException e) {
                           System.out.println(e);;
                    }
    }

}
