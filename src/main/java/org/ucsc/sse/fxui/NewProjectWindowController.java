package org.ucsc.sse.fxui;

import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class NewProjectWindowController implements Initializable {

    @FXML
    private JFXRadioButton bugCheck;
    
    @FXML
    private JFXRadioButton threatCheck;
    
    @FXML
    private void threatCheckAction(ActionEvent event) throws Exception {
        if(bugCheck.isSelected()){
            bugCheck.setSelected(false);
        }
    }
    
    @FXML
    private void bugCheckAction(ActionEvent event) throws Exception {
        if(threatCheck.isSelected()){
            threatCheck.setSelected(false);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
