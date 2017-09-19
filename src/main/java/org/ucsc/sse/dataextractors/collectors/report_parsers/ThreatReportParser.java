package org.ucsc.sse.dataextractors.collectors.report_parsers;

import org.ucsc.sse.dataextractors.collectors.ThreatCollector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ThreatReportParser {

    File threatModelingReport;

    public ThreatReportParser(File threatModelingReport){
        this.threatModelingReport = threatModelingReport;
    }

    public boolean validateFile(){
        return true;
    }

    public ThreatCollector extractData(){

        Document document = null;
        document = this.loadFile(document);

        NodeList nodeList = this.loadNodesByTagName(document, "ThreatInstances");

        for (int i=0; i < nodeList.getLength(); i++){

            Node node = nodeList.item(i);
            Element element = (Element) node;

            NodeList childNodes = element.getChildNodes();

            for (int j=0; j < childNodes.getLength(); j++){

                Node child = childNodes.item(j);
                Element childElement = (Element) child;

            }
        }

        ThreatCollector threatCollector = new ThreatCollector();
        return threatCollector;
    }

    private Document loadFile(Document document) {

        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(threatModelingReport);

            return document;

        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public NodeList loadNodesByTagName(Document document, String tagName){

        NodeList nodeList = document.getElementsByTagName(tagName);
        return nodeList;
    }
 }
