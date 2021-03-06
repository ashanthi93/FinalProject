package org.sse.outputgenerators.report.creator;

import org.sse.outputgenerators.report.model.BugReport;
import org.sse.source.model.BugCategory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BugCategoryReportCreator {

    private HashMap<String, BugCategory> bugCategoryHashMap;

    public BugCategoryReportCreator(HashMap<String, BugCategory> bugCategoryHashMap) {
        this.bugCategoryHashMap = bugCategoryHashMap;
    }

    /**
     *
     * @return
     */
    public BugReport generateReport(String reportName){

        BugReport bugReport = new BugReport();

        bugReport.setName(reportName);
        bugReport.setDate(this.getDate());

        List<BugCategory> bugCategoryArrayList = new ArrayList<BugCategory>();

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
