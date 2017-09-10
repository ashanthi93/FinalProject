package org.ucsc.sse.knowedgemodel.settings.threatmodelconfigs;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class AttackerPerspectiveConfigTest {

    @BeforeTest
    private ArrayList<String[]> createThreats(){

        ArrayList<String[]> threats = new ArrayList<String[]>();

        String[] threat = {"S","Spoofing","Authentication"};
        threats.add(threat);

        threat = new String[]{"T", "Tampering", "Integrity"};
        threats.add(threat);

        threat = new String[]{"R","Repudiation", "Non-repudiation"};
        threats.add(threat);

        threat = new String[]{"I", "Information Disclosure", "Integrity"};
        threats.add(threat);

        threat = new String[]{"D", "Denial of Service", "Availability"};
        threats.add(threat);

        threat = new String[]{"E", "Elevation of Privileges", "Authorization"};
        threats.add(threat);

        return threats;
    }

    @Test
    public void testCreateConfigFile() throws Exception {

        AttackerPerspectiveConfig attackerPerspectiveConfig = new AttackerPerspectiveConfig();
        attackerPerspectiveConfig.createConfigFile(this.createThreats(), "STRIDE");
    }

    @Test
    public void testLoadConfigFile() throws Exception {

    }

    @Test
    public void testLoadThreatControls() throws Exception {

    }

    @Test
    public void testLoadThreatCategoryIdsAndNames() throws Exception {

    }

}