package org.sse.design.model;

import java.util.ArrayList;
import java.util.List;

public class ThreatControl {

    private String id;
    private String name;
    private List<String> description;
    private String parentController;

    public ThreatControl(){
        description = new ArrayList<String>();
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

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public String getParentController() {
        return parentController;
    }

    public void setParentController(String parentController) {
        this.parentController = parentController;
    }
}
