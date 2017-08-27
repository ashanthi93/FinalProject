package settings;

import association.model.Association;
import association.report.AssociationReport;
import association.report.builder.ReportBuilder;
import association.report.builder.concrete.JSONReportBuilder;
import association.report.builder.concrete.XMLReportBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import design.model.Threat;
import org.xml.sax.SAXException;
import settings.owasp_configs.OWASPT10Config;
import settings.stride_configs.STRIDEThreatControlConfig;
import source.model.Bug;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class Temp {

    /*public static void main(String args[]) throws ParserConfigurationException {

        String[] A1 = {"A1", "Injection", "Injection flaws, such as SQL, OS, and LDAP injection occur when untrusted data is sent to an interpreter as part of a command or query. The attacker’s hostile data can trick the interpreter into executing unintended commands or accessing data without proper authorization."};
        String[] A2 = {"A2", "Broken Authentication and Session Management", "Application functions related to authentication and session management are often not implemented correctly, allowing attackers to compromise passwords, keys, or session tokens, or to exploit other implementation flaws to assume other users’ identities."};
        String[] A3 = {"A3", "Cross-Site Scripting (XSS)", "XSS flaws occur whenever an application takes untrusted data and sends it to a web browser without proper validation or escaping. XSS allows attackers to execute scripts in the victim’s browser which can hijack user sessions, deface web sites, or redirect the user to malicious sites."};
        String[] A4 = {"A4", "Insecure Direct Object References", "A direct object reference occurs when a developer exposes a reference to an internal implementation object, such as a file, directory, or database key. Without an access control check or other protection, attackers can manipulate these references to access unauthorized data."};
        String[] A5 = {"A5", "Security Misconfiguration", "Good security requires having a secure configuration defined and deployed for the application, frameworks, application server, web server, database server, and platform. Secure settings should be defined, implemented, and maintained, as defaults are often insecure. Additionally, software should be kept up to date."};
        String[] A6 = {"A6", "Sensitive Data Exposure", "Many web applications do not properly protect sensitive data, such as credit cards, tax IDs, and authentication credentials. Attackers may steal or modify such weakly protected data to conduct credit card fraud, identity theft, or other crimes. Sensitive data deserves extra protection such as encryption at rest or in transit, as well as special precautions when exchanged with the browser."};
        String[] A7 = {"A7", "Missing Function Level Access Control", "Most web applications verify function level access rights before making that functionality visible in the UI. However, applications need to perform the same access control checks on the server when each function is accessed. If requests are not verified, attackers will be able to forge requests in order to access functionality without proper authorization."};
        String[] A8 = {"A8", "Cross-Site Request Forgery (CSRF)", "A CSRF attack forces a logged-on victim’s browser to send a forged HTTP request, including the victim’s session cookie and any other automatically included authentication information, to a vulnerable web application. This allows the attacker to force the victim’s browser to generate requests the vulnerable application thinks are legitimate requests from the victim."};
        String[] A9 = {"A9", "Using Components with Known Vulnerabilities", "Components, such as libraries, frameworks, and other software modules, almost always run with full privileges. If a vulnerable component is exploited, such an attack can facilitate serious data loss or server takeover. Applications using components with known vulnerabilities may undermine application defenses and enable a range of possible attacks and impacts."};
        String[] A10 = {"A10", "Unvalidated Redirects and Forwards", "Web applications frequently redirect and forward users to other pages and websites, and use untrusted data to determine the destination pages. Without proper validation, attackers can redirect victims to phishing or malware sites, or use forwards to access unauthorized pages."};

        ArrayList<String[]> OWASPT10List = new ArrayList<String[]>();
        OWASPT10List.add(A1);
        OWASPT10List.add(A2);
        OWASPT10List.add(A3);
        OWASPT10List.add(A4);
        OWASPT10List.add(A5);
        OWASPT10List.add(A6);
        OWASPT10List.add(A7);
        OWASPT10List.add(A8);
        OWASPT10List.add(A9);
        OWASPT10List.add(A10);

        OWASPT10Config owaspt10Config = new OWASPT10Config();
        //owaspt10Config.createConfigFile(OWASPT10List);

        String[] C2 = {"C2","Parameterize Queries","SQL Injection is one of the most dangerous web application risks. SQL Injection is easy to\n" +
                "exploit with many open source automated attack tools available. SQL injection can also deliver\n" +
                "an impact to your application that is devastating.\n" +
                "The simple insertion of malicious SQL code into your web application – and the entire database\n" +
                "could potentially be stolen, wiped, or modified. The web application can even be used to run\n" +
                "dangerous operating system commands against the operating system hosting your database.\n" +
                "The main concern with SQL injection is the fact, that the SQL query and its parameters are\n" +
                "contained in one query string.\n" +
                "In order to mitigate SQL injection, untrusted input should be prevented from being interpreted as\n" +
                "part of a SQL command. The best way to do this is with the programming technique known as\n" +
                "‘Query Parameterization’. In this case, the SQL statements are sent to and parsed by the\n" +
                "database server separately from any parameters.\n" +
                "Many development frameworks (Rails, Django, Node.js, etc.) employ an objectrelational\n" +
                "model\n" +
                "(ORM) to abstract communication with a database. Many ORMs provide automatic query\n" +
                "parameterization when using programmatic methods to retrieve and modify data, but developers\n" +
                "should still be cautious when allowing user input into object queries (OQL/HQL) or other\n" +
                "advanced queries supported by the framework.\n" +
                "Proper defense in depth against SQL injection includes the use of technologies such as\n" +
                "automated static analysis and proper database management system configuration. If possible,\n" +
                "database engines should be configured to only support parameterized queries."};

        String[] C3 = {"C3","Encode Data","Encoding is a powerful mechanism to help protect against many types of attack, especially\n" +
                "injection attacks. Essentially, encoding involves translating special characters into some\n" +
                "equivalent form that is no longer dangerous in the target interpreter. Encoding is needed to stop\n" +
                "various forms of injection including command injection (Unix command encoding, Windows\n" +
                "command encoding), LDAP injection (LDAP encoding) and XML injection (XML encoding).\n" +
                "Another example of encoding is output encoding which is necessary to prevent cross site\n" +
                "scripting (HTML entity encoding, JavaScript hex encoding, etc).\n" +
                "Web developers often build web pages dynamically, consisting of a mix of static, developer built\n" +
                "HTML/JavaScript and data that was originally populated with user input or some other untrusted\n" +
                "source. This input should be considered to be untrusted data and dangerous, which requires\n" +
                "special handling when building a secure web application. CrossSite\n" +
                "Scripting (XSS) occurs\n" +
                "when an attacker tricks your users into executing malicious script that was not originally built\n" +
                "into your website. XSS attacks execute in the user's browser and can have a wide variety of\n" +
                "effects.\n" +
                "Persistent XSS (or Stored XSS) occurs when an XSS attack can be embedded in a website\n" +
                "database or filesystem. This flavor of XSS is more dangerous because users will typically\n" +
                "already be logged into the site when the attack is executed, and a single injection attack can\n" +
                "affect many different users.\n" +
                "Reflected XSS occurs when the attacker places an XSS payload as part of a URL and tricks a\n" +
                "victim into visiting that URL. When a victim visits this URL, the XSS attack is launched. This type\n" +
                "of XSS is less dangerous since it requires a degree of interaction between the attacker and the\n" +
                "victim.\n" +
                "DOM based XSS is an XSS attack that occurs in DOM, rather than in HTML code. That is, the\n" +
                "page itself does not change, but the client side code contained in the page executes differently\n" +
                "due to the malicious modifications that have occurred in the DOM environment. It can only be\n" +
                "observed on runtime or by investigating the DOM of the page.\n" +
                "Contextual output encoding is a crucial programming technique needed to stop XSS. This is\n" +
                "performed on output, when you’re building a user interface, at the last moment before untrusted\n" +
                "data is dynamically added to HTML. The type of encoding required will depend on the HTML\n" +
                "context of where the untrusted data is added, for example in an attribute value, or in the main\n" +
                "HTML body, or even in a JavaScript code block.\n" +
                "The encoding functions required to stop XSS include HTML Entity Encoding, JavaScript\n" +
                "Encoding and Percent Encoding (aka URL Encoding). OWASP's Java Encoder Project provides\n" +
                "encoders for these functions in Java. In .NET 4.5, the AntiXssEncoder Class provides CSS,\n" +
                "HTML, URL, JavaScriptString and XML encoders other\n" +
                "encoders for LDAP and VBScript are\n" +
                "included in the open source AntiXSS library. Every other web language has some kind of\n" +
                "encoding library or support."};

        String[] C4 = {"C4","Validate All Inputs","Any data which is directly entered by, or influenced by, users should be treated as untrusted.\n" +
                "An application should check that this data is both syntactically and semantically valid (in that\n" +
                "order) before using it in any way (including displaying it back to the user). Additionally, the most\n" +
                "secure applications treat all variables as untrusted and provide security controls regardless of\n" +
                "the source of that data.\n" +
                "Syntax validity means that the data is in the form that is expected. For example, an application\n" +
                "may allow a user to select a fourdigit\n" +
                "“account ID” to perform some kind of operation. The\n" +
                "application should assume the user is entering a SQL injection payload, and should check that\n" +
                "the data entered by the user is exactly four digits in length, and consists only of numbers (in\n" +
                "addition to utilizing proper query parameterization).\n" +
                "Semantic validity means that the data is meaningful: In the above example, the application\n" +
                "should assume that the user is maliciously entering an account ID the user is not permitted to\n" +
                "access. The application should then check that the user has permission to access said account\n" +
                "ID.\n" +
                "Input validation must be wholly serverside:\n" +
                "clientside\n" +
                "controls may be used for convenience.\n" +
                "For example, JavaScript validation may alert the user that a particular field must consist of\n" +
                "numbers, but the server must validate that the field actually does consist of numbers."};
        String[] C5 = {"C5","Implement Identity and Authentication Controls","-----------------"};
        String[] C6 = {"C6","Implement Appropriate Access Controls","------------------"};
        String[] C7 = {"C7","Protect Data","--------------------"};
        String[] C8 = {"C8","Implement Logging and Intrusion Detection","--------------"};
        String[] C9 = {"C9","Leverage Security Frameworks and Libraries","---------------"};
        String[] C10 = {"C10","Error and Exception Handling","------------------------------"};

        ArrayList<String[]> OWASPProactives = new ArrayList<String[]>();
        OWASPProactives.add(C2);
        OWASPProactives.add(C3);
        OWASPProactives.add(C4);
        OWASPProactives.add(C5);
        OWASPProactives.add(C6);
        OWASPProactives.add(C7);
        OWASPProactives.add(C8);
        OWASPProactives.add(C9);
        OWASPProactives.add(C10);

        //OWASPProactiveConfig owaspProactiveConfig = new OWASPProactiveConfig();
        //owaspProactiveConfig.createConfigFile(OWASPProactives);
    }*/

   public static void main(String args[]){

        /*STRIDEThreatControlConfig strideThreatControlConfig = new STRIDEThreatControlConfig();
        try {
            strideThreatControlConfig.createFile(STRIDEThreatControlsOverlayCreator.getInstance().createThreatControlsOverlayList());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }*/

       ReportBuilder reportBuilder = new JSONReportBuilder();

       Association association = new Association();
       association.setThreatCategoryName("Spoofing");

       ArrayList<Threat> threatArrayList = new ArrayList<Threat>();
       Threat threat = new Threat();
       threat.setName("T1");
       Threat threat1 = new Threat();
       threat.setName("T2");

       threatArrayList.add(threat);
       threatArrayList.add(threat1);

       association.setthreatArrayList(threatArrayList);

       ArrayList<Bug> bugArrayList = new ArrayList<Bug>();
       Bug bug = new Bug();
       bug.setName("B1");
       Bug bug1 = new Bug();
       bug1.setName("B2");

       bugArrayList.add(bug);
       bugArrayList.add(bug1);

       association.setbugArrayList(bugArrayList);

       Association association1 = new Association();
       association1.setThreatCategoryName("Tampering");

       threat.setName("T5");
       threat1.setName("T6");
       threatArrayList.add(threat);
       threatArrayList.add(threat1);
       association1.setthreatArrayList(threatArrayList);
       association1.setbugArrayList(bugArrayList);

       AssociationReport associationReport = new AssociationReport();

       ArrayList<Association> associationArrayList = new ArrayList<Association>();
       associationArrayList.add(association);
       associationArrayList.add(association1);

       associationReport.setAssociationArrayList(associationArrayList);

       try {
           reportBuilder.convertReport(associationReport);
       } catch (JsonProcessingException e) {
           e.printStackTrace();
       }
   }
}
