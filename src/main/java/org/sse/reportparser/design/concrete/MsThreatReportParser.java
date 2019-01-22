package org.sse.reportparser.design.concrete;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import org.sse.reportparser.design.ThreatReportParser;
import org.sse.design.model.Threat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MsThreatReportParser extends ThreatReportParser {

    public MsThreatReportParser() {}

    @Override
    public boolean validateFile() {
        return true;
    }

    @Override
    public String extractName() throws DocumentException {

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(super.getThreatModelingFile());

        String xPath = "/*[name()='ThreatModel']/*[name()='MetaInformation']/*[name()='ThreatModelName']";

        Node threatModelNameNode = document.selectSingleNode(xPath);
        Element threatModelNameElement = (Element) threatModelNameNode;

        String threatModelName = new String(threatModelNameElement.getStringValue());

        return (threatModelName == null ? "NewModel" : threatModelName );
    }

    @Override
    public ArrayList<Threat> extractThreats() throws DocumentException {

        ArrayList<Threat> threatList = new ArrayList<Threat>();

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(super.getThreatModelingFile());

        String xPath = "/*[name()='ThreatModel']/*[name()='ThreatInstances']";

        Node threatInstancesNode = document.selectNodes(xPath).get(0);

        Element threatInstanceElement = (Element) threatInstancesNode;

        int id = 1;

        for (Iterator<Element> threatsIterator = threatInstanceElement.elementIterator("KeyValueOfstringThreatpc_P0_PhOB"); threatsIterator.hasNext(); ) {

            Element threatElement = threatsIterator.next();

            Element aValueElement = threatElement.element("Value");
            Element propertyElement = aValueElement.element("Properties");

            HashMap<String, String> keyValueHashMap = new HashMap<String, String>();

            String threatID = "T" + id;
            keyValueHashMap.put("ThreatID", threatID);

            for (Iterator<Element> keyValueIterator = propertyElement.elementIterator("KeyValueOfstringstring"); keyValueIterator.hasNext(); ) {

                Element keyValueElement = keyValueIterator.next();

                Element keyElement = keyValueElement.element("Key");
                Element valueElement = keyValueElement.element("Value");

                keyValueHashMap.put(keyElement.getStringValue(), valueElement.getStringValue());
            }

            Threat threat = this.setThreatValues(keyValueHashMap);
            threatList.add(threat);

            id++;
        }
        return threatList;
    }

    /**
     *
     *
     * @param keyValueHashMap
     * @return
     */
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
