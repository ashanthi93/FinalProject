owasp(X,Bug_type):-
	owasp_top10(
		Bug_type,
		_,
		X
	).

get_mitigation_for_bug(X, Proactive) :-
	owasp_top10_proactive(
		Proactive,
		_,
		X
	).

remove_frame(A):-
	owasp_top10_proactive(
		A,
		_,
		_
	),
	retract(owasp_top10_proactive(A,_,_)).