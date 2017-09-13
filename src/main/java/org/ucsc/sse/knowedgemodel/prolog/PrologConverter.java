package org.ucsc.sse.knowedgemodel.prolog;

import org.jpl7.Query;

public class PrologConverter {
    public void prologCaller(String x){
        String s1 = String.format("consult('src/main/resources/prolog/knowledgeBase.pl').");
        Query q1 = new Query(s1);
        System.out.println("Query Loaded " + (q1.hasSolution() ? "Success" : "Failed"));

        String s2 = "owasp('" + x +"').";
        System.out.println(s2);
        Query q2 = new Query(s2);

        q2.open();
        q2.getSolution();
    }
}
