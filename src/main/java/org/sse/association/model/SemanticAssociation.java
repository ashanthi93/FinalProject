package org.sse.association.model;

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

    public double getsemanticSimilarity() {
        return semanticSimilarity;
    }

    public void setsemanticSimilarity(double semanticSimilarity) {
        this.semanticSimilarity = semanticSimilarity;
    }
}
