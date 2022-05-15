/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev3a37;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class Linechart extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
        HBox root= new HBox();
         Scene Scenee =new Scene(root, 450, 330);
CategoryAxis xAxis =new CategoryAxis();
xAxis.setLabel("moins");
NumberAxis yAxis =new NumberAxis();
yAxis.setLabel("annuelle des News par mois ");
LineChart <String,Number> linechart =new LineChart <String,Number> (xAxis, yAxis);
linechart.setTitle("News");
        XYChart.Series<String,Number> data =new XYChart.Series<>();
        data.getData().add(new XYChart.Data<String,Number>("janvier",4 ));
         data.getData().add(new XYChart.Data<String,Number>("février",2 ));
          data.getData().add(new XYChart.Data<String,Number>("mars",1 ));
           data.getData().add(new XYChart.Data<String,Number>("mai",5 ));
            data.getData().add(new XYChart.Data<String,Number>("juin",0 ));
             data.getData().add(new XYChart.Data<String,Number>("juillet",12 ));
              data.getData().add(new XYChart.Data<String,Number>("septembre",4 ));
              data.getData().add(new XYChart.Data<String,Number>("octobre",10 ));
              data.getData().add(new XYChart.Data<String,Number>("décembre",11 ));
         linechart.getData().add(data);
root.getChildren().add(linechart);
primaryStage.setTitle("News ");
primaryStage.setScene(Scenee);
primaryStage.show();


       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
