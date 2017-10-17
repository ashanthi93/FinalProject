package org.sse.userinterface.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jdk.internal.org.xml.sax.SAXException;
import org.sse.design.ThreatExtractor;
import org.sse.userinterface.MainApp;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewProjectWindowController implements Initializable {

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
    private void addBtnAction(ActionEvent event) throws Exception {
        if (threatCheck.isSelected()) {
            fileOpen("Select Threat Report", "TMT Files (*.tm7)", "*.tm7");
        } else if (bugCheck.isSelected()) {
            //fileOpen("Select Static Code Analysis Reports", "XML Files (*.xml)", "*.xml");

            start("/fxml/BugInputWindow.fxml", "Bug Input Window");

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("\n    Please select a report type!");
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

                    start("/fxml/HomeWindow.fxml", "Home Window");
                    Stage stageMain = (Stage) cancelBtn.getScene().getWindow();
                    stageMain.close();
                    Stage stageMainWelcome = (Stage) MainApp.welcomeWindow.getWindow();
                    stageMainWelcome.close();

                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("\n Threat report validation fails !");
                    alert.showAndWait();
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}