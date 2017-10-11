package org.sse.categories.source.model;

import java.util.ArrayList;
import java.util.List;

public class BugControl {

    private String id;
    private String name;
    private List<String> description;

    public BugControl(){
        description = new ArrayList<String>();
    }

    /* getters and setters */
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

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

}
