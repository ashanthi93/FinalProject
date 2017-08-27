package design.model;

import design.classification.ThreatCategory;

public class Threat {

    private String id;
    private String name;
    private ThreatCategory category;
    private String description;
    private String element;
    private String priority;
    private String interactionId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
