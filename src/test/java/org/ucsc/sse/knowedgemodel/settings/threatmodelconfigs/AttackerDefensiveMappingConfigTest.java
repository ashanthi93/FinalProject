package org.ucsc.sse.knowedgemodel.settings.threatmodelconfigs;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.Assert.*;

public class AttackerDefensiveMappingConfigTest {

    @BeforeTest
    private HashMap<String, String[]> defenisveMapping(){

        HashMap<String, String[]> defensMap = new HashMap<String, String[]>();

        String[] defenses = {"D2"};
        defensMap.put("R",defenses);

        defenses = new String[]{"D1"};
        defensMap.put("S", defenses);

        defenses = new String[]{"D3","D5"};
        defensMap.put("T", defenses);

        defenses = new String[]{"D1", "D8"};
        defensMap.put("I", defenses);

        defenses = new String[]{"D3", "D4"};
        defensMap.put("D", defenses);

        defenses = new String[]{"D6","D5"};
        defensMap.put("E", defenses);

        return defensMap;
    }

    @Test
    public void testCreateFile() throws Exception {

        AttackerDefensiveMappingConfig mappingConfig = new AttackerDefensiveMappingConfig();
        mappingConfig.createFile(this.defenisveMapping());
    }

    @Test
    public void testLoadConfigFile() throws Exception {

    }

}