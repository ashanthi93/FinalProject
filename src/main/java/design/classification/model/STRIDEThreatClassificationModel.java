package design.classification.model;

import design.classification.ThreatCategory;
import design.classification.ThreatClassificationModel;

import java.util.HashMap;

public class STRIDEThreatClassificationModel implements ThreatClassificationModel {

    private ThreatCategory spoofing;
    private ThreatCategory tampering;
    private ThreatCategory repudiation;
    private ThreatCategory informationDisclosure;
    private ThreatCategory dos;
    private ThreatCategory eop;

    public void createThreatCategories() {

        spoofing = this.createThreatCategory("S", "spoofing");
        tampering = this.createThreatCategory("T", "tampering");
        repudiation = this.createThreatCategory("R", "repudiation");
        informationDisclosure = this.createThreatCategory("I", "information disclosure");
        dos = this.createThreatCategory("D", "Denial Of Service");
        eop = this.createThreatCategory("E", "Elevation Of Privilege");
    }

    public HashMap<String, ThreatCategory> getThreatCategories() {

        HashMap<String,ThreatCategory> threatCategoryHashMap = new HashMap<String, ThreatCategory>();

        threatCategoryHashMap.put("S", spoofing);
        threatCategoryHashMap.put("T", tampering);
        threatCategoryHashMap.put("R", repudiation);
        threatCategoryHashMap.put("I", informationDisclosure);
        threatCategoryHashMap.put("D", dos);
        threatCategoryHashMap.put("E", eop);

        return threatCategoryHashMap;
    }

    private ThreatCategory createThreatCategory(String id, String name){

        ThreatCategory threatCategory = new ThreatCategory();
        threatCategory.setId(id);
        threatCategory.setName(name);

        return threatCategory;
    }
}
