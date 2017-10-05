package org.sse.design;

import org.sse.design.model.Interaction;
import org.sse.design.model.Threat;
import org.sse.design.model.ThreatModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThreatModelUtil {

    private ThreatModelUtil() {}

    /**
     * Create ThreatModel object with id and name
     *
     * @param threatModelId
     * @param diagramName
     */
    public static void createThreatModel(ThreatModel threatModel, String threatModelId, String diagramName) {

        threatModel.setId(threatModelId);
        threatModel.setDiagramName(diagramName);
    }

    /**
     * Create interaction objects by using threatArrayList and
     * then add all interaction objects into interactionArrayList & ThreatModel objects
     *
     * @param threatModel
     * @param threatList
     */
    public static void createInteractionsFromThreatListAndSetToThreatModel(ThreatModel threatModel, List<Threat> threatList) {

        HashMap<String, Interaction> interactionHashMap = new HashMap<String, Interaction>();

        int id = 1;

        for (Threat threat : threatList) {

            String interactionName = threat.getInteractionName();
            Interaction interaction;

            if (interactionHashMap.get(interactionName) == null) {

                String interactionId = "I" + id;
                interaction = createInteraction(interactionId, interactionName);
                id++;
            } else {
                interaction = interactionHashMap.get(interactionName);
            }

            List<Threat> threatArrayList = interaction.getThreats();
            threatArrayList.add(threat);

            interaction.setThreats(threatArrayList);

            interactionHashMap.put(interactionName, interaction);
        }

        setInteractionsToThreatModel(threatModel, interactionHashMap);
    }

    /**
     * Get all threats in a threatModel
     *
     * @param threatModel
     * @return
     */
    public static List<Threat> getAllThreatsFromThreatModel(ThreatModel threatModel) {

        List<Threat> threatList = new ArrayList<Threat>();

        for (Interaction interaction : threatModel.getInteractions()) {

            threatList.addAll(interaction.getThreats());
        }

        return threatList;
    }

    /**
     * Create interaction object using id and name
     *
     * @param interactionId
     * @param interactionName
     * @return
     */
    private static Interaction createInteraction(String interactionId, String interactionName) {

        Interaction interaction = new Interaction();

        interaction.setId(interactionId);
        interaction.setName(interactionName);

        return interaction;
    }

    /**
     * Set all interactions in interactionHashMap into threatModel object
     *
     * @param threatModel
     * @param interactionHashMap
     */
    private static void setInteractionsToThreatModel(ThreatModel threatModel, HashMap<String, Interaction> interactionHashMap) {

        List<Interaction> interactionList = new ArrayList<Interaction>();

        for (String interactionName : interactionHashMap.keySet()) {
            interactionList.add(interactionHashMap.get(interactionName));
        }

        threatModel.setInteractions(interactionList);
    }
}
