package source;

import source.model.Bug;
import source.model.BugCollection;

import java.io.File;
import java.util.ArrayList;

class BugCollector {

    BugCollection bugCollection;
    ArrayList<Bug> bugArrayList;

    public BugCollector(){}

    /* getters */
    public BugCollection getBugCollection() {
        return bugCollection;
    }

    public ArrayList<Bug> getBugArrayList() {
        return bugArrayList;
    }

    /**
     *
     *
     * @param xmlFile
     */
    public void readFile(File xmlFile) {

        ReportParser reportParser = new ReportParser();
    }

    /**
     *
     * @param bugCollectionId
     * @param bugCollectionName
     */
    private void createBugCollection(String bugCollectionId, String bugCollectionName){

        bugCollection = new BugCollection();

        bugCollection.setId(bugCollectionId);
        bugCollection.setName(bugCollectionName);
    }

    /**
     *
     * @param bugId
     * @param bugName
     */
    private void createBug(String bugId, String bugName){

        Bug bug = new Bug();
        bug.setId(bugId);
        bug.setName(bugName);

        bugArrayList.add(bug);
    }

    /**
     *
     */
    private void setBugArrayListToBugCollection(){

        bugCollection.setBugArrayList(bugArrayList);
    }
}
