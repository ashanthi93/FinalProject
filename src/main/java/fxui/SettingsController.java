/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import settings.owasp_configs.OWASPT10Config;

/**
 * FXML Controller class
 *
 * @author CHAM PC
 */
public class SettingsController implements Initializable {

    @FXML
    private TableView<String[]> OWASPT10_Table;
    
    @FXML
    private TableColumn<String[], String> id;
    @FXML
    private TableColumn<String[], String> name;
    @FXML
    private TableColumn<String[], String> description;
    
    ArrayList<String[]> OWASP_T10_list;
    ObservableList<String[]> owasp_data;
    OWASPT10Config readConfig = new OWASPT10Config();

    public SettingsController() throws ParserConfigurationException, IOException, SAXException {
        OWASP_T10_list = readConfig.loadConfigFile();
        Collection<String[]> collection = new ArrayList<String[]>(OWASP_T10_list);
        owasp_data = FXCollections.observableArrayList(collection);
        
    }
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory<String[], String>("id"));
        name.setCellValueFactory(new PropertyValueFactory<String[], String>("name"));
        description.setCellValueFactory(new PropertyValueFactory<String[], String>("description"));
        
        OWASPT10_Table.setItems(owasp_data);
        
        for (String[] owasp_data1 : owasp_data) {
            System.out.println(owasp_data1[0] + ", " + OWASPT10_Table.getId());
            //OWASPT10_Table.getId();
        }
        
    }    
    
}
