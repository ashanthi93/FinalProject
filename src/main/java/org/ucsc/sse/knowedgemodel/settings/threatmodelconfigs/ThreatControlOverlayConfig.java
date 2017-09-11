package org.ucsc.sse.knowedgemodel.settings.threatmodelconfigs;

import org.w3c.dom.Element;
import org.ucsc.sse.knowedgemodel.settings.ConfigXMLFileCreator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;

public class ThreatControlOverlayConfig {

    String parentTag = "threat-control-overlay";
    String threatControlTag = "threat-control";
    String idTag = "id";
    String nameTag = "name";
    String fromTag = "from";

    String fileName = "ThreatControlOverlay.xml";

    public ThreatControlOverlayConfig(){}

    public void createFile(ArrayList<String[]> threatControlList) throws ParserConfigurationException, TransformerException {

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();
        configXMLFileCreator.createFile();

        configXMLFileCreator.createParentElement(parentTag);

        /* create threat-control tags */
        for (String[] threatControl : threatControlList){

            Element threatControlElement = configXMLFileCreator.createChildElement(threatControlTag);

            Element idElement = configXMLFileCreator.createChildElement(idTag, threatControl[0]);
            Element nameElement = configXMLFileCreator.createChildElement(nameTag, threatControl[1]);
            Element fromElement = configXMLFileCreator.createChildElement(fromTag, threatControl[2]);

            threatControlElement.appendChild(idElement);
            threatControlElement.appendChild(nameElement);
            threatControlElement.appendChild(fromElement);

            configXMLFileCreator.addToParent(threatControlElement);
        }
        /* end of threat-control tags */

        configXMLFileCreator.transformAndSaveFile(fileName);
    }
}
