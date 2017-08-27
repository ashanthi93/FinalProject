package source.model;

import source.classification.BugCategory;

public class Bug {

    private String id;
    private String name;
    private String description;
    private BugCategory category;

    public Bug() {}

/*    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BugCategory getCategory() {
        return category;
    }

    public void setCategory(BugCategory category) {
        this.category = category;
    }*/
}
