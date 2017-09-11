package org.ucsc.sse.knowedgemodel.settings;

import org.xml.sax.SAXException;
import org.ucsc.sse.knowedgemodel.settings.threatmodelconfigs.AttackerPerspectiveConfig;
import org.ucsc.sse.knowedgemodel.settings.threatmodelconfigs.DefensivePerspectiveConfig;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class ThreatControlOverlayCreator {

    private ArrayList<String> attackerThreatControlList = new ArrayList<String>();
    private ArrayList<String> defensiveThreatControlList = new ArrayList<String>();
    private ArrayList<String[]> threatControlList = new ArrayList<String[]>();

    private static ThreatControlOverlayCreator instance;

    private ThreatControlOverlayCreator() {}

    static{
        try{
            instance = new ThreatControlOverlayCreator();
        }catch (Exception ex){
            throw new RuntimeException("Exception occurred in creating a singleton instance");
        }
    }

    public static ThreatControlOverlayCreator getInstance(){
        return instance;
    }

    /**
     *
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private void loadThreatControlLists() throws ParserConfigurationException, SAXException, IOException {

        AttackerPerspectiveConfig attackerPerspectiveConfig = new AttackerPerspectiveConfig();
        attackerThreatControlList = attackerPerspectiveConfig.loadThreatControls();

        DefensivePerspectiveConfig defensivePerspectiveConfig = new DefensivePerspectiveConfig();
        defensiveThreatControlList = defensivePerspectiveConfig.loadThreatControls();
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public ArrayList<String[]> createThreatControlsOverlayList() throws IOException, SAXException, ParserConfigurationException {

        loadThreatControlLists();

        int idNum = 1;

        for (String attackerThreatControl : attackerThreatControlList) {

            String id = "TC" + idNum;

            String[] threatControl = new String[3];

            threatControl[0] = id;
            threatControl[1] = attackerThreatControl;
            threatControl[2] = "A";

            threatControlList.add(threatControl);

            idNum++;
        }

        for (String defensiveThreatControl : defensiveThreatControlList) {

            int existingIndex = checkThreatControlExists(defensiveThreatControl);

            if (existingIndex == -1) {

                String id = "TC" + idNum;

                String[] threatControl = new String[3];

                threatControl[0] = id;
                threatControl[1] = defensiveThreatControl;
                threatControl[2] = "D";

                threatControlList.add(threatControl);

                idNum++;

            }else{
                String[] threatControl = threatControlList.get(existingIndex);
                threatControl[2] = "AD";

                threatControlList.remove(existingIndex);
                threatControlList.add(existingIndex, threatControl);
            }
        }
        return threatControlList;
    }

    /**
     *
     * @param threatControlName
     * @return
     */
    private int checkThreatControlExists(String threatControlName) {

        int i = 0;
        for (String[] threatControl : threatControlList) {
            if (threatControl[1].equals(threatControlName)) {
                return (i);
            }
            i++;
        }
        return (-1);
    }
}
