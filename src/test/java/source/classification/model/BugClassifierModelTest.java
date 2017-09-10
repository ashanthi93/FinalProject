package source.classification.model;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import org.ucsc.sse.datamodels.source_code.BugCategory;
import org.ucsc.sse.classifierbuilders.source_code.BugClassificationBuilder;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public class BugClassifierModelTest {

    BugClassificationBuilder bugClassificationBuilder = new BugClassificationBuilder();
    HashMap<String, BugCategory> manualBugCategoryHashMap = new HashMap<String, BugCategory>();

    @BeforeTest
    public void createBugCategoryHashMapManually() {

        BugCategory bugCategory = this.createBugCategory("A1", "Injection");
        manualBugCategoryHashMap.put("A1", bugCategory);

        bugCategory = this.createBugCategory("A2", "Broken Authentication and Session Management");
        manualBugCategoryHashMap.put("A2", bugCategory);

        bugCategory = this.createBugCategory("A3", "Cross-Site Scripting (XSS)");
        manualBugCategoryHashMap.put("A3", bugCategory);

        bugCategory = this.createBugCategory("A4", "Insecure Direct Object References");
        manualBugCategoryHashMap.put("A4", bugCategory);

        bugCategory = this.createBugCategory("A5", "Security Misconfiguration");
        manualBugCategoryHashMap.put("A5", bugCategory);

        bugCategory = this.createBugCategory("A6", "Sensitive Data Exposure");
        manualBugCategoryHashMap.put("A6", bugCategory);

        bugCategory = this.createBugCategory("A7", "Missing Function Level Access Control");
        manualBugCategoryHashMap.put("A7", bugCategory);

        bugCategory = this.createBugCategory("A8", "Cross-Site Request Forgery (CSRF)");
        manualBugCategoryHashMap.put("A8", bugCategory);

        bugCategory = this.createBugCategory("A9", "Using Components with Known Vulnerabilities");
        manualBugCategoryHashMap.put("A9", bugCategory);

        bugCategory = this.createBugCategory("A10", "Unvalidated Redirects and Forwards");
        manualBugCategoryHashMap.put("A10", bugCategory);
    }

    @Test
    public void testCreateBugCategories() {

        try {
            bugClassificationBuilder.createBugCategories();

            HashMap<String, BugCategory> bugCategoryHashMap = bugClassificationBuilder.getBugCategories();

            for (String key : bugCategoryHashMap.keySet()){

                Assert.assertEquals(bugCategoryHashMap.get(key).getName(), manualBugCategoryHashMap.get(key).getName());
            }

            Assert.assertEquals(bugCategoryHashMap.size(), manualBugCategoryHashMap.size());

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BugCategory createBugCategory(String id, String name) {

        BugCategory bugCategory = new BugCategory();
        bugCategory.setId(id);
        bugCategory.setName(name);

        return bugCategory;
    }
}