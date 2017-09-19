package org.ucsc.sse.dataextractors.collectors;

import org.ucsc.sse.dataextractors.collectors.report_parsers.ThreatReportParser;
import org.ucsc.sse.datamodels.design.Interaction;
import org.ucsc.sse.datamodels.design.Threat;
import org.ucsc.sse.datamodels.design.ThreatModel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class ThreatCollector {

    private ThreatModel threatModel;
    private ArrayList<Interaction> interactionArrayList;
    private ArrayList<Threat> threatArrayList;

    public ThreatCollector(ArrayList<Threat> threatArrayList) {
        this.threatModel = new ThreatModel();
        this.interactionArrayList = new ArrayList<Interaction>();
        this.threatArrayList = threatArrayList;
    }

    /* getters */
    public ThreatModel getThreatModel() {
        return threatModel;
    }

    public ArrayList<Interaction> getInteractionArrayList() {
        return interactionArrayList;
    }

    public ArrayList<Threat> getThreatArrayList() {
        return threatArrayList;
    }

    /**
     * Create ThreatModel object with id and name
     *
     * @param threatModelId
     * @param diagramName
     */
    public void createThreatModel(String threatModelId, String diagramName) {

        threatModel.setId(threatModelId);
        threatModel.setDiagramName(diagramName);
    }

    /**
     * Create interaction objects by using threatArrayList and
     * then add all interaction objects into interactionArrayList & ThreatModel objects
     */
    public void createInteractionsFromThreats(){

        HashMap<String,Interaction> interactionHashMap = new HashMap<String, Interaction>();

        int id = 1;

        for (Threat threat : threatArrayList){

            String interactionName = threat.getInteractionName();
            Interaction interaction;

            if (interactionHashMap.get(interactionName) == null){

                String interactionId = "I" + id;
                interaction = this.createInteraction(interactionId, interactionName);
                id++;
            }else {
                interaction = interactionHashMap.get(interactionName);
            }

            ArrayList<Threat> threatArrayList = interaction.getThreats();
            threatArrayList.add(threat);

            interaction.setThreats(threatArrayList);
            interactionHashMap.put(interactionName, interaction);
        }

        /* Add all interaction objects into public interactionArrayList */
        for (String interactionName : interactionHashMap.keySet()){

            interactionArrayList.add(interactionHashMap.get(interactionName));
        }

        this.setInteractionArrayListToThreatModel();
    }

    /**
     * Create interaction object using id and name
     *
     * @param interactionId
     * @param interactionName
     * @return
     */
    private Interaction createInteraction(String interactionId, String interactionName) {

        Interaction interaction = new Interaction();

        interaction.setId(interactionId);
        interaction.setName(interactionName);

        return interaction;
    }

    /**
     * Set completed interactionArrayList into ThreatModel object
     */
    private void setInteractionArrayListToThreatModel(){
        threatModel.setInteractions(interactionArrayList);
    }
}
