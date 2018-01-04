package org.sse.association.model;

public class AssociationContainer {

    private String threat;
    private String threatCategory;
    private String bug;
    private String bugCategory;

    /* getters & setters */
    public String getThreat() {
        return threat;
    }

    public void setThreat(String threat) {
        this.threat = threat;
    }

    public String getThreatCategory() {
        return threatCategory;
    }

    public void setThreatCategory(String threatCategory) {
        this.threatCategory = threatCategory;
    }

    public String getBug() {
        return bug;
    }

    public void setBug(String bug) { this.bug = bug; }

    public String getBugCategory(){ return bugCategory;}

    public void setBugCategory(String bugCategory){ this.bugCategory = bugCategory;}
}
