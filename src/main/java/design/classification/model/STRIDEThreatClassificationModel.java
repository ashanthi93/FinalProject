package design.classification.model;

import design.classification.ThreatCategory;
import design.classification.ThreatClassificationModel;
import org.xml.sax.SAXException;
import settings.stride_configs.STRIDEAttackerConfig;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public class STRIDEThreatClassificationModel implements ThreatClassificationModel {

    HashMap<String, ThreatCategory> threatCategoryHashMap;

    public STRIDEThreatClassificationModel(){
        threatCategoryHashMap = new HashMap<String, ThreatCategory>();
    }

    /**
     *
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public void createThreatCategories() throws ParserConfigurationException, SAXException, IOException {

        STRIDEAttackerConfig strideAttackerConfig = new STRIDEAttackerConfig();

        HashMap<String,String> threatIdsAndNames = strideAttackerConfig.loadThreatCategoryIdsAndNames();

        for (String threatID : threatIdsAndNames.keySet()){

            ThreatCategory threatCategory = this.createThreatCategory(threatID, threatIdsAndNames.get(threatID));
            threatCategoryHashMap.put(threatID, threatCategory);
        }
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public HashMap<String, ThreatCategory> getThreatCategories() throws IOException, SAXException, ParserConfigurationException {

        if (threatCategoryHashMap == null){
            this.createThreatCategories();
        }
        return threatCategoryHashMap;
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
