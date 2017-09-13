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
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
    public void transformAndSaveFile(String fileName) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(document);

        File file = new File("src/main/resources/configurations/" + fileName);
        String pathName = file.getAbsolutePath();

        StreamResult streamResult = new StreamResult(new File(pathName));
        transformer.transform(source, streamResult);
    }
}
