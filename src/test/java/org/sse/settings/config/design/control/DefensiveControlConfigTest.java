package org.sse.settings.config.design.control;

import org.testng.annotations.Test;

import java.util.ArrayList;

public class DefensiveControlConfigTest {

    @Test
    public void testCreateFile() throws Exception {

        ArrayList<String[]> controls = new ArrayList<String[]>();

        controls.add(new String[]{"D1", "Authentication", "Authentication Stores. Strong Authentication mechanisms."});
        controls.add(new String[]{"D2", "Integrity", "Crypto Hash. Digital watermark. Isolation and access checks."});
        controls.add(new String[]{"D3", "Non-repudiation", "Logging infrastructure. Full-packet-capture."});
        controls.add(new String[]{"D4", "Confidentiality", "Encryption or Isolation."});
        controls.add(new String[]{"D5", "Availability", "Redundancy. Failover. QoS. Bandwidth throttle."});
        controls.add(new String[]{"D6", "Authorization", "RBAC, DACL, MAC. Sudo, UAC. Privileged account protections."});

        DefensiveControlConfig.createFile(controls);
    }


}