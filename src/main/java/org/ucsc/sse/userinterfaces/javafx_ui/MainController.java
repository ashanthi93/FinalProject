package org.ucsc.sse.userinterfaces.javafx_ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainController implements Initializable {

    @FXML
    private void settingsButtonAction(ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Settings.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Settings");

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        double width = screenBounds.getWidth();
        double height = screenBounds.getHeight();

        Scene scene = new Scene(parent, (width*0.8) , (height*0.8));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        stage.setX((width - stage.getWidth()) /2 );
        stage.setY((height - stage.getHeight()) / 2);
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
