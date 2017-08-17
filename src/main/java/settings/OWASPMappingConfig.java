package settings;

import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
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
            /* end-of t10-type tag */

            configXMLFileCreator.addToParent(t10TypeElement);
        }
        /* end of t10-type tags */

        configXMLFileCreator.transformAndSaveFile(fileName);
    }
}
