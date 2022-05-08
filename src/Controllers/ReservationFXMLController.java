/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Reservation;
import Services.ReservationService;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import javafx.scene.control.TableView;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.YearMonth;
import javafx.scene.layout.StackPane;

/**
 *
 * @author xDrais
 */
public class ReservationFXMLController implements Initializable{

    @FXML
    private DatePicker datepickerstart;
    @FXML
    private DatePicker datepickerend;
    @FXML
    private RadioButton radiodispo;
    @FXML
    private TextField textcoachid;
    @FXML
    private TableView<Reservation> tableviewreservation;
    @FXML
    private TableColumn<Reservation, Integer> colid;
    @FXML
    private TableColumn<Reservation, String> coltempsstart;
    @FXML
    private TableColumn<Reservation, String> coltempsend;
    @FXML
    private TableColumn<Reservation, Boolean> coldispo;
    @FXML
    private TableColumn<Reservation, Integer> colcoachid;
    @FXML
    private Button ajout;
    @FXML
    private Button modif;
    @FXML
    private Button delete;
    
    int id_selected;
    
     ObservableList <Reservation> list = FXCollections.observableArrayList();

     
DateTimeFormatter localdate = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
LocalDateTime now = LocalDateTime.now();
    @FXML
    private Button btexcel;
    @FXML
    private Button btcalendar;
     
    @FXML
    private void ajout(ActionEvent event) {
        
         ReservationService cs = new ReservationService();
         System.out.println(Integer.parseInt(textcoachid.getText()));
         int f;
         if (radiodispo.isSelected())
              f=1;
         else
              f=0;
        Reservation Game = new Reservation(Integer.parseInt(textcoachid.getText()),datepickerstart.getValue().toString(),datepickerend.getValue().toString(),f);
        Reservation te = new Reservation(now.toString());
        Reservation ts = new Reservation(datepickerend.getValue().toString());
         if(textcoachid.getText().length()!=0  &&  Game.compareTo(te)>0 && Game.compareTo(ts) <0) {
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
        ResultSet rst = con.createStatement().executeQuery("SELECT * FROM `reservation`");
        while(rst.next()){
        list.add(new Reservation(rst.getInt(1),rst.getInt(5),rst.getString(2),rst.getString(3),rst.getInt(4)));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(TestFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }   
              colid.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("id"));
            coltempsstart.setCellValueFactory(new PropertyValueFactory<Reservation,String>("tempsstart"));
            coltempsend.setCellValueFactory(new PropertyValueFactory<Reservation,String>("tempsend"));   
            coldispo.setCellValueFactory(new PropertyValueFactory<Reservation,Boolean>("dispo"));
            colcoachid.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("coach"));
            tableviewreservation.setItems(list);
        
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

    @FXML
    private void update(ActionEvent event) {
        
        int index = id_selected;
                        ReservationService C = new ReservationService();
                 
                     
           int f;
         if (radiodispo.isSelected())
              f=1;
         else
              f=0;
        Reservation Game = new Reservation(Integer.parseInt(textcoachid.getText()),datepickerstart.getValue().toString(),datepickerend.getValue().toString(),f);
        Reservation te = new Reservation(now.toString());
        Reservation ts = new Reservation(datepickerend.getValue().toString());

                     if(textcoachid.getText().length()!=0  &&  Game.compareTo(te)>0 && Game.compareTo(ts) <0 ){
        try {
            C.modifier(Game, index);
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(TestFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        
         alert.setContentText("reservation modifier");
         alert.show();
         list.clear();
          try{
        Connection con = MyDB.getInstance().getCon();
        ResultSet rst = con.createStatement().executeQuery("SELECT * FROM `reservation`");
        while(rst.next()){
        list.add(new Reservation(rst.getInt(1),rst.getInt(5),rst.getString(2),rst.getString(3),rst.getInt(4)));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(TestFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }   colid.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("id"));
            coltempsstart.setCellValueFactory(new PropertyValueFactory<Reservation,String>("tempsstart"));
            coltempsend.setCellValueFactory(new PropertyValueFactory<Reservation,String>("tempsend"));   
            coldispo.setCellValueFactory(new PropertyValueFactory<Reservation,Boolean>("dispo"));
            colcoachid.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("coach"));
            tableviewreservation.setItems(list);
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

    @FXML
    private void delete(ActionEvent event) {
        
        ReservationService cs = new ReservationService();
         
         System.out.println(tableviewreservation.getSelectionModel().getSelectedItem().getId()+"");
        try {
            
           cs.Delete(tableviewreservation.getSelectionModel().getSelectedItem().getId());
           JOptionPane.showMessageDialog(null, "Data avec succée!");
            Reservation selectedItem = tableviewreservation.getSelectionModel().getSelectedItem();
            tableviewreservation.getItems().remove(selectedItem);       
        } 
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "error"+ex.getMessage());
       }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         try{
             
                
        Connection con = MyDB.getInstance().getCon();
        ResultSet rst = con.createStatement().executeQuery("SELECT * FROM `reservation`");
        while(rst.next()){
        list.add(new Reservation(rst.getInt(1),rst.getInt(5),rst.getString(2),rst.getString(3),rst.getInt(4)));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(TestFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }           
            colid.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("id"));
            coltempsstart.setCellValueFactory(new PropertyValueFactory<Reservation,String>("tempsstart"));
            coltempsend.setCellValueFactory(new PropertyValueFactory<Reservation,String>("tempsend"));   
            coldispo.setCellValueFactory(new PropertyValueFactory<Reservation,Boolean>("dispo"));
            colcoachid.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("coach"));
            tableviewreservation.setItems(list);
        
    }
    
    
    
     private Stage stage;
 private Scene scene;
 private Parent root;
    Statement stm;
    private void Trait_inc(javafx.event.ActionEvent event) throws IOException {
        
 Parent root = FXMLLoader.load(getClass().getResource("/Gui/ReservationFXML.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
        
        
    }

 
    @FXML
    private void tableview_clicked(javafx.scene.input.MouseEvent event) {
        System.out.println("Clicked on " + tableviewreservation.getSelectionModel().getSelectedItem().getId()); 
                delete.setDisable(false);
                modif.setDisable(false);
                 int index = tableviewreservation.getSelectionModel().getSelectedItem().getId();
                        
             System.out.println(tableviewreservation.getSelectionModel().getSelectedItem().getId());
             
             textcoachid.setText(tableviewreservation.getSelectionModel().getSelectedItem().getCoach()+"");
             
//            String datestart = tableviewreservation.getSelectionModel().getSelectedItem().getTempsstart() ;
//                    
//             datepickerstart.setValue(LocalDate.parse(datestart));
             
 
             
          int k= tableviewreservation.getSelectionModel().getSelectedItem().getDispo();

          if (k==1)
          {
              radiodispo.setSelected(true);
          }
          else
          radiodispo.setSelected(false);
           
            
           
            
            id_selected=tableviewreservation.getSelectionModel().getSelectedItem().getId();
    }

    @FXML
    private void exportexcel(ActionEvent event) {
        
          HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        HSSFSheet hssfSheet=  hssfWorkbook.createSheet("Sheet1");
        HSSFRow firstRow= hssfSheet.createRow(0);

        ///set titles of columns
        for (int i=0; i<tableviewreservation.getColumns().size();i++){

            firstRow.createCell((short) i).setCellValue(tableviewreservation.getColumns().get(i).getText());

        }


        for (int row=0; row<tableviewreservation.getItems().size();row++){

            HSSFRow hssfRow= hssfSheet.createRow(row+1);

            for (int col=0; col<tableviewreservation.getColumns().size(); col++){

                Object celValue = tableviewreservation.getColumns().get(col).getCellObservableValue(row).getValue();

                try {
                    if (celValue != null && Double.parseDouble(celValue.toString()) != 0.0) {
                        hssfRow.createCell((short) col).setCellValue(Double.parseDouble(celValue.toString()));
                    }
                } catch (  NumberFormatException e ){

                    hssfRow.createCell((short) col).setCellValue(celValue.toString());
                }

            }

        }

        //save excel file and close the workbook
        try {
            hssfWorkbook.write(new FileOutputStream("reservation.xls"));
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showcalendar(ActionEvent event) throws IOException {
       StackPane secondaryLayout = new StackPane();
                           Parent bans = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/fullCalendar.fxml"));
				Scene secondScene = new Scene(bans);
                                
				Stage newWindow = new Stage();
				newWindow.setTitle("Second Stage");
				newWindow.setScene(new Scene(new FullCalendarView(YearMonth.now()).getView()));
				newWindow.show();
    }
    
}
  