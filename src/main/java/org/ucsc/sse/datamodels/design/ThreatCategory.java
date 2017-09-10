package org.ucsc.sse.datamodels.design;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "threat-category")
@JsonRootName("threat-category")
public class ThreatCategory {

    @JacksonXmlProperty(localName = "id")
    @JsonProperty("id")
    private String id;

    @JacksonXmlProperty(localName = "name")
    @JsonProperty("name")
    private String name;

    @JacksonXmlElementWrapper(localName = "threats")
    @JacksonXmlProperty(localName = "threat")
    @JsonProperty("threats")
    private ArrayList<Threat> threatList;

    @JsonIgnore
    private ArrayList<String> securityControl;

    @JacksonXmlElementWrapper(localName = "mitigations")
    @JacksonXmlProperty(localName = "mitigation")
    @JsonProperty("mitigations")
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
