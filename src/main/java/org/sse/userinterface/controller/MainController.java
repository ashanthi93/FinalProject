package org.sse.userinterface.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.sse.source.BugCategoriesLoader;

public class MainController implements Initializable {

    public static Scene newProjectWindow;

    @FXML
    private void settingsButtonAction(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Settings.fxml"));
            Parent parent = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Settings");

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            double width = screenBounds.getWidth();
            double height = screenBounds.getHeight();

            Scene scene = new Scene(parent, (width * 0.8), (height * 0.8));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            stage.setX((width - stage.getWidth()) / 2);
            stage.setY((height - stage.getHeight()) / 2);
        }catch (Exception e){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occured while opening the Settings Window.");
            alert.showAndWait();
        }
    }

    @FXML
    private void startAnlzButtonAction(ActionEvent event) {
        try{
            start("/fxml/NewProjectWindow.fxml", "Start New Project", false);
        }catch(Exception e){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occured while opening the New Project Window.");
            alert.showAndWait();
        }
    }

    public void start(String path, String title, Boolean resizable) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = new Stage();
        newProjectWindow = new Scene(root);
        newProjectWindow.getStylesheets().add("/styles/Styles.css");

        stage.setTitle(title);
        stage.setResizable(resizable);
        stage.setScene(newProjectWindow);
        stage.show();
    }

    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void openMenuItemAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CNX File (*.cnx)", "*.cnx")
        );

        Stage stage = new Stage();
        fileChooser.setTitle("Open Project");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            System.out.println("File open");
        }else{
            /**
             * Error messgae - Invalid file
             */
        }
    }
}
