package settings.bugmodel_configs;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import settings.ConfigXMLFileCreator;
import settings.ConfigXMLFileReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OWASPMappingConfig {

    String parentTag = "owasp-mapping";
    String t10TypeTag = "t10-type";
    String idTag = "id";
    String proactivesTag = "proactives";
    String proactiveTag = "proactive";

    String fileName = "OWASP_Mapping.xml";

    public OWASPMappingConfig() {}

    public void createConfigFile(Map<String, String[]> mappingHashMap) throws ParserConfigurationException, TransformerException {

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();
        configXMLFileCreator.createFile();

        configXMLFileCreator.createParentElement(parentTag);

        /* create t10-type tags */
        for (String key : mappingHashMap.keySet()) {

            /* create t10-type tag */
            Element t10TypeElement = configXMLFileCreator.createChildElement(t10TypeTag);

            Element idElement = configXMLFileCreator.createChildElement(idTag, key);
            t10TypeElement.appendChild(idElement);

            /* create proactives tag */
            Element proactivesElement = configXMLFileCreator.createChildElement(proactivesTag);

            for (String proactive : mappingHashMap.get(key)) {
                Element proactiveElement = configXMLFileCreator.createChildElement(proactiveTag, proactive);
                proactivesElement.appendChild(proactiveElement);
            }
            /* end of proactives tag */

            t10TypeElement.appendChild(proactivesElement);
            /* end of t10-type tag */

            configXMLFileCreator.addToParent(t10TypeElement);
        }
        /* end of t10-type tags */

        configXMLFileCreator.transformAndSaveFile(fileName);
    }

    public HashMap<String, String[]> loadConfigFile() throws IOException, SAXException, ParserConfigurationException {

        HashMap<String, String[]> OWASP_mapping = new HashMap<String, String[]>();

        ConfigXMLFileReader configXMLFileReader = new ConfigXMLFileReader();
        configXMLFileReader.loadFile(fileName);

        NodeList nodeList = configXMLFileReader.loadNodesByTagName(t10TypeTag);

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                String key = element.getElementsByTagName(idTag).item(0).getTextContent();
                int size = element.getElementsByTagName(proactiveTag).getLength();
                String[] values = new String[size];
                for(int j=0; j<size; j++){
                    values[j] = element.getElementsByTagName(proactiveTag).item(j).getTextContent();
                }

                OWASP_mapping.put(key, values);
            }
        }

        return OWASP_mapping;
    }
}
