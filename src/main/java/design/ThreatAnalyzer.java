package design;

import design.classification.model.STRIDEThreatClassificationModel;
import design.classification.ThreatClassificationModel;
import design.classification.ThreatCategory;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ThreatAnalyzer {

    ThreatCollector threatCollector;
    HashMap<String, ThreatCategory> threatCategoryHashMap;

    public ThreatAnalyzer() {
        threatCollector = new ThreatCollector();
    }

    public void collectThreats(File xmlFile) {
        threatCollector.readFile(xmlFile);
    }

    /**
     *
     */
    public void classifyThreats() {

        ThreatClassification threatClassification = new ThreatClassification( threatCollector.getThreatArrayList(), this.loadThreatCategoriesByModel());

        try {
            threatClassification.classifyThreats();
            this.threatCategoryHashMap = threatClassification.getThreatCategoryHashMap();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void generateThreatReport() {

    }

    /**
     * @return
     */
    private HashMap<String, ThreatCategory> loadThreatCategoriesByModel() {

        /* This has to be load somehow */
        ThreatClassificationModel threatClassificationModel = new STRIDEThreatClassificationModel();
        threatClassificationModel.createThreatCategories();

        return (threatClassificationModel.getThreatCategories());
    }
}
