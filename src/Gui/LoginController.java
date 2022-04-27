/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Ban;
import Entities.User;
import Entities.loggedUser;
import Services.ServiceBan;
import Services.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author WALID
 */
public class LoginController implements Initializable {

    @FXML
    private TextField username_log;
    @FXML
    private TextField password_log;
    @FXML
    private TextField username_reg;
    @FXML
    private TextField email_reg;
    @FXML
    private PasswordField password_reg;
    @FXML
    private PasswordField conf_pass_reg;
    @FXML
    private Button login_button;
    UserService ServU;
    @FXML
    private CheckBox checkbox_register;
    
    
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void register(ActionEvent event) {
        if ((username_reg.getText().length()<=0||username_reg.getText().length()>25)||(email_reg.getText().length()<=0||email_reg.getText().length()>50)||
                (password_reg.getText().length()<=0||password_reg.getText().length()>50)||(conf_pass_reg.getText().length()<=0||conf_pass_reg.getText().length()>50)||
                (!conf_pass_reg.getText().toString().equals(password_reg.getText().toString())))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot Perform Registration");
            alert.setContentText("Please fill all the the fields before adding and check passwords");
            alert.showAndWait();
        }
        else if (!checkbox_register.isSelected())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot Perform Registration");
            alert.setContentText("Please agree to the terms of the registration");
            alert.showAndWait();
        }
        else
        {
            boolean success = false;
             ServU = new UserService();
            try{
            User U=new User(0, false,username_reg.getText().toString() , email_reg.getText().toString(), password_reg.getText().toString(),
                    "","user");
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("New User Added");
                alert.setContentText("New User Added To the Database");
                alert.showAndWait();
            }
            
        }
    }

    @FXML
    private void login_pressed(ActionEvent event) throws SQLException, ParseException {
        if (username_log.getText().length()==0 || password_log.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot Perform Login");
            alert.setContentText("Please fill all the the fields before logging in");
            alert.showAndWait();
        }
        else
        {
            boolean success = false;
            User U=new User();
             ServU = new UserService();
            try{
             U=ServU.Login(username_log.getText().toString(), password_log.getText().toString());
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
            if (success&&U.getId()!=-1)
            {
                boolean banned=false;
                
                ServiceBan sb=new ServiceBan();
                try
                {
                List<Ban>bans=sb.Search_ban(U.getId());
                
                
                Calendar cal = Calendar.getInstance();
                Date datetoday = cal.getTime();
               
                for (Ban B:bans)
                {
                     SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");  
                     Date date1=formatter.parse(B.getDate());  
                    if (date1.after(datetoday)||date1.equals(datetoday))
                    {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("You Are Banned");
                        alert.setHeaderText("You have Been banned from Using this service");
                        alert.setContentText("Your Account Has been banned for '"+B.getReason()+"' until "+B.getDate());
                        alert.showAndWait();
                        banned=true;
                    }
                }
                }
                catch (SQLException ex)
                {
                    exceptionerror(ex);
                    banned=true;
                }
                if (!banned)
                {
                loggedUser holder = loggedUser.get_instace();      
                holder.setUser(U);
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                try {
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/MainWindow.fxml"));
                    System.out.println("user being logged :"+holder.getUser());
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    scene.getStylesheets().add("/dark-theme.css");
                    stage.getIcons().add(new Image("4.png"));
                    stage.show();
                    } catch (IOException e) {
                           exceptionerror(e);
                    }
            }

            }
            else 
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Login Informations are Wrong");
                alert.setContentText("The Login informations do not match any account please check them");
                alert.showAndWait();
            }
        }
    }
    
}
