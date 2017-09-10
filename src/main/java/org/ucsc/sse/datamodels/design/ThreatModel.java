package org.ucsc.sse.datamodels.design;

import java.util.ArrayList;

public class ThreatModel {

    private String id;
    private String diagramName;
    private ArrayList<Interaction> interactions;

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

    public ArrayList<Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions(ArrayList<Interaction> interactions) {
        this.interactions = interactions;
    }
}
