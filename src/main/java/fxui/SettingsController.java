/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxui;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
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
    private TableColumn<BugCategory, String> id;
    @FXML
    private TableColumn<BugCategory, String> name;
    @FXML
    private TableColumn<BugCategory, String> description;
    
    HashMap<String, BugCategory> OWASP_T10_list;
    ObservableList<BugCategory> owasp_data;
    BugClassificationModel model = new BugClassificationModel();
    
    //For OWASP Proactives table
    

    public SettingsController() throws ParserConfigurationException, IOException, SAXException {
        //For OWASP Top 10 table
        OWASP_T10_list = model.getBugCategoriesWithDescription();
        owasp_data = FXCollections.observableArrayList(OWASP_T10_list.values());
        
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        //OWASP Top 10 Table settings
        id.setCellValueFactory(new PropertyValueFactory<BugCategory, String>("id"));
        id.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(9));
        
        name.setCellValueFactory(new PropertyValueFactory<BugCategory, String>("name"));
        name.setCellFactory(TextFieldTableCell.<BugCategory>forTableColumn());
        name.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(5));
        
        description.setCellValueFactory(new PropertyValueFactory<BugCategory, String>("description"));
        description.setCellFactory(TextFieldTableCell.<BugCategory>forTableColumn());
        description.prefWidthProperty().bind(OWASPT10_Table.widthProperty().divide(1.5));
                
        OWASPT10_Table.setItems(owasp_data);
        
    }    
    
}
