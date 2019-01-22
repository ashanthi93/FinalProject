package org.sse.association.semantic;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UmbcSemanticAssociationCallerTest {

    @Test
    public void testGetSemanticSimilarity() throws Exception {

        SemanticAssociationCaller sm = new UmbcSemanticAssociationCaller();
        double value = sm.getSemanticSimilarity("A small violin is being played by a girl","a child is performing on a tiny instrument");
        System.out.println(value);
    }

}