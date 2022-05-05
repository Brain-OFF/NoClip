/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Inscription_t;
import Entities.Tournoi;
import Services.Inscroption_TSer;
import Utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
import javafx.collections.transformation.FilteredList;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.internet.AddressException;
/**
 * FXML Controller class
 *
 * @author Taha
 */
public class FXMLADD_incController implements Initializable {
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
    @FXML
    private TableView<Tournoi> tvTour;
    @FXML
    private TableColumn<Tournoi, Integer> idT;
    @FXML
    private TableColumn<Tournoi,String> nomT;
    @FXML
    private TableColumn<Tournoi, String> cathT;
    @FXML
    private TableColumn<Tournoi, String> discT;
    @FXML
    private TableColumn<Tournoi,String > dateTo;
ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Bronze",
        "Silver",
        "Gold",
         "Platinum",
         "Diamond",
         "Master",
         "Grand"
    );
    /**
     * Initializes the controller class.
     */
        ObservableList <Tournoi> list = FXCollections.observableArrayList();
    @FXML
    private TextField name;
    @FXML
    private TextField tournoi;
    @FXML
    private ComboBox<String> ranke;
    @FXML
    private TextField email1;
    @FXML
    private RadioButton confirmation;
    @FXML
    private Button Add_in;
    @FXML
    private TextField search_field;
        FilteredList<Tournoi> data = new FilteredList<>(list,b->true);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
                        
                ranke.setValue("Bronze");
                ranke.setItems(options);

        Connection con = MyDB.getInstance().getCon();
        ResultSet rs;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM `tournoi`");
        
        while(rs.next()){
        list.add(new Tournoi(rs.getInt(1),rs.getString("nom"),rs.getString("date"),rs.getString("cathegorie"),rs.getString("discription")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }    
                     System.out.println(list.toString());
                     idT.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id"));
            nomT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("name"));
            dateTo.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("dateT"));   
            cathT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("cathegorie"));
            discT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("discription"));
               search_field.textProperty().addListener((observable,oldvalue,newValue)->{
                data.setPredicate(t->{
                    if(newValue == null||newValue.isEmpty()){
                        return true;
                    }
                    String lowerCasefilter = newValue.toLowerCase();
                    if(t.getName().toLowerCase().indexOf(lowerCasefilter)!=-1){return true;}
                    if(t.getCathegorie().toLowerCase().indexOf(lowerCasefilter)!=-1){return true;}
                    if(t.getDiscription().toLowerCase().indexOf(lowerCasefilter)!=-1){return true;}
                    if(t.getDateT().toLowerCase().indexOf(lowerCasefilter)!=-1){return true;}
                    else
                    return false;
                });
            });
            tvTour.setItems(data);}
int id_selected;
    @FXML
    private void tableview_clicked(MouseEvent event) {
                               System.out.println("Clicked on " + tvTour.getSelectionModel().getSelectedItem().getId()); 
                               Tournoi tt = new Tournoi(
                                       tvTour.getSelectionModel().getSelectedItem().getId(),
                                      tvTour.getSelectionModel().getSelectedItem().getName(),
                                       tvTour.getSelectionModel().getSelectedItem().getDateT(),
                                       tvTour.getSelectionModel().getSelectedItem().getCathegorie(),
                                       tvTour.getSelectionModel().getSelectedItem().getDiscription()
                               );
                               Tournoi td = new Tournoi(now.toString());
                               if(tt.compareTo(td)>0){
                              Add_in.setDisable(false);
                              int id_selected = tvTour.getSelectionModel().getSelectedItem().getId();
                              tournoi.setText(id_selected+"");
    } else 
                                                        Add_in.setDisable(false);
         
    }

    @FXML
    private void Add_inc(ActionEvent event) throws SQLException, Exception {
         int index = id_selected;
                        Inscroption_TSer T = new Inscroption_TSer();
                        int savedValue = Integer.parseInt(tournoi.getText());
                        int x;
                        String b = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
                        Pattern emapat = Pattern.compile(b, Pattern.CASE_INSENSITIVE);
                        Matcher m = emapat.matcher(email1.getText().toString());
                        boolean bb;
                       bb=m.matches();
                       System.out.println(bb);
                       Tournoi tt = new Tournoi(
                                       tvTour.getSelectionModel().getSelectedItem().getId(),
                                      tvTour.getSelectionModel().getSelectedItem().getName(),
                                       tvTour.getSelectionModel().getSelectedItem().getDateT(),
                                       tvTour.getSelectionModel().getSelectedItem().getCathegorie(),
                                       tvTour.getSelectionModel().getSelectedItem().getDiscription()
                               );
                 Tournoi td = new Tournoi(now.toString());

                   if(confirmation.isSelected() && bb==true &&name.getText().length()!=0 && tournoi.getText().toString().length()!=0 && tt.compareTo(td)>0)
                        {   Inscription_t t = new Inscription_t(name.getText(),email1.getText().toString(),1,ranke.getValue().toString(),savedValue);
                            
            T.ajouterInc(t);
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
         alert.setContentText("inscription ajoutée avec succée!");
         alert.show();
        Connection con = MyDB.getInstance().getCon();
        ranke.setItems(options);
                }else 
         JOptionPane.showMessageDialog(null, "error un champ est vide ou email ou date incorrecte ");
    }
  
    }




 
    
    

