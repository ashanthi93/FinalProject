package org.ucsc.sse.knowedgemodel.settings.bugmodelconfigs;

import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.testng.Assert.*;

public class BugModelConfigTest {

    private ArrayList<String[]> createBugCategories(){

        ArrayList<String[]> bugCategories = new ArrayList<String[]>();

        String[] bugCategory = {"A1", "Injection", "Injection flaws, such as SQL, OS, and LDAP injection occur when untrusted data is sent to an\n" +
                "            interpreter as part of a command or query. The attacker’s hostile data can trick the interpreter into\n" +
                "            executing unintended commands or accessing data without proper authorization."};

        bugCategories.add(bugCategory);

        bugCategory = new String[]{"A2","Broken Authentication and Session Management", "Application functions related to authentication and session management are often not implemented\n" +
                "            correctly, allowing attackers to compromise passwords, keys, or session tokens, or to exploit other\n" +
                "            implementation flaws to assume other users’ identities."};

        bugCategories.add(bugCategory);

        bugCategory = new String[]{"A3","Cross-Site Scripting (XSS)", "XSS flaws occur whenever an application takes untrusted data and sends it to a web browser without\n" +
                "            proper validation or escaping. XSS allows attackers to execute scripts in the victim’s browser which can\n" +
                "            hijack user sessions, deface web sites, or redirect the user to malicious sites."};

        bugCategories.add(bugCategory);

        bugCategory = new String[]{"A4","Insecure Direct Object References", "A direct object reference occurs when a developer exposes a reference to an internal implementation\n" +
                "            object, such as a file, directory, or database key. Without an access control check or other protection,\n" +
                "            attackers can manipulate these references to access unauthorized data."};

        bugCategories.add(bugCategory);

        bugCategory = new String[]{"A5","Security Misconfiguration", "Good security requires having a secure configuration defined and deployed for the application,\n" +
                "            frameworks, application server, web server, database server, and platform. Secure settings should be\n" +
                "            defined, implemented, and maintained, as defaults are often insecure. Additionally, software should be kept\n" +
                "            up to date."};

        bugCategories.add(bugCategory);

        bugCategory = new String[]{"A6","Sensitive Data Exposure", "Many web applications do not properly protect sensitive data, such as credit cards, tax IDs, and\n" +
                "            authentication credentials. Attackers may steal or modify such weakly protected data to conduct credit card\n" +
                "            fraud, identity theft, or other crimes. Sensitive data deserves extra protection such as encryption at rest\n" +
                "            or in transit, as well as special precautions when exchanged with the browser."};

        bugCategories.add(bugCategory);

        bugCategory = new String[]{"A7","Missing Function Level Access Control", "Most web applications verify function level access rights before making that functionality visible\n" +
                "            in the UI. However, applications need to perform the same access control checks on the server when each\n" +
                "            function is accessed. If requests are not verified, attackers will be able to forge requests in order to\n" +
                "            access functionality without proper authorization."};

        bugCategories.add(bugCategory);

        bugCategory = new String[]{"A8","Cross-Site Request Forgery (CSRF)", "A CSRF attack forces a logged-on victim’s browser to send a forged HTTP request, including the\n" +
                "            victim’s session cookie and any other automatically included authentication information, to a vulnerable web\n" +
                "            application. This allows the attacker to force the victim’s browser to generate requests the vulnerable\n" +
                "            application thinks are legitimate requests from the victim."};

        bugCategories.add(bugCategory);

        bugCategory = new String[]{"A9","Using Components with Known Vulnerabilities", "Components, such as libraries, frameworks, and other software modules, almost always run with full\n" +
                "            privileges. If a vulnerable component is exploited, such an attack can facilitate serious data loss or\n" +
                "            server takeover. Applications using components with known vulnerabilities may undermine application defenses\n" +
                "            and enable a range of possible attacks and impacts."};

        bugCategories.add(bugCategory);

        bugCategory = new String[]{"A10","Unvalidated Redirects and Forwards", "Web applications frequently redirect and forward users to other pages and websites, and use\n" +
                "            untrusted data to determine the destination pages. Without proper validation, attackers can redirect victims\n" +
                "            to phishing or malware sites, or use forwards to access unauthorized pages."};

        bugCategories.add(bugCategory);

        return bugCategories;
    }


    @Test
    public void testCreateConfigFile() throws Exception {

        ArrayList<String[]> arrayList = this.createBugCategories();

        BugModelConfig bugModelConfig = new BugModelConfig();
        bugModelConfig.createConfigFile(arrayList, "owasp-top10", "2013");
    }


    public void testLoadConfigFile() throws Exception {

    }

    public void testLoadBugCategoryIdsAndNames() throws Exception {

    }

}