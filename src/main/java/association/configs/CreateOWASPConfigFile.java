package association.configs;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Ashi on 8/16/2017.
 */
public class CreateOWASPConfigFile {

    Document document;

    public void createFile() {

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            document = documentBuilder.newDocument();

            Element OWASPElement = document.createElement("OWASP");
            document.appendChild(OWASPElement);

            /* create OWASPT10 elements */
            Element OWASPT10Element = this.createOWASPT10Elements();
            OWASPElement.appendChild(OWASPT10Element);

            /* create OWASP proactives */
            Element OWASPProactiveElement = this.createOWASPProactiveElements();
            OWASPElement.appendChild(OWASPProactiveElement);

            /* Transform and write config file */
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);

            StreamResult streamResult = new StreamResult(new File("src/main/resources/configurations/owasp.xml"));
            transformer.transform(source, streamResult);
            /* end of write */

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private Element createOWASPT10Elements() {

        Element OWASPT10Element = document.createElement("OWASP-T10");

        Element typeElement;

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("C2");
        arrayList.add("C3");
        arrayList.add("C4");

        typeElement = this.createT10TypeElement("A1", "SQL-injection", "---", arrayList);
        OWASPT10Element.appendChild(typeElement);

        arrayList.add("C9");
        typeElement = this.createT10TypeElement("A2", "crosssite", "dddddddddd", arrayList);

        OWASPT10Element.appendChild(typeElement);

        return OWASPT10Element;
    }

    private Element createT10TypeElement(String id, String name, String description, ArrayList<String> proactiveIDs) {

        Element typeElement = document.createElement("type");

        /* create id element */
        Element idElement = document.createElement("id");
        idElement.appendChild(document.createTextNode(id));

        typeElement.appendChild(idElement);

        /* create name and description element */
        Element nameElement = document.createElement("name");
        nameElement.appendChild(document.createTextNode(name));

        Element descriptionElement = document.createElement("description");
        descriptionElement.appendChild(document.createTextNode(description));

        typeElement.appendChild(nameElement);
        typeElement.appendChild(descriptionElement);

        /* create proactive control elements */
        Element proactiveControlElement = document.createElement("proactive-control");

        for (int i = 0; i < proactiveIDs.size(); i++) {

            //create control element
            Element controlElement = document.createElement("control");
            controlElement.appendChild(document.createTextNode(proactiveIDs.get(i)));

            proactiveControlElement.appendChild(controlElement);
        }

        typeElement.appendChild(proactiveControlElement);
        /* end of proactive control element */

        return typeElement;
    }

    private Element createOWASPProactiveElements() {

        Element OWASPProactiveElement = document.createElement("OWASP-Proactives");

        Element proactive = document.createElement("proactive");
        proactive.appendChild(document.createTextNode("C1"));

        OWASPProactiveElement.appendChild(proactive);

        return OWASPProactiveElement;
    }

    public static void main(String args[]) {
        CreateOWASPConfigFile createOWASPConfigFile = new CreateOWASPConfigFile();
        createOWASPConfigFile.createFile();
    }
}
