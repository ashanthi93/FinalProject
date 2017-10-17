package org.sse.userinterface.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import org.sse.source.model.Bug;
import org.sse.source.model.BugCategory;

import java.net.URL;
import java.util.ResourceBundle;

public class BugInputWindowController implements Initializable {

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

    }

    public void initialize(URL url, ResourceBundle rb) {
        setOWASPT10TableProperties();

        //bugTable.getItems().add(new Bug());
        bugTable.getItems().addAll(new Bug());

        bugTable.setRowFactory(new Callback<TableView<Bug>, TableRow<Bug>>() {

            public TableRow<Bug> call(TableView<Bug> param) {
                TableRow<Bug> row = new TableRow<Bug>();
                return row;
            }
        });

    }

    private void setOWASPT10TableProperties(){
        name.setCellValueFactory(new PropertyValueFactory<Bug, String>("name"));
        name.setCellFactory(TextFieldTableCell.<Bug>forTableColumn());
        name.prefWidthProperty().bind(bugTable.widthProperty().divide(9));

        /*category.setCellValueFactory(new PropertyValueFactory<Bug, String>("categoryName"));
        category.setCellFactory(TextFieldTableCell.<Bug>forTableColumn());
        category.prefWidthProperty().bind(bugTable.widthProperty().divide(5));*/

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
                new String("A10 Unvalidated Redirects and Forwards")
        );

        category.setCellFactory(ComboBoxTableCell.<Bug, String>forTableColumn(new DefaultStringConverter(), OWASPcategories));

        /*category.setCellValueFactory(
                new PropertyValueFactory<Bug, String>("categoryName")
        );
        category.setCellFactory(ComboBoxTableCell.<Bug, String>forTableColumn(OWASPcategories));
        category.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Bug, String>>() {
                    public void handle(TableColumn.CellEditEvent<Bug, String> event) {
                        ((Bug) event.getTableView().getItems().get(event.getTablePosition().getRow())).setLevel(event.getNewValue());
                    }

                }
        );*/

        description.setCellValueFactory(new PropertyValueFactory<Bug, String>("description"));
        description.setCellFactory(TextFieldTableCell.<Bug>forTableColumn());
        description.prefWidthProperty().bind(bugTable.widthProperty().divide(1.5));

    }
}
