package association;

import org.ucsc.sse.outputgenerators.report.creator.AssociationReportCreator;
import org.ucsc.sse.outputgenerators.report.model.AssociationReport;
import org.ucsc.sse.outputgenerators.report.builder.ReportBuilder;
import org.ucsc.sse.outputgenerators.report.builder.concrete.XMLReportBuilder;
import org.ucsc.sse.datamodels.design.ThreatCategory;
import org.ucsc.sse.classifierbuilders.design.ThreatClassificationBuilder;
import org.ucsc.sse.datamodels.design.Threat;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import org.ucsc.sse.datamodels.source_code.BugCategory;
import org.ucsc.sse.classifierbuilders.source_code.BugClassificationBuilder;
import org.ucsc.sse.datamodels.source_code.Bug;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AssociationReportCreatorTest {

    @Test
    public void testGenerateReport() throws Exception {

        HashMap<BugCategory, String[]> bugCategoryHashMap = this.createBugCategoryObjects();
        HashMap<String, ThreatCategory> threatCategoryHashMap = this.createThreat();

        AssociationReportCreator associationReportCreator = new AssociationReportCreator(bugCategoryHashMap, threatCategoryHashMap);

        AssociationReport associationReport = associationReportCreator.generateReport("Test Report");

        ReportBuilder reportBuilder = new XMLReportBuilder();

        reportBuilder.convertReportToFile(associationReport, "D:\\association.xml");

    }

    @BeforeTest
    private HashMap<BugCategory, String[]> createBugCategoryObjects() throws ParserConfigurationException, SAXException, IOException {

        BugClassificationBuilder bugClassificationBuilder = new BugClassificationBuilder();
        HashMap<String, BugCategory> bugCategoryHashMap = bugClassificationBuilder.getBugCategories();

        ArrayList<Bug> bugArrayList = new ArrayList<Bug>();

        Bug bug = new Bug();
        bug.setId("B1");
        bug.setCategoryName("A1");
        bugArrayList.add(bug);

        Bug bug1 = new Bug();
        bug1.setId("B2");
        bug1.setCategoryName("A1");
        bugArrayList.add(bug1);

        BugCategory bugCategory = bugCategoryHashMap.get("A1");
        bugCategory.setBugArrayList(bugArrayList);

        bugCategoryHashMap.put("A1", bugCategory);

        bugArrayList = new ArrayList<Bug>();

        Bug bug2 = new Bug();
        bug2.setId("B3");
        bug2.setCategoryName("A2");

        bugArrayList.add(bug2);

        bugCategory = bugCategoryHashMap.get("A2");
        bugCategory.setBugArrayList(bugArrayList);

        bugCategoryHashMap.put("A2", bugCategory);

        ////////////////////////////////////////

        String[] A1 = {"S", "T"};
        String[] A2 = {"R"};
        String[] A3 = {"D"};
        String[] A = new String[0];

        HashMap<BugCategory, String[]> bugCategoryHashMap1 = new HashMap<BugCategory, String[]>();

        for (String id : bugCategoryHashMap.keySet()){

            BugCategory bugCategory1 = bugCategoryHashMap.get(id);

            if (id.equals("A1")){
                bugCategoryHashMap1.put(bugCategory1, A1);
            }else if (id.equals("A2")){
                bugCategoryHashMap1.put(bugCategory1, A2);
            }else if (id.equals("A3")){
                bugCategoryHashMap1.put(bugCategory1, A3);
            }else {
                bugCategoryHashMap1.put(bugCategory1, A);
            }
        }

        return bugCategoryHashMap1;
    }

    @BeforeTest
    private HashMap<String, ThreatCategory> createThreat() throws ParserConfigurationException, SAXException, IOException {

        ThreatClassificationBuilder threatClassificationBuilder = new ThreatClassificationBuilder();
        HashMap<String, ThreatCategory> threatCategoryHashMap = threatClassificationBuilder.getThreatCategories();

        //Spoo

        ArrayList<Threat> threatArrayList = new ArrayList<Threat>();

        Threat threat = new Threat();
        threat.setId("T1");
        threat.setThreatCategoryName("S");
        threatArrayList.add(threat);

        ThreatCategory threatCategory = threatCategoryHashMap.get("S");
        threatCategory.setThreatList(threatArrayList);

        threatCategoryHashMap.put("S", threatCategory);

        //Tam

        threatArrayList = new ArrayList<Threat>();

        threat = new Threat();
        threat.setId("T2");
        threat.setThreatCategoryName("T");
        threatArrayList.add(threat);

        threat = new Threat();
        threat.setId("T3");
        threat.setThreatCategoryName("T");
        threatArrayList.add(threat);

        threatCategory = threatCategoryHashMap.get("T");
        threatCategory.setThreatList(threatArrayList);

        threatCategoryHashMap.put("T", threatCategory);

        //Rep

        threatArrayList = new ArrayList<Threat>();

        threat = new Threat();
        threat.setId("T4");
        threat.setThreatCategoryName("R");
        threatArrayList.add(threat);

        threatCategory = threatCategoryHashMap.get("R");
        threatCategory.setThreatList(threatArrayList);

        threatCategoryHashMap.put("R", threatCategory);

        //Dos

        threatArrayList = new ArrayList<Threat>();

        threat = new Threat();
        threat.setId("T5");
        threat.setThreatCategoryName("D");
        threatArrayList.add(threat);

        threatCategory = threatCategoryHashMap.get("D");
        threatCategory.setThreatList(threatArrayList);

        threatCategoryHashMap.put("D", threatCategory);

        //ID

        threatArrayList = new ArrayList<Threat>();

        threatCategory = threatCategoryHashMap.get("I");
        threatCategory.setThreatList(threatArrayList);

        threatCategoryHashMap.put("I", threatCategory);

        //Eop

        threatArrayList = new ArrayList<Threat>();

        threatCategory = threatCategoryHashMap.get("E");
        threatCategory.setThreatList(threatArrayList);

        threatCategoryHashMap.put("E", threatCategory);

        return threatCategoryHashMap;
    }
}