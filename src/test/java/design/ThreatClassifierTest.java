package design;

import org.ucsc.sse.dataextractors.classifiers.ThreatClassifier;
import org.ucsc.sse.datamodels.design.ThreatCategory;
import org.ucsc.sse.classifierbuilders.design.ThreatClassificationBuilder;
import org.ucsc.sse.datamodels.design.Threat;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ThreatClassifierTest {

    ArrayList<Threat> threatArrayList = new ArrayList<Threat>();
    HashMap<String, ThreatCategory> manualThreatCategoryHashMap = new HashMap<String, ThreatCategory>();

    @BeforeTest
    private void createThreatArrayListAndHashMapManually() throws ParserConfigurationException, SAXException, IOException {

        ThreatClassificationBuilder model = new ThreatClassificationBuilder();
        manualThreatCategoryHashMap = model.getThreatCategories();

        ArrayList<Threat> S = new ArrayList<Threat>();
        ArrayList<Threat> T = new ArrayList<Threat>();
        ArrayList<Threat> R = new ArrayList<Threat>();
        ArrayList<Threat> I = new ArrayList<Threat>();
        ArrayList<Threat> D = new ArrayList<Threat>();
        ArrayList<Threat> E = new ArrayList<Threat>();

        Threat threat = this.createThreat("T1", "s", "Spoofing");
        threatArrayList.add(threat);
        S.add(threat);

        threat = this.createThreat("T2", "t", "Tampering");
        threatArrayList.add(threat);
        T.add(threat);

        threat = this.createThreat("T3", "r", "Repudiation");
        threatArrayList.add(threat);
        R.add(threat);

        threat = this.createThreat("T4", "s", "Spoofing");
        threatArrayList.add(threat);
        S.add(threat);

        threat = this.createThreat("T5", "i", "Information Disclosure");
        threatArrayList.add(threat);
        I.add(threat);

        threat = this.createThreat("T6", "d", "Denial of Service");
        threatArrayList.add(threat);
        D.add(threat);

        threat = this.createThreat("T7", "e", "Elevation of Privileges");
        threatArrayList.add(threat);
        E.add(threat);

        threat = this.createThreat("T8", "t", "Tampering");
        threatArrayList.add(threat);
        T.add(threat);

        threat = this.createThreat("T9", "s", "Spoofing");
        threatArrayList.add(threat);
        S.add(threat);

        threat = this.createThreat("T10", "r", "Repudiation");
        threatArrayList.add(threat);
        R.add(threat);

        ThreatCategory threatCategory = manualThreatCategoryHashMap.get("S");
        threatCategory.setThreatList(S);
        manualThreatCategoryHashMap.put("S", threatCategory);

        threatCategory = manualThreatCategoryHashMap.get("T");
        threatCategory.setThreatList(T);
        manualThreatCategoryHashMap.put("T", threatCategory);

        threatCategory = manualThreatCategoryHashMap.get("R");
        threatCategory.setThreatList(R);
        manualThreatCategoryHashMap.put("R", threatCategory);

        threatCategory = manualThreatCategoryHashMap.get("I");
        threatCategory.setThreatList(I);
        manualThreatCategoryHashMap.put("I", threatCategory);

        threatCategory = manualThreatCategoryHashMap.get("D");
        threatCategory.setThreatList(D);
        manualThreatCategoryHashMap.put("D", threatCategory);

        threatCategory = manualThreatCategoryHashMap.get("E");
        threatCategory.setThreatList(E);
        manualThreatCategoryHashMap.put("E", threatCategory);
    }

    @Test
    public void testClassifyThreats() throws Exception {

        ThreatClassificationBuilder model = new ThreatClassificationBuilder();

        ThreatClassifier threatClassifier = new ThreatClassifier(threatArrayList, model.getThreatCategories());
        threatClassifier.classifyThreats();

        HashMap<String, ThreatCategory> threatCategoryHashMap = threatClassifier.getThreatCategoryHashMap();

        //Assert.assertEquals(threatCategoryHashMap, manualThreatCategoryHashMap);

        for (String key : threatCategoryHashMap.keySet()){

            System.out.println("Key : " + key + " - ");

            for (Threat threat : threatCategoryHashMap.get(key).getThreatList()){
                System.out.println(threat.getName());
            }
        }
    }

    private Threat createThreat(String id, String name, String threatCategoryName){

        Threat threat = new Threat();
        threat.setId(id);
        threat.setName(name);
        threat.setThreatCategoryName(threatCategoryName);

        return threat;
    }
}