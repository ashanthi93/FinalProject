package org.sse.settings.config.source.mapping;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.sse.settings.ConfigFileCreator;
import org.sse.settings.ConfigFileReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappingConfig {

    private static final String parentTag = "bug-controls-for-bug";
    private static final String mappingTag = "mapping";
    private static final String bugIdTag = "bug-id";
    private static final String controlsTag = "controls";
    private static final String controlIdTag = "control-id";

    private static final String fileName = "BugMapping.xml";

    private MappingConfig() {}

    public static void createFile(HashMap<String, List<String>> mappingHashMap) throws IOException {

        ConfigFileCreator configFileCreator = new ConfigFileCreator();
        configFileCreator.createFile();

        configFileCreator.createRootElement(parentTag);

        /* create mapping tags */
        for (String key : mappingHashMap.keySet()) {

            /* create mapping tag */
            Element bugTypeElement = configFileCreator.createChildElement(mappingTag);

            Element idElement = configFileCreator.createChildElement(bugIdTag, key);
            bugTypeElement.add(idElement);

            /* create bug-control tag */
            Element bugControlsElement = configFileCreator.createChildElement(controlsTag);

            for (String bugControl : mappingHashMap.get(key)) {

                Element bugControlElement = configFileCreator.createChildElement(controlIdTag, bugControl);
                bugControlsElement.add(bugControlElement);
            }
            /* end of bug-control tag */

            bugTypeElement.add(bugControlsElement);
            /* end of bug-type tag */

            configFileCreator.addToRoot(bugTypeElement);
        }
        /* end of bug-type tags */

        configFileCreator.writeFile(fileName);
    }

    /**
     *
     *
     * @return
     * @throws DocumentException
     */
    public static HashMap<String, String[]> loadConfigFile() throws DocumentException {

        HashMap<String, String[]> OWASP_mapping = new HashMap<String, String[]>();

        ConfigFileReader configFileReader = new ConfigFileReader();
        configFileReader.readFile(fileName);

        List<Node> nodeList = configFileReader.getNodes("//" + parentTag + "/" + mappingTag);

        for (Node node : nodeList){

            String bugId = node.valueOf(bugIdTag);

            List<Node> controlNodes = node.selectNodes(controlsTag);

            int size = controlNodes.size();
            String[] controlIdValues = new String[size];

            for (Node controlNode : controlNodes){
                controlIdValues[size-1] = controlNode.valueOf(controlIdTag);
                size--;
            }

            OWASP_mapping.put(bugId, controlIdValues);
        }
        return OWASP_mapping;
    }
}
