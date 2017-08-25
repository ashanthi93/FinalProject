package association;

import association.model.Association;
import association.report.AssociationReport;
import design.classification.ThreatCategory;
import source.classification.BugCategory;

import java.util.HashMap;

/**
 * Not completed
 * */
public class AssociationBuilder {

    private HashMap<BugCategory, char[]> OWASP_STRIDE_mapping;

    private ThreatCategory spoofingThreatCategory;
    private ThreatCategory tamperingThreatCategory;
    private ThreatCategory repudiationThreatCategory;
    private ThreatCategory informationDisclosureThreatCategory;
    private ThreatCategory dosThreatCategory;
    private ThreatCategory eopThreatCategory;

    private Association associationBySpoofing = null;
    private Association associationByTampering = null;
    private Association associationByRepudiation = null;
    private Association associationByInformationDisclosure = null;
    private Association associationByDos = null;
    private Association associationByEop = null;

    public AssociationBuilder(HashMap<BugCategory, char[]> OWASP_STRIDE_mapping, ThreatCategory spoofingThreatCategory, ThreatCategory tamperingThreatCategory, ThreatCategory repudiationThreatCategory, ThreatCategory informationDisclosureThreatCategory, ThreatCategory dosThreatCategory, ThreatCategory eopThreatCategory) {
        this.OWASP_STRIDE_mapping = OWASP_STRIDE_mapping;
        this.spoofingThreatCategory = spoofingThreatCategory;
        this.tamperingThreatCategory = tamperingThreatCategory;
        this.repudiationThreatCategory = repudiationThreatCategory;
        this.informationDisclosureThreatCategory = informationDisclosureThreatCategory;
        this.dosThreatCategory = dosThreatCategory;
        this.eopThreatCategory = eopThreatCategory;
    }

    public AssociationReport generateAssociationReport() throws Exception {

        AssociationReport associationReport = new AssociationReport();

        for (BugCategory bugCategory : OWASP_STRIDE_mapping.keySet()) {

            char threatCategoryIDList[] = OWASP_STRIDE_mapping.get(bugCategory);

            for (char threatCategoryID : threatCategoryIDList) {

                Association association = this.getAssociation(threatCategoryID);

                if (association == null) {

                    association = this.createAssociation(threatCategoryID);
                    association.setBugList(bugCategory.getBugList());
                } else {
                    association.addNewBugsToList(bugCategory.getBugList());
                }
            }
        }

        /*
        * associations must be add to AssociationReport
        * */

        return associationReport;
    }

    private Association getAssociation(char threatCategoryID) throws Exception {

        switch (threatCategoryID) {
            case 'S':
                return (associationBySpoofing);
            case 'T':
                return (associationByTampering);
            case 'R':
                return (associationByRepudiation);
            case 'I':
                return (associationByInformationDisclosure);
            case 'D':
                return (associationByDos);
            case 'E':
                return (associationByEop);
            default:
                throw new Exception("Undefined Character");
                /* Exception should be change */
        }
    }

    private Association createAssociation(char threatCategoryID) throws Exception {

        switch (threatCategoryID) {
            case 'S':
                associationBySpoofing = new Association("Spoofing");
                associationBySpoofing.setThreatList(spoofingThreatCategory.getThreatList());

                return (associationBySpoofing);
            case 'T':
                associationByTampering = new Association("Tampering");
                associationByTampering.setThreatList(tamperingThreatCategory.getThreatList());

                return (associationByTampering);
            case 'R':
                associationByRepudiation = new Association("Repudiation");
                associationByRepudiation.setThreatList(repudiationThreatCategory.getThreatList());

                return (associationByRepudiation);
            case 'I':
                associationByInformationDisclosure = new Association("Information Disclosure");
                associationByInformationDisclosure.setThreatList(informationDisclosureThreatCategory.getThreatList());

                return (associationByInformationDisclosure);
            case 'D':
                associationByDos = new Association("DOS");
                associationByDos.setThreatList(dosThreatCategory.getThreatList());

                return (associationByDos);
            case 'E':
                associationByEop = new Association("EOP");
                associationByEop.setThreatList(eopThreatCategory.getThreatList());

                return (associationByEop);
            default:
                throw new Exception("Undefined Charachter");
        }
    }
}
