package org.sse.knowedgemodel.prolog;

import org.jpl7.Query;
import org.jpl7.Term;
import org.sse.design.ThreatExtractor;
import org.sse.design.model.Threat;
import org.sse.source.model.BugControl;

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

    public String getPreventionTechniques(String threat){

        String rule = "get_prevention_for_threat(X,'" + threat + "').";
        Query q = new Query(rule);
        System.out.println(q);
        q.open();
        String solution = "";
        while (q.hasMoreSolutions()){
            String sol = q.nextSolution().toString();
            sol=sol.replace("{X='","").replace("'}","");
            solution = solution +" "+ sol;
        }
        System.out.println(solution);
        return " ";
    }

    public String getMitigationTechniques(String bug) {

        //List<Threat> a =ThreatExtractor.getInstance().getAllThreats();

        String rule = "get_mitigation_techniques(X,'" + bug + "').";
        Query q = new Query(rule);
        System.out.println(q);
        q.open();
        String solution = "";
        while (q.hasMoreSolutions()){
            String sol = q.nextSolution().toString();
            sol=sol.replace("{X='","").replace("'}","");
            solution = solution +" "+ sol;
        }
        System.out.println(solution);
        return " ";

    }

}
