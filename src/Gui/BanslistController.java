/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Ban;
import Entities.User;
import Services.ServiceBan;
import Services.UserService;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 *
 * @author WALID
 */
public class BanslistController implements Initializable {

    @FXML
    private TableColumn<Ban, Integer> id_ban;
    @FXML
    private TableColumn<Ban, Integer> id_user_ban;
    @FXML
    private TableColumn<Ban, String> username_ban;
    @FXML
    private TableColumn<Ban, String> date_fin;
    @FXML
    private DatePicker date;
    @FXML
    private TextArea reason;
    @FXML
    private TableColumn<Ban, String> reason_table;
    @FXML
    private TableView<Ban> tablebans;

    /**
     * Initializes the controller class.
     */
    public void exceptionerror(Exception e)
    {
            Alert alert = new Alert(Alert.AlertType.ERROR);
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
    public void filltable()
    {
        try{
            ServiceBan sb=new ServiceBan();
            ObservableList<Ban> listban = FXCollections.observableArrayList(sb.afficher());
               id_ban.setCellValueFactory(new PropertyValueFactory<Ban,Integer>("id"));
            id_user_ban.setCellValueFactory(new PropertyValueFactory<Ban,Integer>("user_id"));
            username_ban.setCellValueFactory(new PropertyValueFactory<Ban,String>("username"));   
            date_fin.setCellValueFactory(new PropertyValueFactory<Ban,String>("date"));
            reason_table.setCellValueFactory(new PropertyValueFactory<Ban,String>("reason"));
            tablebans.setItems(listban);
        
        } catch (SQLException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            /*Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("DataBase Exception");
            alert.setContentText("Please check the database for errors");
            alert.showAndWait();*/
            exceptionerror(ex);
            
        }           
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filltable();
    }    

    @FXML
    private void update(ActionEvent event) throws SQLException {
        try
        {
        LocalDate d=date.getValue();
        Date datea = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
        DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        String datestring1=formatter.format(datea);
            System.out.println(datestring1);
        Ban B=new Ban(datestring1, tablebans.getSelectionModel().getSelectedItem().getUser_id(), reason.getText().toString());
        ServiceBan sb=new ServiceBan();
        sb.modifier(B, tablebans.getSelectionModel().getSelectedItem().getId());}
        catch (SQLException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            /*Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("DataBase Exception");
            alert.setContentText("Please check the database for errors");
            alert.showAndWait();*/
            exceptionerror(ex);
            
        }
        
        filltable();
        
    }

    @FXML
    private void delete(ActionEvent event) {
        try
        {ServiceBan sb=new ServiceBan();
        sb.Delete( tablebans.getSelectionModel().getSelectedItem().getId());}
        catch (SQLException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            /*Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("DataBase Exception");
            alert.setContentText("Please check the database for errors");
            alert.showAndWait();*/
            exceptionerror(ex);
            
        }
        filltable();
    }

    @FXML
    private void tableclicked(MouseEvent event) {
        reason.setText(tablebans.getSelectionModel().getSelectedItem().getReason());
    }
    
}
