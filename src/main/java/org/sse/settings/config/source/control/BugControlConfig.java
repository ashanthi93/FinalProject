package org.sse.settings.config.source.control;

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

public class BugControlConfig {

    String parentTag = "bug-control";
    String modelNameTag = "model-name";
    String versionTag = "version";
    String proactiveTag = "control";
    String idTag = "id";
    String nameTag = "name";
    String descriptionTag = "description";

    String fileName = "BugControls.xml";

    public BugControlConfig() {}

    /**
     *
     *
     * @param bugControls
     * @param modelNameValue
     * @param versionValue
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public void createConfigFile(ArrayList<String[]> bugControls, String modelNameValue, String versionValue) throws ParserConfigurationException, TransformerException {

        ConfigXMlFileCreator configXMlFileCreator = new ConfigXMlFileCreator();
        configXMlFileCreator.createFile();

        configXMlFileCreator.createParentElement(parentTag);

        Element modelNameElement = configXMlFileCreator.createChildElement(modelNameTag, modelNameValue);
        Element versionNameElement = configXMlFileCreator.createChildElement(versionTag, versionValue);

        configXMlFileCreator.addToParent(modelNameElement);
        configXMlFileCreator.addToParent(versionNameElement);

        /* create proactive tags */
        for (String[] OWASPProactive : bugControls) {

            /* create proactive tag */
            Element proactiveElement = configXMlFileCreator.createChildElement(proactiveTag);

            Element proactive_idElement = configXMlFileCreator.createChildElement(idTag, OWASPProactive[0]);
            Element proactive_nameElement = configXMlFileCreator.createChildElement(nameTag, OWASPProactive[1]);
            Element proactive_descriptionElement = configXMlFileCreator.createChildElement(descriptionTag, OWASPProactive[2]);

            proactiveElement.appendChild(proactive_idElement);
            proactiveElement.appendChild(proactive_nameElement);
            proactiveElement.appendChild(proactive_descriptionElement);
            /* end of proactive tag */

            configXMlFileCreator.addToParent(proactiveElement);
        }
        /* end of proactive tags */

        configXMlFileCreator.transformAndSaveFile(fileName);
    }

    /**
     *
     *
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public ArrayList<String[]> loadConfigFile() throws IOException, SAXException, ParserConfigurationException {

        ArrayList<String[]> OWASP_proactives_list = new ArrayList<String[]>();

        ConfigXMLFileReader configXMLFileReader = new ConfigXMLFileReader();
        configXMLFileReader.loadFile(fileName);

        NodeList nodeList = configXMLFileReader.loadNodesByTagName(proactiveTag);

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                String[] row = new String[3];

                row[0] = element.getElementsByTagName(idTag).item(0).getTextContent();
                row[1] = element.getElementsByTagName(nameTag).item(0).getTextContent();
                row[2] = element.getElementsByTagName(descriptionTag).item(0).getTextContent();

                OWASP_proactives_list.add(row);
            }
        }
        return OWASP_proactives_list;
    }
}
