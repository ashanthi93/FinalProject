package org.sse.association.semantic;

import org.sse.association.semantic.model.SemanticAssociation;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SemanticAssociationsLoaderTest {
    @Test
    public void testCreateSemanticAssociations() throws Exception {

        List<SemanticAssociation> semanticAssociations = SemanticAssociationsLoader.createSemanticAssociations();

        for (SemanticAssociation sa : semanticAssociations){

            System.out.println(sa);
        }
    }

}