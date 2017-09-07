package source.classification;

import org.xml.sax.SAXException;
import settings.owasp_configs.OWASPT10Config;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BugClassificationModel {

    HashMap<String, BugCategory> bugCategoryHashMap;
    HashMap<String, BugCategory> bugCategoryWithDescriptionHashMap;

    public BugClassificationModel(){
        bugCategoryHashMap = new HashMap<String, BugCategory>();
        bugCategoryWithDescriptionHashMap = new HashMap<String, BugCategory>();
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

    public void createBugCategoriesWithDescription() throws ParserConfigurationException, SAXException, IOException {

        OWASPT10Config owaspt10Config = new OWASPT10Config();

        ArrayList<String[]> OWASP_T10_list = owaspt10Config.loadConfigFile();

        for (String[] owaspT10 : OWASP_T10_list){

            BugCategory bugCategoryWithDescription = this.createBugCategoryWithDescription(owaspT10[0], owaspT10[1], owaspT10[2]);

            bugCategoryWithDescriptionHashMap.put(owaspT10[0], bugCategoryWithDescription);
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

    public HashMap<String, BugCategory> getBugCategoriesWithDescription() throws IOException, SAXException, ParserConfigurationException {

        if (bugCategoryWithDescriptionHashMap.isEmpty()){
            this.createBugCategoriesWithDescription();
        }
        return bugCategoryWithDescriptionHashMap;
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

    private BugCategory createBugCategoryWithDescription(String id, String name, String description){

        BugCategory bugCategory = new BugCategory();
        bugCategory.setId(id);
        bugCategory.setName(name);
        bugCategory.setDescription(description);

        return bugCategory;
    }
}
