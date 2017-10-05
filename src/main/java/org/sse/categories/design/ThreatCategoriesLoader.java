package org.sse.categories.design;

import org.sse.categories.design.model.ThreatCategory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public class ThreatCategoriesLoader {

    /* change pkg to classification and add classification model loaders */
    private static HashMap<String, ThreatCategory> threatCategoryHashMap;

    private ThreatCategoriesLoader(){
        threatCategoryHashMap = new HashMap<String, ThreatCategory>();
    }

    /* getter & setter */
    public static HashMap<String, ThreatCategory> getThreatCategoryHashMap() throws ParserConfigurationException, SAXException, IOException {

        if (threatCategoryHashMap.isEmpty()){
            //initializeThreatCategoryHashMap();
        }
        return (threatCategoryHashMap);
    }

    public static void setThreatCategoryHashMap(HashMap<String, ThreatCategory> threatCategoryHashMap) {
        ThreatCategoriesLoader.threatCategoryHashMap = threatCategoryHashMap;
    }

    /**
     *
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    /*private static void initializeThreatCategoryHashMap() throws ParserConfigurationException, SAXException, IOException{

        HashMap<String,String> threatIdsAndNames = ThreatModelConfig.loadThreatCategoryIdsAndNames();

        for (String threatID : threatIdsAndNames.keySet()){

            ThreatCategory threatCategory = createThreatCategory(threatID, threatIdsAndNames.get(threatID));
            threatCategoryHashMap.put(threatID, threatCategory);
        }
    }*/

    /**
     *
     * @param id
     * @param name
     * @return
     */
    private static ThreatCategory createThreatCategory(String id, String name){

        ThreatCategory threatCategory = new ThreatCategory();

        threatCategory.setId(id);
        threatCategory.setName(name);

        return threatCategory;
    }
}
