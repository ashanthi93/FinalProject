package org.ucsc.sse.dataextractors.collectors;

import org.ucsc.sse.datamodels.source_code.Bug;
import org.ucsc.sse.datamodels.source_code.BugCollection;
import org.ucsc.sse.dataextractors.collectors.report_parsers.BugReportParser;

import java.io.File;
import java.util.ArrayList;

public class BugCollector {

    BugCollection bugCollection;
    ArrayList<Bug> bugArrayList;

    public BugCollector(){
        bugCollection = new BugCollection();
        bugArrayList = new ArrayList<Bug>();
    }

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

        BugReportParser bugReportParser = new BugReportParser();
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
