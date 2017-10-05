package org.sse.knowedgemodel.settings;

import org.sse.settings.ConfigXMlFileCreator;
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

        ConfigXMlFileCreator configXMlFileCreator = new ConfigXMlFileCreator();
        configXMlFileCreator.createFile();

        configXMlFileCreator.createParentElement(parentTag);

        /* create mapping tags */
        for (String key : proactiveTControlMappingHashMap.keySet()){

            /* create mapping tag */
            Element mappingElement = configXMlFileCreator.createChildElement(mappingTag);

            Element proactiveIDElement = configXMlFileCreator.createChildElement(proactiveIdTag, key);
            mappingElement.appendChild(proactiveIDElement);

            /* create threat-control tag */
            Element threatControlElement = configXMlFileCreator.createChildElement(threatControlTag);

            for(String id : proactiveTControlMappingHashMap.get(key)){

                Element idElement = configXMlFileCreator.createChildElement(idTag,id);
                threatControlElement.appendChild(idElement);
            }
            /* end of threat-control tag */

            mappingElement.appendChild(threatControlElement);
            /* end of mapping tag */

            configXMlFileCreator.addToParent(mappingElement);
        }
        /* end of mapping tags */

        configXMlFileCreator.transformAndSaveFile(fileName);
    }
}
