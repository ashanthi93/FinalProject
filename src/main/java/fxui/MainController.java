package fxui;

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
        start("/fxml/Settings.fxml");
    }
    
    @FXML
    private void startAnlzButtonAction(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/NewProjectWindow.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("Start New Project");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void start(String path) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.show();
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
