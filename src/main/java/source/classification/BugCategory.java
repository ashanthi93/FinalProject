package source.classification;

import source.model.Bug;

import java.util.ArrayList;

public class BugCategory {

    private String id;
    private String name;
    private ArrayList<Bug> bugArrayList;
    private ArrayList<String> countermeasures;

    /* getters & setters */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Bug> getBugArrayList() {
        return bugArrayList;
    }

    public void setBugArrayList(ArrayList<Bug> bugArrayList) {
        this.bugArrayList = bugArrayList;
    }

    public ArrayList<String> getCountermeasures() {
        return countermeasures;
    }

    public void setCountermeasures(ArrayList<String> countermeasures) {
        this.countermeasures = countermeasures;
    }
}