package org.sse.association.semantic.model;

import org.sse.source.model.BugControl;
import org.sse.design.model.ThreatControl;

public class SemanticAssociation {

    private ThreatControl threatControl;
    private BugControl bugControl;
    private double semanticSimilarity;

    public SemanticAssociation() {
    }

    /* getters & setters */
    public ThreatControl getThreatControl() {
        return threatControl;
    }

    public void setThreatControl(ThreatControl threatControl) {
        this.threatControl = threatControl;
    }

    public BugControl getBugControl() {
        return bugControl;
    }

    public void setBugControl(BugControl bugControl) {
        this.bugControl = bugControl;
    }

    public double getSemanticSimilarity() {
        return semanticSimilarity;
    }

    public void setSemanticSimilarity(double semanticSimilarity) {
        this.semanticSimilarity = semanticSimilarity;
    }

    @Override
    public String toString() {
        String value = threatControl.getName() + " - " + bugControl.getName() + " : " + semanticSimilarity;
        return (value);
    }
}
