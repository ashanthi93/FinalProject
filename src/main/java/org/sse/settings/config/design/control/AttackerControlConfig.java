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

public class AttackerControlConfig {

    private static final String parentTag = "attacker-control";
    private static final String securityControlTag = "security-control";
    private static final String idTag = "id";
    private static final String nameTag = "name";
    private static final String descriptionTag = "description";
    private static final String pointTag = "point";

    private static final String fileName = "AttackerControls.xml";

    private AttackerControlConfig() {
    }

    /**
     * @param attackerControls
     * @throws IOException
     */
    public static void createFile(ArrayList<String[]> attackerControls) throws IOException {

        ConfigFileCreator configFileCreator = new ConfigFileCreator();
        configFileCreator.createFile();

        configFileCreator.createRootElement(parentTag);

        /* create threat-type tags */
        for (String[] attackerControl : attackerControls) {

            /* create threat-type tag */
            Element threatTypeElement = configFileCreator.createChildElement(securityControlTag);

            Element idElement = configFileCreator.createChildElement(idTag, attackerControl[0]);
            Element nameElement = configFileCreator.createChildElement(nameTag, attackerControl[1]);

            /* create description tag */
            Element descriptionElement = configFileCreator.createChildElement(descriptionTag);

            List<String> sentences = DescriptionProcessor.getSentences(attackerControl[2]);

            for (String sentence : sentences) {

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
     *
     * @return
     * @throws DocumentException
     */
    public static ArrayList<ThreatControl> loadConfigFile() throws DocumentException {

        ArrayList<ThreatControl> attackerControlArrayList = new ArrayList<ThreatControl>();

        ConfigFileReader configFileReader = new ConfigFileReader();
        configFileReader.readFile(fileName);

        String xPath = "//" + parentTag + "/" + securityControlTag;

        List<Node> nodeList = configFileReader.getNodes(xPath);

        for (Node node : nodeList) {

            ThreatControl attackerThreatControl = new ThreatControl();

            attackerThreatControl.setId(node.valueOf(idTag));
            attackerThreatControl.setName(node.valueOf(nameTag));

            Node descriptionNode = node.selectSingleNode(descriptionTag);

            List<String> description = new ArrayList<String>();

            for (Node pointNode : descriptionNode.selectNodes(pointTag)){
                description.add(pointNode.getStringValue());
            }

            attackerThreatControl.setDescription(description);

            attackerControlArrayList.add(attackerThreatControl);
        }

        return attackerControlArrayList;
    }
}
