package org.ucsc.sse.classifierbuilders.design;

import org.ucsc.sse.datamodels.design.ThreatCategory;
import org.xml.sax.SAXException;
import org.ucsc.sse.knowedgemodel.settings.threatmodelconfigs.AttackerPerspectiveConfig;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public class ThreatClassificationBuilder {

    HashMap<String, ThreatCategory> threatCategoryHashMap;

    public ThreatClassificationBuilder(){
        threatCategoryHashMap = new HashMap<String, ThreatCategory>();
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public HashMap<String, ThreatCategory> getThreatCategories() throws IOException, SAXException, ParserConfigurationException {

        this.createThreatCategories();

        return threatCategoryHashMap;
    }

    /**
     *
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private void createThreatCategories() throws ParserConfigurationException, SAXException, IOException {

        AttackerPerspectiveConfig attackerPerspectiveConfig = new AttackerPerspectiveConfig();

        HashMap<String,String> threatIdsAndNames = attackerPerspectiveConfig.loadThreatCategoryIdsAndNames();

        for (String threatID : threatIdsAndNames.keySet()){

            ThreatCategory threatCategory = this.createThreatCategory(threatID, threatIdsAndNames.get(threatID));
            threatCategoryHashMap.put(threatID, threatCategory);
        }
    }

    /**
     *
     * @param id
     * @param name
     * @return
     */
    private ThreatCategory createThreatCategory(String id, String name){

        ThreatCategory threatCategory = new ThreatCategory();

        threatCategory.setId(id);
        threatCategory.setName(name);

        return threatCategory;
    }
}
