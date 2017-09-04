package source.classification;

import source.model.Bug;

import java.util.ArrayList;

public class BugCategory {

    protected String id;
    protected String name;
    protected ArrayList<Bug> bugList;
    protected ArrayList<String> countermeasures;

    public ArrayList<Bug> getBugList() {
        return bugList;
    }

    /*public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Bug> getBugList() {
        return bugList;
    }

    public void setBugList(ArrayList<Bug> bugList) {
        this.bugList = bugList;
    }

    public ArrayList<String> getCountermeasures() {
        return countermeasures;
    }

    public void setCountermeasures(ArrayList<String> countermeasures) {
        this.countermeasures = countermeasures;
    }

    /* create rest of the abstract methods */
}
