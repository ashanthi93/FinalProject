package org.ucsc.sse.dataextractors;

import org.ucsc.sse.classifierbuilders.design.ThreatClassificationBuilder;
import org.ucsc.sse.datamodels.design.ThreatCategory;

import org.ucsc.sse.dataextractors.collectors.ThreatCollector;
import org.ucsc.sse.dataextractors.classifiers.ThreatClassifier;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ThreatExtractor {

    ThreatCollector threatCollector;
    HashMap<String, ThreatCategory> threatCategoryHashMap;

    public ThreatExtractor() {
        threatCollector = new ThreatCollector();
        threatCategoryHashMap = new HashMap<String, ThreatCategory>();
    }

    /**
     *
     * @param xmlFile
     */
    public void collectThreats(File xmlFile) {
        threatCollector.readFile(xmlFile);
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
