package org.ucsc.sse.knowedgemodel.settings.bugmodelconfigs;

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

public class BugModelConfig {

    String parentTag = "bug-categorization-model";
    String modelNameTag = "model-name";
    String versionTag = "version";
    String typeTag = "bug-category";
    String idTag = "id";
    String nameTag = "name";
    String descriptionTag = "description";

    String fileName = "BugModel.xml";

    public BugModelConfig() {}

    /**
     *
     *
     * @param bugCategories     id, name, description order
     * @param categoryModelName
     * @param versionValue
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public void createConfigFile(ArrayList<String[]> bugCategories, String categoryModelName, String versionValue) throws ParserConfigurationException, TransformerException {

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();
        configXMLFileCreator.createFile();

        configXMLFileCreator.createParentElement(parentTag);

        Element parentNameElement = configXMLFileCreator.createChildElement(modelNameTag, categoryModelName);
        Element versionElement = configXMLFileCreator.createChildElement(versionTag, versionValue);

        configXMLFileCreator.addToParent(parentNameElement);
        configXMLFileCreator.addToParent(versionElement);

        /* create type tags */
        for (String[] OWASPType : bugCategories) {

            /* create type tag */
            Element typeElement = configXMLFileCreator.createChildElement(typeTag);

            Element type_idElement = configXMLFileCreator.createChildElement(idTag, OWASPType[0]);
            Element type_nameElement = configXMLFileCreator.createChildElement(nameTag, OWASPType[1]);
            Element type_descriptionElement = configXMLFileCreator.createChildElement(descriptionTag, OWASPType[2]);

            typeElement.appendChild(type_idElement);
            typeElement.appendChild(type_nameElement);
            typeElement.appendChild(type_descriptionElement);
            /* end of type tag */

            configXMLFileCreator.addToParent(typeElement);
        }
        /* end of type tags */

        configXMLFileCreator.transformAndSaveFile(fileName);
    }

    /**
     *
     *
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public ArrayList<String[]> loadConfigFile() throws ParserConfigurationException, IOException, SAXException {

        ArrayList<String[]> OWASP_T10_list = new ArrayList<String[]>();

        ConfigXMLFileReader configXMLFileReader = new ConfigXMLFileReader();
        configXMLFileReader.loadFile(fileName);

        NodeList nodeList = configXMLFileReader.loadNodesByTagName(typeTag);

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                String[] row = new String[3];

                row[0] = element.getElementsByTagName(idTag).item(0).getTextContent();
                row[1] = element.getElementsByTagName(nameTag).item(0).getTextContent();
                row[2] = element.getElementsByTagName(descriptionTag).item(0).getTextContent();

                OWASP_T10_list.add(row);
            }
        }
        return OWASP_T10_list;
    }

    /**
     *
     *
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public HashMap<String,String> loadBugCategoryIdsAndNames() throws IOException, SAXException, ParserConfigurationException {

        HashMap<String,String> bugIdsAndNames = new HashMap<String, String>();

        ConfigXMLFileReader configXMLFileReader = new ConfigXMLFileReader();
        configXMLFileReader.loadFile(fileName);

        NodeList nodeList = configXMLFileReader.loadNodesByTagName(typeTag);

        for (int i = 0; i<nodeList.getLength(); i++){

            Node node = nodeList.item(i);
            Element element = (Element) node;

            String id = element.getElementsByTagName(idTag).item(0).getTextContent();
            String name = element.getElementsByTagName(nameTag).item(0).getTextContent();

            bugIdsAndNames.put(id,name);
        }
        return bugIdsAndNames;
    }

}
