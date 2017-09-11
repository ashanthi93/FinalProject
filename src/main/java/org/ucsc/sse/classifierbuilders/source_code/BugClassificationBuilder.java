package org.ucsc.sse.classifierbuilders.source_code;

import org.ucsc.sse.datamodels.source_code.BugCategory;
import org.xml.sax.SAXException;
import org.ucsc.sse.knowedgemodel.settings.bugmodelconfigs.BugModelConfig;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BugClassificationBuilder {

    HashMap<String, BugCategory> bugCategoryHashMap;
    HashMap<Integer, BugCategory> bugCategoryWithDescriptionHashMap;

    public BugClassificationBuilder(){
        bugCategoryHashMap = new HashMap<String, BugCategory>();
        bugCategoryWithDescriptionHashMap = new HashMap<Integer, BugCategory>();
    }

    /**
     *
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public void createBugCategories() throws ParserConfigurationException, SAXException, IOException {

        BugModelConfig bugModelConfig = new BugModelConfig();

        HashMap<String, String> bugIdsAndNames = bugModelConfig.loadBugCategoryIdsAndNames();

        for (String bugID : bugIdsAndNames.keySet()){

            BugCategory bugCategory = this.createBugCategory(bugID, bugIdsAndNames.get(bugID));

            bugCategoryHashMap.put(bugID, bugCategory);
        }
    }

    public void createBugCategoriesWithDescription() throws ParserConfigurationException, SAXException, IOException {

        BugModelConfig bugModelConfig = new BugModelConfig();

        ArrayList<String[]> OWASP_T10_list = bugModelConfig.loadConfigFile();

        for (String[] owaspT10 : OWASP_T10_list){

            BugCategory bugCategoryWithDescription = this.createBugCategoryWithDescription(owaspT10[0], owaspT10[1], owaspT10[2]);

            int key = Integer.parseInt(owaspT10[0].substring(1));

            bugCategoryWithDescriptionHashMap.put(key, bugCategoryWithDescription);
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

    public HashMap<Integer, BugCategory> getBugCategoriesWithDescription() throws IOException, SAXException, ParserConfigurationException {

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
