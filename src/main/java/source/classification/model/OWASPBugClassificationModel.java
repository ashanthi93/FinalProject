package source.classification.model;

import org.xml.sax.SAXException;
import settings.owasp_configs.OWASPT10Config;
import source.classification.BugCategory;
import source.classification.BugClassificationModel;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public class OWASPBugClassificationModel implements BugClassificationModel{

    HashMap<String, BugCategory> bugCategoryHashMap = new HashMap<String, BugCategory>();

    public void createBugCategories() throws ParserConfigurationException, SAXException, IOException {

        OWASPT10Config owaspt10Config = new OWASPT10Config();

        HashMap<String, String> bugIdsAndNames = owaspt10Config.loadBugCategoryIdsAndNames();

        for (String bugID : bugIdsAndNames.keySet()){

            BugCategory bugCategory = this.createBugCategory(bugID, bugIdsAndNames.get(bugID));

            bugCategoryHashMap.put(bugID, bugCategory);
        }
    }

    public HashMap<String, BugCategory> getBugCategories() throws IOException, SAXException, ParserConfigurationException {

        if (bugCategoryHashMap.isEmpty()){
            this.createBugCategories();
        }
        return bugCategoryHashMap;
    }

    private BugCategory createBugCategory(String id, String name){

        BugCategory bugCategory = new BugCategory();
        bugCategory.setId(id);
        bugCategory.setName(name);

        return bugCategory;
    }
}