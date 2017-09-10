package org.ucsc.sse.knowedgemodel.settings.threatmodelconfigs;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.ucsc.sse.knowedgemodel.settings.ConfigXMLFileCreator;
import org.ucsc.sse.knowedgemodel.settings.ConfigXMLFileReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AttackerDefensiveMappingConfig {

    String parentTag = "attacker-defensive-mapping";
    String threatTypeTag = "threat-type";
    String idTag = "id";
    String defensesTag = "defenses";
    String defenseTag = "defense";

    String fileName = "AttackerDefensiveMapping.xml";

    public AttackerDefensiveMappingConfig(){}

    public void createFile(HashMap<String, String[]> defensesMappingHashMap) throws ParserConfigurationException, TransformerException {

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();
        configXMLFileCreator.createFile();

        configXMLFileCreator.createParentElement(parentTag);

        /* create threat-type tags */
        for (String key : defensesMappingHashMap.keySet()){

            /* create threat-type tag */
            Element threatTypeElement = configXMLFileCreator.createChildElement(threatTypeTag);

            Element idElement = configXMLFileCreator.createChildElement(idTag, key);
            threatTypeElement.appendChild(idElement);

            /* create defenses tags */
            Element defensesElement = configXMLFileCreator.createChildElement(defensesTag);

            for(String defense : defensesMappingHashMap.get(key)){
                Element defenseElement = configXMLFileCreator.createChildElement(defenseTag,defense);
                defensesElement.appendChild(defenseElement);
            }
            /* end of defenses tags */

            threatTypeElement.appendChild(defensesElement);
            /* end of threat-type tag */

            configXMLFileCreator.addToParent(threatTypeElement);
        }
        /* end of threat-type tags */

        configXMLFileCreator.transformAndSaveFile(fileName);
    }

    public HashMap<String, String[]> loadConfigFile() throws IOException, SAXException, ParserConfigurationException {

        HashMap<String, String[]> STRIDE_defensive_mapping = new HashMap<String, String[]>();

        ConfigXMLFileReader configXMLFileReader = new ConfigXMLFileReader();
        configXMLFileReader.loadFile(fileName);

        NodeList nodeList = configXMLFileReader.loadNodesByTagName(threatTypeTag);

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                String key = element.getElementsByTagName(idTag).item(0).getTextContent();
                int size = element.getElementsByTagName(defenseTag).getLength();
                String[] values = new String[size];
                for(int j=0; j<size; j++){
                    values[j] = element.getElementsByTagName(defenseTag).item(j).getTextContent();
                }

                STRIDE_defensive_mapping.put(key, values);
            }
        }

        return STRIDE_defensive_mapping;
    }
}
