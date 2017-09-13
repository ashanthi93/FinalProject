package org.ucsc.sse.knowedgemodel.settings.bugmodelconfigs;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.testng.Assert.*;

/**
 * Created by CHAM PC on 9/12/2017.
 */
public class BugControlConfigTest {

    @BeforeTest
    private ArrayList<String[]> createBugControls(){

        ArrayList<String[]> bugControls = new ArrayList<String[]>();

        String[] control = {"C1", "Verify for Security Early and Often", "Include security while writing testing stories and tasks. Include the Proactive Controls in stubs and drivers\n" +
                "Security testing stories should be defined such that the lowest child story can be implemented and accepted in a single iteration; testing a Proactive Control must be lightweight\n" +
                "Consider OWASP ASVS as a guide to define security requirements and testing\n" +
                "Convert scanning output into reusable Proactive Controls to avoid entire classes of problems\n"};

        bugControls.add(control);

        control = new String[]{"C2", "Parameterize Queries", "Untrusted input should be prevented from being interpreted as part of a SQL command\n" +
                "Use ‘Query Parameterization’\n" +
                "SQL statements are sent to and parsed by the database server separately from any parameters\n" +
                "Use defense in depth with use of technologies such as automated static analysis and proper database management system configuration\n" +
                "Configure database engines to only support parameterized queries\n"};

        bugControls.add(control);

        control = new String[]{"C3", "Encode Data", "Encoding is translating special characters into some equivalent form that is no longer dangerous in the target interpreter\n" +
                "Manipulating user generated content: ensure that data is filtered and/or encoded when presenting it in the Web View\n" +
                "Loading content from an external source: apps that need to display untrusted content inside a Web View should use a dedicated server/host to render and escape HTML/Javascript content in a safe way. This prevents access to local system contents by malicious JavaScript code.\n"};

        bugControls.add(control);

        control = new String[]{"C4", "Validate All Inputs", "Use  two general approaches to performing input syntax validation white listing preferred over  black listing \n" +
                "Regular expressions offer a way to check whether data matches a specific pattern this is a great way to implement whitelist validation. \n" +
                "The security of the application should be enforced where that input is used, e.g., if input is used to build an HTML response, then the appropriate HTML encoding should be performed to prevent XSS  attacks. Also, if input is used to build a SQL statement, Query Parameterization should be used.\n"};

        bugControls.add(control);

        control = new String[]{"C5", "Implement Identity and Authentication Controls", "Use Multi Factor Authentication\n" +
                "For Mobile Application: Token Based Authentication. avoid storing/persisting authentication credentials locally on the device. Instead, perform initial authentication using the username and password supplied by the user, and then generate a shortlived access token which can be used to authenticate a client request without sending the user's credentials. \n" +
                "Implement Secure Password Storage using cryptography controls\n" +
                "Implement Secure Password Recovery Mechanism\n" +
                "Session: Generation and Expiration: On any successful authentication and reauthentication the software should generate a new session and session id.\n" +
                "Mandatory to set expiration timeouts for every session, after a specified period of inactivity.  \n" +
                "Reauthentication for Sensitive Features\n"};

        bugControls.add(control);

        control = new String[]{"C6", "Implement Access Controls", "Force All Requests to go Through Access Control Checks\n" +
                "Deny by Default \n" +
                "Principle of Least Privilege: each user or system component should be allocated the minimum privilege required to perform an action for the minimum amount of time\n" +
                "Avoid Hard Coded Access Control Checks: enforcement layer (checks in code) and your access control decision making process (the access control \"engine\") should be separated when possible\n" +
                "Code to the Activity: Consider checking if the user has access to that feature in code, as opposed to checking what role the user is in code\n" +
                "Server side Trusted Data Should Drive Access Control. Policy data such as a user's role or an access control rule should never be part of the request  \n"};

        bugControls.add(control);

        control = new String[]{"C7", "Protect Data", "Classify and Encrypt Data at rest. Develop your own crypto algorithm & key size should be optly chosen \n" +
                "Encrypt Data in Transit. TLS is by far the most common and widely supported model used by web applications for encryption in transit  \n" +
                "Implement Protection in Transit\n" +
                "Mobile Application: Secure Local Storage. Avoid storing the sensitive data  &   authentication credentials, access token, etc. on local device\n"};

        bugControls.add(control);

        control = new String[]{"C8", "Implement Logging and Intrusion Detection", "Ensure proper level of logging\n" +
                "Follow a common logging approach within the system and across systems where possible to make correlation easy\n" +
                "Make sure to always log the timestamp and identifying information like the source IP and userid, but be careful not to log private or confidential data or opt out data or secrets\n" +
                "Design proper positioning of IDS sensors and management \n"};

        bugControls.add(control);

        control = new String[]{"C9", "Leverage Security Frameworks and Libraries", "When possible, the emphasis should be on using the existing secure features of frameworks rather than importing third party libraries. It is preferable to have developers take advantage of what they're already using instead of forcing yet another library on them \n" +
                "Not all the frameworks are completely secured hence it is important to build in additional security where possible, updating frequently and verifying them for security early and often like any other software you  depend upon\n"};

        bugControls.add(control);

        control = new String[]{"C10", "Errors and exceptions handling", "It’s recommended to manage exceptions in a centralized manner to avoid duplicated try/catch blocks in the code, and to ensure that all unexpected behaviors are correctly handled inside the application.\n" +
                "Ensure that error messages displayed to users do not leak critical data, but are still verbose enough to explain the issue to the user.\n" +
                "Ensure that exceptions are logged in a way that gives enough information for Q/A, forensics or incident response teams to understand the problem.\n"};

        bugControls.add(control);

        return bugControls;
    }

    @Test
    public void testCreateConfigFile() throws Exception {

        ArrayList<String[]> bugControls = this.createBugControls();
        BugControlConfig bugControlConfig = new BugControlConfig();
        bugControlConfig.createConfigFile(bugControls,"OWASP-Top10","2013");

    }

    @Test
    public void testLoadConfigFile() throws Exception {

    }

}