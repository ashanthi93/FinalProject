package design.classification;

import java.util.HashMap;

public interface ThreatClassificationModel {

    /**
     *
     */
    public void createThreatCategories();

    /**
     *
     *
     * @return
     */
    public HashMap<String,ThreatCategory> getThreatCategories();
}
