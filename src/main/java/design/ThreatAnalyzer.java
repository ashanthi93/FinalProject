package design;

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
            ThreatClassification threatClassification = new ThreatClassification(
                    threatCollector.getThreatArrayList(), this.loadThreatCategoriesByModel());

            threatClassification.classifyThreats();

            this.threatCategoryHashMap = threatClassification.getThreatCategoryHashMap();

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
        ThreatClassificationModel threatClassificationModel = new ThreatClassificationModel();
        threatClassificationModel.createThreatCategories();

        return (threatClassificationModel.getThreatCategories());
    }
}
