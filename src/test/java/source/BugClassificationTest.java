package source;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import source.classification.BugCategory;
import source.classification.BugClassificationModel;
import source.model.Bug;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import static org.testng.Assert.*;

public class BugClassificationTest {

    ArrayList<Bug> bugArrayList = new ArrayList<Bug>();
    HashMap<String, BugCategory> manualBugCategoryHashMap = new HashMap<String, BugCategory>();

    @BeforeTest
    public void createManualBugArraysAndCategory(){

        try {

            BugClassificationModel model = new BugClassificationModel();
            manualBugCategoryHashMap = model.getBugCategories();

            ArrayList<Bug> A1 = new ArrayList<Bug>();
            ArrayList<Bug> A2 = new ArrayList<Bug>();
            ArrayList<Bug> A3 = new ArrayList<Bug>();

            Bug bug = this.createBug("B1", "a1", "Injection");
            bugArrayList.add(bug);
            A1.add(bug);

            bug = this.createBug("B2", "a2", "Broken Authentication and Session Management");
            bugArrayList.add(bug);
            A2.add(bug);

            bug = this.createBug("B3", "a3", "Cross-Site Scripting (XSS)");
            bugArrayList.add(bug);
            A3.add(bug);

            bug = this.createBug("B4", "a1", "Injection");
            bugArrayList.add(bug);
            A1.add(bug);

            BugCategory bugCategory = manualBugCategoryHashMap.get("A1");
            bugCategory.setBugArrayList(A1);

            manualBugCategoryHashMap.put("A1", bugCategory);

            bugCategory = manualBugCategoryHashMap.get("A2");
            bugCategory.setBugArrayList(A2);

            manualBugCategoryHashMap.put("A2",bugCategory);

            bugCategory = manualBugCategoryHashMap.get("A3");
            bugCategory.setBugArrayList(A3);

            manualBugCategoryHashMap.put("A3", bugCategory);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClassifyBugs() throws Exception {

        BugClassificationModel model = new BugClassificationModel();
        BugClassification bugClassification = new BugClassification(bugArrayList, model.getBugCategories());

        bugClassification.classifyBugs();

        HashMap<String, BugCategory> bugCategoryHashMap = bugClassification.getBugCategoryHashMap();

        for (String key : bugCategoryHashMap.keySet()){

            System.out.println("Key : " + key + " - ");

            for (Bug bug : bugCategoryHashMap.get(key).getBugArrayList()){
                System.out.println(bug.getName());
            }
        }

    }

    private Bug createBug(String id, String name, String bugCategory){

        Bug bug = new Bug();
        bug.setId(id);
        bug.setName(name);
        bug.setCategoryName(bugCategory);

        return bug;
    }
}