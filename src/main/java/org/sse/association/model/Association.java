package org.sse.association.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import org.sse.design.model.Threat;
import org.sse.source.model.Bug;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "association")
@JsonRootName("association")
public class Association{

    @JacksonXmlProperty(localName = "threat-category-name")
    @JsonProperty("threatCategoryName")
    private String threatCategoryName;

    @JacksonXmlElementWrapper(localName = "threats")
    @JacksonXmlProperty(localName = "threat")
    @JsonProperty("threats")
    private List<Threat> threatList;

    @JacksonXmlElementWrapper(localName = "bugs")
    @JacksonXmlProperty(localName = "bug")
    @JsonProperty("bugs")
    private List<Bug> bugList;

    public Association() {
        threatList = new ArrayList<Threat>();
        bugList = new ArrayList<Bug>();
    }

    /* getter and setters */
    public String getThreatCategoryName() {
        return threatCategoryName;
    }

    public void setThreatCategoryName(String threatCategoryName) {
        this.threatCategoryName = threatCategoryName;
    }

    public List<Threat> getThreatList() {
        return threatList;
    }

    public void setThreatList(List<Threat> threatList) {
        this.threatList = threatList;
    }

    public List<Bug> getBugList() {
        return bugList;
    }

    public void setBugList(List<Bug> bugList) {
        this.bugList = bugList;
    }
}
