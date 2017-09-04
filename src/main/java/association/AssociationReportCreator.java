package association;

import association.model.Association;
import association.report.AssociationReport;
import design.classification.ThreatCategory;
import source.classification.BugCategory;
import source.model.Bug;

import java.util.ArrayList;
import java.util.HashMap;

public class AssociationReportCreator {

    private HashMap<BugCategory, String[]> OWASP_STRIDE_mapping;
    private HashMap<String, ThreatCategory> threatCategoryHashMap;

    public AssociationReportCreator(HashMap<BugCategory, String[]> OWASP_STRIDE_mapping, HashMap<String, ThreatCategory> threatCategoryHashMap) {
        this.OWASP_STRIDE_mapping = OWASP_STRIDE_mapping;
        this.threatCategoryHashMap = threatCategoryHashMap;
    }

    /**
     * Returns AssociationReport object that can be serializable.
     *
     * @return the AssociationReport
     */
    public AssociationReport generateReport() {
        AssociationReport associationReport = new AssociationReport();

        //create HashMap for threatCategories
        HashMap<String, ArrayList<Bug>> bugsForThreatCategory = this.createBugsForThreatCategoryMap();

        /* separate each bugList to relevant threat category  */
        for (BugCategory bugCategory : OWASP_STRIDE_mapping.keySet()) {

            String threatCategoryIDList[] = OWASP_STRIDE_mapping.get(bugCategory);

            for (String threatCategoryID : threatCategoryIDList) {

                ArrayList<Bug> bugArrayList = bugsForThreatCategory.get(threatCategoryID);
                bugArrayList.addAll(bugCategory.getBugList());
            }
        }
        //create associations for each threat category
        associationReport.setAssociationArrayList(this.createAssociations(bugsForThreatCategory));

        return associationReport;
    }

    /**
     * Return HashMap containing bugArrayList for each threat category.
     * Key value will be represent according to threat categorization.
     * Relevant value for the key will be an array list of bugs
     *
     * @return HashMap<String, ArrayList<Bug>>
     */
    private HashMap<String, ArrayList<Bug>> createBugsForThreatCategoryMap() {

        HashMap<String, ArrayList<Bug>> bugsForThreatCategory = new HashMap<String, ArrayList<Bug>>();

        ArrayList<Bug> bugArrayList = new ArrayList<Bug>();

        bugsForThreatCategory.put("S", bugArrayList);
        bugsForThreatCategory.put("T", bugArrayList);
        bugsForThreatCategory.put("R", bugArrayList);
        bugsForThreatCategory.put("I", bugArrayList);
        bugsForThreatCategory.put("D", bugArrayList);
        bugsForThreatCategory.put("E", bugArrayList);

        return bugsForThreatCategory;
    }

    /**
     * Return ArrayList containing Association objects
     *
     * @param bugsForThreatCategory hashMap containing bugs for each threat category
     * @return ArrayList<Association>
     */
    private ArrayList<Association> createAssociations(HashMap<String, ArrayList<Bug>> bugsForThreatCategory) {

        ArrayList<Association> associationArrayList = new ArrayList<Association>();

        for (String threatCategoryID : threatCategoryHashMap.keySet()) {

            Association association = new Association();

            association.setThreatCategoryName(threatCategoryID);
            association.setthreatArrayList(threatCategoryHashMap.get(threatCategoryID).getThreatList());
            association.setbugArrayList(bugsForThreatCategory.get(threatCategoryID));

            associationArrayList.add(association);
        }

        return associationArrayList;
    }
}
