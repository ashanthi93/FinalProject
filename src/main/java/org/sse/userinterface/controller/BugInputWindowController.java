package org.sse.userinterface.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import org.sse.source.BugExtractor;
import org.sse.source.model.Bug;
import org.sse.source.model.BugCategory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.sse.source.model.BugCollection;
import org.sse.userinterface.controller.NewProjectWindowController;

public class BugInputWindowController implements Initializable {

    List<Bug> updetedList = new ArrayList<>();
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
    private void cancelBtnAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addBtnAction(ActionEvent event) throws Exception {
        bugList = bugTable.getItems();

        for(Bug bugObj: bugList){
            if(bugObj.getName() == null && bugObj.getCategoryName() == null && bugObj.getDescription() == null){
                //Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.WARNING, "Warning", null, "\n  You haven't entered any details!");
                //alert.showAndWait();
                bugObj = null;
            }else if(bugObj.getName() != null && (bugObj.getCategoryName() == null || bugObj.getDescription() == null)){
                Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.WARNING, "Warning", null, "\n  Please fill all the details for each entered rows!");
                alert.showAndWait();
                if(!updetedList.contains(bugObj)){
                    updetedList.add(bugObj);
                }
            }else if(bugObj.getCategoryName() != null && (bugObj.getName() == null || bugObj.getDescription() == null)){
                Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.WARNING, "Warning", null, "\n  Please fill all the details for each entered rows!");
                alert.showAndWait();
                if(!updetedList.contains(bugObj)){
                    updetedList.add(bugObj);
                }
            }else if(bugObj.getDescription() != null && (bugObj.getName() == null || bugObj.getCategoryName() == null)){
                Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.WARNING, "Warning", null, "\n  Please fill all the details for each entered rows!");
                alert.showAndWait();
                if(!updetedList.contains(bugObj)){
                    updetedList.add(bugObj);
                }
            }
        }

        //System.out.println(updetedList.size());

        if(updetedList.size() == 0){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.WARNING, "Warning", null, "\n  You haven't entered any details!");
            alert.showAndWait();
        }else{
            BugCollection collectedBugs = new BugCollection();
            collectedBugs.setBugList(updetedList);
        }

    }

    public void initialize(URL url, ResourceBundle rb){
        setOWASPT10TableProperties();

        for (int i=0;i<200;i++){
            bugTable.getItems().add(new Bug());
        }
    }

    private void setOWASPT10TableProperties(){
        name.setCellValueFactory(new PropertyValueFactory<Bug, String>("name"));
        name.setCellFactory(TextFieldTableCell.<Bug>forTableColumn());
        name.prefWidthProperty().bind(bugTable.widthProperty().divide(5));
        name.setOnEditCommit(event -> {
            Bug row = event.getRowValue();
            row.setName(event.getNewValue());
        });

        ObservableList<String> OWASPcategories = FXCollections.observableArrayList(
                new String("A1: Injection"),
                new String("A2: Broken Authentication and Session Management"),
                new String("A3: Cross-Site Scripting (XSS)"),
                new String("A4: Insecure Direct Object References"),
                new String("A5: Security Misconfiguration"),
                new String("A6: Sensitive Data Exposure"),
                new String("A7: Missing Function Level Access Control"),
                new String("A8: Cross-Site Request Forgery (CSRF)"),
                new String("A9: Using Components with Known Vulnerabilities"),
                new String("A10: Unvalidated Redirects and Forwards")
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

    }
}
