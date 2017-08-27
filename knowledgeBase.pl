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


