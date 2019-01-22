package org.sse.settings.config.source.mapping;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.*;

public class MappingCreatorTest {

    @Test
    public void testCreate() throws Exception {

        HashMap<String, List<String>> mappingByNames = new HashMap<String, List<String>>();

        List<String> proactives = new ArrayList<String>();
        proactives.add("Parameterize Queries");
        proactives.add("Encode Data");
        proactives.add("Validate All Inputs");
        proactives.add("Implement Logging and Intrusion Detection");
        proactives.add("Leverage Security Frameworks and Libraries");
        proactives.add("Errors and exceptions handling");

        mappingByNames.put("Injection", proactives);

        proactives = new ArrayList<String>();
        proactives.add("Implement Identity and Authentication Controls");
        proactives.add("Implement Logging and Intrusion Detection");
        proactives.add("Leverage Security Frameworks and Libraries");
        proactives.add("Errors and exceptions handling");

        mappingByNames.put("Broken Authentication and Session Management",proactives);

        proactives = new ArrayList<String>();
        proactives.add("Encode Data");
        proactives.add("Validate All Inputs");
        proactives.add("Implement Logging and Intrusion Detection");
        proactives.add("Leverage Security Frameworks and Libraries");
        proactives.add("Errors and exceptions handling");

        mappingByNames.put("Cross-Site Scripting (XSS)",proactives);

        proactives = new ArrayList<String>();
        proactives.add("Implement Identity and Authentication Controls");
        proactives.add("Implement Logging and Intrusion Detection");
        proactives.add("Leverage Security Frameworks and Libraries");
        proactives.add("Errors and exceptions handling");

        mappingByNames.put("Insecure Direct Object References",proactives);

        proactives = new ArrayList<String>();
        proactives.add("Implement Logging and Intrusion Detection");
        proactives.add("Leverage Security Frameworks and Libraries");
        proactives.add("Errors and exceptions handling");

        mappingByNames.put("Security Misconfiguration",proactives);

        proactives = new ArrayList<String>();
        proactives.add("Protect Data");
        proactives.add("Implement Logging and Intrusion Detection");
        proactives.add("Leverage Security Frameworks and Libraries");
        proactives.add("Errors and exceptions handling");

        mappingByNames.put("Sensitive Data Exposure",proactives);

        proactives = new ArrayList<String>();
        proactives.add("Implement Identity and Authentication Controls");
        proactives.add("Implement Logging and Intrusion Detection");
        proactives.add("Leverage Security Frameworks and Libraries");
        proactives.add("Errors and exceptions handling");

        mappingByNames.put("Missing Function Level Access Control",proactives);

        proactives = new ArrayList<String>();
        proactives.add("Implement Logging and Intrusion Detection");
        proactives.add("Leverage Security Frameworks and Libraries");
        proactives.add("Errors and exceptions handling");

        mappingByNames.put("Cross-Site Request Forgery (CSRF)",proactives);

        proactives = new ArrayList<String>();
        proactives.add("Implement Logging and Intrusion Detection");
        proactives.add("Leverage Security Frameworks and Libraries");
        proactives.add("Errors and exceptions handling");

        mappingByNames.put("Using Components with Known Vulnerabilities",proactives);

        proactives = new ArrayList<String>();
        proactives.add("Validate All Inputs");
        proactives.add("Implement Logging and Intrusion Detection");
        proactives.add("Leverage Security Frameworks and Libraries");
        proactives.add("Errors and exceptions handling");

        mappingByNames.put("Unvalidated Redirects and Forwards",proactives);

        MappingCreator.create(mappingByNames);
    }

}