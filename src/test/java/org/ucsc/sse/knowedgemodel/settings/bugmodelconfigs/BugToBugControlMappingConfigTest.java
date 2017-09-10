package org.ucsc.sse.knowedgemodel.settings.bugmodelconfigs;

import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.*;

public class BugToBugControlMappingConfigTest {

    private HashMap<String, String[]> createMapping(){

        HashMap<String, String[]> hashMap = new HashMap<String, String[]>();

        String[] newMap = {"C1","C2","C3","C4","C8","C9","C10"};
        hashMap.put("A1", newMap);

        newMap = new  String[]{"C1","C4","C8","C9","C10"};
        hashMap.put("A2", newMap);

        newMap = new  String[]{"C1","C5","C8","C9","C10"};
        hashMap.put("A3", newMap);

        newMap = new  String[]{"C1","C6","C8","C9","C10"};
        hashMap.put("A4", newMap);

        newMap = new  String[]{"C1","C8","C9","C10"};
        hashMap.put("A5", newMap);

        newMap = new  String[]{"C1","C7","C8","C9","C10"};
        hashMap.put("A6", newMap);

        newMap = new  String[]{"C1","C6","C8","C9","C10"};
        hashMap.put("A7", newMap);

        newMap = new  String[]{"C1","C8","C9","C10"};
        hashMap.put("A8", newMap);

        newMap = new  String[]{"C1","C8","C9","C10"};
        hashMap.put("A9", newMap);

        newMap = new  String[]{"C1","C8","C9","C10"};
        hashMap.put("A10", newMap);

        return hashMap;
    }

    @Test
    public void testCreateConfigFile() throws Exception {

        BugToBugControlMappingConfig mappingConfig = new BugToBugControlMappingConfig();
        mappingConfig.createConfigFile(this.createMapping());
    }

    @Test
    public void testLoadConfigFile() throws Exception {

    }

}