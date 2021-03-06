package org.sse.userinterface.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.stage.WindowEvent;
import org.dom4j.DocumentException;
import org.sse.design.ThreatExtractor;
import org.sse.userinterface.MainApp;

import java.io.File;

import java.net.URL;
import java.util.*;

public class NewProjectWindowController implements Initializable {

    public static Scene homeWindow;

    @FXML
    private JFXRadioButton bugCheck;

    @FXML
    private JFXRadioButton threatCheck;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private void threatCheckAction(ActionEvent event) throws Exception {
        if (bugCheck.isSelected()) {
            bugCheck.setSelected(false);
        }
    }

    @FXML
    private void bugCheckAction(ActionEvent event) throws Exception {
        if (threatCheck.isSelected()) {
            threatCheck.setSelected(false);
        }
    }

    @FXML
    private void addBtnAction(ActionEvent event) {
        if (threatCheck.isSelected()) {
            fileOpen("Select Threat Report", "TMT Files (*.tm7)", "*.tm7");
        } else if (bugCheck.isSelected()) {
            //fileOpen("Select Static Code Analysis Reports", "XML Files (*.xml)", "*.xml");

            start("/fxml/BugInputWindow.fxml", "Bug Input Window");

        } else {
            Alert alert = this.createAlert(Alert.AlertType.WARNING, "Warning", null, "\n  Please select a report type!");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancelBtnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    private void fileOpen(String title, String displayName, String fileType) {

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

                    HomeWindowController.selectedIndex = "DESIGN";
                    start("/fxml/HomeWindow.fxml", "Home Window");
                    Stage stageMain = (Stage) cancelBtn.getScene().getWindow();
                    stageMain.close();
                    Stage stageMainWelcome = (Stage) MainApp.welcomeWindow.getWindow();
                    stageMainWelcome.close();

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
    }

    public void start(String path, String title) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage();
            homeWindow = new Scene(root);
            homeWindow.getStylesheets().add("/styles/Styles.css");

            stage.setTitle(title);
            stage.setScene(homeWindow);
            stage.show();

            if(title.equals("Home Window")){
                stage.setMaximized(true);
                stage.setOnCloseRequest(ev -> {
                    ev.consume();
                    Alert alert = createAlert(Alert.AlertType.CONFIRMATION, "Confirm!", null, "\n Are you sure you want to exit?");
                    ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                    ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                    alert.getButtonTypes().setAll(okButton, noButton);
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == okButton){
                        stage.close();
                    }
                });
            }
        }catch (Exception e){
            Alert alert = createAlert(Alert.AlertType.ERROR, "Error", "Error!" , "\n Error occurred while opening the window.");
            alert.showAndWait();
        }
    }

    public static Alert createAlert(Alert.AlertType alertType, String title, String headerText, String contentText){

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        return alert;
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}