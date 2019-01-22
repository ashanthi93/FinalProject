package org.sse.design.model;

import java.util.ArrayList;
import java.util.List;

public class Interaction {

    private String id;
    private String name;
    private List<Threat> threats;

    public Interaction(){
        threats = new ArrayList<Threat>();
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

    public List<Threat> getThreats() {
        return threats;
    }

    public void setThreats(List<Threat> threats) {
        this.threats = threats;
    }
}
