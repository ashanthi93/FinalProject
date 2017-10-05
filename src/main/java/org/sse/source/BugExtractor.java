package org.sse.source;

import org.sse.categories.source.model.BugCategory;
import org.sse.reportparser.source.BugReportParser;
import org.sse.source.model.Bug;
import org.sse.source.model.BugCollection;
import org.xml.sax.SAXException;
import org.sse.categories.source.BugCategoriesLoader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BugExtractor {

    private static BugExtractor instance;

    private BugReportParser bugReportParser;
    private BugCollection bugCollection;

    static {
        try {
            instance = new BugExtractor();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating threat extractor ! ");
        }
    }

    /* getters */
    public static BugExtractor getInstance(){
        return instance;
    }

    public BugReportParser getBugReportParser() {
        return bugReportParser;
    }

    public BugCollection getBugCollection() {
        return bugCollection;
    }

    /**
     *
     *
     * @param bugReportFile
     */
    public void readFile(File bugReportFile){

    }

    /**
     *
     *
     * @return
     */
    public List<Bug> getAllBugs(){
        return (BugModelUtil.getAllBugs(getBugCollection()));
    }

    /**
     *
     *
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public void classifyBugs() throws IOException, SAXException, ParserConfigurationException {

        List<Bug> bugList = getAllBugs();
        HashMap<String, BugCategory> bugCategoryHashMap = BugCategoriesLoader.getBugCategoryHashMap();

        for (Bug bug : bugList){

            boolean isValidBugCategoryName = false;

            for (String defaultBugCategoryId : bugCategoryHashMap.keySet()){

                BugCategory bugCategory = bugCategoryHashMap.get(defaultBugCategoryId);
                String defaultBugCategoryName = bugCategory.getName();

                if (defaultBugCategoryName.equals(bug.getName())){

                    ArrayList<Bug> bugArrayList = bugCategory.getBugArrayList();
                    bugArrayList.add(bug);
                    bugCategory.setBugArrayList(bugArrayList);

                    bugCategoryHashMap.put(defaultBugCategoryId, bugCategory);

                    isValidBugCategoryName = true;
                }
            }

            if (!isValidBugCategoryName){
                throw new RuntimeException("Invalid Bug Category Name ! ");
            }
        }
    }

    /**
     *
     */
    public void generateBugReport() {

    }
}
