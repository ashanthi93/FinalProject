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
import java.util.HashMap;
import java.util.Map;

public class MappingConfig {

    String parentTag = "bug-to-control-mapping";
    String bugTypeTag = "bug-type";
    String idTag = "id";
    String bugControlsTag = "bug-control";
    String bugControlTag = "bug-control";

    String fileName = "BugToBugControlMapping.xml";

    public MappingConfig() {}

    /**
     *
     * @param mappingHashMap
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public void createConfigFile(Map<String, String[]> mappingHashMap) throws ParserConfigurationException, TransformerException {

        ConfigXMlFileCreator configXMlFileCreator = new ConfigXMlFileCreator();
        configXMlFileCreator.createFile();

        configXMlFileCreator.createParentElement(parentTag);

        /* create bug-type tags */
        for (String key : mappingHashMap.keySet()) {

            /* create bug-type tag */
            Element bugTypeElement = configXMlFileCreator.createChildElement(bugTypeTag);

            Element idElement = configXMlFileCreator.createChildElement(idTag, key);
            bugTypeElement.appendChild(idElement);

            /* create bug-control tag */
            Element bugControlsElement = configXMlFileCreator.createChildElement(bugControlsTag);

            for (String bugControl : mappingHashMap.get(key)) {

                Element bugControlElement = configXMlFileCreator.createChildElement(bugControlTag, bugControl);
                bugControlsElement.appendChild(bugControlElement);
            }
            /* end of bug-control tag */

            bugTypeElement.appendChild(bugControlsElement);
            /* end of bug-type tag */

            configXMlFileCreator.addToParent(bugTypeElement);
        }
        /* end of bug-type tags */

        configXMlFileCreator.transformAndSaveFile(fileName);
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public HashMap<String, String[]> loadConfigFile() throws IOException, SAXException, ParserConfigurationException {

        HashMap<String, String[]> OWASP_mapping = new HashMap<String, String[]>();

        ConfigXMLFileReader configXMLFileReader = new ConfigXMLFileReader();
        configXMLFileReader.loadFile(fileName);

        NodeList nodeList = configXMLFileReader.loadNodesByTagName(bugTypeTag);

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                String key = element.getElementsByTagName(idTag).item(0).getTextContent();
                int size = element.getElementsByTagName(bugControlTag).getLength();
                String[] values = new String[size];
                for(int j=0; j<size; j++){
                    values[j] = element.getElementsByTagName(bugControlTag).item(j).getTextContent();
                }

                OWASP_mapping.put(key, values);
            }
        }

        return OWASP_mapping;
    }
}
