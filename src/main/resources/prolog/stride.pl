stride(
spoofing,
s,
security_control("Authentication"),
[s1]).

stride(
tampering,
t,
security_control("Integrity"),
[s9,s4]).

stride(
repudiation,
r,
security_control("Non-repudiation"),
[s10]).

stride(
information_disclosure,
i,
security_control("Confidentiality"),
[s11]).

stride(
denial_of_service,
d,
security_control("Availability"),
[s12]).

stride(
elevation_of_privilege,
e,
security_control("Authorization"),
[s2,s3]).