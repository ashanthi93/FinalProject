package org.sse.userinterface.controller;

import java.io.File;
import java.net.URL;
import java.util.*;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import com.fasterxml.jackson.core.JsonProcessingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.dom4j.DocumentException;
import org.sse.association.model.Association;
import org.sse.association.model.AssociationContainer;
import org.sse.design.ThreatExtractor;

import org.sse.design.model.ThreatCategory;
import org.sse.knowedgemodel.prolog.PrologConverter;
import org.sse.outputgenerators.FileFormat;
import org.sse.outputgenerators.ReportType;

import javafx.scene.control.cell.PropertyValueFactory;
import org.sse.design.ThreatCategoriesLoader;
import org.sse.design.model.Threat;
import org.sse.design.model.ThreatMitigation;

import org.sse.outputgenerators.report.builder.concrete.JSONReportBuilder;
import org.sse.outputgenerators.report.builder.concrete.XMLReportBuilder;
import org.sse.outputgenerators.report.creator.AssociationReportCreator;
import org.sse.outputgenerators.report.creator.BugCategoryReportCreator;
import org.sse.outputgenerators.report.creator.ThreatCategoryReportCreator;
import org.sse.outputgenerators.report.model.AssociationReport;
import org.sse.outputgenerators.report.model.BugReport;
import org.sse.outputgenerators.report.model.ThreatReport;
import org.sse.source.model.Bug;
import org.sse.source.model.BugCategory;
import org.sse.source.model.BugCountermeasures;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;


public class HomeWindowController implements Initializable {

    public static boolean isHomeOpened = false;
    public static String selectedIndex = "NONE";
    static PrologConverter prolog = new PrologConverter();

    @FXML
    private JFXButton newProjectBtn;
    @FXML
    private JFXButton sourceCancelBtn;
    @FXML
    private JFXButton sourceSaveBtn;
    @FXML
    private JFXButton sourceNextBtn;
    @FXML
    private JFXButton designNextBtn;
    @FXML
    private JFXButton designSaveBtn;
    @FXML
    private JFXButton analysisSaveBtn;

    @FXML private Tab sourceTab;
    @FXML private Tab designTab;
    @FXML private Tab associationTab;

    // create design table
    @FXML
    private TableView<ThreatMitigation> designTable;

    @FXML
    private TableColumn<ThreatMitigation, String> designThreatColumn;
    @FXML
    private TableColumn<ThreatMitigation, String> designCategoryColumn;
    @FXML
    private TableColumn<ThreatMitigation, String> designMitigationColumn;

    @FXML
    private TabPane homeTabPane;

    private  HashMap<String, ThreatCategory> threatMap;
    private  ObservableList<ThreatMitigation> threatData;

    // create source table
    @FXML
    private TableView<BugCountermeasures> sourceTable;

    @FXML
    private TableColumn<BugCountermeasures, String> sourceBugColumn;
    @FXML
    private TableColumn<BugCountermeasures, String> sourceCategoryColumn;
    @FXML
    private TableColumn<BugCountermeasures, String> sourcePreventionColumn;

    public static ObservableList<BugCountermeasures> bugData;


    // create association table

    @FXML
    private TableView<AssociationContainer> associationTable;

    @FXML
    private TableColumn<AssociationContainer, String> associationbugcategory;
    @FXML
    private TableColumn<AssociationContainer, String> assosiationbug;
    @FXML
    private TableColumn<AssociationContainer, String> associationthreatcategory;
    @FXML
    private TableColumn<AssociationContainer, String> associationthreat;

    private static ObservableList<AssociationContainer> AssociationData;


    public void start(String path, String title, Boolean resizable, int index) {

        try{
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
        }catch(Exception e){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occured while opening the Settings Window.");
            alert.showAndWait();
        }
        
    }

    public void initialize(URL url, ResourceBundle rb) {

        if (MainController.hasThreat){
            threatData = MainController.loadedThreatData;
            setThreatProperties(threatData);
            homeTabPane.getSelectionModel().select(1);
            sourceTab.setDisable(true);
            associationTab.setDisable(true);
            MainController.hasThreat = false;
            designNextBtn.setDisable(true);
            designSaveBtn.setDisable(true);
        }
        else if (MainController.hasBug){
            bugData = MainController.loadedBugData;
            setBugProperties(bugData);
            MainController.hasBug = false;
            homeTabPane.getSelectionModel().select(0);
            designTab.setDisable(true);
            associationTab.setDisable(true);
            sourceNextBtn.setDisable(true);
            sourceSaveBtn.setDisable(true);
        }
        else if (MainController.hasAssociation){
            AssociationData = MainController.loadedAssociationData;
            setAssociationProperties(AssociationData);
            MainController.hasAssociation = false;
            homeTabPane.getSelectionModel().select(2);
            sourceTab.setDisable(true);
            designTab.setDisable(true);
            analysisSaveBtn.setDisable(true);
        }
        else {
            setThreatProperties(threatData);
            setBugProperties(bugData);
            populateBugs(bugData);
        }

        if (selectedIndex.equals("DESIGN")) {
            List<Tab> tabs = new ArrayList(homeTabPane.getTabs());
            tabs.sort((o1, o2) -> o1.getText().compareTo(o2.getText()));
            homeTabPane.getTabs().clear();
            homeTabPane.getTabs().setAll(tabs);
            homeTabPane.getSelectionModel().select(0);
        }
    }

    public HomeWindowController() {
        threatLoader();
        bugLoader();
        initializeDesignTab();
    }

    public HomeWindowController(ObservableList<ThreatMitigation> s) {

    }

    /**
     * @throws DocumentException
     */
    private void initializeDesignTab() {

    }


    private void threatLoader() {

        try{
            threatMap = ThreatCategoriesLoader.getThreatCategoryHashMap();

        }catch(DocumentException de){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while loading Threat Categories.");
            alert.showAndWait();
        }



        int id = 0;
        HashMap<Integer, ThreatMitigation> threatObjects = new HashMap<>();

        for (String key : threatMap.keySet()) {

            ThreatCategory categoryList = threatMap.get(key);
            List<Threat> Tlist = categoryList.getThreatList();
            List<String> Mlist = categoryList.getMitigationList();


            int TlistLen = Tlist.size();

            ThreatMitigation threatmitigation = new ThreatMitigation();

            if (TlistLen > 0) {
                threatmitigation.setCategory(categoryList.getName());
                String threatList = "";
                String allMitigations = "";
                for (Threat threat : Tlist) {
                    threatList = threatList + threat.getId() + ": " + threat.getName() + "\n\n";
                }
                threatmitigation.setThreat(threatList.trim());
                for (String mitigation : Mlist) {
                    allMitigations = allMitigations + mitigation + "\n\n";
                }
                threatmitigation.setMitigation(allMitigations);
            }

            threatObjects.put(id, threatmitigation);
            id++;

            // When number of threats are higher than num of mitigations

            /*if (TlistLen>MlistLen){
                if (MlistLen!=0){
                    Threat t = Tlist.get(0);

                    ThreatMitigation threatmitigation = new ThreatMitigation();
                    threatmitigation.setCategory(t.getThreatCategoryName());
                    threatmitigation.setThreat(t.getId() + ": " +t.getName());
                    threatmitigation.setMitigation(Mlist.get(0));
                    threatObjects.put(id,threatmitigation);
                    id++;
                }
                for (int j=1;j<TlistLen;j++){
                    Threat t1 = Tlist.get(j);
                    ThreatMitigation threatmitigationCopy = new ThreatMitigation();

                    threatmitigationCopy.setCategory("");
                    threatmitigationCopy.setThreat(t1.getId() + ": " +t1.getName());
                    if (j<MlistLen){
                        threatmitigationCopy.setMitigation(Mlist.get(j));
                    }
                    else{
                        threatmitigationCopy.setMitigation("");
                    }

                    threatObjects.put(id,threatmitigationCopy);
                    id++;
                }
            }

            // When number of mitigations are higher than or equal to num of threats

            if (MlistLen>=TlistLen){
                if (TlistLen!=0){
                    Threat t = Tlist.get(0);

                    ThreatMitigation threatmitigation = new ThreatMitigation();
                    threatmitigation.setCategory(t.getThreatCategoryName());
                    threatmitigation.setThreat(t.getId() + ": " +t.getName());
                    threatmitigation.setMitigation(Mlist.get(0));
                    threatObjects.put(id,threatmitigation);
                    id++;
                }
                for (int j=1;j<MlistLen;j++){

                    ThreatMitigation threatmitigationCopy = new ThreatMitigation();

                    if (j<TlistLen){
                        Threat t1 = Tlist.get(j);
                        threatmitigationCopy.setCategory("");
                        threatmitigationCopy.setThreat(t1.getId() + ": " +t1.getName());
                        threatmitigationCopy.setMitigation(Mlist.get(j));
                        threatObjects.put(id,threatmitigationCopy);
                        id++;
                    }
                    else {
                        threatmitigationCopy.setCategory("");
                        threatmitigationCopy.setThreat("");
                        threatmitigationCopy.setMitigation(Mlist.get(j));
                        threatObjects.put(id,threatmitigationCopy);
                        id++;
                    }
                }
            }*/


        }

        threatData = FXCollections.observableArrayList(threatObjects.values());
    }

    public static void bugLoader() {

        List<Bug> bugs = BugInputWindowController.updetedList;
        HashMap<String, List<String>> categorisedMap = new HashMap<String, List<String>>();

        //List<String> list = new ArrayList<String>();

        categorisedMap.put("A1: Injection", new ArrayList<String>());
        categorisedMap.put("A2: Broken Authentication and Session Management", new ArrayList<String>());
        categorisedMap.put("A3: Cross-Site Scripting (XSS)", new ArrayList<String>());
        categorisedMap.put("A4: Insecure Direct Object References", new ArrayList<String>());
        categorisedMap.put("A5: Security Misconfiguration", new ArrayList<String>());
        categorisedMap.put("A6: Sensitive Data Exposure", new ArrayList<String>());
        categorisedMap.put("A7: Missing Function Level Access Control", new ArrayList<String>());
        categorisedMap.put("A8: Cross-Site Request Forgery (CSRF)", new ArrayList<String>());
        categorisedMap.put("A9: Using Components with Known Vulnerabilities", new ArrayList<String>());
        categorisedMap.put("A10: Unvalidated Redirects and Forwards", new ArrayList<String>());

        HashMap<Integer, BugCountermeasures> bugObjects = new HashMap<>();
        int id = 0;

        for (Bug bug : bugs) {
            String category = bug.getCategoryName();
            List<String> list1 = categorisedMap.get(category);
            list1.add(bug.getName());
            categorisedMap.put(category, list1);
        }

        for (String key : categorisedMap.keySet()) {

            List<String> Blist = categorisedMap.get(key);
            List<String> Plist = prolog.getPreventionTechniques(key.toLowerCase().split(":")[0].toLowerCase());

            int BlistLen = Blist.size();
            int PlistLen = Plist.size();
            if (BlistLen > 0) {

                BugCountermeasures bugcountermeasure = new BugCountermeasures();
                bugcountermeasure.setCategory(key);
                String allBugs = "";
                String allCountermeasures = "";
                for (String bug : Blist) {
                    allBugs = allBugs + bug + "\n\n";
                }
                for (String mitigation : Plist){
                    allCountermeasures = allCountermeasures + mitigation + "\n\n";
                }
                bugcountermeasure.setBug(allBugs);
                bugcountermeasure.setCountermeasure(allCountermeasures);
                bugObjects.put(id,bugcountermeasure);
                id++;
            }

                /*if (PlistLen >= BlistLen) {
                    if (BlistLen != 0) {

                        BugCountermeasures bugCountermeasures = new BugCountermeasures();
                        bugCountermeasures.setCategory(key);
                        bugCountermeasures.setBug(Blist.get(0));
                        bugCountermeasures.setCountermeasure(Plist.get(0));
                        bugObjects.put(id, bugCountermeasures);
                        id++;
                    }
                    for (int j = 1; j < PlistLen; j++) {

                        BugCountermeasures bugCountermeasuresCopy = new BugCountermeasures();

                        if (j < BlistLen) {
                            bugCountermeasuresCopy.setCategory("");
                            bugCountermeasuresCopy.setBug(Blist.get(j));
                            bugCountermeasuresCopy.setCountermeasure(Plist.get(j));
                            bugObjects.put(id, bugCountermeasuresCopy);
                            id++;
                        } else {
                            bugCountermeasuresCopy.setCategory("");
                            bugCountermeasuresCopy.setBug("");
                            bugCountermeasuresCopy.setCountermeasure(Plist.get(j));
                            bugObjects.put(id, bugCountermeasuresCopy);
                            id++;
                        }
                    }
                }*/


        }

        /*for (Bug bug :bugs) {
            //System.out.println("////////////"+bug.getCategoryName());
            String [] category = bug.getCategoryName().split(":");
            List<String> preventions = prolog.getPreventionTechniques(category[0].toLowerCase());

            BugCountermeasures bugcountermeasure = new BugCountermeasures();
            bugcountermeasure.setBug(bug.getName());
            bugcountermeasure.setCategory(bug.getCategoryName());
            bugcountermeasure.setCountermeasure(preventions.get(0));
            bugObjects.put(id,bugcountermeasure);
            id++;
            for (int i = 1; i <preventions.size() ; i++) {
                BugCountermeasures bugcountermeasureCopy = new BugCountermeasures();
                bugcountermeasureCopy.setBug("");
                bugcountermeasureCopy.setCategory("");
                bugcountermeasureCopy.setCountermeasure(preventions.get(i));
                bugObjects.put(id,bugcountermeasureCopy);
            }
        }*/
        bugData = FXCollections.observableArrayList(bugObjects.values());
    }

    private void associationLoader() {
        if (bugData == null){
            bugLoader();
        }


        List<Bug> bugs = BugInputWindowController.updetedList;
        HashMap<Integer, AssociationContainer> associationObjects = new HashMap<>();

        int id = 0;
        for (BugCountermeasures bug : bugData){
            String category = bug.getCategory();

            String[] threatsForBug = prolog.getThreatCategoriesForBugCategory(category.split(":")[0].toLowerCase());

            for (String s : threatsForBug){

                AssociationContainer associationcontainer = new AssociationContainer();
                associationcontainer.setBugCategory(bug.getCategory());
                associationcontainer.setBug(bug.getBug());

                associationcontainer.setThreatCategory(ThreatCategoriesLoader.tCategories.get(s));
                List<Threat> t = threatMap.get(s.toUpperCase()).getThreatList();
                if (t.size() > 0) {
                    String all = "";
                    for (Threat details : t) {
                        all = all + details.getId() + ": " + details.getName() +"\n\n";
                    }
                    associationcontainer.setThreat(all);
                } else {
                    associationcontainer.setThreat("");
                }
                associationObjects.put(id,associationcontainer);
                id++;
            }

        }

        /*int id = 0;
        int hasBugNameId = 0;
        for (BugCountermeasures bug : bugData) {
            AssociationContainer associationcontainer = new AssociationContainer();
            String category = bug.getCategory();
            if (category != "" && bug.getBug() != "") {
                String[] threatsForBug = prolog.getThreatCategoriesForBugCategory(category);
                associationcontainer.setBugCategory(category);
                associationcontainer.setBug(bug.getBug());

                // put a flag to track where the last bug name was found relevant to a category
                hasBugNameId = id;

                if (threatsForBug[0] != "") {

                    // for the first threat

                    AssociationContainer associationcontainer1 = new AssociationContainer();
                    associationcontainer1.setBugCategory(category);
                    associationcontainer1.setBug(bug.getBug());

                    int size = threatsForBug.length;

                    associationcontainer1.setThreatCategory(ThreatCategoriesLoader.tCategories.get(threatsForBug[0]));
                    //associationcontainer.setThreat("");

                    List<Threat> t = threatMap.get(threatsForBug[0].toUpperCase()).getThreatList();
                    if (t.size() > 0) {
                        for (Threat details : t) {
                            if (associationcontainer1.getThreat() == null) {
                                associationcontainer1.setThreat(details.getId() + " " + details.getName());
                            } else {
                                associationcontainer1.setThreat(associationcontainer1.getThreat() + "\n" + details.getId() + ":  " + " " + details.getName());
                            }

                        }
                    } else {
                        associationcontainer1.setThreat("");
                    }
                    associationObjects.put(id, associationcontainer1);
                    id++;

                    // if there are more than one threat

                    if (size > 1) {
                        for (int i = 1; i < size; i++) {

                            AssociationContainer associationcontainer2 = new AssociationContainer();
                            associationcontainer2.setThreatCategory(ThreatCategoriesLoader.tCategories.get(threatsForBug[i]));
                            //associationcontainer.setThreat("");
                            List<Threat> t1 = threatMap.get(threatsForBug[i].toUpperCase()).getThreatList();
                            ;
                            if (t1.size() > 0) {
                                for (Threat details : t1) {
                                    if (associationcontainer2.getThreat() == null) {
                                        associationcontainer2.setThreat(details.getId() + " " + details.getName());
                                    } else {
                                        associationcontainer2.setThreat(associationcontainer2.getThreat() + "\n" + details.getId() + ":  " + " " + details.getName());
                                    }

                                }
                            } else {
                                associationcontainer2.setThreat("");
                            }
                            associationObjects.put(id, associationcontainer2);
                            id++;
                        }

                    }
                } else {
                    associationcontainer.setThreatCategory("");
                    associationcontainer.setThreat("");
                    associationObjects.put(id, associationcontainer);
                    hasBugNameId = id;
                    id++;
                }

            } else if (bug.getBug() != "" && bug.getCategory() == "") {
                AssociationContainer container = associationObjects.get(hasBugNameId);
                container.setBug(container.getBug() + "\n" + bug.getBug());
                associationObjects.put(hasBugNameId, container);
                //id++;

                *//*associationcontainer.setBugCategory("");
                associationcontainer.setBug(bug.getBug());
                associationObjects.put(id,associationcontainer);*//*
            }


        }*/
        AssociationData = FXCollections.observableArrayList(associationObjects.values());
    }


    public void setThreatProperties(ObservableList<ThreatMitigation> data) {

        designThreatColumn.setCellValueFactory(new PropertyValueFactory<ThreatMitigation, String>("threat"));
        designThreatColumn.prefWidthProperty().bind(designTable.widthProperty().divide(5));

        designCategoryColumn.setCellValueFactory(new PropertyValueFactory<ThreatMitigation, String>("category"));
        designCategoryColumn.prefWidthProperty().bind(designTable.widthProperty().divide(5));

        designMitigationColumn.setCellValueFactory(new PropertyValueFactory<ThreatMitigation, String>("mitigation"));
        designMitigationColumn.prefWidthProperty().bind(designTable.widthProperty().divide(1.5));


        designTable.setItems(data);
    }

    public void setBugProperties(ObservableList<BugCountermeasures> data) {
        sourceBugColumn.setCellValueFactory(new PropertyValueFactory<BugCountermeasures, String>("bug"));
        sourceBugColumn.prefWidthProperty().bind(sourceTable.widthProperty().divide(5));

        sourceCategoryColumn.setCellValueFactory(new PropertyValueFactory<BugCountermeasures, String>("category"));
        sourceCategoryColumn.prefWidthProperty().bind(sourceTable.widthProperty().divide(5));

        sourcePreventionColumn.setCellValueFactory(new PropertyValueFactory<BugCountermeasures, String>("countermeasure"));
        sourcePreventionColumn.prefWidthProperty().bind(sourceTable.widthProperty().divide(1.5));

        sourceTable.setItems(data);
    }

    public void populateBugs(ObservableList<BugCountermeasures> inputList) {
        sourceTable.getItems().removeAll();
        //System.out.println("before " + sourceTable.getItems().size());
        sourceTable.setItems(inputList);
        //System.out.println(sourceTable.getItems().get(0).getBug());
    }

    private void setAssociationProperties(ObservableList<AssociationContainer> data) {

        //System.out.println(AssociationData.get(0).getBug() + AssociationData.get(0).getBugCategory());

        associationthreat.setCellValueFactory(new PropertyValueFactory<AssociationContainer, String>("threat"));
        associationthreat.prefWidthProperty().bind(associationTable.widthProperty().divide(5));

        associationthreatcategory.setCellValueFactory(new PropertyValueFactory<AssociationContainer, String>("threatCategory"));
        associationthreatcategory.prefWidthProperty().bind(associationTable.widthProperty().divide(3));

        assosiationbug.setCellValueFactory(new PropertyValueFactory<AssociationContainer, String>("bug"));
        assosiationbug.prefWidthProperty().bind(associationTable.widthProperty().divide(5));

        associationbugcategory.setCellValueFactory(new PropertyValueFactory<AssociationContainer, String>("bugCategory"));
        associationbugcategory.prefWidthProperty().bind(associationTable.widthProperty().divide(3));

        associationTable.setItems(data);
    }

    @FXML
    private void settingsSubAction(ActionEvent event) {
        start("/fxml/Settings.fxml", "Settings", true, 0);
    }

    @FXML
    private void sourceNextBtnAction(ActionEvent event) {
        int selectedNum = homeTabPane.getSelectionModel().getSelectedIndex();

        if (selectedNum == 0) {
            boolean returned = fileOpen("Select Threat Report", "TMT Files (*.tm7)", "*.tm7");
            if (returned) {
                homeTabPane.getSelectionModel().select(1);
                threatLoader();
                setThreatProperties(threatData);
            }
        } else {
            homeTabPane.getSelectionModel().select(2);
            associationLoader();
            setAssociationProperties(AssociationData);
        }
    }

    @FXML
    private void designSaveBtnAction(ActionEvent event) {

        this.saveReport(ReportType.THREAT_REPORT, FileFormat.CNX);
    }

    @FXML
    private void designNextBtnAction(ActionEvent event) {
        int selectedNum = homeTabPane.getSelectionModel().getSelectedIndex();
        isHomeOpened = true;

        if (selectedNum == 0) {

            try{
                start("/fxml/BugInputWindow.fxml", "Bug Input Window");
            }catch(Exception e){
                Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while opening the Bug Input Window.");
                alert.showAndWait();
            }

            homeTabPane.getSelectionModel().select(1);
            bugLoader();
            setBugProperties(bugData);

        } else {
            homeTabPane.getSelectionModel().select(2);
            associationLoader();
            setAssociationProperties(AssociationData);
        }
    }

    private void cancelBtnMethod() {
        Alert alert = this.createAlert(Alert.AlertType.CONFIRMATION, "Confirm!", null, "\n Are you sure you want to exit?");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.YES) {
                Stage homeStage = (Stage) this.sourceCancelBtn.getScene().getWindow();
                homeStage.close();
            } else if (result.get() == ButtonType.NO) {
                alert.close();
            }
    }






    @FXML
    private void sourceCancelBtnAction(ActionEvent event) {
        try {
            cancelBtnMethod();
        } catch (Exception e) {
            Alert alert = this.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n Error occurred while closing the window.");
            alert.showAndWait();
        }
    }

    @FXML
    private void designCancelBtnAction(ActionEvent event) {
        try {
            cancelBtnMethod();
        } catch (Exception e) {
            Alert alert = this.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n Error occurred while closing the window.");
            alert.showAndWait();
        }
    }

    @FXML
    private void associationCancelBtnAction(ActionEvent event) {
        try {
            cancelBtnMethod();
        } catch (Exception e) {
            Alert alert = this.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n Error occurred while closing the window.");
            alert.showAndWait();
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
            Alert alert = createAlert(Alert.AlertType.ERROR, "Error", "Invalid Threat Model", "\n Threat Category model does not maatch with STRIDE !");
            alert.showAndWait();
        } catch (DocumentException e) {
            e.printStackTrace();
            Alert alert = createAlert(Alert.AlertType.ERROR, "Error", "Invalid File", "\n Threat Report is invalid !");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void start(String path, String title)  {

        try{
            Parent root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        }catch(IOException ie){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while opening the Window.");
            alert.showAndWait();
        }
    }

    public static Alert createAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        return alert;
    }

    @FXML
    private void sourceSaveBtnAction(ActionEvent event) {

        this.saveReport(ReportType.BUG_REPORT, FileFormat.CNX);
    }

    @FXML
    private void analysisSaveBtnAction(ActionEvent event) {

        this.saveReport(ReportType.ASSOCIATION_REPORT, FileFormat.CNX);
    }

    @FXML
    private void xmlMenuItemAction(ActionEvent event) {

        Tab selectedTab = homeTabPane.getSelectionModel().getSelectedItem();

        if (selectedTab.getId().equals("sourceTab")) {
            this.saveReport(ReportType.BUG_REPORT, FileFormat.XML);

        } else if (selectedTab.getId().equals("designTab")) {
            this.saveReport(ReportType.THREAT_REPORT, FileFormat.XML);

        } else {
            this.saveReport(ReportType.ASSOCIATION_REPORT, FileFormat.XML);
        }

    }

    @FXML
    private void jsonMenuItemAction(ActionEvent event) {

        Tab selectedTab = homeTabPane.getSelectionModel().getSelectedItem();

        if (selectedTab.getId().equals("sourceTab")) {
            this.saveReport(ReportType.BUG_REPORT, FileFormat.JSON);

        } else if (selectedTab.getId().equals("designTab")) {
            this.saveReport(ReportType.THREAT_REPORT, FileFormat.JSON);

        } else {
            this.saveReport(ReportType.ASSOCIATION_REPORT, FileFormat.JSON);
        }

    }

    @FXML
    private void newMenuItemAction(ActionEvent event) {

        Alert alert = this.createAlert(Alert.AlertType.CONFIRMATION, "Confirm!", null, "\n Do you want to save the previous analysis-?");
        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        alert.getButtonTypes().setAll(okButton, noButton);
        Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == okButton) {
                Tab selectedTab = homeTabPane.getSelectionModel().getSelectedItem();

                if (selectedTab.getId().equals("sourceTab")) {
                    this.saveReport(ReportType.BUG_REPORT, FileFormat.CNX);

                } else if (selectedTab.getId().equals("designTab")) {
                    this.saveReport(ReportType.THREAT_REPORT, FileFormat.CNX);
                    System.out.println("design");

                } else {
                    this.saveReport(ReportType.ASSOCIATION_REPORT, FileFormat.CNX);
                }
                this.openMenuMethod();
            }
            else if (result.get() == noButton) {
                this.openMenuMethod();
            }
        alert.close();
    }

    @FXML
    private void openMenuItemAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CNX File", ".cnx")
        );
        Stage stage = new Stage();
        fileChooser.setTitle("Open Project");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            System.out.println("File open");
        }
    }

    /**
     * @param reportType
     * @param fileFormat
     * @throws JsonProcessingException
     */

    private void saveReport(ReportType reportType, FileFormat fileFormat) {

        Object reportObject;

        try{
            switch (reportType) {
                case THREAT_REPORT:
                    reportObject = this.convertToThreatReport();
                    break;
                case BUG_REPORT:
                    reportObject = this.convertToBugReport();
                    break;
                case ASSOCIATION_REPORT:
                    reportObject = this.convertToAssociationReport();
                    break;
                default:
                    return;
            }

            if (reportObject != null) {

                String outputFileAsString;
                String fileDescription;
                String fileExtension;

                switch (fileFormat) {
                    case CNX:
                        outputFileAsString = this.convertToXML(reportObject);
                        fileDescription = "CNX File";
                        fileExtension = "*.cnx";
                        break;
                    case XML:
                        outputFileAsString = this.convertToXML(reportObject);
                        fileDescription = "XML File";
                        fileExtension = "*.xml";
                        break;
                    case JSON:
                        outputFileAsString = this.convertToJSON(reportObject);
                        fileDescription = "JSON File";
                        fileExtension = "*.json";
                        break;
                    default:
                        return;
                }

                boolean isSaveSucceed = this.fileSaveAction(outputFileAsString, fileDescription, fileExtension);

                if (!isSaveSucceed) {
                /*
                * error message
                */
                }
            } else {
                throw new NullPointerException("Report can not be null");
            }
        }catch(IOException io){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while saving the report.");
            alert.showAndWait();
        }

    }

    /**
     * @param outputXMLFile
     * @param fileDescription
     * @param fileExtensionFormat
     * @return
     */
    private boolean fileSaveAction(String outputXMLFile, String fileDescription, String fileExtensionFormat) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(fileDescription, fileExtensionFormat)
        );

        File file = fileChooser.showSaveDialog(this.newProjectBtn.getScene().getWindow());

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
     * @return
     */
    private ThreatReport convertToThreatReport() {

        HashMap<String, ThreatCategory> threatCategoryHashMap = new HashMap<>();

        for (ThreatCategory threatCategory : threatMap.values()) {

            threatCategoryHashMap.put(threatCategory.getId(), threatCategory);
        }

        ThreatCategoryReportCreator threatCategoryReportCreator = new ThreatCategoryReportCreator(threatCategoryHashMap);
        ThreatReport threatReport = threatCategoryReportCreator.generateReport("Threats Analysis Report");

        return threatReport;
    }

    /**
     * @return
     */
    private BugReport convertToBugReport() {

        HashMap<String, BugCategory> bugCategoryHashMap = this.initializeBugCategories();

        BugCategoryReportCreator bugCategoryReportCreator = new BugCategoryReportCreator(bugCategoryHashMap);

        BugReport bugReport = bugCategoryReportCreator.generateReport("Bug Analysis Report");

        return bugReport;
    }

    /*
    * */
    private HashMap<String,BugCategory> initializeBugCategories(){

        HashMap<String,BugCategory> bugCategoryHashMap = new HashMap<>();

        for (BugCountermeasures b : sourceTable.getItems()){

            String[] bugCategoryDetails = b.getCategory().split(":");

            String bugCategoryId = bugCategoryDetails[0];
            String bugCategoryName = bugCategoryDetails[1];

            BugCategory bugCategory = bugCategoryHashMap.get(bugCategoryId);

            if (bugCategory == null) {

                bugCategory = new BugCategory();

                bugCategory.setId(bugCategoryId);
                bugCategory.setName(bugCategoryName);

                List<Bug> fullBugList = BugInputWindowController.updetedList;

                List<Bug> bugList = new ArrayList<>();

                for (Bug bug : fullBugList){
                    if (bug.getCategoryName().split(":")[0].equals(bugCategoryId)){
                        bugList.add(bug);
                    }
                }

                bugCategory.setBugList(bugList);

                bugCategory.setCountermeasures(prolog.getPreventionTechniques(bugCategoryId.toLowerCase()));

                bugCategoryHashMap.put(bugCategoryId, bugCategory);
            }
        }
        return bugCategoryHashMap;
    }

    /**
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private AssociationReport convertToAssociationReport() /*throws ParserConfigurationException, SAXException, IOException*/ {

        AssociationReport associationReport = null;

        List<Association> associationList = this.initializeAssociations();

        try{
            //HashMap<BugCategory, String[]> bugCategoryToThreatCategoryMapping = new HashMap<>();
            //HashMap<String, ThreatCategory> threatCategoryHashMap = new HashMap<>();

            AssociationReportCreator associationReportCreator =
                    new AssociationReportCreator(associationList);
            associationReport = associationReportCreator.generateReport("Association Analysis Report");

        }catch(ParserConfigurationException pe){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while converting to association report.");
            alert.showAndWait();
        }catch(SAXException se){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while converting to association report.");
            alert.showAndWait();
        }catch(IOException ie){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while converting to association report.");
            alert.showAndWait();
        }
        return associationReport;
    }

    private List<Association> initializeAssociations(){

        List<Association> associationList = new ArrayList<>();

        for (AssociationContainer asc : associationTable.getItems()){

            Association association = new Association();

            association.setBugCategoryName(asc.getBugCategory());
            association.setThreatCategoryName(asc.getThreatCategory());

            HashMap<String, BugCategory> bugCategoryList = this.initializeBugCategories();

            association.setBugList(bugCategoryList.get(asc.getBugCategory().split(":")[0]).getBugList());

            String threatCategoryId = null;

            System.out.println(asc.getThreatCategory());

            switch (asc.getThreatCategory()){
                case "Elevation of privilege":
                    threatCategoryId = "E";
                    break;
                case "Information disclosure":
                    threatCategoryId = "I";
                    break;
                case "Repudiation":
                    threatCategoryId = "R";
                    break;
                case "Spoofing":
                    threatCategoryId = "S";
                    break;
                case "Tampering":
                    threatCategoryId = "T";
                    break;
                case "Denial of service":
                    threatCategoryId = "D";
                    break;
            }
            
            association.setThreatList(threatMap.get(threatCategoryId).getThreatList());

            associationList.add(association);
            List<Threat> empty = new ArrayList<Threat>();
            threatMap.get(threatCategoryId).setThreatList(empty);
        }

        return associationList;
    }

    /**
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    private String convertToXML(Object object) {

        String xmlReport = null;

        try{
            XMLReportBuilder xmlReportBuilder = new XMLReportBuilder();
            xmlReport = xmlReportBuilder.convertReport(object);
        }catch(JsonProcessingException je){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while converting to XML report.");
            alert.showAndWait();
        }

        return xmlReport;
    }

    /**
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    private String convertToJSON(Object object) throws JsonProcessingException {

        JSONReportBuilder jsonReportBuilder = new JSONReportBuilder();
        String jsonReport = jsonReportBuilder.convertReport(object);

        return jsonReport;
    }

    private void openMenuMethod (){


        for (String s: threatMap.keySet()){
            List<Threat> empty = new ArrayList<Threat>();
            System.out.println(s);
            threatMap.get(s).setThreatList(empty);
        }

        Stage previousStage = (Stage) this.newProjectBtn.getScene().getWindow();
        previousStage.close();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/NewProjectWindow.fxml"));
            Stage stage = new Stage();
            MainController.newProjectWindow = new Scene(root);
            MainController.newProjectWindow.getStylesheets().add("/styles/Styles.css");

            stage.setTitle("Start New Project");
            stage.setResizable(false);
            stage.setScene(MainController.newProjectWindow);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
