package design.report;

import design.classification.ThreatCategory;

import java.util.ArrayList;

public class ThreatReport {

    private ArrayList<ThreatCategory> threatCategories;

    public ArrayList<ThreatCategory> getThreatCategories() {
        return threatCategories;
    }

    public void setThreatCategories(ArrayList<ThreatCategory> threatCategories) {
        this.threatCategories = threatCategories;
    }
}
