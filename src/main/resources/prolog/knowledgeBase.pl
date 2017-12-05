:- [rules].
:- [stride].


owasp_top10(
a1,
name("Injection"),
<<<<<<< HEAD
[]).
owasp_top10(
a2,
name("Broken Authentication and Session Management"),
[]).
owasp_top10(
a3,
name("Cross-Site Scripting (XSS)"),
[]).
owasp_top10(
a4,
name("Insecure Direct Object References"),
[]).
owasp_top10(
a5,
name("Security Misconfiguration"),
[]).
owasp_top10(
a6,
name("Sensitive Data Exposure"),
[]).
owasp_top10(
a7,
name("Missing Function Level Access Control"),
[]).
owasp_top10(
a8,
name("Cross-Site Request Forgery (CSRF)"),
[]).
owasp_top10(
a9,
name("Using Components with Known Vulnerabilities"),
[]).
owasp_top10(
a10,
name("Unvalidated Redirects and Forwards"),
[]).
=======
[c2]).
owasp_top10(
a2,
name("Broken Authentication and Session Management"),
[c3]).
owasp_top10(
a3,
name("Cross-Site Scripting (XSS)"),
[c4]).
owasp_top10(
a4,
name("Insecure Direct Object References"),
[c5]).
owasp_top10(
a5,
name("Security Misconfiguration"),
[c6,c7]).
owasp_top10(
a6,
name("Sensitive Data Exposure"),
[c8]).
owasp_top10(
a7,
name("Missing Function Level Access Control"),
[c9]).
owasp_top10(
a8,
name("Cross-Site Request Forgery (CSRF)"),
[c10]).
owasp_top10(
a9,
name("Using Components with Known Vulnerabilities"),
[c11]).
owasp_top10(
a10,
name("Unvalidated Redirects and Forwards"),
[c11]).
>>>>>>> a2ad2339450e7555d73e8a7df0e4b26ab304b1be


owasp_top10_proactive(
c2,
name("Parameterize Queries"), 
<<<<<<< HEAD
"-LSB- Untrusted input should be prevented from being interpreted as part of a SQL command Use $ Query Parameterization $ SQL statements are sent to and parsed by the database server separately from any parameters Use defense in depth with use of technologies such as automated static analysis and proper database management system configuration Configure database engines to only support parameterized queries -RSB-").
=======
"-LSB- -LSB- Untrusted input should be prevented from being interpreted as part of a SQL command Use $ Query Parameterization $ SQL statements are sent to and parsed by the database server separately from any parameters Use defense in depth with use of technologies such as automated static analysis and proper database management system configuration Configure database engines to only support parameterized queries -RSB- -RSB-").
>>>>>>> a2ad2339450e7555d73e8a7df0e4b26ab304b1be

owasp_top10_proactive(
c3,
name("Encode Data"), 
<<<<<<< HEAD
"-LSB- Encoding is translating special characters into some equivalent form that is no longer dangerous in the target interpreter Manipulating user generated content : ensure that data is filtered and/or encoded when presenting it in the Web View Loading content from an external source : apps that need to display untrusted content inside a Web View should use a dedicated server/host to render and escape HTML/Javascript content in a safe way ., , This prevents access to local system contents by malicious JavaScript code . -RSB-").
=======
"-LSB- -LSB- Encoding is translating special characters into some equivalent form that is no longer dangerous in the target interpreter Manipulating user generated content : ensure that data is filtered and/or encoded when presenting it in the Web View Loading content from an external source : apps that need to display untrusted content inside a Web View should use a dedicated server/host to render and escape HTML/Javascript content in a safe way ., , , This prevents access to local system contents by malicious JavaScript code . -RSB- -RSB-").
>>>>>>> a2ad2339450e7555d73e8a7df0e4b26ab304b1be

owasp_top10_proactive(
c4,
name("Validate All Inputs"), 
<<<<<<< HEAD
"-LSB- Use two general approaches to performing input syntax validation white listing preferred over black listing Regular expressions offer a way to check whether data matches a specific pattern this is a great way to implement whitelist validation ., , The security of the application should be enforced where that input is used , e.g. , if input is used to build an HTML response , then the appropriate HTML encoding should be performed to prevent XSS attacks ., , Also , if input is used to build a SQL statement , Query Parameterization should be used . -RSB-").
=======
"-LSB- -LSB- Use two general approaches to performing input syntax validation white listing preferred over black listing Regular expressions offer a way to check whether data matches a specific pattern this is a great way to implement whitelist validation ., , , The security of the application should be enforced where that input is used , e.g. , if input is used to build an HTML response , then the appropriate HTML encoding should be performed to prevent XSS attacks ., , , Also , if input is used to build a SQL statement , Query Parameterization should be used . -RSB- -RSB-").
>>>>>>> a2ad2339450e7555d73e8a7df0e4b26ab304b1be

owasp_top10_proactive(
c5,
name("Implement Identity and Authentication Controls"), 
<<<<<<< HEAD
"-LSB- Use Multi Factor Authentication For Mobile Application : Token Based Authentication ., , avoid storing/persisting authentication credentials locally on the device ., , Instead , perform initial authentication using the username and password supplied by the user , and then generate a shortlived access token which can be used to authenticate a client request without sending the user 's credentials ., , Implement Secure Password Storage using cryptography controls Implement Secure Password Recovery Mechanism Session : Generation and Expiration : On any successful authentication and reauthentication the software should generate a new session and session id ., , Mandatory to set expiration timeouts for every session , after a specified period of inactivity ., , Reauthentication for Sensitive Features -RSB-").
=======
"-LSB- -LSB- Use Multi Factor Authentication For Mobile Application : Token Based Authentication ., , , avoid storing/persisting authentication credentials locally on the device ., , , Instead , perform initial authentication using the username and password supplied by the user , and then generate a shortlived access token which can be used to authenticate a client request without sending the user 's credentials ., , , Implement Secure Password Storage using cryptography controls Implement Secure Password Recovery Mechanism Session : Generation and Expiration : On any successful authentication and reauthentication the software should generate a new session and session id ., , , Mandatory to set expiration timeouts for every session , after a specified period of inactivity ., , , Reauthentication for Sensitive Features -RSB- -RSB-").
>>>>>>> a2ad2339450e7555d73e8a7df0e4b26ab304b1be

owasp_top10_proactive(
c6,
name("Implement Access Controls"), 
<<<<<<< HEAD
"-LSB- Force All Requests to go Through Access Control Checks Deny by Default Principle of Least Privilege : each user or system component should be allocated the minimum privilege required to perform an action for the minimum amount of time Avoid Hard Coded Access Control Checks : enforcement layer -LRB- checks in code -RRB- and your access control decision making process -LRB- the access control `` engine '' -RRB- should be separated when possible Code to the Activity : Consider checking if the user has access to that feature in code , as opposed to checking what role the user is in code Server side Trusted Data Should Drive Access Control ., , Policy data such as a user 's role or an access control rule should never be part of the request -RSB-").
=======
"-LSB- -LSB- Force All Requests to go Through Access Control Checks Deny by Default Principle of Least Privilege : each user or system component should be allocated the minimum privilege required to perform an action for the minimum amount of time Avoid Hard Coded Access Control Checks : enforcement layer -LRB- checks in code -RRB- and your access control decision making process -LRB- the access control `` engine '' -RRB- should be separated when possible Code to the Activity : Consider checking if the user has access to that feature in code , as opposed to checking what role the user is in code Server side Trusted Data Should Drive Access Control ., , , Policy data such as a user 's role or an access control rule should never be part of the request -RSB- -RSB-").
>>>>>>> a2ad2339450e7555d73e8a7df0e4b26ab304b1be

owasp_top10_proactive(
c7,
name("Protect Data"), 
<<<<<<< HEAD
"-LSB- Classify and Encrypt Data at rest ., , Develop your own crypto algorithm & key size should be optly chosen Encrypt Data in Transit ., , TLS is by far the most common and widely supported model used by web applications for encryption in transit Implement Protection in Transit Mobile Application : Secure Local Storage ., , Avoid storing the sensitive data & authentication credentials , access token , etc. on local device -RSB-").
=======
"-LSB- -LSB- Classify and Encrypt Data at rest ., , , Develop your own crypto algorithm & key size should be optly chosen Encrypt Data in Transit ., , , TLS is by far the most common and widely supported model used by web applications for encryption in transit Implement Protection in Transit Mobile Application : Secure Local Storage ., , , Avoid storing the sensitive data & authentication credentials , access token , etc. on local device -RSB- -RSB-").
>>>>>>> a2ad2339450e7555d73e8a7df0e4b26ab304b1be

owasp_top10_proactive(
c8,
name("Implement Logging and Intrusion Detection"), 
<<<<<<< HEAD
"-LSB- Ensure proper level of logging Follow a common logging approach within the system and across systems where possible to make correlation easy Make sure to always log the timestamp and identifying information like the source IP and userid , but be careful not to log private or confidential data or opt out data or secrets Design proper positioning of IDS sensors and management -RSB-").
=======
"-LSB- -LSB- Ensure proper level of logging Follow a common logging approach within the system and across systems where possible to make correlation easy Make sure to always log the timestamp and identifying information like the source IP and userid , but be careful not to log private or confidential data or opt out data or secrets Design proper positioning of IDS sensors and management -RSB- -RSB-").
>>>>>>> a2ad2339450e7555d73e8a7df0e4b26ab304b1be

owasp_top10_proactive(
c9,
name("Leverage Security Frameworks and Libraries"), 
<<<<<<< HEAD
"-LSB- When possible , the emphasis should be on using the existing secure features of frameworks rather than importing third party libraries ., , It is preferable to have developers take advantage of what they 're already using instead of forcing yet another library on them Not all the frameworks are completely secured hence it is important to build in additional security where possible , updating frequently and verifying them for security early and often like any other software you depend upon -RSB-").
=======
"-LSB- -LSB- When possible , the emphasis should be on using the existing secure features of frameworks rather than importing third party libraries ., , , It is preferable to have developers take advantage of what they 're already using instead of forcing yet another library on them Not all the frameworks are completely secured hence it is important to build in additional security where possible , updating frequently and verifying them for security early and often like any other software you depend upon -RSB- -RSB-").
>>>>>>> a2ad2339450e7555d73e8a7df0e4b26ab304b1be

owasp_top10_proactive(
c10,
name("Errors and exceptions handling"), 
<<<<<<< HEAD
"-LSB- It is recommended to manage exceptions in a centralized manner to avoid duplicated try/catch blocks in the code , and to ensure that all unexpected behaviors are correctly handled inside the application ., , Ensure that error messages displayed to users do not leak critical data , but are still verbose enough to explain the issue to the user ., , Ensure that exceptions are logged in a way that gives enough information for Q/A , forensics or incident response teams to understand the problem . -RSB-").
=======
"-LSB- -LSB- It is recommended to manage exceptions in a centralized manner to avoid duplicated try/catch blocks in the code , and to ensure that all unexpected behaviors are correctly handled inside the application ., , , Ensure that error messages displayed to users do not leak critical data , but are still verbose enough to explain the issue to the user ., , , Ensure that exceptions are logged in a way that gives enough information for Q/A , forensics or incident response teams to understand the problem . -RSB- -RSB-").

owasp_top10_proactive(
c11,
name("aa"), 
"asas").
>>>>>>> a2ad2339450e7555d73e8a7df0e4b26ab304b1be

