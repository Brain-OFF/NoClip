/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import Entities.Ban;
import Entities.History;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Entities.User;
import Entities.loggedUser;
import Services.ServiceBan;
import java.net.URL;
import Utils.MyDB;
import java.sql.Connection;
import Services.UserService;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.control.PopupControl;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidev3a37.fmx;


/**
 * FXML Controller class
 *
 * @author WALID
 */
public class MainWindowController implements Initializable {
    UserService ServU;
    @FXML
    private TableView<User> User_table;
    @FXML
    private TextField Username_text;
    @FXML
    private TextField Email_text;
    @FXML
    private TextField pwd_text;
    @FXML
    private TextField bio_text;
    @FXML
    private TextField pts_text;
    @FXML
    private ComboBox<String> status_text;
    @FXML
    private Button create_user;
    @FXML
    private Button del_user;
    @FXML
    private Button update_user;
    /*private TableColumn<User, Integer> id_user;
    private TableColumn<User, String> username_table;
    private TableColumn<User, String> pwd_table;
    private TableColumn<User, String> email_table;
    private TableColumn<User, String> bio_table;
    private TableColumn<User, Integer> points_table;
    private TableColumn<User, String> status_table;*/
    
    
    ObservableList<String> options = 
    FXCollections.observableArrayList(
        "admin",
        "writer",
        "user"
    );
    ObservableList<String> optionsb = 
    FXCollections.observableArrayList(
        "1",
        "2",
        "7",
        "14",
        "30",
        "1 ans",
        "Permanent"
    );
    User Current_user;
    ObservableList <User> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<User, Integer> id_user;
    @FXML
    private TableColumn<User, String> username_table;
    @FXML
    private TableColumn<User, String> pwd_table;
    @FXML
    private TableColumn<User, String> email_table;
    @FXML
    private TableColumn<User, String> bio_table;
    @FXML
    private TableColumn<User, Integer> points_table;
    @FXML
    private TableColumn<User, String> status_table;
    @FXML
    private Label label_selected_id_user;
    @FXML
    private Label User_logged_label;
    @FXML
    private TextArea ban_reason;
    @FXML
    private ComboBox<String> nb_jours;
    
    List<History> oldlist=new ArrayList<History>();
    List<History> newlist=new ArrayList<History>();
    /**
     * Initializes the controller class.
     */
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
    
    
    public void filltable()
    {
        try{
                 ServU=new UserService();
                status_text.setItems(options);
                            

        ObservableList<User> observableList =ServU.afficherobs();
               id_user.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
            username_table.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
            pwd_table.setCellValueFactory(new PropertyValueFactory<User,String>("password"));   
            email_table.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
            bio_table.setCellValueFactory(new PropertyValueFactory<User,String>("bio"));
            points_table.setCellValueFactory(new PropertyValueFactory<User,Integer>("points"));
            status_table.setCellValueFactory(new PropertyValueFactory<User,String>("status"));
            User_table.setItems(observableList);
        
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
        ServiceBan ServB=new ServiceBan();
        try {
            oldlist=ServB.gethistory();
            newlist=ServB.gethistory();
        } catch (SQLException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
             Timeline timeline =
        new Timeline(new KeyFrame(Duration.millis(500), e ->{
                 try {
                     check_on_history ();
                 } catch (SQLException ex) {
                     Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }));
    timeline.setCycleCount(Animation.INDEFINITE); // loop forever
    timeline.play();
            try
            {
            filltable();
            optionsb = 
    FXCollections.observableArrayList(
        "1",
        "2",
        "7",
        "14",
        "30",
        "1 ans",
        "Permanent"
    );
            nb_jours.setItems(optionsb);
            loggedUser holder = loggedUser.get_instace();
            Current_user= loggedUser.get_instace().getUser();

            System.out.println("current"+Current_user);
            User_logged_label.setText("Current User: " +Current_user.getUsername() +"#"+Current_user.getId());
            User_logged_label.setTextFill(Color.web("#00FF00", 0.8));
            }
            catch (Exception e)
                    {
                         exceptionerror(e);
                    }
    }   

    @FXML
    private void Table_user_clicked(MouseEvent event) {
        del_user.setDisable(false);
        update_user.setDisable(false);
        label_selected_id_user.setText("Selected id : "+User_table.getSelectionModel().getSelectedItem().getId());
        Username_text.setText(User_table.getSelectionModel().getSelectedItem().getUsername());
        Email_text.setText(User_table.getSelectionModel().getSelectedItem().getEmail());
        bio_text.setText(User_table.getSelectionModel().getSelectedItem().getBio());
        pts_text.setText(User_table.getSelectionModel().getSelectedItem().getPoints()+"");
    }

    @FXML
    private void create_user_clicked(MouseEvent event) {
        if ((Username_text.getText().length()<=0||Username_text.getText().length()>25)||(Email_text.getText().length()<=0||Email_text.getText().length()>50)||
                (pwd_text.getText().length()<=0||pwd_text.getText().length()>50))
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot Perform Add");
            alert.setContentText("Please fill all the the fields before adding");
            alert.showAndWait();
        }
        else
        {
            boolean success = false;
            ServU=new UserService();
            try{
            User U=new User(parseInt(pts_text.getText().toString()), false,Username_text.getText().toString() , Email_text.getText().toString(), pwd_text.getText().toString(),
                    bio_text.getText().toString(),status_text.getValue().toString());
            ServU.ajouter(U);
            success=true;
            }
            catch (SQLException ex)
            {
                /*Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
            alert.setHeaderText("SQL Exception");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();*/
                exceptionerror(ex);
            }
            if (success)
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("New User Added");
                alert.setContentText("New User Added To the Database");
                alert.showAndWait();
                filltable();
            }
            
        }
    
    }

    @FXML
    private void del_user_clicked(MouseEvent event) {
            boolean success = false;
            ServU=new UserService();
            try{
            ServU.Delete(User_table.getSelectionModel().getSelectedItem().getId());
            success=true;
            }
            catch (SQLException ex)
            {
                /*Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
            alert.setHeaderText("SQL Exception");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();*/
                exceptionerror(ex);
            }
            if (success)
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("User Deleted");
                alert.setContentText("User Deleted From the Database");
                alert.showAndWait();
                filltable();
            }
        
    }

    @FXML
    private void update_user_clicked(MouseEvent event) {
        if ((Username_text.getText().length()<=0||Username_text.getText().length()>25)||(Email_text.getText().length()<=0||Email_text.getText().length()>50)||
                (pwd_text.getText().length()<=0||pwd_text.getText().length()>50))
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot Perform Add");
            alert.setContentText("Please fill all the the fields before adding");
            alert.showAndWait();
        }
        else
        {
            boolean success = false;
            ServU=new UserService();
            try{
            User U=new User(parseInt(pts_text.getText().toString()), false,Username_text.getText().toString() , Email_text.getText().toString(), pwd_text.getText().toString(),
                    bio_text.getText().toString(),status_text.getValue().toString());
            ServU.modifier(U, User_table.getSelectionModel().getSelectedItem().getId());
            success=true;
            }
            catch (SQLException ex)
            {
                /*Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
            alert.setHeaderText("SQL Exception");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();*/
                exceptionerror(ex);
            }
            if (success)
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("User Modified");
                alert.setContentText("User Has Been Updated");
                alert.showAndWait();
                filltable();
            }
            
        }
    }

    @FXML
    private void ban_button(javafx.event.ActionEvent event) {
        
        
            boolean success = false;
            ServiceBan ServB=new ServiceBan();
            String datestring1="";
            if (nb_jours.getValue()=="Permanent")
            {
                 datestring1="2030-12-31";
                 
            }
            else if (nb_jours.getValue()=="1 ans")
            {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, 1);
                Date datefin = cal.getTime();
                DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
                 datestring1=formatter.format(datefin);
                 System.out.println(datestring1);
            }
            else 
            {
                
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, Integer.parseInt(nb_jours.getValue()));
                Date datefin = cal.getTime();
                DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
                 datestring1=formatter.format(datefin);
                System.out.println(datestring1);

            }
            Calendar cal = Calendar.getInstance();
            Date datetoday = cal.getTime();
            DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");  
            
            
            try{
            Ban B=new Ban(datestring1,User_table.getSelectionModel().getSelectedItem().getId(),ban_reason.getText());
            ServB.ajouter(B);
            success=true;
            }
            catch (SQLException ex)
            {
                /*Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
            alert.setHeaderText("SQL Exception");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();*/
                exceptionerror(ex);
            }
            if (success)
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("User Banned");
                alert.setContentText("User is now banned");
                alert.showAndWait();
                filltable();
            }
            
        
    }

    @FXML
    private void gotobans(javafx.event.ActionEvent event) throws IOException {
        
                                
				StackPane secondaryLayout = new StackPane();
                                Parent bans = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/banslist.fxml"));
				Scene secondScene = new Scene(bans);
                                secondScene.getStylesheets().add("/dark-theme.css");
				Stage newWindow = new Stage();
				newWindow.setTitle("Second Stage");
				newWindow.setScene(secondScene);
				newWindow.show();
    }

    @FXML
    private void logout(javafx.event.ActionEvent event) {
         loggedUser holder = loggedUser.get_instace();      
                holder.setUser(null);
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                try {
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/Login.fxml"));
                    System.out.println("user being logged :"+holder.getUser());
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    scene.getStylesheets().add("/dark-theme.css");
                    stage.getIcons().add(new javafx.scene.image.Image("4.png"));
                    stage.show();
                    } catch (IOException e) {
                           exceptionerror(e);
                    }
    }

    @FXML
    private void refresh(javafx.event.ActionEvent event) {
        try
        {filltable();
        loggedUser holder = loggedUser.get_instace();
            Current_user= loggedUser.get_instace().getUser();

            System.out.println("current"+Current_user);
            User_logged_label.setText("Current User: " +Current_user.getUsername() +"#"+Current_user.getId());
            User_logged_label.setTextFill(Color.web("#00FF00", 0.8));}
        catch (Exception e)
                    {
                         exceptionerror(e);
                    }
    }

    @FXML
    private void go_to_profile(javafx.event.ActionEvent event) throws IOException {
        StackPane secondaryLayout = new StackPane();
                           Parent bans = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/Profile.fxml"));
				Scene secondScene = new Scene(bans);
                                secondScene.getStylesheets().add("/dark-theme.css");
				Stage newWindow = new Stage();
				newWindow.setTitle("Second Stage");
				newWindow.setScene(secondScene);
				newWindow.show();
    }

    @FXML
    private void History(javafx.event.ActionEvent event) {
        Notifications.create()
              .title("Title Text")
              .text("Hello World 0!")
              .showWarning();
    }
    
    
    private void check_on_history () throws SQLException
    {
        ServiceBan ServB=new ServiceBan();
        newlist=ServB.gethistory();
        String un = null;
        for(int i = 0; i < newlist.size(); i++){
            
         if (i>=oldlist.size())
         {
             if (newlist.get(i).getType().equals("A"))
             {
                 un=ServU.getusername(newlist.get(i).getId_user());
             Notifications.create()
              .title("A User got Banned")
              .text(un+" got banned until "+newlist.get(i).getDate())
              .showWarning();
             }
             if (newlist.get(i).getType().equals("D"))
             {
                 un=ServU.getusername(newlist.get(i).getId_user());
             Notifications.create()
              .title("A User got UnBanned")
              .text(un+" got Unbanned !")
              .showWarning();
             }
         }
         
      }
        oldlist=newlist;

    }
    
    
    
    
    }    
    

