package org.ucsc.sse.userinterfaces.javafx_ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Preloader.StateChangeNotification.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NewProjectWindowController implements Initializable {

    @FXML
    private JFXRadioButton bugCheck;
    
    @FXML
    private JFXRadioButton threatCheck;
    
    @FXML
    private JFXButton cancelBtn;
    
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
    private void addBtnAction(ActionEvent event) throws Exception {
        if(threatCheck.isSelected()){
            fileOpen("Select Threat Reports", "HTM Files (*.htm)", "*.htm");
        }else if(bugCheck.isSelected()){
            fileOpen("Select Static Code Analysis Reports", "XML Files (*.xml)", "*.xml");
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("\n    Please select an option first!");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void cancelBtnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    private void fileOpen(String title, String displayName, String fileType){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(displayName, fileType);
        fileChooser.getExtensionFilters().add(extFilter);
        Stage stage = new Stage();
        fileChooser.setTitle(title);
        fileChooser.showOpenDialog(stage);
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
