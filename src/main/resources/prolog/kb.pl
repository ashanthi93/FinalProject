:- [owasp].
:- [stride].
:- [similaritymatch].

isCausedByThreatCategories(BugCategory,TList_Unique) :- findall(T,
												  			isCausedByThreatCategory(BugCategory,T),
												  			TList),
														sort(TList,TList_Unique).

isCausedByThreatCategory(BugCategory,T) :- lacksProactive(BugCategory,P),
											mapsToSecurityControl(P,S),
											isWeakendByThreatCategory(S,T).

% ----------------------- %
% rules to find proactive %
% ----------------------- %

lacksProactive(BugCategory,C) :- isProactiveListOf(CList,BugCategory), member(C,CList).

isProactiveListOf(CList,BugCategory) :- owasp_top10(BugCategory,_,CList).

% ------------------------------- %
% rules to find security controls %
% ------------------------------- %

mapsToSecurityControl(Proactive,S) :- isMappingSecurityControlListOf(SList,Proactive), member(S,SList).

isMappingSecurityControlListOf(SList,Proactive) :- isMappingSimilarityValuesFor(SimList,Proactive),
												isSecurityControlListOf(SList,Proactive,SimList).

isMappingSimilarityValuesFor(SimList, Proactive) :- isMaxThreeOf(SimList, AllSimilarities),
													isAllSimilarityValuesFor(AllSimilarities,Proactive).

isMaxThreeOf([X,Y,Z],[X,Y,Z|_]).
isAllSimilarityValuesFor(AllSimList_DESC,Proactive) :- findall(Similarity,
														semanticAssociation(Proactive,_,Similarity),
														AllSimList),
													sort(AllSimList,AllSimList_ASC),
													reverse(AllSimList_ASC,AllSimList_DESC).

isSecurityControlListOf(SList,Proactive,SimList) :- findall(S,
													 isASecurityControlOf(S,Proactive,SimList),
													 SList).
isASecurityControlOf(S,Proactive,SimList) :- semanticAssociation(Proactive,S,Similarity),
											member(Similarity, SimList).

% ----------------------------- %
% rules to find threat category %
% ----------------------------- %

isWeakendByThreatCategory(SecurityControl,T) :- stride(_, T, _, SecContList),
													member(SecurityControl, SecContList).