package source.report;

import source.classification.BugCategory;

import java.util.ArrayList;

public class BugReport {

    ArrayList<BugCategory> bugCategories;

    public BugReport(){
        bugCategories = new ArrayList<BugCategory>();
    }

    /* getters & setters */
    public ArrayList<BugCategory> getBugCategories() {
        return bugCategories;
    }

    public void setBugCategories(ArrayList<BugCategory> bugCategories) {
        this.bugCategories = bugCategories;
    }
}
