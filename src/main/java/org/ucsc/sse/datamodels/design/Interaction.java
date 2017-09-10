package org.ucsc.sse.datamodels.design;

import java.util.ArrayList;

public class Interaction {

    private String name;
    //private String type;
    private ArrayList<Threat> threats;

    public Interaction(){
        threats = new ArrayList<Threat>();
    }

    /* getters & setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Threat> getThreats() {
        return threats;
    }

    public void setThreats(ArrayList<Threat> threats) {
        this.threats = threats;
    }
}
