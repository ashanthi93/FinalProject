package org.ucsc.sse.dataextractors;

import org.ucsc.sse.classifierbuilders.design.ThreatClassificationBuilder;
import org.ucsc.sse.dataextractors.collectors.report_parsers.ThreatReportParser;
import org.ucsc.sse.datamodels.design.ThreatCategory;

import org.ucsc.sse.dataextractors.collectors.ThreatCollector;
import org.ucsc.sse.dataextractors.classifiers.ThreatClassifier;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ThreatExtractor {

    private static ThreatExtractor instance;

    private ThreatReportParser threatReportParser;
    private ThreatCollector threatCollector = new ThreatCollector();
    private HashMap<String, ThreatCategory> threatCategoryHashMap = new HashMap<String, ThreatCategory>();

    private ThreatExtractor(){}

    static {
        try{
            instance = new ThreatExtractor();
        }catch (Exception e){
            throw new RuntimeException("Exception occurred in creating singleton instance ! ");
        }
    }

    public static ThreatExtractor getInstance(){
        return instance;
    }

    /**
     * Need to implement the validator
     *
     * @param threatModelingReport
     * @return
     */
    public boolean validateFile(File threatModelingReport){

        threatReportParser = new ThreatReportParser(threatModelingReport);
        return (threatReportParser.validateFile());
    }

    /**
     *
     */
    public boolean extractData() {

        threatCollector = threatReportParser.extractData();
        return ((threatCollector != null) ? true : false);
    }

    /**
     *
     */
    public void classifyThreats() {

        try {
            ThreatClassifier threatClassifier = new ThreatClassifier(
                    threatCollector.getThreatArrayList(), this.loadThreatCategoriesByModel());

            threatClassifier.classifyThreats();

            this.threatCategoryHashMap = threatClassifier.getThreatCategoryHashMap();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void generateThreatReport() {

    }

    /**
     * @return
     */
    private HashMap<String, ThreatCategory> loadThreatCategoriesByModel() throws IOException, SAXException, ParserConfigurationException {

        /* The specific classification model has to be load somehow */
        ThreatClassificationBuilder threatClassificationBuilder = new ThreatClassificationBuilder();

        return (threatClassificationBuilder.getThreatCategories());
    }
}
