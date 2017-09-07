/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxui;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import source.classification.BugCategory;
import source.classification.BugClassificationModel;
import source.classification.BugControl;
import source.classification.BugControlClassificationModel;

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
    BugClassificationModel model = new BugClassificationModel();
    
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
    BugControlClassificationModel bugControlModel = new BugControlClassificationModel();

    public SettingsController() throws ParserConfigurationException, IOException, SAXException {
        //For OWASP Top 10 table
        OWASP_T10_list = model.getBugCategoriesWithDescription();
        TreeMap<Integer, BugCategory> owaspMap = new TreeMap<Integer, BugCategory>(OWASP_T10_list);
        owasp_data = FXCollections.observableArrayList(owaspMap.values());
        
        //for OWASP proactives table
        proactives_list = bugControlModel.getBugControlsWithDescription();
        TreeMap<Integer, BugControl> proactivesMap = new TreeMap<Integer, BugControl>(proactives_list);
        proactive_data = FXCollections.observableArrayList(proactivesMap.values());
        
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        //OWASP Top 10 Table settings
        t10_id.setCellValueFactory(new PropertyValueFactory<BugCategory, String>("id"));
        t10_id.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(9));
        
        t10_name.setCellValueFactory(new PropertyValueFactory<BugCategory, String>("name"));
        t10_name.setCellFactory(TextFieldTableCell.<BugCategory>forTableColumn());
        t10_name.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(5));
        
        t10_description.setCellValueFactory(new PropertyValueFactory<BugCategory, String>("description"));
        t10_description.setCellFactory(TextFieldTableCell.<BugCategory>forTableColumn());
        t10_description.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(1.5));
                
        OWASPT10_Table.setItems(owasp_data);
        
        //OWASP Proactives Table settings
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
    
}
