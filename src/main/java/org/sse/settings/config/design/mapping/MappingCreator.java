package org.sse.settings.config.design.mapping;

import org.dom4j.DocumentException;
import org.sse.settings.config.design.ThreatModelConfig;
import org.sse.settings.config.design.control.ThreatControlConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MappingCreator {

    private static HashMap<String, List<String>> mapping;
    private static HashMap<String, String> threatCategoryIdAndName;
    private static HashMap<String, String> threatControlIdAndName;

    static {
        try {
            mapping = new HashMap<String, List<String>>();
            threatCategoryIdAndName = ThreatModelConfig.loadThreatCategoryIdsAndNames();
            threatControlIdAndName = ThreatControlConfig.loadThreatControlIdsAndNames();

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    private MappingCreator(){
    }

    /**
     *
     * @return
     */
    private static HashMap<String, List<String>> initialize(){

        HashMap<String, List<String>> mappingByNames = new HashMap<String, List<String>>();

        List<String> controlNames = new ArrayList<String>();
        controlNames.add("Authentication");
        controlNames.add("Data Protection in Storage and Transit");
        controlNames.add("User and Session management");

        mappingByNames.put("Spoofing", controlNames);

        controlNames = new ArrayList<String>();
        controlNames.add("Integrity");
        controlNames.add("Authorization");
        controlNames.add("User and Session Management");
        controlNames.add("Data Protection in Storage and Transit");

        mappingByNames.put("Tampering", controlNames);

        controlNames = new ArrayList<String>();
        controlNames.add("Non-repudiation");
        controlNames.add("Auditing and Logging");

        mappingByNames.put("Repudiation", controlNames);

        controlNames = new ArrayList<String>();
        controlNames.add("Confidentiality");
        controlNames.add("Authorization");
        controlNames.add("Data Protection in Storage and Transit");

        mappingByNames.put("Information Disclosure", controlNames);

        controlNames = new ArrayList<String>();
        controlNames.add("Availability");

        mappingByNames.put("Denial of Service", controlNames);

        controlNames = new ArrayList<String>();
        controlNames.add("Authorization");
        controlNames.add("Configuration Management");
        controlNames.add("Error Handling and Exception Management");

        mappingByNames.put("Elevation of Privileges", controlNames);

        return mappingByNames;
    }

    /**
     *
     */
    public static void create() throws IOException {

        HashMap<String, List<String>> mappingByNames = initialize();

        for (String threatCategoryName : mappingByNames.keySet()){

            String threatCategoryId = getThreatCategoryId(threatCategoryName);

            List<String> threatControlNames = mappingByNames.get(threatCategoryName);

            List<String> threatControlIds = new ArrayList<String>();

            for (String threatControlName : threatControlNames){
                threatControlIds.add(getThreatControlId(threatControlName));
            }

            mapping.put(threatCategoryId, threatControlIds);
        }

        MappingConfig.createFile(mapping);
    }

    /**
     *
     *
     * @param threatCategoryName
     * @return
     */
    private static String getThreatCategoryId(String threatCategoryName){

        for (String threatCategoryId : threatCategoryIdAndName.keySet()){

            if (threatCategoryIdAndName.get(threatCategoryId).equalsIgnoreCase(threatCategoryName)){
                return threatCategoryId;
            }
        }

        throw new RuntimeException("Invalid Threat Category Name");
    }

    /**
     *
     *
     * @param threatControlName
     * @return
     */
    private static String getThreatControlId(String threatControlName){

        for (String threatControlId : threatControlIdAndName.keySet()){

            if (threatControlIdAndName.get(threatControlId).equalsIgnoreCase(threatControlName)){
                return threatControlId;
            }
        }

        throw new RuntimeException("Invalid Threat Control Name");
    }
}
