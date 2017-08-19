package settings.stride_configs;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import settings.ConfigXMLFileCreator;
import settings.ConfigXMLFileReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
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

    public ArrayList<String[]> loadConfigFile() throws IOException, SAXException, ParserConfigurationException {

        ArrayList<String[]> STRIDE_list = new ArrayList<String[]>();

        ConfigXMLFileReader configXMLFileReader = new ConfigXMLFileReader();
        configXMLFileReader.loadFile(fileName);

        NodeList nodeList = configXMLFileReader.loadNodesByTagName(threatTypeTag);

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                String[] row = new String[4];

                row[0] = element.getElementsByTagName(idTag).item(0).getTextContent();
                row[1] = element.getElementsByTagName(nameTag).item(0).getTextContent();
                row[2] = element.getElementsByTagName(controlNameTag).item(0).getTextContent();
                row[3] = element.getElementsByTagName(descriptionTag).item(0).getTextContent();

                STRIDE_list.add(row);
            }
        }
        return STRIDE_list;
    }
}
