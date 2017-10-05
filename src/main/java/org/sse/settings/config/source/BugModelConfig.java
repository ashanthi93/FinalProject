package org.sse.settings.config.source;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.sse.settings.ConfigXMlFileCreator;
import org.sse.settings.ConfigXMLFileReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BugModelConfig {

    private static String parentTag = "bug-categorization-model";
    private static String modelNameTag = "model-name";
    private static String versionTag = "version";
    private static String typeTag = "bug-category";
    private static String idTag = "id";
    private static String nameTag = "name";
    private static String descriptionTag = "description";

    private static String fileName = "BugModel.xml";

    private BugModelConfig() {}

    /**
     *
     *
     * @param bugCategories     id, name, description order
     * @param categoryModelName
     * @param versionValue
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public static void createConfigFile(ArrayList<String[]> bugCategories, String categoryModelName, String versionValue) throws ParserConfigurationException, TransformerException {

        ConfigXMlFileCreator configXMlFileCreator = new ConfigXMlFileCreator();
        configXMlFileCreator.createFile();

        configXMlFileCreator.createParentElement(parentTag);

        Element parentNameElement = configXMlFileCreator.createChildElement(modelNameTag, categoryModelName);
        Element versionElement = configXMlFileCreator.createChildElement(versionTag, versionValue);

        configXMlFileCreator.addToParent(parentNameElement);
        configXMlFileCreator.addToParent(versionElement);

        /* create type tags */
        for (String[] OWASPType : bugCategories) {

            /* create type tag */
            Element typeElement = configXMlFileCreator.createChildElement(typeTag);

            Element type_idElement = configXMlFileCreator.createChildElement(idTag, OWASPType[0]);
            Element type_nameElement = configXMlFileCreator.createChildElement(nameTag, OWASPType[1]);
            Element type_descriptionElement = configXMlFileCreator.createChildElement(descriptionTag, OWASPType[2]);

            typeElement.appendChild(type_idElement);
            typeElement.appendChild(type_nameElement);
            typeElement.appendChild(type_descriptionElement);
            /* end of type tag */

            configXMlFileCreator.addToParent(typeElement);
        }
        /* end of type tags */

        configXMlFileCreator.transformAndSaveFile(fileName);
    }

    /**
     *
     *
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static ArrayList<String[]> loadConfigFile() throws ParserConfigurationException, IOException, SAXException {

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
    public static HashMap<String,String> loadBugCategoryIdsAndNames() throws IOException, SAXException, ParserConfigurationException {

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
