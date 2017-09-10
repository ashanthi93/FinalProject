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
import java.util.HashMap;

public class AttackerPerspectiveConfig {

    String parentTag = "attacker-perspective";
    String modelNameTag = "model-name";
    String threatTypeTag = "threat-type";
    String idTag = "id";
    String nameTag = "name";
    String securityControlTag = "security-control";
    String controlNameTag = "control-name";

    String fileName = "AttackerPerspective.xml";

    public AttackerPerspectiveConfig(){}

    /**
     *
     * @param threats           id, name, security control
     * @param modelNameValue
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public void createConfigFile(ArrayList<String[]> threats, String modelNameValue) throws ParserConfigurationException, TransformerException {

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();
        configXMLFileCreator.createFile();

        configXMLFileCreator.createParentElement(parentTag);

        Element modelNameElement = configXMLFileCreator.createChildElement(modelNameTag, modelNameValue);
        configXMLFileCreator.addToParent(modelNameElement);

        /* create threat-type tags */
        for (String[] threatDetail : threats){

            /* create threat-type tag */
            Element threatTypeElement = configXMLFileCreator.createChildElement(threatTypeTag);

            Element idElement = configXMLFileCreator.createChildElement(idTag, threatDetail[0]);
            Element nameElement = configXMLFileCreator.createChildElement(nameTag, threatDetail[1]);

            /* create security-control tag */
            Element securityControlElement = configXMLFileCreator.createChildElement(securityControlTag);

            Element controlNameElement = configXMLFileCreator.createChildElement(controlNameTag, threatDetail[2]);

            securityControlElement.appendChild(controlNameElement);
            /* end of security-control tag */

            threatTypeElement.appendChild(idElement);
            threatTypeElement.appendChild(nameElement);
            threatTypeElement.appendChild(securityControlElement);
            /* end of threat-type tag */

            configXMLFileCreator.addToParent(threatTypeElement);
        }
        /* end of threat-type tags */

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

        ArrayList<String[]> STRIDE_list = new ArrayList<String[]>();

        ConfigXMLFileReader configXMLFileReader = new ConfigXMLFileReader();
        configXMLFileReader.loadFile(fileName);

        NodeList nodeList = configXMLFileReader.loadNodesByTagName(threatTypeTag);

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                String[] row = new String[4];

                row[0] = element.getElementsByTagName(idTag).item(0).getTextContent();
                row[1] = element.getElementsByTagName(nameTag).item(0).getTextContent();
                row[2] = element.getElementsByTagName(controlNameTag).item(0).getTextContent();

                STRIDE_list.add(row);
            }
        }
        return STRIDE_list;
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

        NodeList nodeList = configXMLFileReader.loadNodesByTagName(securityControlTag);

        for (int i=0; i<nodeList.getLength(); i++){

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE){

                Element element = (Element) node;

                String controlTagValue = element.getElementsByTagName(controlNameTag).item(0).getTextContent();
                threatControls.add(i,controlTagValue);
            }
        }
        return threatControls;
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public HashMap<String,String> loadThreatCategoryIdsAndNames() throws IOException, SAXException, ParserConfigurationException {

        HashMap<String,String> threatIdsAndNames = new HashMap<String, String>();

        ConfigXMLFileReader configXMLFileReader = new ConfigXMLFileReader();
        configXMLFileReader.loadFile(fileName);

        NodeList nodeList = configXMLFileReader.loadNodesByTagName(threatTypeTag);

        for (int i = 0; i<nodeList.getLength(); i++){

            Node node = nodeList.item(i);
            Element element = (Element) node;

            String id = element.getElementsByTagName(idTag).item(0).getTextContent();
            String name = element.getElementsByTagName(nameTag).item(0).getTextContent();

            threatIdsAndNames.put(id,name);
        }
        return threatIdsAndNames;
    }
}
