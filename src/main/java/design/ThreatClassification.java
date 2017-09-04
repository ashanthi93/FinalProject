package design;

import design.classification.ThreatCategory;
import design.model.Threat;
import org.xml.sax.SAXException;
import settings.stride_configs.STRIDEAttackerConfig;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ThreatClassification {

    private ArrayList<Threat> threatArrayList;
    private HashMap<String, ThreatCategory> threatCategoryHashMap;

    public ThreatClassification(ArrayList<Threat> threatArrayList, HashMap<String, ThreatCategory> threatCategoryHashMap){

        this.threatArrayList = threatArrayList;
        this.threatCategoryHashMap = threatCategoryHashMap;
    }

    public HashMap<String, ThreatCategory> getThreatCategoryHashMap() {
        return threatCategoryHashMap;
    }

    /**
     *
     *
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public void classifyThreats() throws IOException, SAXException, ParserConfigurationException {

        HashMap<String,String> defaultThreatIdsAndNames = this.loadThreatIdsAndNamesByConfigFile();

        for (Threat threat : threatArrayList){

            String threatId = this.getDefaultThreatIdForThreatCategoryName(defaultThreatIdsAndNames, threat.getThreatCategoryName());

            if (threatId != null){

                ThreatCategory threatCategory = threatCategoryHashMap.get(threatId);

                ArrayList<Threat> threatArrayList = threatCategory.getThreatList();
                threatArrayList.add(threat);

                threatCategory.setThreatList(threatArrayList);

                threatCategoryHashMap.put(threatId, threatCategory);
            }else {
                /*
                 should throw exception here
                  */
            }
        }
    }

    /**
     *
     *
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private HashMap<String,String> loadThreatIdsAndNamesByConfigFile() throws ParserConfigurationException, SAXException, IOException {

        STRIDEAttackerConfig strideAttackerConfig = new STRIDEAttackerConfig();
        return (strideAttackerConfig.loadThreatIdsAndNames());
    }

    /**
     *
     *
     * @param defaultThreatIdsAndNames
     * @param threatCategoryName
     * @return
     */
    private String getDefaultThreatIdForThreatCategoryName(HashMap<String,String> defaultThreatIdsAndNames, String threatCategoryName){

        for (String defaultThreatId : defaultThreatIdsAndNames.keySet()){

            String defaultThreatName = defaultThreatIdsAndNames.get(defaultThreatId);

            if (threatCategoryName.equals(defaultThreatName)){
                return defaultThreatId;
            }
        }
        return null;
    }
}
