package design.classification;

import design.model.Threat;

import java.util.ArrayList;

public class ThreatCategory {

    private String id;
    private String name;
    private ArrayList<Threat> threatList;
    private ArrayList<String> securityControl;
    private ArrayList<String> mitigationList;

    public ThreatCategory() {
        threatList = new ArrayList<Threat>();
        securityControl = new ArrayList<String>();
        mitigationList = new ArrayList<String>();
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

    public ArrayList<Threat> getThreatList() {
        return threatList;
    }

    public void setThreatList(ArrayList<Threat> threatList) {
        this.threatList = threatList;
    }

    public ArrayList<String> getSecurityControl() {
        return securityControl;
    }

    public void setSecurityControl(ArrayList<String> securityControl) {
        this.securityControl = securityControl;
    }

    public ArrayList<String> getMitigationList() {
        return mitigationList;
    }

    public void setMitigationList(ArrayList<String> mitigationList) {
        this.mitigationList = mitigationList;
    }
}
