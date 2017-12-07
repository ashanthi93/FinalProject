package org.sse.design.model;

public class ThreatMitigation {

    private String threat;
    private String category;
    private String mitigation;

    /* getters & setters */
    public String getThreat() {
        return threat;
    }

    public void setThreat(String threat) {
        this.threat = threat;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMitigation() {
        return mitigation;
    }

    public void setMitigation(String mitigation) { this.mitigation = mitigation; }
}
