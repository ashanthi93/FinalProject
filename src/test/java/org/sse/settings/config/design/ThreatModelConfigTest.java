package org.sse.settings.config.design;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class ThreatModelConfigTest {

    @Test
    public void testCreateConfigFile() throws Exception {

        ArrayList<String[]> threatDetails = new ArrayList<String[]>();

        threatDetails.add(new String[]{"S","Spoofing"});
        threatDetails.add(new String[]{"T","Tampering"});
        threatDetails.add(new String[]{"R","Repudiation"});
        threatDetails.add(new String[]{"I","Information Disclosure"});
        threatDetails.add(new String[]{"D","Denial Of Service"});
        threatDetails.add(new String[]{"E","Elevation Of Privilege"});

        ThreatModelConfig.createConfigFile(threatDetails);
    }

    @Test
    public void testLoadConfigFile() throws Exception{

        ArrayList<String[]> thrests = ThreatModelConfig.loadConfigFile();

        for (String[] threat : thrests){
            System.out.println("Id : " + threat[0] );
            System.out.println("name : " + threat[1] );
        }
    }

    public void testLoadThreatCategoryIdsAndNames() throws Exception{

        HashMap<String,String> idAndName = ThreatModelConfig.loadThreatCategoryIdsAndNames();

        for (String key : idAndName.keySet()){

            System.out.println(key + " - " + idAndName.get(key));
        }
    }
}