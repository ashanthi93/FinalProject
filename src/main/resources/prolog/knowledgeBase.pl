:- [rules].
:- [stride].

owasp_top10(
a1,
name("Injection"),
[c2]).
owasp_top10(
a2,
name("Broken Authentication and Session Management"),
[c5]).
owasp_top10(
a3,
name("Cross-Site Scripting (XSS)"),
[c3]).
owasp_top10(
a4,
name("Insecure Direct Object References"),
[c5]).
owasp_top10(
a5,
name("Security Misconfiguration"),
[c8]).
owasp_top10(
a6,
name("Sensitive Data Exposure"),
[c7]).
owasp_top10(
a7,
name("Missing Function Level Access Control"),
[c5]).
owasp_top10(
a8,
name("Cross-Site Request Forgery (CSRF)"),
[c8]).
owasp_top10(
a9,
name("Using Components with Known Vulnerabilities"),
[c8]).
owasp_top10(
a10,
name("Unvalidated Redirects and Forwards"),
[c4]).


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