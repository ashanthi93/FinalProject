package association.report;
import association.model.Association;

import java.util.ArrayList;

public class AssociationReport {

    private ArrayList<Association> associationList;

    public AssociationReport() {}

    /* getters & setters */
    public ArrayList<Association> getAssociationList() {
        return associationList;
    }

    public void setAssociationList(ArrayList<Association> associationList) {
        this.associationList = associationList;
    }

    @Override
    public String toString() {

        String output = "Association Report { ";

        Association finalAssociation = associationList.get(associationList.size()-1);

        for (Association association : associationList){
            output += association.toString();

            if (finalAssociation != association){
                output += " , \n ";
            }else {
                output += " } ";
            }
        }
        return (output);
    }
}
