package source.classification;

import source.model.Bug;

/**
 * Created by Ashi on 8/1/2017.
 */
public interface BugCategory {

    public int id = 0; //make public or static final?? (All the variables)
    static final Bug[] bugInstanceList = null;
    static final String countermeasures = "";

    public void getBugList();

    public void addBug(Bug bug);

}
