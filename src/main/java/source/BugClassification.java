package source;

import org.xml.sax.SAXException;
import settings.owasp_configs.OWASPT10Config;
import source.classification.BugCategory;
import source.model.Bug;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

class BugClassification {

    private ArrayList<Bug> bugArrayList;
    private HashMap<String, BugCategory> bugCategoryHashMap;

    public BugClassification(ArrayList<Bug> bugArrayList, HashMap<String, BugCategory> bugCategoryHashMap) {
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

        OWASPT10Config owaspt10Config = new OWASPT10Config();
        return (owaspt10Config.loadBugCategoryIdsAndNames());
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
