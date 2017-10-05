package org.sse.settings.config.design.control;

import org.dom4j.DocumentException;
import org.sse.design.model.ThreatControl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThreatControlsCreator {

    private static HashMap<String, ThreatControl> threatControls;

    static {
        threatControls = new HashMap<String, ThreatControl>();
    }

    private ThreatControlsCreator(){
    }

    /**
     *
     *
     * @throws DocumentException
     * @throws IOException
     */
    public static void initialize() throws DocumentException, IOException {

        List<ThreatControl> attackerThreatControls = AttackerControlConfig.loadConfigFile();
        List<ThreatControl> defensiveThreatControls = DefensiveControlConfig.loadConfigFile();

        int idNum = 1;

        for (ThreatControl attackerThreatControl : attackerThreatControls){

            ThreatControl threatControl = new ThreatControl();

            String key = "T" + idNum;

            threatControl.setId(key);
            threatControl.setName(attackerThreatControl.getName());
            threatControl.setDescription(attackerThreatControl.getDescription());
            threatControl.setParentController("A");

            idNum++;

            threatControls.put(key, threatControl);
        }

        for (ThreatControl defensiveThreatControl : defensiveThreatControls){

            ThreatControl threatControl = new ThreatControl();

            String keyOfExistingThreatControl = checkThreatControlExists(defensiveThreatControl.getName());

            if (keyOfExistingThreatControl != null){

                threatControl = threatControls.get(keyOfExistingThreatControl);
                threatControl.setParentController("AD");

                threatControls.put(keyOfExistingThreatControl, threatControl);

            }else{

                String key = "T" + idNum;

                threatControl.setId(key);
                threatControl.setName(defensiveThreatControl.getName());
                threatControl.setDescription(defensiveThreatControl.getDescription());
                threatControl.setParentController("D");

                idNum++;

                threatControls.put(key, threatControl);
            }
        }

        ThreatControlConfig.createFile(new ArrayList<ThreatControl>((threatControls.values())));
    }

    /**
     *
     *
     * @param threatControlName
     * @return
     */
    private static String checkThreatControlExists(String threatControlName) {

        for (ThreatControl threatControl : threatControls.values()) {

            if (threatControl.getName().equals(threatControlName)) {
                return (threatControl.getId());
            }
        }
        return (null);
    }
}
