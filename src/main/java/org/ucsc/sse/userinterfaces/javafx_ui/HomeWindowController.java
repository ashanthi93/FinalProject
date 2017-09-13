package org.ucsc.sse.userinterfaces.javafx_ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class HomeWindowController implements Initializable {
    
    @FXML
    private void settingsSub1Action(ActionEvent event) throws Exception {
        start("/fxml/Settings.fxml", "Settings", true, 0);
    }
    
    @FXML
    private void settingsSub2Action(ActionEvent event) throws Exception {
        start("/fxml/Settings.fxml", "Settings", true, 1);
    }
    
    @FXML
    private void settingsSub3Action(ActionEvent event) throws Exception {
        start("/fxml/Settings.fxml", "Settings", true, 2);
    }
    
    public void start(String path, String title, Boolean resizable, int index) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle(title);
        stage.setResizable(resizable);
        stage.setScene(scene);
        stage.show();
        
        TabPane tabs = (TabPane) scene.lookup("#settingsTabPane");
        SingleSelectionModel<Tab> selectionModel = tabs.getSelectionModel();
        selectionModel.select(index);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
