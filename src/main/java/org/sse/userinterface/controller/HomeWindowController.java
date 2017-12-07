package org.sse.userinterface.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.dom4j.DocumentException;
import org.sse.design.ThreatExtractor;
import org.sse.source.model.BugCategory;
import org.sse.userinterface.MainApp;

public class HomeWindowController implements Initializable {

    public static boolean isHomeOpened = false;
    public static String selectedIndex = "NONE";

    @FXML
    private TabPane tabPane;

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

    // Table to hold source code bugs and details

    @FXML
    private TableView<BugCategory> OWASPT10_Table;

    @FXML
    private TableColumn<BugCategory, String> t10_id;
    @FXML
    private TableColumn<BugCategory, String> t10_name;
    @FXML
    private TableColumn<BugCategory, String> t10_description;

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
    

    public void initialize(URL url, ResourceBundle rb) {
        if(selectedIndex.equals("DESIGN")){
            List<Tab> tabs = new ArrayList(tabPane.getTabs());
            tabs.sort((o1, o2) -> o1.getText().compareTo(o2.getText()));
            tabPane.getTabs().clear();
            tabPane.getTabs().setAll(tabs);
            tabPane.getSelectionModel().select(0);

        }
    }

    @FXML
    private void sourceNextBtnAction(ActionEvent event) throws Exception {
        int selectedNum = tabPane.getSelectionModel().getSelectedIndex();

        if(selectedNum == 0){
            boolean returned = fileOpen("Select Threat Report", "TMT Files (*.tm7)", "*.tm7");
            if(returned){
                tabPane.getSelectionModel().select(1);
            }
        }else{
            tabPane.getSelectionModel().select(2);
        }
    }

    @FXML
    private void designNextBtnAction(ActionEvent event) throws Exception {
        int selectedNum = tabPane.getSelectionModel().getSelectedIndex();
        isHomeOpened = true;

        if(selectedNum == 0){
            start("/fxml/BugInputWindow.fxml", "Bug Input Window");
            tabPane.getSelectionModel().select(1);

        }else{
            tabPane.getSelectionModel().select(2);
        }
    }

    private boolean fileOpen(String title, String displayName, String fileType) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(displayName, fileType);
        fileChooser.getExtensionFilters().add(extFilter);
        Stage stage = new Stage();
        fileChooser.setTitle(title);
        File file = fileChooser.showOpenDialog(stage);

        try {
            if (file != null) {

                ThreatExtractor threatExtractor = ThreatExtractor.getInstance();

                if (threatExtractor.readFile(file)) {

                    threatExtractor.classifyThreats();
                    return true;

                } else {
                    Alert alert = this.createAlert(Alert.AlertType.ERROR, "Error", null, "\n Threat report validation fails !");

                    alert.showAndWait();
                }
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            Alert alert = createAlert(Alert.AlertType.ERROR, "Error", "Invalid Threat Model" , "\n Threat Category model does not maatch with STRIDE !");
            alert.showAndWait();
        } catch (DocumentException e) {
            e.printStackTrace();
            Alert alert = createAlert(Alert.AlertType.ERROR, "Error", "Invalid File" , "\n Threat Report is invalid !");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void start(String path, String title) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static Alert createAlert(Alert.AlertType alertType, String title, String headerText, String contentText){

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        return alert;
    }
    
}
