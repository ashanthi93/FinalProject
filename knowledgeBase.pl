stride(
	spoofing,
	security_control(authentication),
	mitigation_techniques(a,b,c),
	[d1]
	).

stride(
	tampering,
	security_control(integrity),
	mitigation_techniques(d,e,f),
	[d6]
	).

stride(
	repudiation,
	security_control(non_repudiation),
	mitigation_techniques(g,h,i),
	[d2]
	).

stride(
	information_disclosure,
	security_control(confidentiality),
	mitigation_techniques(j,k,l),
	[d8]
	).

stride(
	denial_of_service,
	security_control(availability),
	mitigation_techniques(m,n,o),
	[d3, d7]
	).

stride(
	elevation_of_priviladge,
	security_control(authorization),
	mitigation_techniques(p,q,r),
	[d3, d4, d5]
	).




stride_defensive(
	d1,
	name("Authentication")
	).

stride_defensive(
	d2,
	name("Authorization")
	).

stride_defensive(
	d3,
	name("Configuration Management")
	).

stride_defensive(
	d4,
	name("Data Protection in Storage and Transit")
	).

stride_defensive(
	d5,
	name("Data validation/ Parameter validation")
	).

stride_defensive(
	d6,
	name("Error Handling and Exception Management")
	).

stride_defensive(
	d7,
	name("User and Session Management")
	).

stride_defensive(
	d8,
	name("Auditing and Logging")
	).



owasp_top10(
	a1,
	name("Injection"),
	[c2, c3, c4, c8, c9, c10]
	).

owasp_top10(
	a2,
	name("Broken Authentication and Session Management"),
	[c5, c8, c9, c10]
	).

owasp_top10(
	a3,
	name("Cross-Site Scripting (XSS)"),
	[c3, c4, c8, c9, c10]
	).

owasp_top10(
	a4,
	name("Insecure Direct Object References"),
	[c6, c8, c9, c10]
	).

owasp_top10(
	a5,
	name("Security Misconfiguration"),
	[c8, c9, c10]
	).

owasp_top10(
	a6,
	name("Sensitive Data Exposure"),
	[c7, c8, c9, c10]
	).

owasp_top10(
	a7,
	name("Missing Function Level Access Control"),
	[c6, c8, c9, c10]
	).

owasp_top10(
	a8,
	name("Cross-Site Request Forgery (CSRF)"),
	[c8, c9, c10]
	).

owasp_top10(
	a9,
	name("Using Components with Known Vulnerabilities"),
	[c8, c9, c10]
	).

owasp_top10(
	a10,
	name("Unvalidated Redirects and Forwards"),
	[c4, c8, c9, c10]
	).

owasp(X):-
	owasp_top10(
		X,
		_,
		Y
	),
	write(Y),
	member(c2,[c7|Y]). /* check c2 is matched with Y's first element. */

	



owasp_top10_proactive(
	c2,
	name("Parameterize Queries"),
	description("c2 description")
	).

owasp_top10_proactive(
	c3,
	name("Encode Data"),
	description("c3 description")
	).

owasp_top10_proactive(
	c4,
	name("Validate All Inputs"),
	description("c4 description")
	).

owasp_top10_proactive(
	c5,
	name("Implement Identity and Authentication Controls"),
	description("c5 description")
	).

owasp_top10_proactive(
	c6,
	name("Implement Appropriate Access Controls"),
	description("c6 description")
	).

owasp_top10_proactive(
	c7,
	name("Protect Data"),
	description("c7 description")
	).

owasp_top10_proactive(
	c8,
	name("Implement Logging and Intrusion Detection"),
	description("c8 description")
	).

owasp_top10_proactive(
	c9,
	name("Leverage Security Frameworks and Libraries"),
	description("c9 description")
	).

owasp_top10_proactive(
	c10,
	name("Error and Exception Handling"),
	description("c10 description")
	).
