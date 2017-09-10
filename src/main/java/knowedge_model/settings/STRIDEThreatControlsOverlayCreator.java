package knowedge_model.settings;

import org.xml.sax.SAXException;
import knowedge_model.settings.threatmodel_configs.STRIDEAttackerConfig;
import knowedge_model.settings.threatmodel_configs.STRIDEDefensiveConfig;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class STRIDEThreatControlsOverlayCreator {

    private ArrayList<String> attackerThreatControlList = new ArrayList<String>();
    private ArrayList<String> defensiveThreatControlList = new ArrayList<String>();
    private ArrayList<String[]> threatControlList = new ArrayList<String[]>();

    private static STRIDEThreatControlsOverlayCreator instance;

    private STRIDEThreatControlsOverlayCreator() {}

    static{
        try{
            instance = new STRIDEThreatControlsOverlayCreator();
        }catch (Exception ex){
            throw new RuntimeException("Exception occured in creating a singleton instance");
        }
    }

    public static STRIDEThreatControlsOverlayCreator getInstance(){
        return instance;
    }

    private void loadThreatControlLists() throws ParserConfigurationException, SAXException, IOException {

        STRIDEAttackerConfig strideAttackerConfig = new STRIDEAttackerConfig();
        attackerThreatControlList = strideAttackerConfig.loadThreatControls();

        STRIDEDefensiveConfig strideDefensiveConfig = new STRIDEDefensiveConfig();
        defensiveThreatControlList = strideDefensiveConfig.loadThreatControls();
    }

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
