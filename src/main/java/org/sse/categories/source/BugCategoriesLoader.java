package org.sse.categories.source;

import org.sse.categories.source.model.BugCategory;
import org.xml.sax.SAXException;
import org.sse.settings.config.source.BugModelConfig;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BugCategoriesLoader {

    private static HashMap<String, BugCategory> bugCategoryHashMap;
    private static HashMap<Integer, BugCategory> bugCategoryWithDescriptionHashMap;

    private BugCategoriesLoader(){
        bugCategoryHashMap = new HashMap<String, BugCategory>();
        bugCategoryWithDescriptionHashMap = new HashMap<Integer, BugCategory>();
    }

    /* getters & setters */
    public static HashMap<String, BugCategory> getBugCategoryHashMap() throws ParserConfigurationException, SAXException, IOException {

        if (bugCategoryHashMap.isEmpty()){
            initializeBugCategoryHashMap();
        }
        return bugCategoryHashMap;
    }

    public static void setBugCategoryHashMap(HashMap<String, BugCategory> bugCategoryHashMap) {
        BugCategoriesLoader.bugCategoryHashMap = bugCategoryHashMap;
    }

    public static HashMap<Integer, BugCategory> getBugCategoryWithDescriptionHashMap() throws IOException, SAXException, ParserConfigurationException {

        if (bugCategoryWithDescriptionHashMap.isEmpty()){
            initializeBugCategoryHashMapWithDescription();
        }
        return bugCategoryWithDescriptionHashMap;
    }

    /**
     *
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private static void initializeBugCategoryHashMap() throws ParserConfigurationException, SAXException, IOException {

        HashMap<String, String> bugIdsAndNames = BugModelConfig.loadBugCategoryIdsAndNames();

        for (String bugID : bugIdsAndNames.keySet()){

            BugCategory bugCategory = createBugCategory(bugID, bugIdsAndNames.get(bugID));

            bugCategoryHashMap.put(bugID, bugCategory);
        }
    }

    /**
     *
     *
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    private static void initializeBugCategoryHashMapWithDescription() throws IOException, SAXException, ParserConfigurationException {

        ArrayList<String[]> OWASP_T10_list = BugModelConfig.loadConfigFile();

        for (String[] owaspT10 : OWASP_T10_list){

            BugCategory bugCategoryWithDescription = createBugCategoryWithDescription(owaspT10[0], owaspT10[1], owaspT10[2]);

            int key = Integer.parseInt(owaspT10[0].substring(1));

            bugCategoryWithDescriptionHashMap.put(key, bugCategoryWithDescription);
        }
    }

    /**
     *
     *
     * @param id
     * @param name
     * @return
     */
    private static BugCategory createBugCategory(String id, String name){

        BugCategory bugCategory = new BugCategory();
        bugCategory.setId(id);
        bugCategory.setName(name);

        return bugCategory;
    }

    /**
     *
     *
     * @param id
     * @param name
     * @param description
     * @return
     */
    private static BugCategory createBugCategoryWithDescription(String id, String name, String description){

        BugCategory bugCategory = new BugCategory();
        bugCategory.setId(id);
        bugCategory.setName(name);
        bugCategory.setDescription(description);

        return bugCategory;
    }
}
