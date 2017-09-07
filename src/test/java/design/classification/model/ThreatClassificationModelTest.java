package design.classification.model;

import design.classification.ThreatCategory;
import design.classification.ThreatClassificationModel;
import design.model.Threat;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public class ThreatClassificationModelTest {

    ThreatClassificationModel threatClassificationModel  = new ThreatClassificationModel();
    HashMap<String, ThreatCategory> manualThreatCategoryHashMap = new HashMap<String, ThreatCategory>();

    @BeforeTest
    private void createThreatCategoryHashMapManually(){

        ThreatCategory threatCategory = this.createThreatCategory("S", "Spoofing");
        manualThreatCategoryHashMap.put("S",threatCategory);

        threatCategory = this.createThreatCategory("T", "Tampering");
        manualThreatCategoryHashMap.put("T", threatCategory);

        threatCategory = this.createThreatCategory("R", "Repudiation");
        manualThreatCategoryHashMap.put("R", threatCategory);

        threatCategory = this.createThreatCategory("I", "Information Disclosure");
        manualThreatCategoryHashMap.put("I", threatCategory);

        threatCategory = this.createThreatCategory("D", "Denial of Service");
        manualThreatCategoryHashMap.put("D", threatCategory);

        threatCategory = this.createThreatCategory("E", "Elevation of Privilege");
        manualThreatCategoryHashMap.put("E", threatCategory);
    }

    @Test
    private void testCreateThreatCategories(){

        try {
            HashMap<String, ThreatCategory> threatCategoryHashMap = threatClassificationModel.getThreatCategories();

            for (String key : threatCategoryHashMap.keySet()){
                Assert.assertEquals(threatCategoryHashMap.get(key).getName(), manualThreatCategoryHashMap.get(key).getName());
            }

            Assert.assertEquals(threatCategoryHashMap.size(), manualThreatCategoryHashMap.size());

            //Assert.assertEquals(threatCategoryHashMap, manualThreatCategoryHashMap);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ThreatCategory createThreatCategory(String id, String name){

        ThreatCategory threatCategory = new ThreatCategory();
        threatCategory.setId(id);
        threatCategory.setName(name);

        return threatCategory;
    }
}