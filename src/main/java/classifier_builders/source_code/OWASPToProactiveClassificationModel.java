package classifier_builders.source_code;

import org.xml.sax.SAXException;
import knowedge_model.settings.bugmodel_configs.OWASPMappingConfig;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class OWASPToProactiveClassificationModel {

    HashMap<Integer, BugToBugControlMapper> owaspProactiveMapping;

    public OWASPToProactiveClassificationModel(){
        owaspProactiveMapping = new HashMap<Integer, BugToBugControlMapper>();
    }

    public void createMapping() throws ParserConfigurationException, SAXException, IOException {

        OWASPMappingConfig readConfig = new OWASPMappingConfig();

        Map<String, String[]> OWASP_proactives_mapping;

        OWASP_proactives_mapping = new TreeMap<String, String[]>(readConfig.loadConfigFile());

        for (String key : OWASP_proactives_mapping.keySet()){

            Boolean c1=false,c2=false,c3=false,c4=false,c5=false,c6=false,c7=false,c8=false,c9=false,c10=false;
            for (String proactive : OWASP_proactives_mapping.get(key)){
                int id = Integer.parseInt(proactive.substring(1));
                switch (id){
                    case (1): c1 = true; break;
                    case (2): c2 = true; break;
                    case (3): c3 = true; break;
                    case (4): c4 = true; break;
                    case (5): c5 = true; break;
                    case (6): c6 = true; break;
                    case (7): c7 = true; break;
                    case (8): c8 = true; break;
                    case (9): c9 = true; break;
                    case (10): c10 = true; break;
                }

            }

            BugToBugControlMapper mappingProactive = this.createOWASPToProactiveMapping(key, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10);
            int keyId = Integer.parseInt(key.substring(1));

            owaspProactiveMapping.put(keyId, mappingProactive);
        }

    }

    public HashMap<Integer, BugToBugControlMapper> getMapping() throws IOException, SAXException, ParserConfigurationException {
        if (owaspProactiveMapping.isEmpty()){
            this.createMapping();
        }
        return owaspProactiveMapping;
    }

    private BugToBugControlMapper createOWASPToProactiveMapping(String bugType, Boolean c1, Boolean c2, Boolean c3, Boolean c4, Boolean c5, Boolean c6, Boolean c7, Boolean c8, Boolean c9, Boolean c10){

        BugToBugControlMapper mapping = new BugToBugControlMapper();

        mapping.setBugType(bugType);

        mapping.setC1(c1);
        mapping.setC2(c2);
        mapping.setC3(c3);
        mapping.setC4(c4);
        mapping.setC5(c5);
        mapping.setC6(c6);
        mapping.setC7(c7);
        mapping.setC8(c8);
        mapping.setC9(c9);
        mapping.setC10(c10);

        return mapping;
    }
}
