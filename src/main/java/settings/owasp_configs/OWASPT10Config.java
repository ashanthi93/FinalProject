package settings.owasp_configs;

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

public class OWASPT10Config {

    String parentTag = "owasp-t10";
    String typeTag = "type";
    String idTag = "id";
    String nameTag = "name";
    String descriptionTag = "description";

    String fileName = "OWASPT10.xml";

    public OWASPT10Config() {}

    public void createConfigFile(ArrayList<String[]> OWASPT10Types) throws ParserConfigurationException, TransformerException {

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();
        configXMLFileCreator.createFile();

        configXMLFileCreator.createParentElement(parentTag);

        /* create type tags */
        for (String[] OWASPType : OWASPT10Types) {

            /* create type tag */
            Element typeElement = configXMLFileCreator.createChildElement(typeTag);

            Element type_idElement = configXMLFileCreator.createChildElement(idTag, OWASPType[0]);
            Element type_nameElement = configXMLFileCreator.createChildElement(nameTag, OWASPType[1]);
            Element type_descriptionElement = configXMLFileCreator.createChildElement(descriptionTag, OWASPType[2]);

            typeElement.appendChild(type_idElement);
            typeElement.appendChild(type_nameElement);
            typeElement.appendChild(type_descriptionElement);
            /* end of type tag */

            configXMLFileCreator.addToParent(typeElement);
        }
        /* end of type tags */

        configXMLFileCreator.transformAndSaveFile(fileName);
    }

    public ArrayList<String[]> loadConfigFile() throws ParserConfigurationException, IOException, SAXException {

        ArrayList<String[]> OWASP_T10_list = new ArrayList<String[]>();

        ConfigXMLFileReader configXMLFileReader = new ConfigXMLFileReader();
        configXMLFileReader.loadFile(fileName);

        NodeList nodeList = configXMLFileReader.loadNodesByTagName(typeTag);

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                String[] row = new String[3];

                row[0] = element.getElementsByTagName(idTag).item(0).getTextContent();
                row[1] = element.getElementsByTagName(nameTag).item(0).getTextContent();
                row[2] = element.getElementsByTagName(descriptionTag).item(0).getTextContent();

                OWASP_T10_list.add(row);
            }
        }
        return OWASP_T10_list;
    }
}
