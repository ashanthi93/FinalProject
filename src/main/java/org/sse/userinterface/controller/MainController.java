package org.sse.userinterface.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.sse.association.model.AssociationContainer;
import org.sse.design.model.ThreatMitigation;
import org.sse.reportparser.CnxThreatReportPaser;
import org.sse.source.model.BugCountermeasures;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class MainController implements Initializable {

    public static Scene newProjectWindow;
    public static ObservableList<ThreatMitigation> loadedThreatData;
    public static ObservableList<BugCountermeasures> loadedBugData;
    public static ObservableList<AssociationContainer> loadedAssociationData;
    public static boolean hasThreat = false;
    public static boolean hasBug = false;
    public static boolean hasAssociation = false;

    @FXML
    private void settingsButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Settings.fxml"));
            Parent parent = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Settings");

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            double width = screenBounds.getWidth();
            double height = screenBounds.getHeight();

            Scene scene = new Scene(parent, (width * 0.8), (height * 0.8));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            stage.setX((width - stage.getWidth()) / 2);
            stage.setY((height - stage.getHeight()) / 2);
        } catch (Exception e) {
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occured while opening the Settings Window.");
            alert.showAndWait();
        }
    }

    @FXML
    private void startAnlzButtonAction(ActionEvent event) {
        try {
            start("/fxml/NewProjectWindow.fxml", "Start New Project", false);
        } catch (Exception e) {
            Alert alert = NewProjectWindowController.createAlert(Alert.AlertType.ERROR, "Error!", null, "\n  Error occured while opening the New Project Window.");
            alert.showAndWait();
        }
    }

    public void start(String path, String title, Boolean resizable) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = new Stage();
        newProjectWindow = new Scene(root);
        newProjectWindow.getStylesheets().add("/styles/Styles.css");

        stage.setTitle(title);
        stage.setResizable(resizable);
        stage.setScene(newProjectWindow);
        stage.show();
    }

    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void openMenuItemAction(ActionEvent event) throws DocumentException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CNX File (*.cnx)", "*.cnx")
        );

        Stage stage = new Stage();
        fileChooser.setTitle("Open Project");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {

            File inputFile = new File("input.txt");
            SAXReader reader = new SAXReader();
            org.dom4j.Document document = reader.read(file);
            String root = document.getRootElement().getName();

            // if it is a threat report
            if (root == "threat-category-report"){

                HashMap<Integer, ThreatMitigation> threatObjects = CnxThreatReportPaser.extractThreats(file);
                loadedThreatData = FXCollections.observableArrayList(threatObjects.values());
                hasThreat = true;
                Parent homeWindowRoot = null;
                HomeWindowController.selectedIndex = "DESIGN";
                try {
                    homeWindowRoot = FXMLLoader.load(getClass().getResource("/fxml/HomeWindow.fxml"));
                } catch (IOException e) {

                }
                Stage homewindowStage = new Stage();
                Scene scene = new Scene(homeWindowRoot);
                scene.getStylesheets().add("/styles/Styles.css");

                homewindowStage.setTitle("Home Window");
                homewindowStage.setScene(scene);
                homewindowStage.setMaximized(true);
                homewindowStage.show();

            }
            // if it is a bug report
            else if (root == "bug-category-report"){

                HashMap<Integer, BugCountermeasures> bugObjects = CnxThreatReportPaser.extractBugs(file);
                loadedBugData = FXCollections.observableArrayList(bugObjects.values());
                hasBug = true;
                Parent homeWindowRoot = null;
                HomeWindowController.selectedIndex = "Source";
                try {
                    homeWindowRoot = FXMLLoader.load(getClass().getResource("/fxml/HomeWindow.fxml"));
                } catch (IOException e) {

                }
                Stage homewindowStage = new Stage();
                Scene scene = new Scene(homeWindowRoot);
                scene.getStylesheets().add("/styles/Styles.css");

                homewindowStage.setTitle("Home Window");
                homewindowStage.setScene(scene);
                homewindowStage.setMaximized(true);
                homewindowStage.show();

            }
            // if it is a association report
            else {

            }

            //System.out.println("XML VALidation : " + xmlValidation(file));

        } else {
            /**
             * Error messgae - Invalid file
             */
        }
    }

    /**
     * @param xmlFile
     * @return
     */
    private boolean xmlValidation(File xmlFile) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();

            builder.setErrorHandler(new ErrorHandler() {

                //To handle Fatal Errors
                public void fatalError(SAXParseException exception) throws SAXException {
                    System.err.println("Line: " + exception.getLineNumber() + "\nFatal Error: " + exception.getMessage());
                    return;
                }

                //To handle Errors
                public void error(SAXParseException e) throws SAXParseException {
                    System.err.println("Line: " + e.getLineNumber() + "\nError: " + e.getMessage());
                    return;
                }

                //To Handle warnings
                public void warning(SAXParseException err) throws SAXParseException {
                    System.err.println("Line: " + err.getLineNumber() + "\nWarning: " + err.getMessage());
                    return;
                }
            });

            Document xmlDocument = builder.parse(new FileInputStream(xmlFile));
            DOMSource source = new DOMSource(xmlDocument);

            StreamResult result = new StreamResult(System.out);

            TransformerFactory tf = TransformerFactory.newInstance();

            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "/dtds/threatreport.dtd");
            transformer.transform(source, result);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            return false;
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        } catch (TransformerException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
