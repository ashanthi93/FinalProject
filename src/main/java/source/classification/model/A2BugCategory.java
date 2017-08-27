package source.classification.model;

import source.classification.BugCategory;
import source.model.Bug;

import java.util.ArrayList;

public class A2BugCategory extends BugCategory {

    public ArrayList<Bug> getBugList() {
        return  bugList;
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
}
