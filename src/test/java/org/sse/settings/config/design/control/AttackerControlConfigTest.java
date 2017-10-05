package org.sse.settings.config.design.control;

import org.sse.design.model.ThreatControl;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AttackerControlConfigTest {

    @BeforeTest
    public void testCreateFile() throws Exception {

        ArrayList<String[]> defensive = new ArrayList<String[]>();

        defensive.add(new String[]{"AS1","Authentication","Credentials and authentication tokens are protected with encryption in storage and transit. " +
                "Protocols are resistant to brute force, dictionary, and replay attacks. " +
                "Strong password policies are enforced. " +
                "Trusted server authentication is used instead of SQL authentication. " +
                "Passwords are stored with salted hashes. " +
                "Password resets do not reveal password hints and valid usernames. " +
                "Account lockouts do not result in a denial of service attack"});

        defensive.add(new String[]{"AS2","Authorization","Strong ACLs are used for enforcing authorized access to resources. " +
                "Role-based access control are used to restrict access to specific operations. " +
                "The system follows the principle of least privilege for user and service accounts. " +
                "Privilege separation is correctly configured within the presentation, business and data access layers"});

        defensive.add(new String[]{"AS3","Configuration Management","Standard encryption algorithms and correct key sizes are being used. " +
                "Hashed message authentication codes (HMACs) are used to protect data integrity. " +
                "Secrets (e.g. keys, confidential data ) are cryptographically protected both in transport and in storage. " +
                "Built-in secure storage is used for protecting keys. " +
                "No credentials and sensitive data are sent in clear text over the wire"});

        defensive.add(new String[]{"AS4","Data Protection in Storage and Transit","Standard encryption algorithms and correct key sizes are being used. " +
                "Hashed message authentication codes (HMACs) are used to protect data integrity. " +
                "Secrets (e.g. keys, confidential data ) are cryptographically protected both in transport and in storage. " +
                "Built-in secure storage is used for protecting keys. " +
                "No credentials and sensitive data are sent in clear text over the wire"});

        defensive.add(new String[]{"AS5","Data Validation / Parameter Validation","Data type, format, length, and range checks are enforced. " +
                "All data sent from the client is validated. " +
                "No security decision is based upon parameters (e.g. URL parameters) that can be manipulated. " +
                "Input filtering via white list validation is used. " +
                "Output encoding is used"});

        defensive.add(new String[]{"AS6","Error Handling and Exception Management","All exceptions are handled in a structured manner. " +
                "Privileges are restored to the appropriate level in case of errors and exceptions. " +
                "Error messages are scrubbed so that no sensitive information is revealed to the attacker"});

        defensive.add(new String[]{"AS7","User and Session Management","No sensitive information is stored in clear text in the cookie. " +
                "The contents of the authentication cookies is encrypted. " +
                "Cookies are configured to expire. " +
                "Sessions are resistant to replay attacks. " +
                "Secure communication channels are used to protect authentication cookies. " +
                "User is forced to re-authenticate when performing critical functions. " +
                "Sessions are expired at logout"});

        defensive.add(new String[]{"AS8","Auditing and Logging","Sensitive information (e.g. passwords, PII) is not logged. " +
                "Access control (e.g. ACLs) are enforced on log files to prevent un-authorized access. " +
                "Integrity control (e.g. signatures) are enforced on log files to provide non-repudiation. " +
                "Log files provide for audit trail for sensitive operations and logging of key events. " +
                "Auditing and logging is enabled across the tiers on multiple servers"});

        AttackerControlConfig.createFile(defensive);
    }

    @Test
    public void testLoadConfigFile() throws Exception{

        List<ThreatControl> threatControls = AttackerControlConfig.loadConfigFile();

        for (ThreatControl threatControl : threatControls){
            System.out.println(threatControl.getDescription().toString());
            System.out.println("------------");
        }
    }
}