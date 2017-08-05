package design.classification;

import design.model.Threat;

/**
 * Created by Ashi on 8/1/2017.
 * ThreatCategory instead of STRIDECategory
 * SpoofingThreatCategory implements ThreatCategory
 */
public interface ThreatCategory {

   public int id = 0; //make public or static final?? (All the variables)
   static final Threat[] threatList = null;
   static final String securityControl = "";
   static final String Mitigation = "";

   public void getThreatList();

   public void addThreat(Threat threat);

}
