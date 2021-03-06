/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Inscription_t;
import Entities.Tournoi;
import Services.Inscroption_TSer;
import Services.Service_Tournoi;
import Utils.MyDB;
import com.gembox.spreadsheet.ExcelFile;
import com.gembox.spreadsheet.ExcelWorksheet;
import com.gembox.spreadsheet.SpreadsheetInfo;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


import javafx.scene.control.TableView;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Taha
 */
public class FXMLIncriptionController implements Initializable {
        int id_selected;
        static {
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
    }
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
    @FXML
    public TableView<Inscription_t> tv_inc;
    
    public TableView tv_incd ;
    @FXML
    private TableColumn<Inscription_t, Integer> id_inc;
    @FXML
    private TableColumn<Inscription_t, String> user_name;
    @FXML
    private TableColumn<Inscription_t, String> email;
    @FXML
    private TableColumn<Inscription_t, Integer> tournoi_id;
    @FXML
    private TableColumn<Inscription_t, Integer> etat;
    @FXML
    private TableColumn<Inscription_t, String> Rank;

    /**
     * Initializes the controller class.
     */
        ObservableList <Inscription_t> list = FXCollections.observableArrayList();
        private static List<Inscription_t> liste = new ArrayList<Inscription_t>();
    @FXML
    private Button delete_inc;
    @FXML
    private Button modif_inc;
    @FXML
    private TextField name;
    @FXML
    private TextField email1;
    @FXML
    private TextField tournoi;
    @FXML
    private RadioButton confirmation;
    @FXML
    private ComboBox<String> ranke;
    @FXML
    private Button excel_id;
    @FXML
    private Button make_excel;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try{
        Connection con = MyDB.getInstance().getCon();
        ranke.setItems(options);
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `inscription_t`");
        while(rs.next()){
        list.add(new Inscription_t(rs.getInt("id"),rs.getString("user_name"),rs.getString("email"),rs.getInt("tournoi_id"),rs.getString("rank"),rs.getInt("etat")));
        liste.add(new Inscription_t(rs.getString("user_name"),rs.getString("email"),rs.getInt("tournoi_id"),rs.getString("rank"),rs.getInt("etat")));        }
        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }           
            id_inc.setCellValueFactory(new PropertyValueFactory<Inscription_t,Integer>("id"));
            user_name.setCellValueFactory(new PropertyValueFactory<Inscription_t,String>("user_name"));
            email.setCellValueFactory(new PropertyValueFactory<Inscription_t,String>("email")); 
            etat.setCellValueFactory(new PropertyValueFactory<Inscription_t,Integer>("tournoi_id"));
            Rank.setCellValueFactory(new PropertyValueFactory<Inscription_t,String>("rank"));
            tournoi_id.setCellValueFactory(new PropertyValueFactory<Inscription_t,Integer>("etat"));

            tv_inc.setItems(list);
            tv_incd = tv_inc;
    } 

    @FXML
    private void Delete_inc(ActionEvent event) {
        
           Inscroption_TSer T = new Inscroption_TSer();


                             System.out.println(tv_inc.getSelectionModel().getSelectedItem().getId()+"");
       try{
                                       T.DeleteInc(tv_inc.getSelectionModel().getSelectedItem().getId());
                                       
                                       
           JOptionPane.showMessageDialog(null, "Data telah terhapus");
           Inscription_t selectedItem = tv_inc.getSelectionModel().getSelectedItem();
            tv_inc.getItems().remove(selectedItem);       
       } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error"+e.getMessage());

        }
        
    }

    @FXML
    private void inc_clicked(MouseEvent event) {
         System.out.println("Clicked on " + tv_inc.getSelectionModel().getSelectedItem().getId()); 
                delete_inc.setDisable(false);
                int index = tv_inc.getSelectionModel().getSelectedItem().getId();
                        Service_Tournoi T = new Service_Tournoi();
              modif_inc.setDisable(false);

            name.setText(tv_inc.getSelectionModel().getSelectedItem().getUser_name());
            email1.setText(tv_inc.getSelectionModel().getSelectedItem().getEmail());
            tournoi.setText(tv_inc.getSelectionModel().getSelectedItem().isEtat()+"");
            confirmation.setText(tv_inc.getSelectionModel().getSelectedItem().getTournoi_id()+"");
            ranke.setValue(tv_inc.getSelectionModel().getSelectedItem().getRank());
            id_selected=tv_inc.getSelectionModel().getSelectedItem().getId();          
    }

    @FXML
    private void mofidifier_inc(ActionEvent event) {
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
                        if(confirmation.isSelected() && bb==true &&name.getText().length()!=0 && tournoi.getText().toString().length()!=0)
                        {   Inscription_t t = new Inscription_t(name.getText(),email1.getText().toString(),1,ranke.getValue().toString(),savedValue);
                        try {
                            
            T.modifierInc(t, index);
                                                 JOptionPane.showMessageDialog(null, "modif incription avec succ??e");

                             list.clear();
                             try{
        Connection con = MyDB.getInstance().getCon();
        ranke.setItems(options);
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM `inscription_t`");
        while(rs.next()){
        list.add(new Inscription_t(rs.getInt("id"),rs.getString("user_name"),rs.getString("email"),rs.getInt("tournoi_id"),rs.getString("rank"),rs.getInt("etat")));
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }           
            id_inc.setCellValueFactory(new PropertyValueFactory<Inscription_t,Integer>("id"));
            user_name.setCellValueFactory(new PropertyValueFactory<Inscription_t,String>("user_name"));
            email.setCellValueFactory(new PropertyValueFactory<Inscription_t,String>("email")); 
            etat.setCellValueFactory(new PropertyValueFactory<Inscription_t,Integer>("tournoi_id"));
            Rank.setCellValueFactory(new PropertyValueFactory<Inscription_t,String>("rank"));
            tournoi_id.setCellValueFactory(new PropertyValueFactory<Inscription_t,Integer>("etat"));

            tv_inc.setItems(list);
    id_inc.setCellValueFactory(new PropertyValueFactory<Inscription_t,Integer>("id"));
          
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        } 
                               
        
                        }
                        else                                      JOptionPane.showMessageDialog(null, "modif non traiter!");

                            }
    private Stage stage;
 private Scene scene;
 private Parent root;
    Statement stm;
    @FXML
    void back(ActionEvent event) throws IOException {
Parent root = FXMLLoader.load(getClass().getResource("/Gui/FXMLTournoi.fxml"));
 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  scene.getStylesheets().add("/dark-theme.css");

  stage.show();
        
        
    }
     @FXML
    private void Make_excel(ActionEvent event) {
         HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        HSSFSheet hssfSheet=  hssfWorkbook.createSheet("Sheet1");
        HSSFRow firstRow= hssfSheet.createRow(0);

        ///set titles of columns
        for (int i=0; i<tv_inc.getColumns().size();i++){

            firstRow.createCell((short) i).setCellValue(tv_inc.getColumns().get(i).getText());

        }


        for (int row=0; row<tv_inc.getItems().size();row++){

            HSSFRow hssfRow= hssfSheet.createRow(row+1);

            for (int col=0; col<tv_inc.getColumns().size(); col++){

                Object celValue = tv_inc.getColumns().get(col).getCellObservableValue(row).getValue();

                try {
                    if (celValue != null && Double.parseDouble(celValue.toString()) != 0.0) {
                        hssfRow.createCell((short) col).setCellValue(Double.parseDouble(celValue.toString()));
                    }
                } catch (  NumberFormatException e ){

                    hssfRow.createCell((short) col).setCellValue(celValue.toString());
                }

            }

        }

        try {
            hssfWorkbook.write(new FileOutputStream("File.xls"));
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    
    
    @FXML
    private void excel_action(ActionEvent event) throws IOException {

  StackPane secondaryLayout = new StackPane();
                           Parent bans = FXMLLoader.load(getClass().getClassLoader().getResource("Gui/sample.fxml"));
				Scene secondScene = new Scene(bans);
                                secondScene.getStylesheets().add("/dark-theme.css");
				Stage newWindow = new Stage();
				newWindow.setTitle("Second Stage");
				newWindow.setScene(secondScene);
				newWindow.show();
    }

   
    }

   

    
    
    
    
    

