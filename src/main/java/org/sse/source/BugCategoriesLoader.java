package org.sse.source;

import org.dom4j.DocumentException;
import org.sse.source.model.BugCategory;
import org.sse.settings.config.source.BugModelConfig;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class BugCategoriesLoader {

    private static HashMap<String, BugCategory> bugCategoryHashMap;
    private static HashMap<Integer, BugCategory> bugCategoryWithDescriptionHashMap;

    static{
        bugCategoryHashMap = new HashMap<String, BugCategory>();
        bugCategoryWithDescriptionHashMap = new HashMap<Integer, BugCategory>();
    }

    private BugCategoriesLoader(){

    }

    /* getters */
    public static HashMap<String, BugCategory> getBugCategoryHashMap() throws DocumentException {

        if (bugCategoryHashMap.isEmpty()){
            initializeBugCategoryHashMap();
        }
        return bugCategoryHashMap;
    }

    public static HashMap<Integer, BugCategory> getBugCategoryWithDescriptionHashMap() throws DocumentException {

        if (bugCategoryWithDescriptionHashMap.isEmpty()){
            initializeBugCategoryHashMapWithDescription();
        }
        return bugCategoryWithDescriptionHashMap;
    }

    public static String getVersionName() throws DocumentException {

        return (BugModelConfig.getVersionTag());
    }

    /**
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private static void initializeBugCategoryHashMap() throws DocumentException {

        List<BugCategory> bugCategoryList = BugModelConfig.loadConfigFile();

        for (BugCategory bugCategory : bugCategoryList){

            bugCategoryHashMap.put(bugCategory.getId(), bugCategory);
        }
    }

    /**
     *
     *
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    private static void initializeBugCategoryHashMapWithDescription() throws DocumentException {

        List<BugCategory> OWASP_T10_list = BugModelConfig.loadConfigFile();

        for (BugCategory bugCategory : OWASP_T10_list){

            int key = Integer.parseInt(bugCategory.getId().substring(1));
            bugCategoryWithDescriptionHashMap.put(key, bugCategory);
        }
    }
}
