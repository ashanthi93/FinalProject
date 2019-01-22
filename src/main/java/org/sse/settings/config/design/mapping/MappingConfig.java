package org.sse.settings.config.design.mapping;

import org.dom4j.Element;

import org.sse.settings.ConfigFileCreator;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class MappingConfig {

    private static final String parentTag = "threat-controls-for-threat";
    private static final String mappingTag = "mapping";
    private static final String threatIdTag = "threat-id";
    private static final String controlsTag = "controls";
    private static final String controlIdTag = "control-id";

    private static final String fileName = "ThreatMapping.xml";

    private MappingConfig(){}

    /**
     *
     *
     * @param threatMappings
     * @throws IOException
     */
    public static void createFile(HashMap<String,List<String>> threatMappings) throws IOException {

        ConfigFileCreator configFileCreator = new ConfigFileCreator();
        configFileCreator.createFile();

        configFileCreator.createRootElement(parentTag);

        for (String threatId : threatMappings.keySet()){

            Element mappingElement = configFileCreator.createChildElement(mappingTag);

            Element threatIdElement = configFileCreator.createChildElement(threatIdTag, threatId);
            Element controlElement = configFileCreator.createChildElement(controlsTag);

            List<String> controlIdValues = threatMappings.get(threatId);

            for (String controlIdValue : controlIdValues){

                Element controlIdElement = configFileCreator.createChildElement(controlIdTag, controlIdValue);
                controlElement.add(controlIdElement);
            }

            mappingElement.add(threatIdElement);
            mappingElement.add(controlElement);

            configFileCreator.addToRoot(mappingElement);
        }

        configFileCreator.writeFile(fileName);
    }

}
