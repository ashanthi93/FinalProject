owasp(Bug_type):-
	owasp_top10(
		Bug_type,
		_,
		Y
	),
	length(Y,A),
	testloop(0,A,Y).

testloop(N, Length, List):- 
	N<Length, 
	nth0(N,List,B),
	get_proactive_description(B), nl, 
	M is N+1, 
	testloop(M,Length,List).

get_proactive_description(Name):-
	owasp_top10_proactive(
		Name,
		_,
		Y
		),
	write(Y).

remove_frame(A):-
	owasp_top10_proactive(
		A,
		_,
		_
	),
	retract(owasp_top10_proactive(A,_,_)).