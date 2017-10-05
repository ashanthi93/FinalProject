package org.sse.design.model;

import java.util.ArrayList;
import java.util.List;

public class ThreatModel {

    private String id;
    private String diagramName;
    private List<Interaction> interactions;

    public ThreatModel(){
        interactions = new ArrayList<Interaction>();
    }

    /* getters & setters */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiagramName() {
        return diagramName;
    }

    public void setDiagramName(String diagramName) {
        this.diagramName = diagramName;
    }

    public List<Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions(List<Interaction> interactions) {
        this.interactions = interactions;
    }
}
