package org.ucsc.sse.datamodels.source_code;

import java.util.ArrayList;

public class BugCollection {

    private String id;
    private String name;
    private ArrayList<Bug> bugArrayList;

    public BugCollection(){
        bugArrayList = new ArrayList<Bug>();
    }

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
}
