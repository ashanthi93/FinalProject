package settings;

import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;

public class STRIDEAttackerConfig {

    String parentTag = "stride";
    String threatTypeTag = "threat-type";
    String idTag = "id";
    String nameTag = "name";
    String securityControlTag = "security-control";
    String controlNameTag = "control-name";
    String descriptionTag = "description";

    String fileName = "STRIDE.xml";

    public STRIDEAttackerConfig(){}

    public void createConfigFile(ArrayList<String[]> STRIDEThreats) throws ParserConfigurationException, TransformerException {

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();
        configXMLFileCreator.createFile();

        configXMLFileCreator.createParentElement(parentTag);

        /* create threat-type tags */
        for (String[] threatDetail : STRIDEThreats){

            /* create threat-type tag */
            Element threatTypeElement = configXMLFileCreator.createChildElement(threatTypeTag);

            Element idElement = configXMLFileCreator.createChildElement(idTag, threatDetail[0]);
            Element nameElement = configXMLFileCreator.createChildElement(nameTag, threatDetail[1]);

            /* create security-control tag */
            Element securityControlElement = configXMLFileCreator.createChildElement(securityControlTag);

            Element controlNameElement = configXMLFileCreator.createChildElement(controlNameTag, threatDetail[2]);
            Element descriptionElement = configXMLFileCreator.createChildElement(descriptionTag, threatDetail[3]);

            securityControlElement.appendChild(controlNameElement);
            securityControlElement.appendChild(descriptionElement);
            /* end of security-control tag */

            threatTypeElement.appendChild(idElement);
            threatTypeElement.appendChild(nameElement);
            threatTypeElement.appendChild(securityControlElement);
            /* end of threat-type tag */

            configXMLFileCreator.addToParent(threatTypeElement);
        }
        /* end of threat-type tags */

        configXMLFileCreator.transformAndSaveFile(fileName);
    }
}
