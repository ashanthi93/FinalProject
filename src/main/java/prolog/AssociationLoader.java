package prolog;

import org.jpl7.*;


public class AssociationLoader {

    public void callProlog () {
        String s1 = String.format("consult('src/main/resources/prolog/knowledgeBase.pl').");
        Query q1 = new Query(s1);
        System.out.println("Query Loaded " + (q1.hasSolution() ? "Success" : "Failed"));
    }
}
