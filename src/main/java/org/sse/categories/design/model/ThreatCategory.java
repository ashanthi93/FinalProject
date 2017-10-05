package org.sse.categories.design.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.sse.design.model.Threat;

import java.util.ArrayList;
import java.util.List;

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
    private List<Threat> threatList;

    @JsonIgnore
    private List<String> securityControl;

    @JacksonXmlElementWrapper(localName = "mitigations")
    @JacksonXmlProperty(localName = "mitigation")
    @JsonProperty("mitigations")
    private List<String> mitigationList;

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

    public List<Threat> getThreatList() {
        return threatList;
    }

    public void setThreatList(List<Threat> threatList) {
        this.threatList = threatList;
    }

    public List<String> getSecurityControl() {
        return securityControl;
    }

    public void setSecurityControl(List<String> securityControl) {
        this.securityControl = securityControl;
    }

    public List<String> getMitigationList() {
        return mitigationList;
    }

    public void setMitigationList(List<String> mitigationList) {
        this.mitigationList = mitigationList;
    }
}
