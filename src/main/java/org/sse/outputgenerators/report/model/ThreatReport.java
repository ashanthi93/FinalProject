package org.sse.outputgenerators.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.sse.design.model.ThreatCategory;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "threat-category-report")
@JsonRootName("threatCategoryReport")
public class ThreatReport {

    @JacksonXmlProperty(localName = "report-name")
    @JsonProperty("report-name")
    private String name;

    @JacksonXmlProperty(localName = "time-generated")
    @JsonProperty("time-generated")
    private String date;

    @JacksonXmlElementWrapper(localName = "threat-categories")
    @JacksonXmlProperty(localName = "threat-category")
    @JsonProperty("threatCategories")
    private List<ThreatCategory> threatCategories;

    public ThreatReport(){}

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

    public List<ThreatCategory> getThreatCategories() {
        return threatCategories;
    }

    public void setThreatCategories(List<ThreatCategory> threatCategories) {
        this.threatCategories = threatCategories;
    }
}
