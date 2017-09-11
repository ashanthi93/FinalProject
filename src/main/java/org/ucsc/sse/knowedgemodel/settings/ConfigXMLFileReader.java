package org.ucsc.sse.knowedgemodel.settings;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ConfigXMLFileReader {

    Document document;

    public void loadFile(String fileName) throws ParserConfigurationException, IOException, SAXException {

        String pathName = "src/main/resources/configurations/" + fileName;
        File xmlFIle = new File(pathName);

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        document = documentBuilder.parse(xmlFIle);
    }

    public NodeList loadNodesByTagName(String tagName){

        NodeList nodeList = document.getElementsByTagName(tagName);
        return nodeList;
    }
}
