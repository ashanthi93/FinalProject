package source;

import org.xml.sax.SAXException;
import source.classification.BugCategory;
import source.classification.BugClassificationModel;
import source.classification.model.OWASPBugClassificationModel;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BugAnalyzer {

    BugCollector bugCollector;
    HashMap<String, BugCategory> bugCategoryHashMap;

    public BugAnalyzer(){
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
            BugClassification bugClassification = new BugClassification(
                    bugCollector.getBugArrayList(), this.loadBugCategoriesByModel());

            bugClassification.classifyBugs();

            this.bugCategoryHashMap = bugClassification.getBugCategoryHashMap();

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

        BugClassificationModel bugClassificationModel = new OWASPBugClassificationModel();

        bugClassificationModel.createBugCategories();
        return (bugClassificationModel.getBugCategories());
    }
}
