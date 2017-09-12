package org.ucsc.sse.knowedgemodel.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ConfigXMLFileCreator {

    Document document;
    Element parentElement;

    public ConfigXMLFileCreator(){}

    public void createFile() throws ParserConfigurationException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.newDocument();
    }

    public void createParentElement(String parentTagName) {

        parentElement = document.createElement(parentTagName);
        document.appendChild(parentElement);
    }

    public void addToParent(Element element){

        parentElement.appendChild(element);
    }

    public Element createChildElement(String childTagName) {

        Element childElement = document.createElement(childTagName);
        return childElement;
    }

    public Element createChildElement(String childTagName, String value) {

        Element childElement = document.createElement(childTagName);
        childElement.appendChild(document.createTextNode(value));

        return childElement;
    }

    // Transform and write config file
    public void transformAndSaveFile(String fileName) {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

        DOMSource source = new DOMSource(document);

        File file = new File("src/main/resources/" + fileName);
        String pathName = file.getAbsolutePath();

        StreamResult streamResult = new StreamResult(new File(pathName));

        try {
            transformer.transform(source, streamResult);

        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
