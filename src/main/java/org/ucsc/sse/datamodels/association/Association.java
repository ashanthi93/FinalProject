package org.ucsc.sse.datamodels.association;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import org.ucsc.sse.datamodels.design.Threat;
import org.ucsc.sse.datamodels.source_code.Bug;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "association")
@JsonRootName("association")
public class Association{

    @JacksonXmlProperty(localName = "threat-category-name")
    @JsonProperty("threatCategoryName")
    private String threatCategoryName;

    @JacksonXmlElementWrapper(localName = "threats")
    @JacksonXmlProperty(localName = "threat")
    @JsonProperty("threats")
    private ArrayList<Threat> threatArrayList;

    @JacksonXmlElementWrapper(localName = "bugs")
    @JacksonXmlProperty(localName = "bug")
    @JsonProperty("bugs")
    private ArrayList<Bug> bugArrayList;

    public Association() {
        threatArrayList = new ArrayList<Threat>();
        bugArrayList = new ArrayList<Bug>();
    }

    /* getter and setters */
    public String getThreatCategoryName() {
        return threatCategoryName;
    }

    public void setThreatCategoryName(String threatCategoryName) {
        this.threatCategoryName = threatCategoryName;
    }

    public ArrayList<Threat> getThreatArrayList() {
        return threatArrayList;
    }

    public void setThreatArrayList(ArrayList<Threat> threatArrayList) {
        this.threatArrayList = threatArrayList;
    }

    public ArrayList<Bug> getBugArrayList() {
        return bugArrayList;
    }

    public void setBugArrayList(ArrayList<Bug> bugArrayList) {
        this.bugArrayList = bugArrayList;
    }
}
