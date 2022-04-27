package pidev3a37;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xDrais
 */
import Controllers.FullCalendarView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.YearMonth;
public class Main  extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Full Calendar Example");
        primaryStage.setScene(new Scene(new FullCalendarView(YearMonth.now()).getView()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}