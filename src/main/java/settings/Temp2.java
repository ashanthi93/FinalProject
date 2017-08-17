package settings;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Temp2 {

    public static void main(String args[]) {

        Map<String, String[]> mappingHashMap = new HashMap<String, String[]>();

        String[] array = {"C2", "C3", "C4", "C8", "C9", "C10"};
        mappingHashMap.put("A1", array);

        array = new String[]{"C5", "C8", "C9", "C10"};
        mappingHashMap.put("A2", array);

        array = new String[]{"C3", "C4", "C8", "C9", "C10"};
        mappingHashMap.put("A3", array);

        array = new String[]{"C6", "C8", "C9", "C10"};
        mappingHashMap.put("A4", array);

        array = new String[]{"C8", "C9", "C10"};
        mappingHashMap.put("A5", array);

        array = new String[]{"C7", "C8", "C9", "C10"};
        mappingHashMap.put("A6", array);

        array = new String[]{"C6", "C8", "C9", "C10"};
        mappingHashMap.put("A7", array);

        array = new String[]{"C8", "C9", "C10"};
        mappingHashMap.put("A8", array);

        array = new String[]{"C8", "C9", "C10"};
        mappingHashMap.put("A9", array);

        array = new String[]{"C4", "C8", "C9", "C10"};
        mappingHashMap.put("A10", array);

        //OWASPMappingConfig owaspMappingConfig = new OWASPMappingConfig();
        //owaspMappingConfig.createConfigFile(mappingHashMap);

        ArrayList<String[]> strideThreats = new ArrayList<String[]>();

        String[] threat = {"S", "Spoofing", "Authentication", "Authentication Stores, Strong Authentication mechanisms"};
        strideThreats.add(threat);

        threat = new String[]{"T", "Tampering", "Integrity", "Crypto Hash, Digital watermark/isolation and access checks"};
        strideThreats.add(threat);

        threat = new String[]{"R", "Repudiation", "Non-repudiation", "Logging infrastructure, full-packet-capture"};
        strideThreats.add(threat);

        threat = new String[]{"I", "Information Disclosure", "Confidentiality", "Encryption/Isolation"};
        strideThreats.add(threat);

        threat = new String[]{"D", "Denial of Service", "Availability", "Redundancy, Failover, QoS, Bandwidth throttle"};
        strideThreats.add(threat);

        threat = new String[]{"E", "Elevation of Privilege", "Authorization", "RBAC, DACL, MAC; Sudo, UAC, Privileged account protections"};
        strideThreats.add(threat);

        //STRIDEAttackerConfig strideAttackerConfig = new STRIDEAttackerConfig();

        strideThreats = new ArrayList<String[]>();

        threat = new String[]{"D1", "Authentication", "---------------"};
        strideThreats.add(threat);

        threat = new String[]{"D2", "Authorization", "============"};
        strideThreats.add(threat);

        threat = new String[]{"D3", "Configuration Management", "-------------------"};
        strideThreats.add(threat);

        threat = new String[]{"D4", "Data Protection in Storage and Transit", "-------Encryption/Isolation"};
        strideThreats.add(threat);

        threat = new String[]{"D5", "Data validation/ Parameter validation", "=========Availability"};
        strideThreats.add(threat);

        threat = new String[]{"D6", "Error Handling and Exception Management", "------Authorization"};
        strideThreats.add(threat);

        threat = new String[]{"D7", "User and Session Management", "------Authorization"};
        strideThreats.add(threat);

        threat = new String[]{"D8", "Auditing and Logging", "------Authorization"};
        strideThreats.add(threat);

        STRIDEDefensiveConfig strideDefensiveConfig = new STRIDEDefensiveConfig();
        try {
            strideDefensiveConfig.createFile(strideThreats);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
