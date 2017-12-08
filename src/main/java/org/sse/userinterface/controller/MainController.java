package org.sse.userinterface.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.sse.source.BugCategoriesLoader;
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

    @FXML
    private void settingsButtonAction(ActionEvent event) throws Exception {

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
    }

    @FXML
    private void startAnlzButtonAction(ActionEvent event) throws Exception {
        start("/fxml/NewProjectWindow.fxml", "Start New Project", false);
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
    private void openMenuItemAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CNX File (*.cnx)", "*.cnx")
        );

        Stage stage = new Stage();
        fileChooser.setTitle("Open Project");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {

            System.out.println("XML VALidation : " + xmlValidation(file));

        } else {
            /**
             * Error messgae - Invalid file
             */
        }
    }

    private boolean xmlValidation(File xmlFile) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();

            builder.setErrorHandler(new ErrorHandler() {

                //To handle Fatal Errors
                public void fatalError(SAXParseException exception) throws SAXException {
                    System.out.println("Line: " + exception.getLineNumber() + "\nFatal Error: " + exception.getMessage());
                }

                //To handle Errors
                public void error(SAXParseException e) throws SAXParseException {
                    System.out.println("Line: " + e.getLineNumber() + "\nError: " + e.getMessage());
                }

                //To Handle warnings
                public void warning(SAXParseException err) throws SAXParseException {
                    System.out.println("Line: " + err.getLineNumber() + "\nWarning: " + err.getMessage());
                }
            });

            Document xmlDocument = builder.parse(new FileInputStream(xmlFile));
            DOMSource source = new DOMSource(xmlDocument);

            StreamResult result = new StreamResult(System.out);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "/dtds/threatreport.dtd");
            transformer.transform(source, result);

            return true;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return false;
    }
}
