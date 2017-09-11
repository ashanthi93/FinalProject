package org.ucsc.sse.knowedgemodel.settings.threatmodelconfigs;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class DefensivePerspectiveConfigTest {

    @BeforeTest
    private ArrayList<String[]> createASF(){

        ArrayList<String[]> asf = new ArrayList<String[]>();

        String[] threat = {"D1", "Authentication", "Credentials and authentication tokens are protected with encryption in storage and transit.\n" +
                "Protocols are resistant to brute force, dictionary, and replay attacks.\n" +
                "Strong password policies are enforced.\n" +
                "Trusted server authentication is used instead of SQL authentication.\n" +
                "Passwords are stored with salted hashes.\n" +
                "Password resets do not reveal password hints and valid usernames.\n" +
                "Account lockouts do not result in a denial of service attack.\n"};

        asf.add(threat);

        threat = new String[]{"D2", "Authorization", "Strong ACLs are used for enforcing authorized access to resources\n" +
                "Role-based access controls are used to restrict access to specific operations\n" +
                "The system follows the principle of least privilege for user and service accounts\n" +
                "Privilege separation is correctly configured within the presentation, business and data access layers\n"};

        asf.add(threat);

        threat = new String[]{"D3", "Configuration Management", "Least privileged processes are used and service accounts with no administration capability\n" +
                "Auditing and logging of all administration activities is enabled\n" +
                "Access to configuration files and administrator interfaces is restricted to administrators\n"};

        asf.add(threat);

        threat = new String[]{"D4", "Data Protection in Storage and Transit", "Standard encryption algorithms and correct key sizes are being used\n" +
                "Hashed message authentication codes (HMACs) are used to protect data integrity\n" +
                "Secrets (e.g. keys, confidential data ) are cryptographically protected both in transport and in storage\n" +
                "Built-in secure storage is used for protecting keys\n" +
                "No credentials and sensitive data are sent in clear text over the wire\n"};

        asf.add(threat);

        threat = new String[]{"D5","Data Validation / Parameter Validation", "Data type, format, length, and range checks are enforced\n" +
                "All data sent from the client is validated\n" +
                "No security decision is based upon parameters (e.g. URL parameters) that can be manipulated\n" +
                "Input filtering via white list validation is used\n" +
                "Output encoding is used\n"};

        asf.add(threat);

        threat = new String[]{"D6","Error Handling and Exception Management", "All exceptions are handled in a structured manner\n" +
                "Privileges are restored to the appropriate level in case of errors and exceptions\n" +
                "Error messages are scrubbed so that no sensitive information is revealed to the attacker\n"};

        asf.add(threat);

        threat = new String[]{"D7","User and Session Management", "No sensitive information is stored in clear text in the cookie\n" +
                "The contents of the authentication cookies is encrypted\n" +
                "Cookies are configured to expire\n" +
                "Sessions are resistant to replay attacks\n" +
                "Secure communication channels are used to protect authentication cookies\n" +
                "User is forced to re-authenticate when performing critical functions\n" +
                "Sessions are expired at logout\n"};

        asf.add(threat);

        threat = new String[]{"D8", "Auditing and Logging", "Sensitive information (e.g. passwords, PII) is not logged\n" +
                "Access controls (e.g. ACLs) are enforced on log files to prevent un-authorized access\n" +
                "Integrity controls (e.g. signatures) are enforced on log files to provide non-repudiation\n" +
                "Log files provide for audit trail for sensitive operations and logging of key events\n" +
                "Auditing and logging is enabled across the tiers on multiple servers\n"};

        asf.add(threat);

        return asf;
    }

    @Test
    public void testCreateFile() throws Exception {

        DefensivePerspectiveConfig defensivePerspectiveConfig = new DefensivePerspectiveConfig();
        defensivePerspectiveConfig.createFile(this.createASF(), "ASF");
    }

    @Test
    public void testLoadConfigFile() throws Exception {

    }

    @Test
    public void testLoadThreatControls() throws Exception {

    }
}