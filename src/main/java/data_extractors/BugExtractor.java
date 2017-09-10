package data_extractors;

import data_extractors.collectors.BugCollector;
import data_extractors.classifiers.BugClassifier;
import org.xml.sax.SAXException;
import data_models.source_code.BugCategory;
import classifier_builders.source_code.BugClassificationBuilder;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BugExtractor {

    BugCollector bugCollector;
    HashMap<String, BugCategory> bugCategoryHashMap;

    public BugExtractor(){
        bugCollector = new BugCollector();
        bugCategoryHashMap = new HashMap<String, BugCategory>();
    }

    /**
     *
     * @param xmlFile
     */
    public void collectBugs(File xmlFile){
        bugCollector.readFile(xmlFile);
    }

    /**
     *
     */
    public void classifyBugs(){

        try {
            BugClassifier bugClassifier = new BugClassifier(
                    bugCollector.getBugArrayList(), this.loadBugCategoriesByModel());

            bugClassifier.classifyBugs();

            this.bugCategoryHashMap = bugClassifier.getBugCategoryHashMap();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void generateBugReport() {

    }

    /**
     *
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    private HashMap<String, BugCategory> loadBugCategoriesByModel() throws IOException, SAXException, ParserConfigurationException {

        BugClassificationBuilder bugClassificationBuilder = new BugClassificationBuilder();

        bugClassificationBuilder.createBugCategories();
        return (bugClassificationBuilder.getBugCategories());
    }
}
