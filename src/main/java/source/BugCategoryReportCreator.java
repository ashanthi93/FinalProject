package source;

import reports.BugReport;
import source.classification.BugCategory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BugCategoryReportCreator {

    private HashMap<String, BugCategory> bugCategoryHashMap;

    public BugCategoryReportCreator(HashMap<String, BugCategory> bugCategoryHashMap) {
        this.bugCategoryHashMap = bugCategoryHashMap;
    }

    /**
     * ******** Countermeasures are already loaded to the bugcategory object???**********
     *
     * @return
     */
    public BugReport generateReport(String reportName){

        BugReport bugReport = new BugReport();

        bugReport.setName(reportName);
        bugReport.setDate(this.getDate());

        ArrayList<BugCategory> bugCategoryArrayList = new ArrayList<BugCategory>();

        for (String bugCategoryID : bugCategoryHashMap.keySet()){

            BugCategory bugCategory = bugCategoryHashMap.get(bugCategoryID);
            bugCategoryArrayList.add(bugCategory);
        }

        bugReport.setBugCategories(bugCategoryArrayList);

        return bugReport;
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
