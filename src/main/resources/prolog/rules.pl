
owasp(X,Bug_type):-
	owasp_top10(
		Bug_type,
		_,
		X
	).

get_mitigation_techniques(S,Bc) :-
	owasp_top10(Bc,_,Array),
	member(X,Array),
	nl,
	get_mitigation_for_bug(S,X).

get_mitigation_for_bug(X, Proactive) :-
	owasp_top10_proactive(
		Proactive,
		_,
		X
	).

get_prevention_techniques(S,Tc) :-
	stride(Tc,_,_,Array),
	member(X,Array),
	nl,
	get_prevention_for_threat(S,X).

get_prevention_for_threat(S,X) :-
	stride_defensive(X,_,S).
	

remove_frame(A):-
	owasp_top10_proactive(
		A,
		_,
		_
	),
	retract(owasp_top10_proactive(A,_,_)).
