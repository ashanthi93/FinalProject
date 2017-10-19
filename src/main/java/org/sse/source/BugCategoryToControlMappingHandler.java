package org.sse.source;

import org.dom4j.DocumentException;
import org.sse.settings.config.source.control.BugControlConfig;
import org.sse.settings.config.source.mapping.MappingConfig;

import java.util.*;

public class BugCategoryToControlMappingHandler {

    HashMap<Integer, BugCategoryToControlMapping> owaspProactiveMapping;

    public BugCategoryToControlMappingHandler(){
        owaspProactiveMapping = new HashMap<Integer, BugCategoryToControlMapping>();
    }

    public void createMapping() throws DocumentException {

        HashMap<String, ArrayList<String>> controlsMapping = new HashMap<String, ArrayList<String>>();

        /* Initialize the controlsMapping list */
        HashMap<String,String> controlIdsAndNamesMap = BugControlConfig.loadControlIdsAndNames();

        for (String controlId : controlIdsAndNamesMap.keySet()){
            controlsMapping.put(controlId, new ArrayList<>());
        }

        Map<String, String[]> OWASP_proactives_mapping = new TreeMap<String, String[]>(MappingConfig.loadConfigFile());

        for (String key : OWASP_proactives_mapping.keySet()){

            for (String proactive : OWASP_proactives_mapping.get(key)){

                ArrayList<String> values = controlsMapping.get(proactive);
                values.add(key);
                controlsMapping.put(proactive, values);
            }
        }

        for (String key : controlsMapping.keySet()) {

            Boolean a1 = false, a2 = false, a3 = false, a4 = false, a5 = false, a6 = false, a7 = false, a8 = false, a9 = false, a10 = false;
            for (String category : controlsMapping.get(key)) {
                int id = Integer.parseInt(category.substring(1));
                switch (id) {
                    case (1):a1 = true;break;
                    case (2):a2 = true;break;
                    case (3):a3 = true;break;
                    case (4):a4 = true;break;
                    case (5):a5 = true;break;
                    case (6):a6 = true;break;
                    case (7):a7 = true;break;
                    case (8):a8 = true;break;
                    case (9):a9 = true;break;
                    case (10):a10 = true;break;
                }

            }

            BugCategoryToControlMapping mappingProactive = this.createOWASPToProactiveMapping(key, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10);

            int keyId = Integer.parseInt(key.substring(1));
            owaspProactiveMapping.put(keyId, mappingProactive);
        }

    }

    public HashMap<Integer, BugCategoryToControlMapping> getMapping() throws DocumentException {
        if (owaspProactiveMapping.isEmpty()){
            this.createMapping();
        }
        return owaspProactiveMapping;
    }

    private BugCategoryToControlMapping createOWASPToProactiveMapping(String control, Boolean a1, Boolean a2, Boolean a3, Boolean a4, Boolean a5, Boolean a6, Boolean a7, Boolean a8, Boolean a9, Boolean a10){

        BugCategoryToControlMapping mapping = new BugCategoryToControlMapping();
        mapping.setControl(control);
        mapping.setA1(a1);
        mapping.setA2(a2);
        mapping.setA3(a3);
        mapping.setA4(a4);
        mapping.setA5(a5);
        mapping.setA6(a6);
        mapping.setA7(a7);
        mapping.setA8(a8);
        mapping.setA9(a9);
        mapping.setA10(a10);

        return mapping;
    }
}
