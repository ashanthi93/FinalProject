package org.sse.association.semantic;

import org.dom4j.DocumentException;
import org.sse.association.semantic.model.SemanticAssociation;
import org.sse.design.model.ThreatControl;
import org.sse.settings.DescriptionProcessor;
import org.sse.settings.config.design.control.ThreatControlConfig;
import org.sse.settings.config.source.control.BugControlConfig;
import org.sse.source.model.BugControl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SemanticAssociationsLoader {

    private static List<ThreatControl> threatControlList;
    private static List<BugControl> bugControlList;

    static {
        try {
            threatControlList = ThreatControlConfig.loadConfigFile();
            bugControlList = BugControlConfig.loadConfigFile();

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    private SemanticAssociationsLoader() {}

    public static List<SemanticAssociation> createSemanticAssociations() {

        UmbcSemanticAssociationCaller semanticAssociationsBuilder = new UmbcSemanticAssociationCaller();
        List<SemanticAssociation> semanticAssociationList = new ArrayList<SemanticAssociation>();

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        for (ThreatControl threatControl : threatControlList) {

            for (BugControl bugControl : bugControlList) {

                List<String> descriptionPoints = DescriptionProcessor.getSentences(bugControl.getDescription());
                double similaritySum = 0;

                for (String threatControlPoint : threatControl.getDescription()) {

                    for (String bugControlPoint : descriptionPoints) {
                        similaritySum += semanticAssociationsBuilder.getSemanticSimilarity(threatControlPoint, bugControlPoint);
                    }
                }

                int count = (descriptionPoints.size() * threatControl.getDescription().size());

                // round to third decimal point
                double similarity = Double.parseDouble(df.format((similaritySum / count)));

                SemanticAssociation semanticAssociation = new SemanticAssociation();

                semanticAssociation.setThreatControl(threatControl);
                semanticAssociation.setBugControl(bugControl);
                semanticAssociation.setSemanticSimilarity(similarity);

                semanticAssociationList.add(semanticAssociation);
            }
        }
        return semanticAssociationList;
    }
}
