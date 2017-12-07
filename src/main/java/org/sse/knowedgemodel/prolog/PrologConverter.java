package org.sse.knowedgemodel.prolog;

import org.jpl7.Query;
import org.jpl7.Term;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PrologConverter {

    String s1 = String.format("consult('src/main/resources/prolog/knowledgeBase.pl').");
    Query q1 = new Query(s1);


    public void prologCaller(String x) {

        System.out.println("Query Loaded " + (q1.hasSolution() ? "Success" : "Failed"));
        String s2 = "owasp(X,'" + x + "').";
        String s3 = "get_mitigation_for_bug(X,'" + x + "').";
        System.out.println(s2);
        Query q2 = new Query(s2);
        Query q3 = new Query(s3);


        while (q2.hasNext()) {
            //get a mapping of variables to instantiations:

            Map<String, Term> map = q2.next();
            //get the instance of variable X

            Term varX []= ((Term) map.get("X")).toTermArray();

            for (int i = 0; i <varX.length ; i++) {
                System.out.print(varX[i] + " ");
                System.out.println();
            }

        }



        /*while(q2.hasMoreSolutions()){
            Map solution = q2.nextSolution();

            //System.out.println(solution.get("Z"));

        }*/
    }

    public List<String> getMitigationTechniques(String threat){

        System.out.println("Query Loaded " + (q1.hasSolution() ? "Success" : "Failed"));
        String rule = "get_mitigation_techniques(X,'" + threat + "').";
        Query q = new Query(rule);
        System.out.println(q);
        q.open();
        String solution = "";
        while (q.hasMoreSolutions()){
            String sol = q.nextSolution().toString();
            sol=sol.replace("{X='","").replace("'}","").replace("\n", "");
            solution = solution +" "+ sol;
        }
        List<String> mitigations = Arrays.asList(solution.split("[.]"));

        /*for (String x : mitigations) {
            System.out.println(x);
        }*/

        return mitigations;
    }

    public List<String> getPreventionTechniques(String bug) {

        System.out.println("Query Loaded " + (q1.hasSolution() ? "Success" : "Failed"));
        String rule = "get_prevention_techniques(X,'" + bug + "').";
        Query q = new Query(rule);
        System.out.println(q);
        q.open();
        String solution = "";
        while (q.hasMoreSolutions()){
            String sol = q.nextSolution().toString();
            sol=sol.replace("{X='","").replace("'}","").replace("\n", "");
            solution = solution +" "+ sol;
        }
        List<String> preventions = Arrays.asList(solution.split("[.]"));

        /*for (String x : preventions) {
            System.out.println(x);
        }*/
        return preventions;

    }



}
