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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.awt.Desktop;
import java.text.SimpleDateFormat;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
/**
 * FXML Controller class
 *
 * @author Taha
 */
public class ChartFxmlController implements Initializable {
public static int numeroPDF = 0;
    Document doc = new Document();
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

    @FXML
    private void pdf_ch(MouseEvent event) {
                 numeroPDF = numeroPDF + 1;
        String nom = "Graph statistique " + numeroPDF + ".pdf";
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat Heure = new SimpleDateFormat("hh:mm:ss");
            //Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);

            WritableImage wimg = pieChart.snapshot(new SnapshotParameters(), null);
            File file = new File("ChartSnapshot.png");
            ImageIO.write(SwingFXUtils.fromFXImage(wimg, null), "png", file);

            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(System.getProperty("user.home") + "\\Desktop\\" + nom));
            doc.open();
//            Image img = Image.getInstance("C:\\xampp\\htdocs\\TunisiaBonPlan2-Integration\\web\\uploads\\images\\Sanstitre.png");
            Image img = Image.getInstance("ChartSnapshot.png");
            doc.add(img);
            doc.close();
            Desktop.getDesktop().open(new File(System.getProperty("user.home") + "\\Desktop\\" + nom));
            writer.close();

        } catch (Exception e) {

            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
    }
    }   

       
    
    

