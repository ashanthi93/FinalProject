package source.classification;

import source.model.Bug;

import java.util.ArrayList;

public abstract class BugCategory {

    //make public or static final?? (All the variables)
    protected int id;
    protected ArrayList<Bug> bugList;
    protected ArrayList<String> countermeasures;

    public abstract ArrayList<Bug> getBugList();

    public abstract void setBugList(ArrayList<Bug> bugList);

    public abstract ArrayList<String> getCountermeasures();

    public abstract void setCountermeasures(ArrayList<String> countermeasures);
}
