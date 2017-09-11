package org.ucsc.sse.outputgenerators.report.creator;

import org.ucsc.sse.datamodels.association.Association;
import org.ucsc.sse.outputgenerators.report.model.AssociationReport;
import org.ucsc.sse.datamodels.design.ThreatCategory;
import org.xml.sax.SAXException;
import org.ucsc.sse.datamodels.source_code.BugCategory;
import org.ucsc.sse.datamodels.source_code.Bug;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class AssociationReportCreator {

    private HashMap<BugCategory, String[]> bugCategoryToThreatCategoryMapping;
    private HashMap<String, ThreatCategory> threatCategoryHashMap;

    public AssociationReportCreator(HashMap<BugCategory, String[]> bugCategoryToThreatCategoryMapping,
                                    HashMap<String, ThreatCategory> threatCategoryObjectHashMap) {

        this.bugCategoryToThreatCategoryMapping = bugCategoryToThreatCategoryMapping;
        this.threatCategoryHashMap = threatCategoryObjectHashMap;
    }

    /**
     * Returns AssociationReport object that can be serializable.
     *
     * @return the AssociationReport
     */
    public AssociationReport generateReport(String reportName) throws IOException, SAXException, ParserConfigurationException {

        AssociationReport associationReport = new AssociationReport();

        associationReport.setName(reportName);
        associationReport.setDate(this.getDate());

        HashMap<String, Association> associationHashMap = this.createAssociationsForEachThreatCategoryObject();

        HashMap<String, ArrayList<Bug>> bugArrayListHashMap = this.createBugArrayListForEachThreatCategory();

        for (BugCategory bugCategory : bugCategoryToThreatCategoryMapping.keySet()){

            String[] threatCategoryIDs = bugCategoryToThreatCategoryMapping.get(bugCategory);

            for (String threatCategoryID : threatCategoryIDs){

                ArrayList<Bug> updatedBugArrayList = this.setBugArrayListForThreatCategory(
                        bugArrayListHashMap.get(threatCategoryID), bugCategory);

                bugArrayListHashMap.put(threatCategoryID, updatedBugArrayList);
            }
        }

        ArrayList<Association> associationArrayList = new ArrayList<Association>();

        for (String threatCategoryID : associationHashMap.keySet()){

            Association association = associationHashMap.get(threatCategoryID);
            association.setBugArrayList(bugArrayListHashMap.get(threatCategoryID));

            associationArrayList.add(association);
        }

        associationReport.setAssociationArrayList(associationArrayList);

        return associationReport;
    }

    /**
     *
     *
     * @return
     */
    private HashMap<String, Association> createAssociationsForEachThreatCategoryObject(){

        HashMap<String, Association> associationHashMap = new HashMap<String, Association>();

        for (String threatCategoryID : threatCategoryHashMap.keySet()){

            Association association = new Association();

            ThreatCategory threatCategory = threatCategoryHashMap.get(threatCategoryID);

            association.setThreatCategoryName(threatCategory.getName());
            association.setThreatArrayList(threatCategory.getThreatList());

            associationHashMap.put(threatCategoryID, association);
        }

        return associationHashMap;
    }

    /**
     * Return HashMap containing bugArrayList for each threat category.
     * Key value will be represent according to threat categorization.
     * Relevant value for the key will be an array list of bugs
     *
     * @return HashMap<String, ArrayList<Bug>>
     */
    private HashMap<String, ArrayList<Bug>> createBugArrayListForEachThreatCategory() throws ParserConfigurationException, SAXException, IOException {

        HashMap<String, ArrayList<Bug>> bugArrayListForEachThreatCategory = new HashMap<String, ArrayList<Bug>>();

        for (String threatCategoryID : threatCategoryHashMap.keySet()){

            ArrayList<Bug> bugArrayList = new ArrayList<Bug>();
            bugArrayListForEachThreatCategory.put(threatCategoryID, bugArrayList);
        }

        return bugArrayListForEachThreatCategory;
    }

    /**
     *
     * @param currentBugArrayList
     * @param bugCategory
     * @return
     */
    private ArrayList<Bug> setBugArrayListForThreatCategory(ArrayList<Bug> currentBugArrayList, BugCategory bugCategory){

        for (Bug bug : bugCategory.getBugArrayList()){

            if (!currentBugArrayList.contains(bug)){
                currentBugArrayList.add(bug);
            }
        }

        return currentBugArrayList;
    }

    /**
     *
     *
     * @return
     */
    private String getDate(){

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date = new Date();

        return (dateFormat.format(date));
    }
}
