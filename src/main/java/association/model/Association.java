package association.model;

import design.model.Threat;
import source.model.Bug;

import java.util.ArrayList;

public class Association {

    private String threatCategoryName;
    private ArrayList<Threat> threatList;
    private ArrayList<Bug> bugList;

    public Association() {}

    /* getter and setters */
    public String getThreatCategoryName() {
        return threatCategoryName;
    }

    public void setThreatCategoryName(String threatCategoryName) {
        this.threatCategoryName = threatCategoryName;
    }

    public ArrayList<Threat> getThreatList() {
        return threatList;
    }

    public void setThreatList(ArrayList<Threat> threatList) {
        this.threatList = threatList;
    }

    public ArrayList<Bug> getBugList() {
        return bugList;
    }

    public void setBugList(ArrayList<Bug> bugList) {
        this.bugList = bugList;
    }

    @Override
    public String toString() {
        return "Association{" +
                "threatCategoryName='" + threatCategoryName + '\'' +
                ", threatList=" + threatList +
                ", bugList=" + bugList +
                '}';
    }
}
