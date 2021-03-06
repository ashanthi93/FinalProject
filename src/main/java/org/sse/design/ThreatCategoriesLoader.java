package org.sse.design;

import org.dom4j.DocumentException;
import org.sse.design.model.ThreatCategory;
import org.sse.settings.config.design.ThreatModelConfig;

import java.util.HashMap;

public class ThreatCategoriesLoader {

    private static HashMap<String, ThreatCategory> threatCategoryHashMap;
    public static final   HashMap <String, String> tCategories = new HashMap<String, String>();


    static {
        threatCategoryHashMap = new HashMap<String, ThreatCategory>();
        tCategories.put("s","Spoofing");
        tCategories.put("t","Tampering");
        tCategories.put("r","Repudiation");
        tCategories.put("i","Information disclosure");
        tCategories.put("d","Denial of service");
        tCategories.put("e","Elevation of privilege");
    }

    private ThreatCategoriesLoader(){
    }

    /* getter & setter */
    public static HashMap<String, ThreatCategory> getThreatCategoryHashMap() throws DocumentException {

        if (threatCategoryHashMap.isEmpty()){
            initializeThreatCategoryHashMap();
        }
        return (threatCategoryHashMap);
    }

    public static void setThreatCategoryHashMap(HashMap<String, ThreatCategory> threatCategoryHashMap) {
        ThreatCategoriesLoader.threatCategoryHashMap = threatCategoryHashMap;
    }

    /**
     *
     * @throws DocumentException
     */
    private static void initializeThreatCategoryHashMap() throws DocumentException {

        HashMap<String,String> threatIdsAndNames = ThreatModelConfig.loadThreatCategoryIdsAndNames();

        for (String threatID : threatIdsAndNames.keySet()){

            ThreatCategory threatCategory = createThreatCategory(threatID, threatIdsAndNames.get(threatID));
            threatCategoryHashMap.put(threatID, threatCategory);
        }
    }

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
