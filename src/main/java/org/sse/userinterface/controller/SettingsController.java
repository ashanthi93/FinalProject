/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sse.userinterface.controller;

import java.beans.beancontext.BeanContextChildComponentProxy;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import javax.print.Doc;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.dom4j.Text;
import org.sse.association.semantic.SemanticAssociationsLoader;
import org.sse.association.semantic.model.SemanticAssociation;
import org.sse.knowedgemodel.prolog.KbBuilder;
import org.sse.settings.config.source.BugModelConfig;
import org.sse.settings.config.source.control.BugControlConfig;
import org.sse.settings.config.source.mapping.MappingConfig;
import org.xml.sax.SAXException;
import org.sse.source.model.BugCategory;
import org.sse.source.BugCategoriesLoader;
import org.sse.source.model.BugControl;
import org.sse.source.BugControlsLoader;
import org.sse.source.BugCategoryToControlMappingHandler;
import org.sse.source.BugCategoryToControlMapping;

import static org.sse.userinterface.controller.NewProjectWindowController.createAlert;

public class SettingsController implements Initializable {

    Boolean isT10Edited = false;
    Boolean isProactivesEdited = false;
    Boolean isMappingEdited = false;

    @FXML
    private JFXButton owaspNextBtn;

    @FXML
    private JFXButton proactAddBtn;

    @FXML
    private JFXButton proactDeleteBtn;

    @FXML
    private JFXButton proactiveNextBtn;

    @FXML
    private JFXButton btnSave;

    @FXML
    private TextField owaspTop10Version;
    @FXML
    private TextField proactiveVersion;

    @FXML
    private Label t10Version;
    @FXML
    private Label proVersion;

    @FXML
    private TabPane settingsTabPane;

    @FXML private Tab proactivesTab;
    @FXML private Tab mappingTab;

    //for updated OWASP Top 10 table
    List<BugCategory> updatedOWASP_T10_list;

    //for updated OWASP proactives table
    List<BugControl> updatedProactives_list;

    //for updated OWASP Top 10 mapping table
    List<BugCategoryToControlMapping> updatedOWASP_proactives_mapping;

    //For OWASP Top 10 table
    @FXML
    private TableView<BugCategory> OWASPT10_Table;

    @FXML
    private TableColumn<BugCategory, String> t10_id;
    @FXML
    private TableColumn<BugCategory, String> t10_name;
    @FXML
    private TableColumn<BugCategory, String> t10_description;

    HashMap<Integer, BugCategory> OWASP_T10_list;
    ObservableList<BugCategory> owasp_data;
    ArrayList<BugCategory> copyOf_owasp_data = new ArrayList<BugCategory>();

    //For OWASP Proactives table
    @FXML
    private TableView<BugControl> proactive_table;

    @FXML
    private TableColumn<BugControl, String> proact_id;
    @FXML
    private TableColumn<BugControl, String> proact_name;
    @FXML
    private TableColumn<BugControl, String> proact_description;

    HashMap<Integer, BugControl> proactives_list;
    ObservableList<BugControl> proactive_data;
    ArrayList<BugControl> copyOf_proactive_data = new ArrayList<BugControl>();

    //For OWASP_proactives mapping table
    @FXML
    private TableView<BugCategoryToControlMapping> proactMap_table;

    @FXML
    private TableColumn<BugCategoryToControlMapping, String> proact;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a1;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a2;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a3;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a4;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a5;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a6;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a7;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a8;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a9;
    @FXML
    private TableColumn<BugCategoryToControlMapping, CheckBox> owaspProMap_a10;

    HashMap<Integer, BugCategoryToControlMapping> OWASP_proactives_mapping;
    ObservableList<BugCategoryToControlMapping> OWASP_proactive_MappingData;
    ArrayList<BugCategoryToControlMapping> copyOf_mapping_data = new ArrayList<BugCategoryToControlMapping>();
    BugCategoryToControlMappingHandler owaspMappingModel = new BugCategoryToControlMappingHandler();

    public SettingsController() {
        try{
            //For OWASP Top 10 table
            OWASP_T10_list = BugCategoriesLoader.getBugCategoryWithDescriptionHashMap();
            TreeMap<Integer, BugCategory> owaspTreeMap = new TreeMap<Integer, BugCategory>(OWASP_T10_list);
            owasp_data = FXCollections.observableArrayList(owaspTreeMap.values());

            for (BugCategory obj : owasp_data) {
                BugCategory copy = new BugCategory();
                copy.setId(obj.getId());
                copy.setName(obj.getName());
                copy.setDescription(obj.getDescription());
                copyOf_owasp_data.add(copy);
            }
        }catch (DocumentException de){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occured while loading Bug Categories.");
            alert.showAndWait();
        }

        try{
            //for OWASP proactives table
            proactives_list = BugControlsLoader.getBugControlsWithDescription();
            TreeMap<Integer, BugControl> proactivesTreeMap = new TreeMap<Integer, BugControl>(proactives_list);
            proactive_data = FXCollections.observableArrayList(proactivesTreeMap.values());

            for (BugControl obj : proactive_data) {
                BugControl copy = new BugControl();
                copy.setId(obj.getId());
                copy.setName(obj.getName());
                copy.setDescription(obj.getDescription());
                copyOf_proactive_data.add(copy);
            }
        }catch (DocumentException de){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occured while loading Proactives.");
            alert.showAndWait();
        }

        try{
            //for OWASP_proactives mapping table
            OWASP_proactives_mapping = owaspMappingModel.getMapping();
            TreeMap<Integer, BugCategoryToControlMapping> proactiveMappingTreeMap = new TreeMap<Integer, BugCategoryToControlMapping>(OWASP_proactives_mapping);
            OWASP_proactive_MappingData = FXCollections.observableArrayList(proactiveMappingTreeMap.values());

            for (BugCategoryToControlMapping obj : OWASP_proactive_MappingData) {
                BugCategoryToControlMapping copy = new BugCategoryToControlMapping();
                copy.setControl(obj.getControl());
                copy.setA1(obj.getA1());
                copy.setA2(obj.getA2());
                copy.setA3(obj.getA3());
                copy.setA4(obj.getA4());
                copy.setA5(obj.getA5());
                copy.setA6(obj.getA6());
                copy.setA7(obj.getA7());
                copy.setA8(obj.getA8());
                copy.setA9(obj.getA9());
                copy.setA10(obj.getA10());
                copyOf_mapping_data.add(copy);
            }
        }catch (DocumentException de){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occured while loading Proactives mapping data.");
            alert.showAndWait();
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {

        try {
            //OWASP Top 10 Table org.sse.knowedgemodel.settings
            setOWASPT10TableProperties();

            String t10VersionValue = BugCategoriesLoader.getVersionName();
            owaspTop10Version.setText(t10VersionValue);
            t10Version.setText(t10VersionValue);

            //OWASP Proactives Table org.sse.knowedgemodel.settings
            setOWASPProactivesTableProperties();

            String proactiveVersionValue = BugControlsLoader.getVersionName();
            proactiveVersion.setText(proactiveVersionValue);
            proVersion.setText(proactiveVersionValue);

            //OWASP proatcives mapping table properties
            setOWASP_ProactivesMappingTableProperties();

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void setOWASPT10TableProperties() {
        t10_id.setCellValueFactory(new PropertyValueFactory<BugCategory, String>("id"));
        t10_id.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(9));


        t10_name.setCellValueFactory(new PropertyValueFactory<BugCategory, String>("name"));
        t10_name.setCellFactory(TextFieldTableCell.<BugCategory>forTableColumn());
        t10_name.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(5));
        t10_name.setOnEditCommit(event -> {
            BugCategory row = event.getRowValue();
            row.setName(event.getNewValue());
        });

        t10_description.setCellValueFactory(new PropertyValueFactory<BugCategory, String>("description"));
        t10_description.setCellFactory(TextFieldTableCell.<BugCategory>forTableColumn());
        t10_description.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(1.5));
        t10_description.setOnEditCommit(event -> {
            BugCategory row = event.getRowValue();
            row.setDescription(event.getNewValue());
        });

        OWASPT10_Table.setItems(owasp_data);
    }

    private void setOWASPProactivesTableProperties() {
        proact_id.setCellValueFactory(new PropertyValueFactory<BugControl, String>("id"));
        proact_id.setCellFactory(TextFieldTableCell.<BugControl>forTableColumn());
        proact_id.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(9));
        proact_id.setOnEditCommit(event -> {
            BugControl row = event.getRowValue();
            row.setId(event.getNewValue());
        });

        proact_name.setCellValueFactory(new PropertyValueFactory<BugControl, String>("name"));
        proact_name.setCellFactory(TextFieldTableCell.<BugControl>forTableColumn());
        proact_name.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(5));
        proact_name.setOnEditCommit(event -> {
            BugControl row = event.getRowValue();
            row.setName(event.getNewValue());
        });

        proact_description.setCellValueFactory(new PropertyValueFactory<BugControl, String>("description"));
        proact_description.setCellFactory(TextFieldTableCell.<BugControl>forTableColumn());
        proact_description.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(1.5));
        proact_description.setOnEditCommit(event -> {
            BugControl row = event.getRowValue();
            row.setDescription(event.getNewValue());
        });

        proactive_table.setItems(proactive_data);
    }

    private void setOWASP_ProactivesMappingTableProperties() {

        proact.setCellValueFactory(new PropertyValueFactory<BugCategoryToControlMapping, String>("control"));
        proact.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));
        proact.setOnEditCommit(event -> {
            BugCategoryToControlMapping row = event.getRowValue();
            row.setControl(event.getNewValue());
        });

        owaspProMap_a1.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<BugCategoryToControlMapping, CheckBox> param) {
                BugCategoryToControlMapping obj = param.getValue();

                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().setValue(obj.getA1());
                checkBox.selectedProperty().addListener((ov, old_val, new_val) -> {
                    obj.setA1(new_val);
                });

                checkBox.setAlignment(Pos.CENTER);
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });
        owaspProMap_a1.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a2.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> param) {
                BugCategoryToControlMapping obj = param.getValue();

                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().setValue(obj.getA2());
                checkBox.selectedProperty().addListener((ov, old_val, new_val) -> {
                    obj.setA2(new_val);
                });

                checkBox.setAlignment(Pos.CENTER);
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });
        owaspProMap_a2.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a3.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> param) {
                BugCategoryToControlMapping obj = param.getValue();

                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().setValue(obj.getA3());
                checkBox.selectedProperty().addListener((ov, old_val, new_val) -> {
                    obj.setA3(new_val);
                });

                checkBox.setAlignment(Pos.CENTER);
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });
        owaspProMap_a3.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a4.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> param) {
                BugCategoryToControlMapping obj = param.getValue();

                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().setValue(obj.getA4());
                checkBox.selectedProperty().addListener((ov, old_val, new_val) -> {
                    obj.setA4(new_val);
                });

                checkBox.setAlignment(Pos.CENTER);
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });
        owaspProMap_a4.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a5.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> param) {
                BugCategoryToControlMapping obj = param.getValue();

                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().setValue(obj.getA5());
                checkBox.selectedProperty().addListener((ov, old_val, new_val) -> {
                    obj.setA5(new_val);
                });

                checkBox.setAlignment(Pos.CENTER);
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });
        owaspProMap_a5.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a6.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> param) {
                BugCategoryToControlMapping obj = param.getValue();

                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().setValue(obj.getA6());
                checkBox.selectedProperty().addListener((ov, old_val, new_val) -> {
                    obj.setA6(new_val);
                });

                checkBox.setAlignment(Pos.CENTER);
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });
        owaspProMap_a6.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a7.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> param) {
                BugCategoryToControlMapping obj = param.getValue();

                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().setValue(obj.getA7());
                checkBox.selectedProperty().addListener((ov, old_val, new_val) -> {
                    obj.setA7(new_val);
                });

                checkBox.setAlignment(Pos.CENTER);
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });
        owaspProMap_a7.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a8.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> param) {
                BugCategoryToControlMapping obj = param.getValue();

                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().setValue(obj.getA8());
                checkBox.selectedProperty().addListener((ov, old_val, new_val) -> {
                    obj.setA8(new_val);
                });

                checkBox.setAlignment(Pos.CENTER);
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });
        owaspProMap_a8.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a9.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> param) {
                BugCategoryToControlMapping obj = param.getValue();

                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().setValue(obj.getA9());
                checkBox.selectedProperty().addListener((ov, old_val, new_val) -> {
                    obj.setA9(new_val);
                });

                checkBox.setAlignment(Pos.CENTER);
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });
        owaspProMap_a9.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a10.setCellValueFactory(new Callback<CellDataFeatures<BugCategoryToControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugCategoryToControlMapping, CheckBox> param) {
                BugCategoryToControlMapping obj = param.getValue();

                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().setValue(obj.getA10());
                checkBox.selectedProperty().addListener((ov, old_val, new_val) -> {
                    obj.setA10(new_val);
                });

                checkBox.setAlignment(Pos.CENTER);
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });
        owaspProMap_a10.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        proactMap_table.setItems(OWASP_proactive_MappingData);
    }

    @FXML
    private void owaspNextBtnAction(ActionEvent event) {

        updatedOWASP_T10_list = new ArrayList<BugCategory>();

        ObservableList<BugCategory> updatedOWASP_T10 = OWASPT10_Table.getItems();

        updatedOWASP_T10_list = updatedOWASP_T10;

        //updatedOWASP_T10.sorted();
        for (int i = 0; i < copyOf_owasp_data.size(); i++) {
            if (!copyOf_owasp_data.get(i).getName().equals(updatedOWASP_T10_list.get(i).getName()) || !copyOf_owasp_data.get(i).getDescription().equals(updatedOWASP_T10_list.get(i).getDescription())) {
                isT10Edited = true;
                break;
            }
        }

        for (BugCategory obj : updatedOWASP_T10_list) {
            if (obj.getId().equals("") || obj.getName().equals("") || obj.getDescription().equals("")) {
                Alert alert = createAlert(Alert.AlertType.WARNING, "Warning", null, "\n  Please enter all the details in every row!");
                alert.showAndWait();
                return;
            }
        }

        SingleSelectionModel<Tab> selectionModel = settingsTabPane.getSelectionModel();
        selectionModel.select(1);

        proactivesTab.setDisable(false);
    }

    @FXML
    private void proactAddBtnAction(ActionEvent event) {
        BugControl newRow = new BugControl();
        newRow.setId("");
        newRow.setName("");
        newRow.setDescription("");
        proactive_table.getItems().add(newRow);
    }

    @FXML
    private void proactDeleteBtnAction(ActionEvent event) {
        proactive_table.getItems().removeAll(proactive_table.getSelectionModel().getSelectedItems());
    }

    @FXML
    private void proactiveNextBtnAction(ActionEvent event) {

        t10Version.setText(owaspTop10Version.getText());
        proVersion.setText(proactiveVersion.getText());

        updatedProactives_list = new ArrayList<BugControl>();

        ObservableList<BugControl> updatedProactives = proactive_table.getItems();

        updatedProactives_list = updatedProactives;
        //updatedProactives_list.sort(Comparator.comparing(BugControl::getId));

        int updatedSize = updatedProactives_list.size();
        int previousSize = copyOf_proactive_data.size();
        if (updatedSize == previousSize) {
            for (int i = 0; i < updatedSize; i++) {
                if (!copyOf_proactive_data.get(i).getName().equals(updatedProactives_list.get(i).getName()) || !copyOf_proactive_data.get(i).getDescription().equals(updatedProactives_list.get(i).getDescription())) {
                    isProactivesEdited = true;
                    break;
                }
            }
        } else {
            isProactivesEdited = true;
            for (BugControl obj : updatedProactives_list) {
                if (obj.getId().equals("") || obj.getName().equals("") || obj.getDescription().equals("")) {
                    Alert alert = createAlert(Alert.AlertType.WARNING, "Warning", null, "\n  Please enter all the details in every row!");
                    alert.showAndWait();
                    return;
                }
            }
        }

        SingleSelectionModel<Tab> selectionModel = settingsTabPane.getSelectionModel();
        selectionModel.select(2);

        if (isT10Edited || isProactivesEdited) {
            proactMap_table.getItems().removeAll(OWASP_proactive_MappingData);
            for (int i = 0; i < updatedSize; i++) {
                BugCategoryToControlMapping newRow = new BugCategoryToControlMapping();
                newRow.setControl(updatedProactives_list.get(i).getId());
                newRow.setA1(false);
                newRow.setA2(false);
                newRow.setA3(false);
                newRow.setA4(false);
                newRow.setA5(false);
                newRow.setA6(false);
                newRow.setA7(false);
                newRow.setA8(false);
                newRow.setA9(false);
                newRow.setA10(false);
                proactMap_table.getItems().add(newRow);
            }
        }

        mappingTab.setDisable(false);
    }

    @FXML
    private void btnSaveAction(ActionEvent event) {

        updatedOWASP_proactives_mapping = new ArrayList<BugCategoryToControlMapping>();

        ObservableList<BugCategoryToControlMapping> updatedMapping = proactMap_table.getItems();

        updatedOWASP_proactives_mapping = updatedMapping;

        if (!isT10Edited && !isProactivesEdited) {

            for (int i = 0; i < copyOf_mapping_data.size(); i++) {

                if (!copyOf_mapping_data.get(i).getControl().equals(updatedOWASP_proactives_mapping.get(i).getControl())
                        || !copyOf_mapping_data.get(i).getA1().equals(updatedOWASP_proactives_mapping.get(i).getA1())
                        || !copyOf_mapping_data.get(i).getA2().equals(updatedOWASP_proactives_mapping.get(i).getA2())
                        || !copyOf_mapping_data.get(i).getA3().equals(updatedOWASP_proactives_mapping.get(i).getA3())
                        || !copyOf_mapping_data.get(i).getA4().equals(updatedOWASP_proactives_mapping.get(i).getA4())
                        || !copyOf_mapping_data.get(i).getA5().equals(updatedOWASP_proactives_mapping.get(i).getA5())
                        || !copyOf_mapping_data.get(i).getA6().equals(updatedOWASP_proactives_mapping.get(i).getA6())
                        || !copyOf_mapping_data.get(i).getA7().equals(updatedOWASP_proactives_mapping.get(i).getA7())
                        || !copyOf_mapping_data.get(i).getA8().equals(updatedOWASP_proactives_mapping.get(i).getA8())
                        || !copyOf_mapping_data.get(i).getA9().equals(updatedOWASP_proactives_mapping.get(i).getA9())
                        || !copyOf_mapping_data.get(i).getA10().equals(updatedOWASP_proactives_mapping.get(i).getA10()
                )) {
                    isMappingEdited = true;
                    break;
                }
            }
        } else {
            isMappingEdited = true;
        }

        if (isT10Edited) {
            updateOWASPT10();
        }
        if (isProactivesEdited) {
            updateProactives();
        }
        if (isMappingEdited) {
            updateOWASP_proactives_mapping();
        }

<<<<<<< HEAD
        try{
            if (isT10Edited || isProactivesEdited || isMappingEdited) {
                KbBuilder.write();
            }

        }catch (DocumentException de){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while updating the Knowledge Base.");
            alert.showAndWait();
        }catch (IOException de){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while updating the Knowledge Base.");
            alert.showAndWait();
        }catch (ParserConfigurationException de){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while updating the Knowledge Base.");
            alert.showAndWait();
        }catch (SAXException de){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while updating the Knowledge Base.");
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while updating the Knowledge Base.");
            alert.showAndWait();
        }
    }

    private void updateOWASPT10() {
        try{
            BugModelConfig.createConfigFile(updatedOWASP_T10_list, "OWASP-Top-10", owaspTop10Version.getText());
        }catch (IOException ie){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while updating OWASP Top 10 List.");
            alert.showAndWait();
        }catch(Exception e){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while updating OWASP Top 10 List.");
            alert.showAndWait();
        }
    }

    private void updateProactives() {
        try{
            BugControlConfig.createConfigFile(updatedProactives_list, "OWASP-Proactives", proactiveVersion.getText());
        }catch (IOException ie){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while updating Proactives List.");
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while updating Proactives List.");
            alert.showAndWait();
        }
    }

    private void updateOWASP_proactives_mapping() {
=======
        /* load semantic similarity values */
        List<SemanticAssociation> semanticAssociations = SemanticAssociationsLoader.createSemanticAssociations();

        if (isT10Edited || isProactivesEdited || isMappingEdited) {
            KbBuilder.write();
            KbBuilder.writeSimilarity(semanticAssociations);
        }
    }

    /**
     * Updates owasp t10 config file
     * @throws IOException
     */
    private void updateOWASPT10() throws IOException {
        BugModelConfig.createConfigFile(updatedOWASP_T10_list, "OWASP-Top-10", owaspTop10Version.getText());
    }

    /**
     * * Updates proactive config file
     * @throws IOException
     */
    private void updateProactives() throws IOException {
        BugControlConfig.createConfigFile(updatedProactives_list, "OWASP-Proactives", proactiveVersion.getText());
    }

    /**
     * Updates mapping
     * @throws IOException
     */
    private void updateOWASP_proactives_mapping() throws IOException {
>>>>>>> 2c294e70f81962a0a49f3c3f7d794970654f40b7

        HashMap<String, List<String>> mappingHashMap = new HashMap<>();

        for (BugCategory OWASPCategory : updatedOWASP_T10_list) {
            mappingHashMap.put(OWASPCategory.getId(), new ArrayList<>());
        }

        for (BugCategoryToControlMapping bugCategoryToControlMapping : updatedOWASP_proactives_mapping) {

            String controlId = bugCategoryToControlMapping.getControl();

            if (bugCategoryToControlMapping.getA1()) {
                List<String> controlIds = mappingHashMap.get("A1");
                controlIds.add(controlId);
            }

            if (bugCategoryToControlMapping.getA2()) {
                List<String> controlIds = mappingHashMap.get("A2");
                controlIds.add(controlId);
            }

            if (bugCategoryToControlMapping.getA3()) {
                List<String> controlIds = mappingHashMap.get("A3");
                controlIds.add(controlId);
            }

            if (bugCategoryToControlMapping.getA4()) {
                List<String> controlIds = mappingHashMap.get("A4");
                controlIds.add(controlId);
            }

            if (bugCategoryToControlMapping.getA5()) {
                List<String> controlIds = mappingHashMap.get("A5");
                controlIds.add(controlId);
            }

            if (bugCategoryToControlMapping.getA6()) {
                List<String> controlIds = mappingHashMap.get("A6");
                controlIds.add(controlId);
            }

            if (bugCategoryToControlMapping.getA7()) {
                List<String> controlIds = mappingHashMap.get("A7");
                controlIds.add(controlId);
            }

            if (bugCategoryToControlMapping.getA8()) {
                List<String> controlIds = mappingHashMap.get("A8");
                controlIds.add(controlId);
            }

            if (bugCategoryToControlMapping.getA9()) {
                List<String> controlIds = mappingHashMap.get("A9");
                controlIds.add(controlId);
            }

            if (bugCategoryToControlMapping.getA10()) {
                List<String> controlIds = mappingHashMap.get("A10");
                controlIds.add(controlId);
            }
        }

        try{
            MappingConfig.createFile(mappingHashMap);
        }catch (IOException ie){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while updating the Mapping List.");
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occurred while updating the Mapping List.");
            alert.showAndWait();
        }
    }
}
