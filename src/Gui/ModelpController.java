/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Games;
import Entities.Panier;
import Entities.User;
import Entities.games_user;
import Services.GamesService;
import Utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author month
 */
public class ModelpController implements Initializable {

    @FXML
    private Text createdAtText;
    @FXML
    private Text desctxt;
    @FXML
    private Text prixtxt;
    @FXML
    private Text qtetxt;
    @FXML
    private Text nbrvu;
    @FXML
    private Text dateajouttxt;
    @FXML
    private Pane supppane;
    @FXML
    private Pane ajoutpane;
    @FXML
    private Button heartbtn;
    User currentuser ;
    @FXML
    private Text dateajouttxt1;
    @FXML
    private Text idgames;
     ObservableList <games_user> list = FXCollections.observableArrayList();
       ObservableList <Panier> list1 = FXCollections.observableArrayList();
     Entities.loggedUser holder = Entities.loggedUser.get_instace();     
    @FXML
    private Button ajpanier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        currentuser=loggedUser.get_instace().getUser();
//        Connection con = MyDB.getInstance().getCon();
//        try {
//            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `Games`");
//            ResultSet rs1 = con.createStatement().executeQuery("SELECT * FROM `Games_user`");
//        } catch (SQLException ex) {
//            Logger.getLogger(ModelpController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }    

    @FXML
    private void ajoutfav(ActionEvent event) {
        try {
            GamesService gs = new GamesService();
            Connection con = MyDB.getInstance().getCon();
            System.out.println(Integer.parseInt(idgames.getText().toString()));
            System.out.println("***********************************************");
            System.out.println(holder.getUser());
            
            
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `Games_user`");
            
            
            
            while(rs.next())
            {boolean exist=false;
                list.add(new games_user(rs.getInt(1),rs.getInt(2)));
                
                for(games_user r : list) {
                    if (r.getGames_id()==Integer.parseInt(idgames.getText().toString()) && r.getUser_id()==holder.getUser().getId())
                    exist=true;
            
                }
                if(exist==true)
                    {gs.removefav(Integer.parseInt(idgames.getText().toString()), holder.getUser());
                      System.out.println("game supprem");
                      
                      break;}
           else{gs.ajouterfav(Integer.parseInt(idgames.getText().toString()), holder.getUser());
                      }
                
            }   } catch (SQLException ex) {
            Logger.getLogger(ModelpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterpanier(ActionEvent event) {
        try {
            GamesService gs = new GamesService();
            Connection con = MyDB.getInstance().getCon();
            System.out.println(Integer.parseInt(idgames.getText().toString()));
            System.out.println("***********************************************");
            System.out.println(holder.getUser());
            
            
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `cart`");
            
            
            
            while(rs.next())
            {boolean exist=false;
                list1.add(new Panier(rs.getInt(1),rs.getInt(2)));
                
                for(Panier r : list1) {
                    if (r.getIdProduit()==Integer.parseInt(idgames.getText().toString()) )
                    exist=true;
            
                }
                if(exist==true)
                    {gs.removepanier(Integer.parseInt(idgames.getText().toString()), holder.getUser());
                      System.out.println("game supprem");
                      
                      break;}
           else{gs.ajouterpanier(Integer.parseInt(idgames.getText().toString()), holder.getUser());
           System.out.println("ajouté au panier **********");
                      }
                
            }   } catch (SQLException ex) {
            Logger.getLogger(ModelpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
