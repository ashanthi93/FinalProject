package settings;

import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;

public class OWASPProactiveConfig {

    String parentTag = "owasp-proactives";
    String proactiveTag = "proactive";
    String idTag = "id";
    String nameTag = "name";
    String descriptionTag = "description";

    public OWASPProactiveConfig() {}

    public void createConfigFile(ArrayList<String[]> OWASPProactives) throws ParserConfigurationException, TransformerException {

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();
        configXMLFileCreator.createFile();

        configXMLFileCreator.createParentElement(parentTag);

        /* create proactive tags */
        for (String[] OWASPProactive : OWASPProactives) {

            /* create proactive tag */
            Element proactiveElement = configXMLFileCreator.createChildElement(proactiveTag);

            Element proactive_idElement = configXMLFileCreator.createChildElement(idTag, OWASPProactive[0]);
            Element proactive_nameElement = configXMLFileCreator.createChildElement(nameTag, OWASPProactive[1]);
            Element proactive_descriptionElement = configXMLFileCreator.createChildElement(descriptionTag, OWASPProactive[2]);

            proactiveElement.appendChild(proactive_idElement);
            proactiveElement.appendChild(proactive_nameElement);
            proactiveElement.appendChild(proactive_descriptionElement);
            /* end of proactive tag */

            configXMLFileCreator.addToParent(proactiveElement);
        }
        /* end of proactive tags */

        configXMLFileCreator.transformAndSaveFile("OWASP_Proactives.xml");
    }
}
