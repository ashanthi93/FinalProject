:- [rules].

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


stride_defensive(
s1,
name("Authentication"),
"Credentials and authentication tokens are protected with encryption in storage and transit . Protocols are resistant to brute force , dictionary , and replay attacks .
Strong password policies are enforced . Trusted server authentication is used instead of SQL authentication . Passwords are stored with salted hashes .
Password resets do not reveal password hints and valid usernames . Account lockouts do not result in a denial of service attack .").

stride_defensive(
s2,
name("Authorization"),
"Strong ACLs are used for enforcing authorized access to resources . Role-based access control are used to restrict access to specific operations . The system follows the principle of least privilege for user and service accounts .
Privilege separation is correctly configured within the presentation , business and data access layers .").

stride_defensive(
s3,
name("Configuration Management"),
"Standard encryption algorithms and correct key sizes are being used . Hashed message authentication codes -LRB- HMACs -RRB- are used to protect data integrity .
Secrets -LRB- eg:- keys , confidential data -RRB- are cryptographically protected both in transport and in storage . Built-in secure storage is used for protecting keys .
No credentials and sensitive data are sent in clear text over the wire .").

stride_defensive(
s4,
name("Data Protection in Storage and Transit"),
"Standard encryption algorithms and correct key sizes are being used . Hashed message authentication codes -LRB- HMACs -RRB- are used to protect data integrity .
Secrets -LRB- eg:- keys , confidential data -RRB- are cryptographically protected both in transport and in storage . Built-in secure storage is used for protecting keys .
No credentials and sensitive data are sent in clear text over the wire .").

stride_defensive(
s5,
name("Data validation/ Parameter validation"),
"Data type , format , length , and range checks are enforced . All data sent from the client is validated . No security decision is based upon parameters -LRB- eg:- URL parameters -RRB- that can be manipulated .
Input filtering via white list validation is used . Output encoding is used .").

stride_defensive(
s6,
name("Error Handling and Exception Management"),
"All exceptions are handled in a structured manner . Privileges are restored to the appropriate level in case of errors and exceptions .
Error messages are scrubbed so that no sensitive information is revealed to the attacker .").

stride_defensive(
s7,
name("User and Session Management"),
"No sensitive information is stored in clear text in the cookie . The contents of the authentication cookies is encrypted .
Cookies are configured to expire . Sessions are resistant to replay attacks . Secure communication channels are used to protect authentication cookies .
User is forced to re-authenticate when performing critical functions . Sessions are expired at logout .").

stride_defensive(
s8,
name("Auditing and Logging"),
"Sensitive information -LRB- eg:- passwords , PII -RRB- is not logged . Access control -LRB- eg:- ACLs -RRB- are enforced on log files to prevent un-authorized access .
Integrity control -LRB- eg:- signatures -RRB- are enforced on log files to provide non-repudiation . Log files provide for audit trail for sensitive operations and logging of key events .
Auditing and logging is enabled across the tiers on multiple servers .").