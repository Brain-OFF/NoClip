/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author xDrais
 */
public class SmsApiController {

    @FXML
    private TextField tfsendto;
    @FXML
    private TextArea tfmessage;
    @FXML
    private Button btsend;

    @FXML
    private void send(ActionEvent event) {
        apisms ap = new apisms();
        ap.sms("xdrais", "Yassin3244", "+216"+tfsendto.getText(), tfmessage.getText());
        
    }
    
}
