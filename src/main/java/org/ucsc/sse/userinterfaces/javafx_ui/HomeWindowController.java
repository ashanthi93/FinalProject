package org.ucsc.sse.userinterfaces.javafx_ui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Background;

public class HomeWindowController implements Initializable {
    
    @FXML
    private JFXButton newProjectBtn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //newProjectBtn.setStyle("-fx-background-image: url('/resources/images/project.png')");
    }    
    
}
