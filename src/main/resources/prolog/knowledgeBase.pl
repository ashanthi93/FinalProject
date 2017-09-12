:- [rules].
:- [stride].


owasp_top10(
a1,
name("Injection"),
[c1,c2,c3,c4,c8,c9,c10]).
owasp_top10(
a2,
name("Broken Authentication and Session Management"),
[c1,c4,c8,c9,c10]).
owasp_top10(
a3,
name("Cross-Site Scripting (XSS)"),
[c1,c5,c8,c9,c10]).
owasp_top10(
a4,
name("Insecure Direct Object References"),
[c1,c6,c8,c9,c10]).
owasp_top10(
a5,
name("Security Misconfiguration"),
[c1,c8,c9,c10]).
owasp_top10(
a6,
name("Sensitive Data Exposure"),
[c1,c7,c8,c9,c10]).
owasp_top10(
a7,
name("Missing Function Level Access Control"),
[c1,c6,c8,c9,c10]).
owasp_top10(
a8,
name("Cross-Site Request Forgery (CSRF)"),
[c1,c8,c9,c10]).
owasp_top10(
a9,
name("Using Components with Known Vulnerabilities"),
[c1,c8,c9,c10]).
owasp_top10(
a10,
name("Unvalidated Redirects and Forwards"),
[c1,c8,c9,c10]).


owasp_top10_proactive(
c1,
name("Verify for Security Early and Often"), 
"c1 description"
).

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
name("Implement Access Controls"), 
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
name("Errors and exceptions handling"), 
"c10 description"
).



