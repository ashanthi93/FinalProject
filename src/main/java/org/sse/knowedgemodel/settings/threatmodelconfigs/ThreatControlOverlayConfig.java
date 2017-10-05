package org.sse.knowedgemodel.settings.threatmodelconfigs;

import org.w3c.dom.Element;
import org.sse.settings.ConfigXMlFileCreator;

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

        ConfigXMlFileCreator configXMlFileCreator = new ConfigXMlFileCreator();
        configXMlFileCreator.createFile();

        configXMlFileCreator.createParentElement(parentTag);

        /* create threat-control tags */
        for (String[] threatControl : threatControlList){

            Element threatControlElement = configXMlFileCreator.createChildElement(threatControlTag);

            Element idElement = configXMlFileCreator.createChildElement(idTag, threatControl[0]);
            Element nameElement = configXMlFileCreator.createChildElement(nameTag, threatControl[1]);
            Element fromElement = configXMlFileCreator.createChildElement(fromTag, threatControl[2]);

            threatControlElement.appendChild(idElement);
            threatControlElement.appendChild(nameElement);
            threatControlElement.appendChild(fromElement);

            configXMlFileCreator.addToParent(threatControlElement);
        }
        /* end of threat-control tags */

        configXMlFileCreator.transformAndSaveFile(fileName);
    }
}
