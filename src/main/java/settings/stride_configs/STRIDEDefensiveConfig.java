package settings.stride_configs;

import org.w3c.dom.Element;
import settings.ConfigXMLFileCreator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;

public class STRIDEDefensiveConfig {

    String parentTag = "defensive-threats";
    String threatTypeTag = "threat-type";
    String idTag = "id";
    String nameTag = "name";
    String descriptionTag = "description";

    String fileName = "STRIDE_Defensive_Model.xml";

    public STRIDEDefensiveConfig(){}

    public void createFile(ArrayList<String[]> defensiveThreats) throws ParserConfigurationException, TransformerException {

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();
        configXMLFileCreator.createFile();

        configXMLFileCreator.createParentElement(parentTag);

        /* create threat-type tags */
        for (String[] defensiveThreat : defensiveThreats){

            /* create threat-type tag */
            Element threatTypeElement = configXMLFileCreator.createChildElement(threatTypeTag);

            Element idElement = configXMLFileCreator.createChildElement(idTag, defensiveThreat[0]);
            Element nameElement = configXMLFileCreator.createChildElement(nameTag, defensiveThreat[1]);
            Element descriptionElement = configXMLFileCreator.createChildElement(descriptionTag, defensiveThreat[2]);

            threatTypeElement.appendChild(idElement);
            threatTypeElement.appendChild(nameElement);
            threatTypeElement.appendChild(descriptionElement);
            /* end of  threat-type tag */

            configXMLFileCreator.addToParent(threatTypeElement);
        }
        /* end of threat-type tags*/

        configXMLFileCreator.transformAndSaveFile(fileName);
    }
}
