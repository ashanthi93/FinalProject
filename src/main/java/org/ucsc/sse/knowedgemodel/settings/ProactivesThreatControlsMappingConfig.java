package org.ucsc.sse.knowedgemodel.settings;

import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.Map;

public class ProactivesThreatControlsMappingConfig {

    String parentTag = "proactive-tcontrols-mapping";
    String mappingTag = "mapping";
    String proactiveIdTag = "proactive-id";
    String threatControlTag = "threat-control";
    String idTag = "id";

    String fileName = "Proactive_TControl_Mapping.xml";

    public ProactivesThreatControlsMappingConfig(){}

    public void createFile(Map<String, String[]> proactiveTControlMappingHashMap) throws ParserConfigurationException, TransformerException {

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();
        configXMLFileCreator.createFile();

        configXMLFileCreator.createParentElement(parentTag);

        /* create mapping tags */
        for (String key : proactiveTControlMappingHashMap.keySet()){

            /* create mapping tag */
            Element mappingElement = configXMLFileCreator.createChildElement(mappingTag);

            Element proactiveIDElement = configXMLFileCreator.createChildElement(proactiveIdTag, key);
            mappingElement.appendChild(proactiveIDElement);

            /* create threat-control tag */
            Element threatControlElement = configXMLFileCreator.createChildElement(threatControlTag);

            for(String id : proactiveTControlMappingHashMap.get(key)){

                Element idElement = configXMLFileCreator.createChildElement(idTag,id);
                threatControlElement.appendChild(idElement);
            }
            /* end of threat-control tag */

            mappingElement.appendChild(threatControlElement);
            /* end of mapping tag */

            configXMLFileCreator.addToParent(mappingElement);
        }
        /* end of mapping tags */

        configXMLFileCreator.transformAndSaveFile(fileName);
    }
}
