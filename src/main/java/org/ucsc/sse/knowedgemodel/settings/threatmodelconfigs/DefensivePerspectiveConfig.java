package org.ucsc.sse.knowedgemodel.settings.threatmodelconfigs;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.ucsc.sse.knowedgemodel.settings.ConfigXMLFileCreator;
import org.ucsc.sse.knowedgemodel.settings.ConfigXMLFileReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class DefensivePerspectiveConfig {

    String parentTag = "defensive-perspective";
    String modelNameTag = "model-name";
    String threatTypeTag = "threat-type";
    String idTag = "id";
    String nameTag = "name";
    String descriptionTag = "description";

    String fileName = "DefensivePerspective.xml";

    public DefensivePerspectiveConfig(){}

    /**
     *
     * @param defensiveThreats
     * @param modelNameValue
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public void createFile(ArrayList<String[]> defensiveThreats, String modelNameValue) throws ParserConfigurationException, TransformerException {

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();
        configXMLFileCreator.createFile();

        configXMLFileCreator.createParentElement(parentTag);

        Element modelNameElement = configXMLFileCreator.createChildElement(modelNameTag, modelNameValue);
        configXMLFileCreator.addToParent(modelNameElement);

        /* create threat-type tags */
        for (String[] defensiveThreat : defensiveThreats){

            /* create threat-type tag */
            Element threatTypeElement = configXMLFileCreator.createChildElement(threatTypeTag);

            Element idElement = configXMLFileCreator.createChildElement(idTag, defensiveThreat[0]);
            Element nameElement = configXMLFileCreator.createChildElement(nameTag, defensiveThreat[1]);
            Element descriptionElement = configXMLFileCreator.createChildElement(descriptionTag, defensiveThreat[2]);

            threatTypeElement.appendChild(idElement);
            threatTypeElement.appendChild(nameElement);
            threatTypeElement.appendChild(descriptionElement);
            /* end of  threat-type tag */

            configXMLFileCreator.addToParent(threatTypeElement);
        }
        /* end of threat-type tags*/

        configXMLFileCreator.transformAndSaveFile(fileName);
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public ArrayList<String[]> loadConfigFile() throws IOException, SAXException, ParserConfigurationException {

        ArrayList<String[]> defensive_list = new ArrayList<String[]>();

        ConfigXMLFileReader configXMLFileReader = new ConfigXMLFileReader();
        configXMLFileReader.loadFile(fileName);

        NodeList nodeList = configXMLFileReader.loadNodesByTagName(threatTypeTag);

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                String[] row = new String[3];

                row[0] = element.getElementsByTagName(idTag).item(0).getTextContent();
                row[1] = element.getElementsByTagName(nameTag).item(0).getTextContent();
                row[2] = element.getElementsByTagName(descriptionTag).item(0).getTextContent();

                defensive_list.add(row);
            }
        }
        return defensive_list;
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public ArrayList<String> loadThreatControls() throws IOException, SAXException, ParserConfigurationException {

        ArrayList<String> threatControls = new ArrayList<String>();

        ConfigXMLFileReader configXMLFileReader = new ConfigXMLFileReader();
        configXMLFileReader.loadFile(fileName);

        NodeList nodeList = configXMLFileReader.loadNodesByTagName(threatTypeTag);

        for (int i=0; i<nodeList.getLength(); i++){

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE){

                Element element = (Element) node;

                String controlTagValue = element.getElementsByTagName(nameTag).item(0).getTextContent();
                threatControls.add(i,controlTagValue);
            }
        }
        return threatControls;
    }
}
