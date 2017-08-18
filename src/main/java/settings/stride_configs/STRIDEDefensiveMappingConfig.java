package settings.stride_configs;

import org.w3c.dom.Element;
import settings.ConfigXMLFileCreator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.Map;

public class STRIDEDefensiveMappingConfig {

    String parentTag = "stride-defensive-mapping";
    String threatTypeTag = "threat-type";
    String idTag = "id";
    String defensesTag = "defenses";
    String defenseTag = "defense";

    String fileName = "STRIDE_Defensive_Mapping.xml";

    public STRIDEDefensiveMappingConfig(){}

    public void createFile(Map<String, String[]> defensesMappingHashMap) throws ParserConfigurationException, TransformerException {

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();
        configXMLFileCreator.createFile();

        configXMLFileCreator.createParentElement(parentTag);

        /* create threat-type tags */
        for (String key : defensesMappingHashMap.keySet()){

            /* create threat-type tag */
            Element threatTypeElement = configXMLFileCreator.createChildElement(threatTypeTag);

            Element idElement = configXMLFileCreator.createChildElement(idTag, key);
            threatTypeElement.appendChild(idElement);

            /* create defenses tags */
            Element defensesElement = configXMLFileCreator.createChildElement(defensesTag);

            for(String defense : defensesMappingHashMap.get(key)){
                Element defenseElement = configXMLFileCreator.createChildElement(defenseTag,defense);
                defensesElement.appendChild(defenseElement);
            }
            /* end of defenses tags */

            threatTypeElement.appendChild(defensesElement);
            /* end of threat-type tag */

            configXMLFileCreator.addToParent(threatTypeElement);
        }
        /* end of threat-type tags */

        configXMLFileCreator.transformAndSaveFile(fileName);
    }
}
