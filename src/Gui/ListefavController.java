/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Games;
import Entities.games_user;
import Services.GamesService;
import Tools.constants;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
public class ListefavController implements Initializable {

    @FXML
    private Text topText;
    @FXML
    private VBox mainVBox;
    GamesService ps=new GamesService();
    Entities.loggedUser holder = Entities.loggedUser.get_instace();
     ObservableList <games_user> list = FXCollections.observableArrayList();
    @FXML
    private Button reterntogames;

    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
         List<Games> listProd = ps.ShowProduit();
        Collections.reverse(listProd);
          Connection con = MyDB.getInstance().getCon();
         boolean exist=false;
        if (!listProd.isEmpty()) {
            for (Games abo : listProd) {
                 try {
                  exist=false;   
            
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `Games_user`");
     
                 
            
            while(rs.next())
            {
                
                list.add(new games_user(rs.getInt(1),rs.getInt(2)));
                
                   for(games_user r : list) {
                       
            if (r.getGames_id()==abo.getId() && r.getUser_id()==holder.getUser().getId())
            {exist=true;}
//            
            
        }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelpController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 if(exist==true){
                mainVBox.getChildren().add(makeProdModel(abo));}
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
         
        Connection con = MyDB.getInstance().getCon();
        
        Parent parent = null;
        float k=0;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(constants.FXML_MODEL_PROD)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            
            ((Text) innerContainer.lookup("#createdAtText")).setText("Nom Produit : " + abo.getName());
            ((Text) innerContainer.lookup("#desctxt")).setText("Description : " + abo.getDescreption());
            k=remise(abo);
            
            ((Text) innerContainer.lookup("#prixtxt")).setText("prix : " + abo.getPrix()*k);
            ((Text) innerContainer.lookup("#idgames")).setText( abo.getId()+"");
      
           

            
            ((Pane) innerContainer.lookup("#supppane")).setVisible(false);
            
              
           
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void reternto_games(ActionEvent event) {
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
         try {
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/AfficheGames.fxml"));
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
