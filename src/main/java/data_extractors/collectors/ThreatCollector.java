package data_extractors.collectors;

import data_extractors.collectors.report_parsers.ThreatReportParser;
import data_models.design.Interaction;
import data_models.design.Threat;
import data_models.design.ThreatModel;

import java.io.File;
import java.util.ArrayList;

public class ThreatCollector {

    private ThreatModel threatModel;
    private ArrayList<Interaction> interactionArrayList;
    private ArrayList<Threat> threatArrayList;

    public ThreatCollector() {
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
     *
     *
     * @param xmlFile
     */
    public void readFile(File xmlFile) {

        ThreatReportParser threatReportParser = new ThreatReportParser();
        /*
        * This file should read xml and
        * generate ThreatModel, Interaction and Threats
        */
    }

    /**
     * @param threatModelID
     * @param diagramName
     */
    private void createThreatModel(String threatModelID, String diagramName) {

        threatModel.setId(threatModelID);
        threatModel.setDiagramName(diagramName);
    }

    /**
     * Create interaction object, then add into global interaction arrayList of ThreatModel
     * and then return the interaction object.
     *
     * @param interactionName
     * @param threatArrayList arrayList containing threats of the interaction
     * @return interaction object
     */
    private Interaction createInteraction(String interactionName, ArrayList<Threat> threatArrayList) {

        /* create interaction object */
        Interaction interaction = new Interaction();

        interaction.setName(interactionName);
        interaction.setThreats(threatArrayList);

        //add interaction object to interactionArrayList
        interactionArrayList.add(interaction);

        return interaction;
    }

    /**
     * @param threatId
     * @param threatName
     * @param threatCategoryName
     * @param description
     * @return
     */
    private Threat createThreat(String threatId, String threatName, String threatCategoryName, String description) {

        Threat threat = new Threat();

        threat.setId(threatId);
        threat.setName(threatName);
        threat.setThreatCategoryName(threatCategoryName);
        threat.setDescription(description);

        threatArrayList.add(threat);

        return threat;
    }

    /**
     *
     */
    private void setInteractionArrayListToThreatModel(){
        threatModel.setInteractions(interactionArrayList);
    }

    /**
     *
     * @param interaction
     * @param threatArrayList
     */
    private void setThreatArrayListToInteraction(Interaction interaction, ArrayList<Threat> threatArrayList){
        interaction.setThreats(threatArrayList);
    }
}
