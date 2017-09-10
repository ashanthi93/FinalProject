package org.ucsc.sse.dataextractors.classifiers;

import org.xml.sax.SAXException;
import org.ucsc.sse.knowedgemodel.settings.bugmodelconfigs.BugModelConfig;
import org.ucsc.sse.datamodels.source_code.BugCategory;
import org.ucsc.sse.datamodels.source_code.Bug;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BugClassifier {

    private ArrayList<Bug> bugArrayList;
    private HashMap<String, BugCategory> bugCategoryHashMap;

    public BugClassifier(ArrayList<Bug> bugArrayList, HashMap<String, BugCategory> bugCategoryHashMap) {
        this.bugArrayList = bugArrayList;
        this.bugCategoryHashMap = bugCategoryHashMap;
    }

    /* getters */
    public HashMap<String, BugCategory> getBugCategoryHashMap() {
        return bugCategoryHashMap;
    }

    public void classifyBugs() throws IOException, SAXException, ParserConfigurationException {

        HashMap<String, String> defaultBugCategoryIdsAndNames = this.loadBugCategoryIdsAndNamesByConfigFile();

        for (Bug bug : bugArrayList){

            String bugCategoryID = this.getDefaultBugCategoryIdForThreatCategoryName(defaultBugCategoryIdsAndNames, bug.getCategoryName());

            if (bugCategoryID != null){

                BugCategory bugCategory = bugCategoryHashMap.get(bugCategoryID);

                ArrayList<Bug> bugArrayList = bugCategory.getBugArrayList();
                bugArrayList.add(bug);

                bugCategory.setBugArrayList(bugArrayList);

                bugCategoryHashMap.put(bugCategoryID, bugCategory);

            }else{
                /*
                 should throw exception here
                  */
            }
        }

    }

    /**
     *
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private HashMap<String,String> loadBugCategoryIdsAndNamesByConfigFile() throws ParserConfigurationException, SAXException, IOException {

        BugModelConfig bugModelConfig = new BugModelConfig();
        return (bugModelConfig.loadBugCategoryIdsAndNames());
    }

    /**
     *
     * @param defaultBugCategoryIdsAndNames
     * @param bugCategoryName
     * @return
     */
    private String getDefaultBugCategoryIdForThreatCategoryName(HashMap<String,String> defaultBugCategoryIdsAndNames, String bugCategoryName){

        for (String defaultBugCategoryId : defaultBugCategoryIdsAndNames.keySet()){

            String defaultBugCategoryName = defaultBugCategoryIdsAndNames.get(defaultBugCategoryId);

            if (bugCategoryName.equals(defaultBugCategoryName)){
                return defaultBugCategoryId;
            }
        }
        return null;
    }
}
