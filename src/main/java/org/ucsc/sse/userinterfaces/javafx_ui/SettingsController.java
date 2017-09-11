/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ucsc.sse.userinterfaces.javafx_ui;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.ucsc.sse.datamodels.source_code.BugCategory;
import org.ucsc.sse.classifierbuilders.source_code.BugClassificationBuilder;
import org.ucsc.sse.datamodels.source_code.BugControl;
import org.ucsc.sse.classifierbuilders.source_code.BugControlClassificationBuilder;
import org.ucsc.sse.classifierbuilders.source_code.BugToBugControlClassificationModel;
import org.ucsc.sse.classifierbuilders.source_code.BugToBugControlMapping;

/**
 * FXML Controller class
 *
 * @author CHAM PC
 */
public class SettingsController implements Initializable {

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
    BugClassificationBuilder model = new BugClassificationBuilder();
    
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
    BugControlClassificationBuilder bugControlModel = new BugControlClassificationBuilder();
    
    //For OWASP_proactives mapping table
    @FXML
    private TableView<BugToBugControlMapping> proactMap_table;
    
    @FXML
    private TableColumn<BugToBugControlMapping, String> proact;
    @FXML
    private TableColumn<BugToBugControlMapping, CheckBox> owaspProMap_a1;
    @FXML
    private TableColumn<BugToBugControlMapping, CheckBox> owaspProMap_a2;
    @FXML
    private TableColumn<BugToBugControlMapping, CheckBox> owaspProMap_a3;
    @FXML
    private TableColumn<BugToBugControlMapping, CheckBox> owaspProMap_a4;
    @FXML
    private TableColumn<BugToBugControlMapping, CheckBox> owaspProMap_a5;
    @FXML
    private TableColumn<BugToBugControlMapping, CheckBox> owaspProMap_a6;
    @FXML
    private TableColumn<BugToBugControlMapping, CheckBox> owaspProMap_a7;
    @FXML
    private TableColumn<BugToBugControlMapping, CheckBox> owaspProMap_a8;
    @FXML
    private TableColumn<BugToBugControlMapping, CheckBox> owaspProMap_a9;
    @FXML
    private TableColumn<BugToBugControlMapping, CheckBox> owaspProMap_a10;
    
    HashMap<Integer, BugToBugControlMapping> OWASP_proactives_mapping;
    ObservableList<BugToBugControlMapping> OWASP_proactive_MappingData;
    BugToBugControlClassificationModel owaspMappingModel = new BugToBugControlClassificationModel();

    public SettingsController() throws ParserConfigurationException, IOException, SAXException {
        //For OWASP Top 10 table
        OWASP_T10_list = model.getBugCategoriesWithDescription();
        TreeMap<Integer, BugCategory> owaspTreeMap = new TreeMap<Integer, BugCategory>(OWASP_T10_list);
        owasp_data = FXCollections.observableArrayList(owaspTreeMap.values());
        
        //for OWASP proactives table
        proactives_list = bugControlModel.getBugControlsWithDescription();
        TreeMap<Integer, BugControl> proactivesTreeMap = new TreeMap<Integer, BugControl>(proactives_list);
        proactive_data = FXCollections.observableArrayList(proactivesTreeMap.values());
        
        //for OWASP_proactives mapping table 
        OWASP_proactives_mapping = owaspMappingModel.getMapping();
        TreeMap<Integer, BugToBugControlMapping> proactiveMappingTreeMap = new TreeMap<Integer, BugToBugControlMapping>(OWASP_proactives_mapping);
        OWASP_proactive_MappingData = FXCollections.observableArrayList(proactiveMappingTreeMap.values());
        
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        //OWASP Top 10 Table org.ucsc.sse.knowedgemodel.settings
        setOWASPT10TableProperties();
        
        //OWASP Proactives Table org.ucsc.sse.knowedgemodel.settings
        setOWASPProactivesTableProperties();
        
        //OWASP proatcives mapping table properties
        setOWASP_ProactivesMappingTableProperties();
        
    }    
    
    private void setOWASPT10TableProperties(){
        t10_id.setCellValueFactory(new PropertyValueFactory<BugCategory, String>("id"));
        t10_id.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(9));
        
        t10_name.setCellValueFactory(new PropertyValueFactory<BugCategory, String>("name"));
        t10_name.setCellFactory(TextFieldTableCell.<BugCategory>forTableColumn());
        t10_name.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(5));
        
        t10_description.setCellValueFactory(new PropertyValueFactory<BugCategory, String>("description"));
        t10_description.setCellFactory(TextFieldTableCell.<BugCategory>forTableColumn());
        t10_description.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(1.5));
                
        OWASPT10_Table.setItems(owasp_data);
    }
    
    private void setOWASPProactivesTableProperties(){
        proact_id.setCellValueFactory(new PropertyValueFactory<BugControl, String>("id"));
        proact_id.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(9));
        
        proact_name.setCellValueFactory(new PropertyValueFactory<BugControl, String>("name"));
        proact_name.setCellFactory(TextFieldTableCell.<BugControl>forTableColumn());
        proact_name.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(5));
        
        proact_description.setCellValueFactory(new PropertyValueFactory<BugControl, String>("description"));
        proact_description.setCellFactory(TextFieldTableCell.<BugControl>forTableColumn());
        proact_description.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(1.5));
                
        proactive_table.setItems(proactive_data);
    }
    
    private void setOWASP_ProactivesMappingTableProperties(){

        proact.setCellValueFactory(new PropertyValueFactory<BugToBugControlMapping, String>("control"));
        proact.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));
        
        owaspProMap_a1.setCellValueFactory( new Callback<CellDataFeatures<BugToBugControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugToBugControlMapping, CheckBox> p) {
                BugToBugControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);
                
                if(obj.getA1()){
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a1.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a2.setCellValueFactory( new Callback<CellDataFeatures<BugToBugControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugToBugControlMapping, CheckBox> p) {
                BugToBugControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);
                
                if(obj.getA2()){
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a2.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a3.setCellValueFactory( new Callback<CellDataFeatures<BugToBugControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugToBugControlMapping, CheckBox> p) {
                BugToBugControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);
                
                if(obj.getA3()){
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a3.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a4.setCellValueFactory( new Callback<CellDataFeatures<BugToBugControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugToBugControlMapping, CheckBox> p) {
                BugToBugControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);
                
                if(obj.getA4()){
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a4.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a5.setCellValueFactory( new Callback<CellDataFeatures<BugToBugControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugToBugControlMapping, CheckBox> p) {
                BugToBugControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);
                
                if(obj.getA5()){
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a5.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a6.setCellValueFactory( new Callback<CellDataFeatures<BugToBugControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugToBugControlMapping, CheckBox> p) {
                BugToBugControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);
                
                if(obj.getA6()){
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a6.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a7.setCellValueFactory( new Callback<CellDataFeatures<BugToBugControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugToBugControlMapping, CheckBox> p) {
                BugToBugControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);
                
                if(obj.getA7()){
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a7.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a8.setCellValueFactory( new Callback<CellDataFeatures<BugToBugControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugToBugControlMapping, CheckBox> p) {
                BugToBugControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);
                
                if(obj.getA8()){
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a8.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a9.setCellValueFactory( new Callback<CellDataFeatures<BugToBugControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugToBugControlMapping, CheckBox> p) {
                BugToBugControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);
                
                if(obj.getA9()){
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a9.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));

        owaspProMap_a10.setCellValueFactory( new Callback<CellDataFeatures<BugToBugControlMapping, CheckBox>, ObservableValue<CheckBox>>() {

            public ObservableValue<CheckBox> call(CellDataFeatures<BugToBugControlMapping, CheckBox> p) {
                BugToBugControlMapping obj = p.getValue();
                CheckBox box = new CheckBox();
                box.setAlignment(Pos.CENTER);
                
                if(obj.getA10()){
                    box.selectedProperty().setValue(Boolean.TRUE);
                }
                return new SimpleObjectProperty<CheckBox>(box);
            }
        });
        owaspProMap_a10.prefWidthProperty().bind(proactMap_table.widthProperty().divide(11));
        
        proactMap_table.setItems(OWASP_proactive_MappingData);
    }
    
}
