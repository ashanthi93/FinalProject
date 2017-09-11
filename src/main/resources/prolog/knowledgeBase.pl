:- [rules].
:- [stride].


owasp_top10(
a1,
name("Injection"),
[c2,c3,c4,c8,c9,c10]).
owasp_top10(
a2,
name("Broken Authentication and Session Management"),
[c5,c8,c9,c10]).
owasp_top10(
a3,
name("Cross-Site Scripting (XSS)"),
[c3,c4,c8,c9,c10]).
owasp_top10(
a4,
name("Insecure Direct Object References"),
[c6,c8,c9,c10]).
owasp_top10(
a5,
name("Security Misconfiguration"),
[c8,c9,c10]).
owasp_top10(
a6,
name("Sensitive Data Exposure"),
[c7,c8,c9,c10]).
owasp_top10(
a7,
name("Missing Function Level Access Control"),
[c6,c8,c9,c10]).
owasp_top10(
a8,
name("Cross-Site Request Forgery (CSRF)"),
[c8,c9,c10]).
owasp_top10(
a9,
name("Using Components with Known Vulnerabilities"),
[c8,c9,c10]).
owasp_top10(
a10,
name("Unvalidated Redirects and Forwards"),
[c4,c8,c9,c10]).


owasp_top10_proactive(
c2,
name("Parameterize Queries"), 
"c2 description"
).

owasp_top10_proactive(
c3,
name("Encode Data"), 
"c3 description"
).

owasp_top10_proactive(
c4,
name("Validate All Inputs"), 
"c4 description"
).

owasp_top10_proactive(
c5,
name("Implement Identity and Authentication Controls"), 
"c5 description"
).

owasp_top10_proactive(
c6,
name("Implement Appropriate Access Controls"), 
"c6 description"
).

owasp_top10_proactive(
c7,
name("Protect Data"), 
"c7 description"
).

owasp_top10_proactive(
c8,
name("Implement Logging and Intrusion Detection"), 
"c8 description"
).

owasp_top10_proactive(
c9,
name("Leverage Security Frameworks and Libraries"), 
"c9 description"
).

owasp_top10_proactive(
c10,
name("Error and Exception Handling"), 
"c10 description"
).



