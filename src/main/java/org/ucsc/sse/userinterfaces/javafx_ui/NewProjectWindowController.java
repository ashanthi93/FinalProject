package org.ucsc.sse.userinterfaces.javafx_ui;

import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

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
    
    @FXML
    private void addBtnction(ActionEvent event) throws Exception {
        if(threatCheck.isSelected()){
            fileOpen("Select Threat Reports");
        }else if(bugCheck.isSelected()){
            fileOpen("Select Static Code Analysis Reports");
        }
    }
    
    private void fileOpen(String title){
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(stage);
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
