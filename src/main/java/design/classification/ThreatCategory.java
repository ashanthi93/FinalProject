package design.classification;

import design.model.Threat;

import java.util.ArrayList;

public abstract class ThreatCategory {

   protected int id = 0;
   protected ArrayList<Threat> threatList;
   protected String securityControl;
   protected ArrayList<String> mitigationList;

   public abstract ArrayList<Threat> getThreatList();

   public abstract void setThreatList(ArrayList<Threat> threatList);

   public abstract String getSecurityControl();

   public abstract void setSecurityControl(String securityControl);

   public abstract ArrayList<String> getMitigationList();

   public abstract void setMitigationList(ArrayList<String> mitigationList);

}
