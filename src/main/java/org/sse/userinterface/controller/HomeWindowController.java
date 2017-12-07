package org.sse.userinterface.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.sse.design.model.ThreatCategory;
import org.sse.outputgenerators.report.builder.concrete.JSONReportBuilder;
import org.sse.outputgenerators.report.builder.concrete.XMLReportBuilder;
import org.sse.outputgenerators.report.creator.BugCategoryReportCreator;
import org.sse.outputgenerators.report.creator.ThreatCategoryReportCreator;
import org.sse.outputgenerators.report.model.BugReport;
import org.sse.outputgenerators.report.model.ThreatReport;
import org.sse.source.model.BugCategory;

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

    // Table to hold source code bugs and details

    @FXML
    private TableView<BugCategory> OWASPT10_Table;

    @FXML
    private TableColumn<BugCategory, String> t10_id;
    @FXML
    private TableColumn<BugCategory, String> t10_name;
    @FXML
    private TableColumn<BugCategory, String> t10_description;

    // create design table
    @FXML
    private TableView<ThreatCategory> designTable;

    @FXML
    private TableColumn<ThreatCategory, String> designThreatColumn;
    @FXML
    private TableColumn<ThreatCategory, String> designCategoryColumn;
    @FXML
    private TableColumn<ThreatCategory, String> designMitigationColumn;

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

    }

    @FXML
    private void sourceSaveBtnAction(ActionEvent event) throws Exception {

        Window currentStage = ((Node) event.getSource()).getScene().getWindow();

        BugReport bugReport = this.convertToBugReport();
        String outputXMLFile = this.convertToXML(bugReport);

        boolean isSaveSucceed = this.fileSaveAction(currentStage, outputXMLFile);

        if (isSaveSucceed) {
            //success window
        } else {
            //error
        }
    }

    @FXML
    private void designSaveBtnAction(ActionEvent event) throws Exception {

        Window currentStage = ((Node) event.getSource()).getScene().getWindow();

        ThreatReport threatReport = this.convertToThreatReport();
        String outputXMLFile = this.convertToXML(threatReport);

        boolean isSaveSucceed = this.fileSaveAction(currentStage, outputXMLFile);

        if (isSaveSucceed) {
            //success window
        } else {
            //error
        }
    }

    private boolean fileSaveAction(Window currentStage, String outputXMLFile) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CNX Files", "*.cnx")
        );

        File file = fileChooser.showSaveDialog(currentStage);

        if (file != null) {
            try (PrintStream ps = new PrintStream(file)) {

                ps.print(outputXMLFile);
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     *
     * @return
     */
    private ThreatReport convertToThreatReport() {

        HashMap<String, ThreatCategory> threatCategoryHashMap = new HashMap<>();

        for (ThreatCategory threatCategory : designTable.getItems()) {
            threatCategoryHashMap.put(threatCategory.getId(), threatCategory);
        }

        ThreatCategoryReportCreator threatCategoryReportCreator = new ThreatCategoryReportCreator(threatCategoryHashMap);

        ThreatReport threatReport = threatCategoryReportCreator.generateReport("Threats Analysis Report");

        return threatReport;
    }

    /**
     *
     * @return
     */
    private BugReport convertToBugReport() {

        HashMap<String, BugCategory> bugCategoryHashMap = new HashMap<>();

        for (BugCategory bugCategory : OWASPT10_Table.getItems()) {
            bugCategoryHashMap.put(bugCategory.getId(), bugCategory);
        }

        BugCategoryReportCreator bugCategoryReportCreator = new BugCategoryReportCreator(bugCategoryHashMap);

        BugReport bugReport = bugCategoryReportCreator.generateReport("Bug Analysis Report");

        return bugReport;
    }

    /**
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    private String convertToXML(Object object) throws JsonProcessingException {

        XMLReportBuilder xmlReportBuilder = new XMLReportBuilder();
        String xmlReport = xmlReportBuilder.convertReport(object);

        return xmlReport;
    }

    /**
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    private String convertToJSON(Object object) throws JsonProcessingException {

        JSONReportBuilder jsonReportBuilder = new JSONReportBuilder();
        String jsonReport = jsonReportBuilder.convertReport(object);

        return jsonReport;
    }
}
