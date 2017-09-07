package source.classification;

import org.xml.sax.SAXException;
import settings.bugmodel_configs.OWASPT10Config;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public class BugClassificationModel {

    HashMap<String, BugCategory> bugCategoryHashMap;

    public BugClassificationModel(){
        bugCategoryHashMap = new HashMap<String, BugCategory>();
    }

    /**
     *
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public void createBugCategories() throws ParserConfigurationException, SAXException, IOException {

        OWASPT10Config owaspt10Config = new OWASPT10Config();

        HashMap<String, String> bugIdsAndNames = owaspt10Config.loadBugCategoryIdsAndNames();

        for (String bugID : bugIdsAndNames.keySet()){

            BugCategory bugCategory = this.createBugCategory(bugID, bugIdsAndNames.get(bugID));

            bugCategoryHashMap.put(bugID, bugCategory);
        }
    }

    /**
     *
     *
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public HashMap<String, BugCategory> getBugCategories() throws IOException, SAXException, ParserConfigurationException {

        if (bugCategoryHashMap.isEmpty()){
            this.createBugCategories();
        }
        return bugCategoryHashMap;
    }

    /**
     *
     *
     * @param id
     * @param name
     * @return
     */
    private BugCategory createBugCategory(String id, String name){

        BugCategory bugCategory = new BugCategory();
        bugCategory.setId(id);
        bugCategory.setName(name);

        return bugCategory;
    }
}
