package association.report;

import association.model.Association;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "association-report")
@JsonRootName("associationReport")
public class AssociationReport {

    @JacksonXmlElementWrapper(localName = "associations")
    @JacksonXmlProperty(localName = "association")
    @JsonProperty("associations")
    private ArrayList<Association> associationArrayList;

    public AssociationReport() {
    }

    /* getters & setters */
    public ArrayList<Association> getAssociationArrayList() {
        return associationArrayList;
    }

    public void setAssociationArrayList(ArrayList<Association> associations) {
        this.associationArrayList = associations;
    }
}
