package org.sse.reportparser;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.sse.association.model.AssociationContainer;
import org.sse.design.model.Threat;
import org.sse.design.model.ThreatMitigation;
import org.sse.knowedgemodel.prolog.PrologConverter;
import org.sse.source.model.BugCountermeasures;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CnxReportPaser {
    static PrologConverter prolog = new PrologConverter();
    public static HashMap <Integer,ThreatMitigation> threats = new HashMap<>();
    public static HashMap <Integer,BugCountermeasures> bugs = new HashMap<>();

    public static HashMap<Integer, ThreatMitigation> extractThreats(File file) throws DocumentException {

        SAXReader saxReader = new SAXReader();
        saxReader.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId)
                    throws SAXException, IOException {
                if (systemId.contains("threatreport.dtd")) {
                    return new InputSource(new StringReader(""));
                } else {
                    return null;
                }
            }
        });
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

            String allThreats = "";
            for (Iterator<Element> keyValueIterator = propertyElement.elementIterator("threat"); keyValueIterator.hasNext(); ) {

                Element keyValueElement = keyValueIterator.next();

                Element threat = keyValueElement.element("name");
                allThreats = allThreats + "\n" + threat.getStringValue() + "\n";

            }
            threatmitigation.setThreat(allThreats.trim());
            Element propertyElement2 = threatElement.element("mitigations");
            String mitigations = "";

            for (Iterator<Element> keyValueIterator = propertyElement2.elementIterator("mitigation"); keyValueIterator.hasNext(); ) {

                Element keyValueElement = keyValueIterator.next();

                String mitigate = keyValueElement.getStringValue();
                mitigations = mitigations + "\n" + mitigate + "\n";

            }
            threatmitigation.setMitigation(mitigations.trim());
            threatObjects.put(id,threatmitigation);
            id++;
        }

        return threatObjects;
    }

    public static HashMap<Integer, BugCountermeasures> extractBugs(File file) throws DocumentException {

        SAXReader saxReader = new SAXReader();
        saxReader.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId)
                    throws SAXException, IOException {
                if (systemId.contains("threatreport.dtd")) {
                    return new InputSource(new StringReader(""));
                } else {
                    return null;
                }
            }
        });
        Document document = saxReader.read(file);

        String xPath = "/*[name()='bug-category-report']/*[name()='bug-categories']";

        Node bugInstancesNode = document.selectNodes(xPath).get(0);

        Element bugInstanceElement = (Element) bugInstancesNode;

        int id = 1;
        HashMap<Integer, BugCountermeasures> bugObjects = new HashMap<>();

        for (Iterator<Element> bugsIterator = bugInstanceElement.elementIterator("bug-category"); bugsIterator.hasNext(); ) {

            Element bugElement = bugsIterator.next();

            Element category = bugElement.element("name");
            Element propertyElement = bugElement.element("bugs");

            BugCountermeasures bugCountermeasures = new BugCountermeasures();
            bugCountermeasures.setCategory(category.getStringValue());

            String allBugs = "";
            for (Iterator<Element> keyValueIterator = propertyElement.elementIterator("bug"); keyValueIterator.hasNext(); ) {

                Element keyValueElement = keyValueIterator.next();

                Element bug = keyValueElement.element("name");
                allBugs = allBugs + "\n" + bug.getStringValue() + "\n";

            }
            bugCountermeasures.setBug(allBugs.trim());
            Element propertyElement2 = bugElement.element("countermeasures");
            String countermeasures = "";

            for (Iterator<Element> keyValueIterator = propertyElement2.elementIterator("countermeasure"); keyValueIterator.hasNext(); ) {

                Element keyValueElement = keyValueIterator.next();

                String countermeasure = keyValueElement.getStringValue();
                countermeasures = countermeasures + "\n" + countermeasure + "\n";

            }
            bugCountermeasures.setCountermeasure(countermeasures.trim());
            bugObjects.put(id,bugCountermeasures);
            id++;
        }

        return bugObjects;
    }

    public static HashMap<Integer, AssociationContainer> extractAssociations(File file) throws DocumentException {

        SAXReader saxReader = new SAXReader();
        saxReader.setEntityResolver(new EntityResolver() {
            @Override
            public InputSource resolveEntity(String publicId, String systemId)
                    throws SAXException, IOException {
                if (systemId.contains("threatreport.dtd")) {
                    return new InputSource(new StringReader(""));
                } else {
                    return null;
                }
            }
        });
        Document document = saxReader.read(file);

        String xPath = "/*[name()='association-report']/*[name()='associations']";

        Node associationInstancesNode = document.selectNodes(xPath).get(0);

        Element associationInstanceElement = (Element) associationInstancesNode;

        int id = 1;
        HashMap<Integer, AssociationContainer> associationObjects = new HashMap<>();
        HashMap<Integer, BugCountermeasures> bugObjects = new HashMap<>();
        HashMap<Integer, ThreatMitigation> threatObjects = new HashMap<>();

        String previousCategory = "";
        for (Iterator<Element> associationIterator = associationInstanceElement.elementIterator("association"); associationIterator.hasNext(); ) {

            AssociationContainer associationContainer = new AssociationContainer();
            ThreatMitigation threatMitigation = new ThreatMitigation();
            BugCountermeasures bugCountermeasures = new BugCountermeasures();

            Element associationElement = associationIterator.next();

            Element bugCategory = associationElement.element("bug-category-name");
            Element propertyElementBug = associationElement.element("bugs");

            Element threatCategory = associationElement.element("threat-category-name");
            Element propertyElementThreat = associationElement.element("threats");

            String bcategory = bugCategory.getStringValue();
            String tcategory = threatCategory.getStringValue();

            associationContainer.setBugCategory(bcategory);

            if (!bcategory.equals(previousCategory)) {
                bugCountermeasures.setCategory(bcategory);
                List<String> Plist = prolog.getPreventionTechniques(bcategory.toLowerCase().split(":")[0]);
                String allCountermeasures = "";
                for (String mitigation : Plist) {
                    allCountermeasures = allCountermeasures + mitigation + "\n\n";
                }
                bugCountermeasures.setCountermeasure(allCountermeasures);
            }

            associationContainer.setThreatCategory(tcategory);
            threatMitigation.setCategory(tcategory);

            List<String> Tlist = prolog.getMitigationTechniques(tcategory.toLowerCase().replace(" ", "_"));
            String allpreventions = "";
            for (String prevention : Tlist){
                allpreventions = allpreventions + prevention + "\n\n";
            }
            threatMitigation.setMitigation(allpreventions);

            String allBugs = "";
            for (Iterator<Element> keyValueIterator = propertyElementBug.elementIterator("bug"); keyValueIterator.hasNext(); ) {

                Element keyValueElement = keyValueIterator.next();

                Element bug = keyValueElement.element("name");
                allBugs = allBugs + "\n" + bug.getStringValue() + "\n";

            }
            allBugs = allBugs.trim();
            associationContainer.setBug(allBugs);

            if (!bcategory.equals(previousCategory)) {
                bugCountermeasures.setBug(allBugs);
            }

            String allThreats = "";
            for (Iterator<Element> keyValueIterator = propertyElementThreat.elementIterator("threat"); keyValueIterator.hasNext(); ) {

                Element keyValueElement = keyValueIterator.next();

                Element threat = keyValueElement.element("name");
                allThreats = allThreats + "\n" + threat.getStringValue() + "\n";

            }
            allThreats = allThreats.trim();
            associationContainer.setThreat(allThreats);
            threatMitigation.setThreat(allThreats);
            associationObjects.put(id,associationContainer);

            if (!bcategory.equals(previousCategory)) {
                bugObjects.put(id, bugCountermeasures);
                previousCategory = bcategory;
            }
            threatObjects.put(id,threatMitigation);
            id++;
        }
        threats = threatObjects;
        bugs = bugObjects;
        return associationObjects;
    }
}
