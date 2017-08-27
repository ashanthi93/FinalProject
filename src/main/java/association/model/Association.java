package association.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import design.model.Threat;
import source.model.Bug;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "association")
@JsonRootName("association")
public class Association{

    @JacksonXmlProperty(localName = "threat-category-name")
    @JsonProperty("threatCategoryName")
    private String threatCategoryName;

    @JacksonXmlProperty(localName = "threats")
    @JsonProperty("threats")
    private ArrayList<Threat> threatArrayList;

    @JacksonXmlProperty(localName = "bugs")
    @JsonProperty("bugs")
    private ArrayList<Bug> bugArrayList;

    public Association() {}

    /* getter and setters */
    public String getThreatCategoryName() {
        return threatCategoryName;
    }

    public void setThreatCategoryName(String threatCategoryName) {
        this.threatCategoryName = threatCategoryName;
    }

    public ArrayList<Threat> getthreatArrayList() {
        return threatArrayList;
    }

    public void setthreatArrayList(ArrayList<Threat> threatArrayList) {
        this.threatArrayList = threatArrayList;
    }

    public ArrayList<Bug> getbugArrayList() {
        return bugArrayList;
    }

    public void setbugArrayList(ArrayList<Bug> bugArrayList) {
        this.bugArrayList = bugArrayList;
    }
}
