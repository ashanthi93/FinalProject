package org.ucsc.sse.dataextractors.classifiers;

import org.ucsc.sse.datamodels.design.ThreatCategory;
import org.ucsc.sse.datamodels.design.Threat;
import org.xml.sax.SAXException;
import org.ucsc.sse.knowedgemodel.settings.threatmodelconfigs.AttackerPerspectiveConfig;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ThreatClassifier {

    private ArrayList<Threat> threatArrayList;
    private HashMap<String, ThreatCategory> threatCategoryHashMap;

    public ThreatClassifier(ArrayList<Threat> threatArrayList, HashMap<String, ThreatCategory> threatCategoryHashMap){

        this.threatArrayList = threatArrayList;
        this.threatCategoryHashMap = threatCategoryHashMap;
    }

    /* getters */
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
    public void classifyThreats() throws Exception {

        HashMap<String,String> defaultThreatCategoryIdsAndNames = this.loadThreatCategoryIdsAndNamesByConfigFile();

        for (Threat threat : threatArrayList){

            String threatCategoryID = this.getDefaultThreatCategoryIdForThreatCategoryName(
                    defaultThreatCategoryIdsAndNames, threat.getThreatCategoryName());

            if (threatCategoryID != null){

                ThreatCategory threatCategory = threatCategoryHashMap.get(threatCategoryID);

                ArrayList<Threat> threatArrayList = threatCategory.getThreatList();
                threatArrayList.add(threat);

                threatCategory.setThreatList(threatArrayList);

                threatCategoryHashMap.put(threatCategoryID, threatCategory);
            }else {
                throw new Exception();
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
    private HashMap<String,String> loadThreatCategoryIdsAndNamesByConfigFile() throws ParserConfigurationException, SAXException, IOException {

        AttackerPerspectiveConfig attackerPerspectiveConfig = new AttackerPerspectiveConfig();
        return (attackerPerspectiveConfig.loadThreatCategoryIdsAndNames());
    }

    /**
     *
     *
     * @param defaultThreatCategoryIdsAndNames
     * @param threatCategoryName
     * @return
     */
    private String getDefaultThreatCategoryIdForThreatCategoryName(HashMap<String,String> defaultThreatCategoryIdsAndNames, String threatCategoryName){

        for (String defaultThreatCategoryId : defaultThreatCategoryIdsAndNames.keySet()){

            String defaultThreatCategoryName = defaultThreatCategoryIdsAndNames.get(defaultThreatCategoryId);

            if (threatCategoryName.equals(defaultThreatCategoryName)){
                return defaultThreatCategoryId;
            }
        }
        return null;
    }
}
