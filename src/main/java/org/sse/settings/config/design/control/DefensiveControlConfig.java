package org.sse.settings.config.design.control;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.sse.design.model.ThreatControl;
import org.sse.settings.ConfigFileCreator;
import org.sse.settings.ConfigFileReader;
import org.sse.settings.DescriptionProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefensiveControlConfig {

    private static final String parentTag = "defensive-control";
    private static final String securityControlTag = "security-control";
    private static final String idTag = "id";
    private static final String nameTag = "name";
    private static final String descriptionTag = "description";
    private static final String pointTag = "point";

    private static final String fileName = "DefensiveControls.xml";

    private DefensiveControlConfig(){}

    /**
     *
     *
     * @param defensiveControls
     * @throws IOException
     */
    public static void createFile(ArrayList<String[]> defensiveControls) throws IOException {

        ConfigFileCreator configFileCreator = new ConfigFileCreator();
        configFileCreator.createFile();

        configFileCreator.createRootElement(parentTag);

        /* create threat-type tags */
        for (String[] defensiveControl : defensiveControls){

            /* create threat-type tag */
            Element threatTypeElement = configFileCreator.createChildElement(securityControlTag);

            Element idElement = configFileCreator.createChildElement(idTag, defensiveControl[0]);
            Element nameElement = configFileCreator.createChildElement(nameTag, defensiveControl[1]);

            /* create description tag */
            Element descriptionElement = configFileCreator.createChildElement(descriptionTag);

            List<String> sentences = DescriptionProcessor.getSentences(defensiveControl[2]);

            for (String sentence : sentences){

                Element pointElement = configFileCreator.createChildElement(pointTag, sentence);
                descriptionElement.add(pointElement);
            }
            /* end of description tag */

            threatTypeElement.add(idElement);
            threatTypeElement.add(nameElement);
            threatTypeElement.add(descriptionElement);
            /* end of  threat-type tag */

            configFileCreator.addToRoot(threatTypeElement);
        }
        /* end of threat-type tags*/

        configFileCreator.writeFile(fileName);
    }

    /**
     * 
     * @return
     * @throws DocumentException
     */
    public static List<ThreatControl> loadConfigFile() throws DocumentException {

        List<ThreatControl> defensiveControlArrayList = new ArrayList<ThreatControl>();

        ConfigFileReader configFileReader = new ConfigFileReader();
        configFileReader.readFile(fileName);

        String xPath = "//" + parentTag + "/" + securityControlTag;

        List<Node> nodeList = configFileReader.getNodes(xPath);

        for (Node node : nodeList) {

            ThreatControl defensiveThreatControl = new ThreatControl();

            defensiveThreatControl.setId(node.valueOf(idTag));
            defensiveThreatControl.setName(node.valueOf(nameTag));

            Node descriptionNode = node.selectSingleNode(descriptionTag);

            List<String> description = new ArrayList<String>();

            for (Node pointNode : descriptionNode.selectNodes(pointTag)){
                description.add(pointNode.getStringValue());
            }

            defensiveThreatControl.setDescription(description);

            defensiveControlArrayList.add(defensiveThreatControl);
        }

        return defensiveControlArrayList;
    }
    
}
