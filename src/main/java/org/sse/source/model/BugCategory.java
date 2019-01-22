package org.sse.source.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.sse.source.model.Bug;

import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "bug-category")
@JsonRootName("bug-category")
public class BugCategory {

    @JacksonXmlProperty(localName = "id")
    @JsonProperty("id")
    private String id;

    @JacksonXmlProperty(localName = "name")
    @JsonProperty("name")
    private String name;

    @JsonIgnore
    private String description;

    @JacksonXmlElementWrapper(localName = "bugs")
    @JacksonXmlProperty(localName = "bug")
    @JsonProperty("bugs")
    private List<Bug> bugArrayList;

    @JacksonXmlElementWrapper(localName = "countermeasures")
    @JacksonXmlProperty(localName = "countermeasure")
    @JsonProperty("countermeasures")
    private List<String> countermeasures;

    public BugCategory(){
        bugArrayList = new ArrayList<Bug>();
        countermeasures = new ArrayList<String>();
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

    public List<Bug> getBugList() {
        return bugArrayList;
    }

    public void setBugList(List<Bug> bugList) {
        this.bugArrayList = bugList;
    }

    public List<String> getCountermeasures() {
        return countermeasures;
    }

    public void setCountermeasures(List<String> countermeasures) {
        this.countermeasures = countermeasures;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}