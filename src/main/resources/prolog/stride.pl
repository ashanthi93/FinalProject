stride(
spoofing,
security_control("Authentication"),
[d1]).

stride(
tampering,
security_control("Integrity"),
[d6]).

stride(
repudiation,
security_control("Non-repudiation"),
[d2]).

stride(
information_disclosure,
security_control("Confidentiality"),
[d8]).

stride(
denial_of_service,
security_control("Availability"),
[d3,d7]).

stride(
elevation_of_privilege,
security_control("Authorization"),
[d3,d4,d5]).



stride_defensive(
d1,
name("Authentication")).

stride_defensive(
d2,
name("Authorization")).

stride_defensive(
d3,
name("Configuration Management")).

stride_defensive(
d4,
name("Data Protection in Storage and Transit")).

stride_defensive(
d5,
name("Data validation/ Parameter validation")).

stride_defensive(
d6,
name("Error Handling and Exception Management")).

stride_defensive(
d7,
name("User and Session Management")).

stride_defensive(
d8,
name("Auditing and Logging")).