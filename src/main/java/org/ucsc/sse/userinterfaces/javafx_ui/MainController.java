package org.ucsc.sse.userinterfaces.javafx_ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController implements Initializable {
    
    @FXML
    private void settingsButtonAction(ActionEvent event) throws Exception {
        start("/fxml/Settings.fxml", "Settings", true);
    }
    
    @FXML
    private void startAnlzButtonAction(ActionEvent event) throws Exception {
        start("/fxml/NewProjectWindow.fxml", "Start New Project", false);
    }

    public void start(String path, String title, Boolean resizable) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle(title);
        stage.setResizable(resizable);
        stage.setScene(scene);
        stage.show();
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
