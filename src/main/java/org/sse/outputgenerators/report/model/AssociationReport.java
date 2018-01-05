package org.sse.outputgenerators.report.model;

import org.sse.association.model.Association;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "association-report")
@JsonRootName("associationReport")
public class AssociationReport {

    @JacksonXmlProperty(localName = "report-name")
    @JsonProperty("report-name")
    private String name;

    @JacksonXmlProperty(localName = "time-generated")
    @JsonProperty("time-generated")
    private String date;

    @JacksonXmlElementWrapper(localName = "associations")
    @JacksonXmlProperty(localName = "association")
    @JsonProperty("associations")
    private List<Association> associationList;

    public AssociationReport() {
    }

    /* getters & setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Association> getAssociationList() {
        return associationList;
    }

    public void setAssociationList(List<Association> associations) {
        this.associationList = associations;
    }
}
