/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Coach;
import Entities.Reservation;
import Services.ReservationService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 *
 * @author xDrais
 */
public class CoachFrontFXML implements Initializable{

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
    @FXML
    private Button reservation;
    ObservableList <Coach> list = FXCollections.observableArrayList();
    @FXML
    private TextField coachid;
    @FXML
    private DatePicker datepickerstart;
    @FXML
    private DatePicker datepickerend;
    @FXML
    private RadioButton radiodispo;
int id_selected;
DateTimeFormatter localdate = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
LocalDateTime now = LocalDateTime.now();
    @FXML
    
    private void tableview_clicked(MouseEvent event) {
       
                            System.out.println("Clicked on " + tableviewcoach.getSelectionModel().getSelectedItem().getId()); 
                              reservation.setDisable(false);
                              int id_selected = tableviewcoach.getSelectionModel().getSelectedItem().getId();
                              coachid.setText(id_selected+"");
    
    }

    @FXML
    private void reserver(ActionEvent event) {
        int index = id_selected;
         ReservationService cs = new ReservationService();
         System.out.println(Integer.parseInt(coachid.getText()));
         int f;
         if (radiodispo.isSelected())
              f=1;
         else
              f=0;
         System.out.println("Date S: "+datepickerstart.getValue().toString()+" Date E: "+datepickerend.getValue().toString());
        Reservation Game = new Reservation(Integer.parseInt(coachid.getText()),datepickerstart.getValue().toString(),datepickerend.getValue().toString(),f);
        Reservation te = new Reservation(now.toString());
        Reservation ts = new Reservation(datepickerend.getValue().toString());
         if(coachid.getText().length()!=0  &&  Game.compareTo(te)>0 && Game.compareTo(ts) <0) {
        try {
            cs.ajouter(Game);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        
         alert.setContentText("reservation ajoutée");
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
          else if (Game.compareTo(te)<0)
                     {
                         JOptionPane.showMessageDialog(null, "error un date must be supp to todays date");
                     }
                      else if (Game.compareTo(ts)>0)
                     {
                         JOptionPane.showMessageDialog(null, "error end date must be supp to begin date");
                     }
        
         else
          JOptionPane.showMessageDialog(null, "error un champ est vide");
                       
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
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
    
}
