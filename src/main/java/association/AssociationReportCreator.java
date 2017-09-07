package association;

import association.model.Association;
import association.report.AssociationReport;
import design.classification.ThreatCategory;
import design.classification.ThreatClassificationModel;
import org.xml.sax.SAXException;
import source.classification.BugCategory;
import source.model.Bug;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

class AssociationReportCreator {

    private HashMap<BugCategory, String[]> bugCategoryToThreatCategoryMapping;
    private HashMap<String, ThreatCategory> threatCategoryHashMap;

    public AssociationReportCreator(HashMap<BugCategory, String[]> bugCategoryToThreatCategoryMapping,
                                    HashMap<String, ThreatCategory> threatCategoryHashMap) {

        this.bugCategoryToThreatCategoryMapping = bugCategoryToThreatCategoryMapping;
        this.threatCategoryHashMap = threatCategoryHashMap;
    }

    /**
     * Returns AssociationReport object that can be serializable.
     *
     * @return the AssociationReport
     */
    public AssociationReport generateReport() throws IOException, SAXException, ParserConfigurationException {

        AssociationReport associationReport = new AssociationReport();

        HashMap<String, ArrayList<Bug>> bugArrayListForEachThreatCategory = this.createBugArrayListForEachThreatCategory();

        /* separate each bugList to relevant threat category  */
        for (BugCategory bugCategory : bugCategoryToThreatCategoryMapping.keySet()) {

            String threatCategoryIDList[] = bugCategoryToThreatCategoryMapping.get(bugCategory);

            for (String threatCategoryID : threatCategoryIDList) {

                ArrayList<Bug> bugArrayList = bugArrayListForEachThreatCategory.get(threatCategoryID);
                bugArrayList.addAll(bugCategory.getBugArrayList());
            }
        }
        //create associations for each threat category
        associationReport.setAssociationArrayList(this.createAssociations(bugArrayListForEachThreatCategory));

        return associationReport;
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

        ThreatClassificationModel threatClassificationModel = new ThreatClassificationModel();
        HashMap<String, ThreatCategory> threatCategoryHashMap = threatClassificationModel.getThreatCategories();

        for (String threatCategoryID : threatCategoryHashMap.keySet()){

            ArrayList<Bug> bugArrayList = new ArrayList<Bug>();
            bugArrayListForEachThreatCategory.put(threatCategoryID, bugArrayList);
        }

        return bugArrayListForEachThreatCategory;
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
            association.setThreatArrayList(threatCategoryHashMap.get(threatCategoryID).getThreatList());
            association.setBugArrayList(bugsForThreatCategory.get(threatCategoryID));

            associationArrayList.add(association);
        }
        return associationArrayList;
    }
}
