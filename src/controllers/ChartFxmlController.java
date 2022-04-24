/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Tournoi;
import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Taha
 */
public class ChartFxmlController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private PieChart pieChart;
                 ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
                         Map<String, Integer> hm = new HashMap<String, Integer>();
                        

    @Override
    public void initialize(URL url, ResourceBundle rb) {
             try{
              
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT cathegorie,COUNT(id) as c FROM `tournoi` group by cathegorie");
        while(rs.next()){
            list.add(new PieChart.Data(rs.getString("cathegorie"),rs.getInt(2)));
        }
                     pieChart.setData(list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }      
    private Stage stage;
 private Scene scene;
    @FXML
    void Back(MouseEvent event) throws IOException {
        
 Parent root = FXMLLoader.load(getClass().getResource("/Gui/FXMLTournoi.fxml"));
 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  scene.getStylesheets().add("/dark-theme.css");

  stage.show();

    }
    }   

       
    
    

