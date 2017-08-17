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

    public OWASPMappingConfig(){}

    public void createConfigFile(Map<String,String[]> mappingHashMap){

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();

        try {
            configXMLFileCreator.createFile();

            configXMLFileCreator.createParentElement(parentTag);

            for (String key : mappingHashMap.keySet()){

                Element t10TypeElement = configXMLFileCreator.createChildElement(t10TypeTag);
                Element idElement = configXMLFileCreator.createChildElement(idTag, key);
                Element proactivesElement = configXMLFileCreator.createChildElement(proactivesTag);

                for (String proactive: mappingHashMap.get(key)){

                    Element proactiveElement = configXMLFileCreator.createChildElement(proactiveTag, proactive);
                    proactivesElement.appendChild(proactiveElement);
                }

                t10TypeElement.appendChild(idElement);
                t10TypeElement.appendChild(proactivesElement);
                configXMLFileCreator.addToParent(t10TypeElement);
            }

            configXMLFileCreator.transformAndSaveFile(fileName);

        } catch (ParserConfigurationException e) {
            System.err.println(e);
        } catch (TransformerException e) {
            System.err.println(e);
        }
    }
}
