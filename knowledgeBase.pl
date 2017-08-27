stride(
	spoofing,
	security_control(authentication),
	mitigation_techniques(a,b,c),
	defence(d1)
	).

stride(
	tampering,
	security_control(integrity),
	mitigation_techniques(d,e,f),
	defence(d6)
	).

stride(
	repudiation,
	security_control(non_repudiation),
	mitigation_techniques(g,h,i),
	defence(d2)
	).

stride(
	information_disclosure,
	security_control(confidentiality),
	mitigation_techniques(j,k,l),
	defence(d8)
	).

stride(
	denial_of_service,
	security_control(availability),
	mitigation_techniques(m,n,o),
	defence(d3, d7)
	).

stride(
	elevation_of_priviladge,
	security_control(authorization),
	mitigation_techniques(p,q,r),
	defence(d3, d4, d5)
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
	proactives([c2, c3, c4, c8, c9, c10])
	).

owasp_top10(
	a2,
	name("Broken Authentication and Session Management"),
	proactives(c5, c8, c9, c10)
	).

owasp_top10(
	a3,
	name("Cross-Site Scripting (XSS)"),
	proactives(c3, c4, c8, c9, c10)
	).

owasp_top10(
	a4,
	name("Insecure Direct Object References"),
	proactives(c6, c8, c9, c10)
	).

owasp_top10(
	a5,
	name("Security Misconfiguration"),
	proactives(c8, c9, c10)
	).

owasp_top10(
	a6,
	name("Sensitive Data Exposure"),
	proactives(c7, c8, c9, c10)
	).

owasp_top10(
	a7,
	name("Missing Function Level Access Control"),
	proactives(c6, c8, c9, c10)
	).

owasp_top10(
	a8,
	name("Cross-Site Request Forgery (CSRF)"),
	proactives(c8, c9, c10)
	).

owasp_top10(
	a9,
	name("Using Components with Known Vulnerabilities"),
	proactives(c8, c9, c10)
	).

owasp_top10(
	a10,
	name("Unvalidated Redirects and Forwards"),
	practives(c4, c8, c9, c10)
	).

owasp(X):-
	owasp_top10(
		X,
		Y,
		_
	),
	write(Y).


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

owasp_proactive(X):-
	owasp_top10_proactive(
		X,
		_,
		Z
		),
	write(Z).
