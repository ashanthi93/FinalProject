package org.sse.association.semantic;

import org.sse.association.semantic.model.SemanticAssociation;
import org.sse.source.model.BugControl;
import org.sse.design.model.ThreatControl;

public abstract class SemanticAssociationCaller {

    /**
     *
     * @param para1
     * @param para2
     * @return
     */
    public abstract Double getSemanticSimilarity(String para1, String para2) throws RuntimeException;

    /**
     *
     * @param threatControl
     * @param bugControl
     * @param semanticSimilarity
     * @return
     */
    public SemanticAssociation createSemanticAssociation(ThreatControl threatControl, BugControl bugControl, Double semanticSimilarity) {

        SemanticAssociation semanticAssociation = new SemanticAssociation();

        semanticAssociation.setThreatControl(threatControl);
        semanticAssociation.setBugControl(bugControl);
        semanticAssociation.setsemanticSimilarity(semanticSimilarity);

        return semanticAssociation;
    }
}
