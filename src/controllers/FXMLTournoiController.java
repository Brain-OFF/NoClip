/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Tournoi;
import Services.Service_Tournoi;
import Utils.MyDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import Services.ServiceFacebook;
import com.restfb.exception.FacebookException;
import java.io.FileNotFoundException;
/**
 * FXML Controller class
 *
 * @author Taha
 */
public class FXMLTournoiController implements Initializable {
DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now(); 
            @FXML
    private TextField search_field;
    @FXML
    private TextField nom;
    @FXML
    private DatePicker dateT;
    ObservableList<String> options = 
    FXCollections.observableArrayList(
        "RPG",
        "MMORPG",
        "MOBA",
         "Battle Royale",
         "Beat Them All",
         "survival Horror",
         "RTS"
    );
    @FXML
    private ComboBox<String> cathegorie;
    @FXML
    private TextArea Discription;
    @FXML
    private Button AddT;
    @FXML
    private TableColumn<Tournoi, Integer> idT;
    @FXML
    private TableColumn<Tournoi, String> nomT;
    @FXML
    private TableColumn<Tournoi, String> cathT;
    @FXML
    private TableColumn<Tournoi, String> discT;
    @FXML
    private TableView<Tournoi> tvTour;
    @FXML
    private TableColumn<Tournoi, String> dateTo;
    int id_selected;
    /**
     * Initializes the controller class.
     */
    ObservableList <Tournoi> list = FXCollections.observableArrayList();
        FilteredList<Tournoi> data = new FilteredList<>(list,b->true);

    @FXML
    private Button delete;
    @FXML
    private Button updateT;
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        try{
                cathegorie.setValue("Rpg");
                cathegorie.setItems(options);
                dateT.setValue(LocalDate.now());
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `tournoi`");
        while(rs.next()){
        list.add(new Tournoi(rs.getInt(1),rs.getString("nom"),rs.getString("date"),rs.getString("cathegorie"),rs.getString("discription")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }      
       
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
            tvTour.setItems(data);
    }   
   
    @FXML
    private void AddT(javafx.event.ActionEvent event) {
          Service_Tournoi T = new Service_Tournoi();
                          Tournoi T1 = new Tournoi(nom.getText(),dateT.getValue().toString(),cathegorie.getValue().toString(),Discription.getText());
                          Tournoi td = new Tournoi(now.toString());

         if(nom.getText().length()!=0 && Discription.getText().length()!=0 && dateT.getValue().toString().length()!=0 && T1.compareTo(td)>0){
          try {
            T.ajouter(T1);
             try{
            ServiceFacebook fb = new ServiceFacebook();
            try {
                fb.publish("hello world", "");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch(FacebookException ex){
            ex.printStackTrace();
        }
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("User Modified");
                alert.setContentText("User Has Been Updated");
                alert.showAndWait();
            tvTour.setItems(list);   
          } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        
         alert.setContentText("Tournoi ajouter avec succ??e!");
         alert.show();
         
         list.clear();
          try{
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `tournoi`");
        while(rs.next()){
        list.add(new Tournoi(rs.getInt(1),rs.getString("nom"),rs.getString("date"),rs.getString("cathegorie"),rs.getString("discription")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }   
            idT.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id"));
            nomT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("name"));
            dateTo.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("dateT"));   
            cathT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("cathegorie"));
            discT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("discription"));
            tvTour.setItems(list);
    }
         else 
          JOptionPane.showMessageDialog(null, "error un champ est vide ou date in correcte ");
         
             }

    Connection con;
    Statement s;
    PreparedStatement pst;
    int myIndex;
    int id;

    @FXML
    private void deleteT(javafx.event.ActionEvent event)throws IOException {    
                Service_Tournoi T = new Service_Tournoi();


                             System.out.println(tvTour.getSelectionModel().getSelectedItem().getId()+"");
       try{
                                       T.Delete(tvTour.getSelectionModel().getSelectedItem().getId());
                                       Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("User Modified");
                alert.setContentText("User Has Been Updated");
                alert.showAndWait();
                                       
            JOptionPane.showMessageDialog(null, "Data avec succ??e!");
            list.clear();
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `tournoi`");
        while(rs.next()){
        list.add(new Tournoi(rs.getInt(1),rs.getString("nom"),rs.getString("date"),rs.getString("cathegorie"),rs.getString("discription")));
        }
       } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error"+e.getMessage());

        }
    }

    @FXML
    private void modifierT(javafx.event.ActionEvent event) {
        int index = id_selected;
                        Service_Tournoi T = new Service_Tournoi();
           Tournoi t=new Tournoi(nom.getText().toString(),dateT.getValue().toString(),cathegorie.getValue().toString(),Discription.getText().toString());
                     if(nom.getText().length()!=0 && Discription.getText().length()!=0 && dateT.getValue().toString().length()!=0){
        try {
            T.modifier(t, index);
            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("User Modified");
                alert.setContentText("User Has Been Updated");
                alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("success");
        
         alert.setContentText("Tournoi modifier");
         alert.show();
         list.clear();
          try{
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `tournoi`");
        while(rs.next()){
        list.add(new Tournoi(rs.getInt(1),rs.getString("nom"),rs.getString("date"),rs.getString("cathegorie"),rs.getString("discription")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }            idT.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id"));
            nomT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("name"));
            dateTo.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("dateT"));   
            cathT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("cathegorie"));
            discT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("discription"));
            tvTour.setItems(list);
    }
    else
                                     JOptionPane.showMessageDialog(null, "error un champ est vide");

                         }
    
    /*public void clickItem(MouseEvent event) {
    tvTour.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("Clicked on " + (tvTour.getSelectionModel().getSelectedCells().get(0)).getColumn());        
        }
    });
}*/
    


    @FXML
    private void tableview_clicked(MouseEvent event) {
                System.out.println("Clicked on " + tvTour.getSelectionModel().getSelectedItem().getId()); 
                delete.setDisable(false);
                updateT.setDisable(false);
                 int index = tvTour.getSelectionModel().getSelectedItem().getId();
                        Service_Tournoi T = new Service_Tournoi();
             System.out.println(tvTour.getSelectionModel().getSelectedItem().getId());

            nom.setText(tvTour.getSelectionModel().getSelectedItem().getName());
            Discription.setText(tvTour.getSelectionModel().getSelectedItem().getDiscription());
            cathegorie.setValue(tvTour.getSelectionModel().getSelectedItem().getCathegorie());
            id_selected=tvTour.getSelectionModel().getSelectedItem().getId();
    }
private Stage stage;
 private Scene scene;
 private Parent root;
    Statement stm;
    @FXML
    private void Trait_inc(javafx.event.ActionEvent event) throws IOException {
        
 Parent root = FXMLLoader.load(getClass().getResource("/Gui/FXMLIncription.fxml"));
 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  scene.getStylesheets().add("/dark-theme.css");

  stage.show();
        
        
    }
    public void exceptionerror(Exception e)
    {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Exception Dialog");
            alert.setHeaderText("Exception Encountered");
            alert.setContentText("Exception: "+e.getClass());
            Exception ex = e;
            // Create expandable Exception.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("The exception stacktrace was:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);
            alert.showAndWait();
    }
   
    
        void search_s(MouseEvent event) {
                list.clear();
                try{
                cathegorie.setValue("Rpg");
                cathegorie.setItems(options);
                dateT.setValue(LocalDate.now());
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `tournoi` where nom LIKE '%"+search_field.getText().toString()+"%'"+"or cathegorie LIKE '%"+
                search_field.getText().toString()+"%'"+"or discription  LIKE '%"+search_field.getText().toString()+"%'"+"or date LIKE '%"+search_field.getText().toString()+"%'");
        while(rs.next()){ 
        list.add(new Tournoi(rs.getInt(1),rs.getString("nom"),rs.getString("date"),rs.getString("cathegorie"),rs.getString("discription")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }           
            idT.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id"));
            nomT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("name"));
            dateTo.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("dateT"));   
            cathT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("cathegorie"));
            discT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("discription"));
            tvTour.setItems(list);
    }   
    @FXML
    void Stat(MouseEvent event) throws IOException {
        
 Parent root = FXMLLoader.load(getClass().getResource("/Gui/ChartFxml.fxml"));
 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  scene.getStylesheets().add("/dark-theme.css");

  stage.show();
    }
//    
//     @FXML
//    void Search_d(InputMethodEvent event) {
//          list.clear();
//                try{
//                cathegorie.setValue("Rpg");
//                cathegorie.setItems(options);
//                dateT.setValue(LocalDate.now());
//        Connection con = MyDB.getInstance().getCon();
//        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `tournoi` where nom LIKE '%"+search_field.getText().toString()+"%'"+"or cathegorie LIKE '%"+
//                search_field.getText().toString()+"%'"+"or discription  LIKE '%"+search_field.getText().toString()+"%'"+"or date LIKE '%"+search_field.getText().toString()+"%'");
//        while(rs.next()){ 
//        list.add(new Tournoi(rs.getInt(1),rs.getString("nom"),rs.getString("date"),rs.getString("cathegorie"),rs.getString("discription")));
//        }
//        
//        } catch (SQLException ex) {
//            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
//        }           
//            idT.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id"));
//            nomT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("name"));
//            dateTo.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("dateT"));   
//            cathT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("cathegorie"));
//            discT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("discription"));
//            tvTour.setItems(list);
//
//    }

    @FXML
    private void Trie_asc(javafx.event.ActionEvent event) {
        list.clear();
                try{
                cathegorie.setValue("Rpg");
                cathegorie.setItems(options);
                dateT.setValue(LocalDate.now());
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `tournoi` ORDER BY date ASC");
        while(rs.next()){
        list.add(new Tournoi(rs.getInt(1),rs.getString("nom"),rs.getString("date"),rs.getString("cathegorie"),rs.getString("discription")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }           
            idT.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id"));
            nomT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("name"));
            dateTo.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("dateT"));   
            cathT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("cathegorie"));
            discT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("discription"));
            tvTour.setItems(list);
    }

    @FXML
    private void trie_desc(javafx.event.ActionEvent event) {
         list.clear();
                try{
                cathegorie.setValue("Rpg");
                cathegorie.setItems(options);
                dateT.setValue(LocalDate.now());
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `tournoi` ORDER BY date DESC");
        while(rs.next()){
        list.add(new Tournoi(rs.getInt(1),rs.getString("nom"),rs.getString("date"),rs.getString("cathegorie"),rs.getString("discription")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }           
            idT.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id"));
            nomT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("name"));
            dateTo.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("dateT"));   
            cathT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("cathegorie"));
            discT.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("discription"));
            tvTour.setItems(list);
    }

   

}
    
    
   
    
    
    
    
     

    

