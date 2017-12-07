package org.sse.userinterface.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;


import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.dom4j.DocumentException;
import org.sse.design.ThreatCategoriesLoader;
import org.sse.design.model.Threat;
import org.sse.design.model.ThreatCategory;
import org.sse.design.model.ThreatMitigation;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.sse.outputgenerators.report.builder.concrete.JSONReportBuilder;
import org.sse.outputgenerators.report.builder.concrete.XMLReportBuilder;
import org.sse.outputgenerators.report.creator.BugCategoryReportCreator;
import org.sse.outputgenerators.report.model.BugReport;
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
    private TableView<ThreatMitigation> designTable;

    @FXML
    private TableColumn<ThreatMitigation, String> designThreatColumn;
    @FXML
    private TableColumn<ThreatMitigation, String> designCategoryColumn;
    @FXML
    private TableColumn<ThreatMitigation, String> designMitigationColumn;


    HashMap<String, ThreatCategory> threatMap;
    ObservableList<ThreatMitigation> threatData;

    public HomeWindowController() throws DocumentException {
        threatMap = ThreatCategoriesLoader.getThreatCategoryHashMap();

        int id = 0;
        HashMap<Integer, ThreatMitigation> threatObjects = new HashMap<>();

        for (String key : threatMap.keySet()) {

            ThreatCategory categoryList =threatMap.get(key);
            List<Threat> list = categoryList.getThreatList();

            for (Threat threat : list){
                List<String> mitigations = categoryList.getMitigationList();
                ThreatMitigation threatmitigation = new ThreatMitigation();
                threatmitigation.setThreat(threat.getName());
                threatmitigation.setCategory(threat.getThreatCategoryName());
                threatmitigation.setMitigation(mitigations.get(0));

                threatObjects.put(id,threatmitigation);
                id++;

                for (int i = 1; i < mitigations.size(); i++) {
                    ThreatMitigation threatmitigationCopy = new ThreatMitigation();
                    threatmitigationCopy.setThreat("");
                    threatmitigationCopy.setCategory("");
                    threatmitigationCopy.setMitigation(mitigations.get(i));

                    threatObjects.put(id,threatmitigationCopy);
                    id++;
                }
            }


        }

        threatData = FXCollections.observableArrayList(threatObjects.values());


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

    private void setThreatProperties() {

        designThreatColumn.setCellValueFactory(new PropertyValueFactory<ThreatMitigation, String>("threat"));
        designThreatColumn.prefWidthProperty().bind(designTable.widthProperty().divide(5));

        designCategoryColumn.setCellValueFactory(new PropertyValueFactory<ThreatMitigation, String>("category"));
        designCategoryColumn.prefWidthProperty().bind(designTable.widthProperty().divide(5));

        designMitigationColumn.setCellValueFactory(new PropertyValueFactory<ThreatMitigation, String>("mitigation"));
        designMitigationColumn.prefWidthProperty().bind(designTable.widthProperty().divide(1.5));

        designTable.setItems(threatData);
    }

    public void initialize(URL url, ResourceBundle rb) {

        setThreatProperties();
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
    /*private void designSaveBtnAction(ActionEvent event) throws Exception {

        Window currentStage = ((Node) event.getSource()).getScene().getWindow();

        ThreatReport threatReport = this.convertToThreatReport();
        String outputXMLFile = this.convertToXML(threatReport);

        boolean isSaveSucceed = this.fileSaveAction(currentStage, outputXMLFile);

        if (isSaveSucceed) {
            //success window
        } else {
            //error
        }
    }*/

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
    /*private ThreatReport convertToThreatReport() {

        HashMap<String, ThreatCategory> threatCategoryHashMap = new HashMap<>();

        for (ThreatCategory threatCategory : designTable.getItems()) {
            threatCategoryHashMap.put(threatCategory.getId(), threatCategory);
        }

        ThreatCategoryReportCreator threatCategoryReportCreator = new ThreatCategoryReportCreator(threatCategoryHashMap);

        ThreatReport threatReport = threatCategoryReportCreator.generateReport("Threats Analysis Report");

        return threatReport;
    }*/

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
