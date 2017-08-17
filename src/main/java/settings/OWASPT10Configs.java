package settings;

import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;

public class OWASPT10Configs {

    ArrayList<String[]> OWASPT10Types;

    String parentTag = "OWASP-T10";
    String typeTag = "type";
    String idTag = "id";
    String nameTag = "name";
    String descriptionTag = "description";

    public OWASPT10Configs(ArrayList<String[]> OWASPT10Types) {
        this.OWASPT10Types = OWASPT10Types;
    }

    public void createConfigFile() {

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();

        try {
            configXMLFileCreator.createFile();

            configXMLFileCreator.createParentElement(parentTag);

            for (String[] OWASPType : OWASPT10Types) {

                Element typeElement = configXMLFileCreator.createChildElement(typeTag);

                Element type_idElement = configXMLFileCreator.createChildElement(idTag, OWASPType[0]);
                Element type_nameElement = configXMLFileCreator.createChildElement(nameTag, OWASPType[1]);
                Element type_descriptionElement = configXMLFileCreator.createChildElement(descriptionTag, OWASPType[2]);

                typeElement.appendChild(type_idElement);
                typeElement.appendChild(type_nameElement);
                typeElement.appendChild(type_descriptionElement);

                configXMLFileCreator.addToParent(typeElement);
            }

            configXMLFileCreator.transformAndSaveFile("OWASPT10.xml");

        } catch (ParserConfigurationException e) {
            System.err.println(e);
        } catch (TransformerException e) {
            System.err.println(e);
        }
    }
}
