package org.ucsc.sse.dataextractors.collectors.report_parsers;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.ucsc.sse.datamodels.design.Threat;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ThreatReportParser {

    File threatModelingReport;

    public ThreatReportParser(File threatModelingReport) {
        this.threatModelingReport = threatModelingReport;
    }

    public boolean validateFile() {
        return true;
    }

    public String extractName(){

        String threatModelName = null;

        /* Need to test with TMT tool */
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(threatModelingReport);

            String xPath = "/*[name()='ThreatModel']/*[name()='MetaInformation']/*[name()='ThreatModelName']";

            Node threatModelNameNode = document.selectSingleNode(xPath);
            Element threatModelNameElement = (Element) threatModelNameNode;

            threatModelName = threatModelNameElement.getStringValue();

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return threatModelName;
    }

    /**
     *
     *
     * @return
     */
    public List<Threat> extractThreats() {

        List<Threat> threatList = null;

        try {
            threatList = this.fileParser();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return threatList;
    }

    /**
     *
     *
     * @return
     * @throws DocumentException
     */
    private List<Threat> fileParser() throws DocumentException {

        List<Threat> threatList = new ArrayList<Threat>();

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(threatModelingReport);

        String xPath = "/*[name()='ThreatModel']/*[name()='ThreatInstances']";

        Node threatInstancesNode = document.selectNodes(xPath).get(0);

        Element threatInstanceElement = (Element) threatInstancesNode;

        int id = 1;

        for (Iterator<Element> threatsIterator = threatInstanceElement.elementIterator("KeyValueOfstringThreatpc_P0_PhOB"); threatsIterator.hasNext(); ) {

            Element threatElement = threatsIterator.next();

            Element aValueElement = threatElement.element("Value");
            Element propertyElement = aValueElement.element("Properties");

            HashMap<String,String> keyValueHashMap = new HashMap<String, String>();

            String threatID = "T" + id;
            keyValueHashMap.put("ThreatID",threatID);

            for (Iterator<Element> keyValueIterator = propertyElement.elementIterator("KeyValueOfstringstring"); keyValueIterator.hasNext(); ) {

                Element keyValueElement = keyValueIterator.next();

                Element keyElement = keyValueElement.element("Key");
                Element valueElement = keyValueElement.element("Value");

                keyValueHashMap.put(keyElement.getStringValue(),valueElement.getStringValue());
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
    private Threat setThreatValues(HashMap<String,String> keyValueHashMap){

        Threat threat = new Threat();

        for (String key : keyValueHashMap.keySet()) {

            String value = keyValueHashMap.get(key);

            if(key.equals("ThreatID")){
                threat.setId(value);
            }if (key.equals("Title")) {
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
