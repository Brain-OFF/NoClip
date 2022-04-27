/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Games;
import Entities.Promos;
import Services.GamesService;
import Tools.Constants;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author month
 */
public class AfficheGamesController implements Initializable {

    private ListView<Games> txtlistusers;
    public static Games currentProd;
    private AnchorPane mainPain;
    @FXML
    private Text topText;
    @FXML
    private VBox mainVBox;
    GamesService ps=new GamesService();
    @FXML
    private TextField ftr;
    @FXML
    private Button recherchelIv;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Games> listProd = ps.ShowProduit();
        Collections.reverse(listProd);

        if (!listProd.isEmpty()) {
            for (Games abo : listProd) {
                mainVBox.getChildren().add(makeProdModel(abo));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }    
        public float remise(Games g)
         {
             float k=1;
             Connection con = MyDB.getInstance().getCon();
        try {
            int c=0;
            c=g.getPromos_id();
            
            ResultSet rs = con.createStatement().executeQuery("SELECT `pourc` FROM `promos` WHERE id=" +c);
            
            while(rs.next()){
                
            k=Float.parseFloat(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficheGamesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return k;
         }
    public Parent makeProdModel( Games abo )  {
        Parent parent = null;
        float k=0;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_MODEL_PROD)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            
            ((Text) innerContainer.lookup("#createdAtText")).setText("Nom Produit : " + abo.getName());
            ((Text) innerContainer.lookup("#desctxt")).setText("Description : " + abo.getDescreption());
            k=remise(abo);
            
            ((Text) innerContainer.lookup("#prixtxt")).setText("prix : " + abo.getPrix()*k);
       
           

            
            ((Pane) innerContainer.lookup("#supppane")).setVisible(false);
            
              
           
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
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
          mainVBox.getChildren().clear();
          System.out.println("*********");
       for(Games r : listuser) {
          
           mainVBox.getChildren().add(makeProdModel(r));
        }
         
      
    }
}
