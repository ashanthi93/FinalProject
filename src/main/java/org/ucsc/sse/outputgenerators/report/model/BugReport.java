package org.ucsc.sse.outputgenerators.report.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import org.ucsc.sse.datamodels.source_code.BugCategory;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "bug-category-report")
@JsonRootName("bugCategoryReport")
public class BugReport {

    @JacksonXmlProperty(localName = "report-name")
    @JsonProperty("report-name")
    private String name;

    @JacksonXmlProperty(localName = "time-generated")
    @JsonProperty("time-generated")
    private String date;

    @JacksonXmlElementWrapper(localName = "bug-categories")
    @JacksonXmlProperty(localName = "bug-category")
    @JsonProperty("bugCategories")
    ArrayList<BugCategory> bugCategories;

    public BugReport() {}

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

    public ArrayList<BugCategory> getBugCategories() {
        return bugCategories;
    }

    public void setBugCategories(ArrayList<BugCategory> bugCategories) {
        this.bugCategories = bugCategories;
    }
}
