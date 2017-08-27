package association.model;

import design.model.Threat;
import source.model.Bug;

import java.util.ArrayList;

/**
 * Not completed
 * */
public class Association {

    private String threatCategoryName;
    private ArrayList<Threat> threatList;
    private ArrayList<Bug> bugList;

    public Association(String threatCategoryName) {
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

    /*
    * Need to remove from here and add to Association Builder
    * */
    public void addNewBugsToList(ArrayList<Bug> newBugList){

        for (Bug bug : newBugList){
            if (!this.checkBugExist(bug)){
                bugList.add(bug);
            }
        }
    }

    private boolean checkBugExist(Bug newBug){

        for (Bug bug : bugList){
            if (bug.equals(newBug)){
                return true;
            }
        }
        return false;
    }
}
