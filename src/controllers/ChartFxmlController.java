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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
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
                 ObservableList<BarChart.Data> listbar = FXCollections.observableArrayList();
                 
                         Map<String, Integer> hm = new HashMap<String, Integer>();
    @FXML
    private BarChart<String, Double> bar;
                        

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

    @FXML
    private void StatTournoi(ActionEvent event) {
        list.clear();
        bar.setVisible(false);
        pieChart.setVisible(true);
        pieChart.setDisable(false);
        bar.setDisable(true);
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

    @FXML
    private void StatInc(ActionEvent event) {
                list.clear();
                bar.setVisible(false);
        pieChart.setVisible(true);
        pieChart.setDisable(false);
        bar.setDisable(true);

          try{
              
        Connection con = MyDB.getInstance().getCon();
        ResultSet rs = con.createStatement().executeQuery("SELECT Rank,COUNT(id) as c FROM `inscription_t` group by rank");
        while(rs.next()){
            list.add(new PieChart.Data(rs.getString("Rank"),rs.getInt(2)));
        }
                     pieChart.setData(list);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    @FXML
    void linechart(ActionEvent event) {
        


        double total = 0;
        DecimalFormat df2 = new DecimalFormat(".##");
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("Nombre de reclamations par jour");
        stage.setWidth(600);
        stage.setHeight(600);


        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        //creating the chart
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
                         try{
              
        Connection con = MyDB.getInstance().getCon();
        ObservableList<XYChart.Series<String, Double>> data =
         FXCollections.observableArrayList();
                data.clear();
                 Map <String,Series<String, Double>> elements=new HashMap<String,Series<String, Double>>();

        for (int i=1;i<13;i++)
        {   
        ResultSet rs = con.createStatement().executeQuery("SELECT `cathegorie`,`date`,count(`id`) as `c` FROM `tournoi` WHERE MONTH (`date`)='"+i+"' group by `cathegorie`");
       
              /*  elements.put("RPG", new Series<>());
                elements.put("MMORPG", new Series<>());
                elements.put("MOBA", new Series<>());
                elements.put("Battle Royale", new Series<>());
                elements.put("Beat Them All", new Series<>());
                elements.put("Survival Horror", new Series<>());
                elements.put("RTS", new Series<>());*/

        while(rs.next()){
            Date date1 = null;  
            try {
                date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("date"));
                System.out.println(date1);
            } catch (ParseException ex) {
                Logger.getLogger(ChartFxmlController.class.getName()).log(Level.SEVERE, null, ex);
            }

                System.out.println(rs.getString("c"));
                if (!elements.containsKey(rs.getString("cathegorie")))
                {
                    elements.put(rs.getString("cathegorie"), new Series<>());
                    elements.get(rs.getString("cathegorie")).setName(rs.getString("cathegorie"));
                elements.get(rs.getString("cathegorie")).getData().add(new XYChart.Data<> (Integer.toString(i), Double.parseDouble(rs.getString("c"))));
                }
                else
                {
                    elements.get(rs.getString("cathegorie")).setName(rs.getString("cathegorie"));
                elements.get(rs.getString("cathegorie")).getData().add(new XYChart.Data<> (Integer.toString(i), Double.parseDouble(rs.getString("c"))));
                }
                
                System.out.println(elements.get(rs.getString("cathegorie")));
            
            
        }
        for (Map.Entry<String,Series<String, Double>> entry : elements.entrySet())
            {
            data.add(entry.getValue());
            System.out.println(entry.getValue());
            }
        bar.setVisible(true);
        pieChart.setVisible(false);
        pieChart.setDisable(true);
        bar.setDisable(false);
        bar.setData(data);
        
        }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTournoiController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
       
    }

    


       
    
    

