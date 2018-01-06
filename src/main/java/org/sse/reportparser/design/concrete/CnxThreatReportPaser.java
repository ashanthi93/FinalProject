package org.sse.reportparser.design.concrete;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.sse.design.model.Threat;
import org.sse.design.model.ThreatMitigation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CnxThreatReportPaser {
    public static HashMap<Integer, ThreatMitigation> extractThreats(File file) throws DocumentException {

        ArrayList<Threat> threatList = new ArrayList<Threat>();

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(file);

        String xPath = "/*[name()='threat-category-report']/*[name()='threat-categories']";

        Node threatInstancesNode = document.selectNodes(xPath).get(0);

        Element threatInstanceElement = (Element) threatInstancesNode;

        int id = 1;
        HashMap<Integer, ThreatMitigation> threatObjects = new HashMap<>();

        for (Iterator<Element> threatsIterator = threatInstanceElement.elementIterator("threat-category"); threatsIterator.hasNext(); ) {

            Element threatElement = threatsIterator.next();

            Element category = threatElement.element("name");
            Element propertyElement = threatElement.element("threats");

            ThreatMitigation threatmitigation = new ThreatMitigation();
            threatmitigation.setCategory(category.getStringValue());


            for (Iterator<Element> keyValueIterator = propertyElement.elementIterator("threat"); keyValueIterator.hasNext(); ) {

                Element keyValueElement = keyValueIterator.next();

                Element threat = keyValueElement.element("name");
                threatmitigation.setThreat(threat.getStringValue());

            }
            Element propertyElement2 = threatElement.element("mitigations");
            String mitigations = "";

            for (Iterator<Element> keyValueIterator = propertyElement2.elementIterator("mitigation"); keyValueIterator.hasNext(); ) {

                Element keyValueElement = keyValueIterator.next();

                String mitigate = keyValueElement.getStringValue();
                mitigations = mitigations + "\n" + mitigate;

            }
            threatmitigation.setMitigation(mitigations.trim());
            threatObjects.put(id,threatmitigation);
            id++;
        }

        return threatObjects;
    }

    private Threat setThreatValues(HashMap<String, String> keyValueHashMap) {

        Threat threat = new Threat();

        for (String key : keyValueHashMap.keySet()) {

            String value = keyValueHashMap.get(key);

            if (key.equals("ThreatID")) {
                threat.setId(value);
            }else if (key.equals("Title")) {
                threat.setName(value);
            } else if (key.equals("UserThreatCategory")) {
                threat.setThreatCategoryName(value);
            } else if (key.equals("UserThreatShortDescription")) {
                threat.setShortDescription(value);
            } else if (key.equals("UserThreatDescription")) {
                threat.setDescription(value);
            } else if (key.equals("InteractionString")) {
                threat.setInteractionName(value);
            } else if (key.equals("Priority")) {
                threat.setPriority(value);
            }
        }
        return threat;
    }
}
