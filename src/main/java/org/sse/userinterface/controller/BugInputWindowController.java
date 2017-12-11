package org.sse.userinterface.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import org.dom4j.DocumentException;
import org.sse.source.BugCategoriesLoader;
import org.sse.source.BugExtractor;
import org.sse.source.model.Bug;
import org.sse.source.model.BugCategory;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import org.sse.source.model.BugCollection;
import org.sse.userinterface.MainApp;
import org.sse.userinterface.controller.NewProjectWindowController;

import static org.sse.userinterface.controller.HomeWindowController.isHomeOpened;

public class BugInputWindowController implements Initializable {

    static List<Bug> updetedList = new ArrayList<>();
    List<Bug> bugList;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXButton addBtn;

    @FXML
    private TableView<Bug> bugTable;

    @FXML
    private TableColumn<Bug, String> name;
    @FXML
    private TableColumn<Bug, String> category;
    @FXML
    private TableColumn<Bug, String> description;

    @FXML
    private void cancelBtnAction(ActionEvent event) {
        try{
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();
        }catch (Exception e){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  An error occurred while cancelling.");
            alert.showAndWait();
        }

    }

    @FXML
    private void addBtnAction(ActionEvent event) {
        try{
            bugList = bugTable.getItems();

            for(Bug bugObj: bugList){
                if(bugObj.getName() == null && bugObj.getCategoryName() == null && bugObj.getDescription() == null){
                    bugObj = null;
                }else if(bugObj.getName() != null && (bugObj.getCategoryName() == null || bugObj.getDescription() == null)){
                    Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.WARNING, "Warning", null, "\n  Please fill all the details for each entered rows!");
                    alert.showAndWait();
                    return;
                }else if(bugObj.getCategoryName() != null && (bugObj.getName() == null || bugObj.getDescription() == null)){
                    Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.WARNING, "Warning", null, "\n  Please fill all the details for each entered rows!");
                    alert.showAndWait();
                    return;
                }else if(bugObj.getDescription() != null && (bugObj.getName() == null || bugObj.getCategoryName() == null)){
                    Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.WARNING, "Warning", null, "\n  Please fill all the details for each entered rows!");
                    alert.showAndWait();
                    return;
                }else{
                    updetedList.add(bugObj);
                }
            }

            if(updetedList.size() == 0){
                Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.WARNING, "Warning", null, "\n  You haven't entered any details!");
                alert.showAndWait();
            }else{
                BugCollection collectedBugs = new BugCollection();
                collectedBugs.setBugList(updetedList);

                if(!isHomeOpened){
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/HomeWindow.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("/styles/Styles.css");

                    stage.setTitle("Home Window");
                    stage.setResizable(true);
                    stage.setScene(scene);
                    stage.show();
                    stage.setMaximized(true);

                    Stage stageMain = (Stage) MainController.newProjectWindow.getWindow();
                    stageMain.close();
                    Stage stageMainWelcome = (Stage) MainApp.welcomeWindow.getWindow();
                    stageMainWelcome.close();
                }

                Stage stage2 = (Stage) addBtn.getScene().getWindow();
                stage2.close();
                //HomeWindowController.bugLoader();
            }

        }catch (IOException e){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occured while opening the HomeWindow.");
            alert.showAndWait();
        }catch (Exception ex){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occured while adding bugs.");
            alert.showAndWait();
        }
    }

    public void initialize(URL url, ResourceBundle rb){
        setOWASPT10TableProperties();

        for (int i=0;i<200;i++){
            bugTable.getItems().add(new Bug());
        }
    }

    private void setOWASPT10TableProperties(){
        try{
            name.setCellValueFactory(new PropertyValueFactory<Bug, String>("name"));
            name.setCellFactory(TextFieldTableCell.<Bug>forTableColumn());
            name.prefWidthProperty().bind(bugTable.widthProperty().divide(5));
            name.setOnEditCommit(event -> {
                Bug row = event.getRowValue();
                row.setName(event.getNewValue());
            });

            HashMap<String, BugCategory> OWASP_T10_list = BugCategoriesLoader.getBugCategoryHashMap();
            ObservableList<String> OWASPcategories = FXCollections.observableArrayList(
                    new String("A1: " + OWASP_T10_list.get("A1").getName()),
                    new String("A2: " + OWASP_T10_list.get("A2").getName()),
                    new String("A3: " + OWASP_T10_list.get("A3").getName()),
                    new String("A4: " + OWASP_T10_list.get("A4").getName()),
                    new String("A5: " + OWASP_T10_list.get("A5").getName()),
                    new String("A6: " + OWASP_T10_list.get("A6").getName()),
                    new String("A7: " + OWASP_T10_list.get("A7").getName()),
                    new String("A8: " + OWASP_T10_list.get("A8").getName()),
                    new String("A9: " + OWASP_T10_list.get("A9").getName()),
                    new String("A10: " + OWASP_T10_list.get("A10").getName())
            );
            category.setCellFactory(ComboBoxTableCell.<Bug, String>forTableColumn(new DefaultStringConverter(), OWASPcategories));
            category.prefWidthProperty().bind(bugTable.widthProperty().divide(3.75));
            category.setOnEditCommit(event -> {
                Bug row = event.getRowValue();
                row.setCategoryName(event.getNewValue());
            });

            description.setCellValueFactory(new PropertyValueFactory<Bug, String>("description"));
            description.setCellFactory(TextFieldTableCell.<Bug>forTableColumn());
            description.prefWidthProperty().bind(bugTable.widthProperty().divide(1.9));
            description.setOnEditCommit(event -> {
                Bug row = event.getRowValue();
                row.setDescription(event.getNewValue());
            });

        }catch(DocumentException de){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while setting the table properties in the window.");
            alert.showAndWait();

        }

    }
}
