package design.classification.model;

import design.classification.ThreatCategory;
import design.model.Threat;

import java.util.ArrayList;

public class SpoofingThreatCategory extends ThreatCategory {

    public ArrayList<Threat> getThreatList() {
        return threatList;
    }

    public void setThreatList(ArrayList<Threat> threatList) {
        this.threatList = threatList;
    }

    public String getSecurityControl() {
        return securityControl;
    }

    public void setSecurityControl(String securityControl) {
        this.securityControl = securityControl;
    }

    public ArrayList<String> getMitigationList() {
        return mitigationList;
    }

    public void setMitigationList(ArrayList<String> mitigationList) {
        this.mitigationList = mitigationList;
    }
}
