package settings;

import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;

public class OWASPProactiveConfigs {

    ArrayList<String[]> OWASPProactives;

    String parentTag = "OWASP-Proactives";
    String proactiveTag = "proactive";
    String idTag = "id";
    String nameTag = "name";
    String descriptionTag = "description";

    public OWASPProactiveConfigs(ArrayList<String[]> OWASPProactives){

        this.OWASPProactives = OWASPProactives;
    }

    public void createConfigFile() {

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();

        try {
            configXMLFileCreator.createFile();

            configXMLFileCreator.createParentElement(parentTag);

            for (String[] OWASPProactive : OWASPProactives) {

                Element proactiveElement = configXMLFileCreator.createChildElement(proactiveTag);

                Element proactive_idElement = configXMLFileCreator.createChildElement(idTag, OWASPProactive[0]);
                Element proactive_nameElement = configXMLFileCreator.createChildElement(nameTag, OWASPProactive[1]);
                Element proactive_descriptionElement = configXMLFileCreator.createChildElement(descriptionTag, OWASPProactive[2]);

                proactiveElement.appendChild(proactive_idElement);
                proactiveElement.appendChild(proactive_nameElement);
                proactiveElement.appendChild(proactive_descriptionElement);

                configXMLFileCreator.addToParent(proactiveElement);
            }

            configXMLFileCreator.transformAndSaveFile("OWASP_Proactives.xml");

        } catch (ParserConfigurationException e) {
            System.err.println(e);
        } catch (TransformerException e) {
            System.err.println(e);
        }
    }
}
