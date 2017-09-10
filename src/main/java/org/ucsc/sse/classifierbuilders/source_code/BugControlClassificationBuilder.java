package org.ucsc.sse.classifierbuilders.source_code;

import org.ucsc.sse.datamodels.source_code.BugControl;
import org.xml.sax.SAXException;
import org.ucsc.sse.knowedgemodel.settings.bugmodelconfigs.BugControlConfig;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BugControlClassificationBuilder {

    HashMap<Integer, BugControl> bugControlsWithDescriptionHashMap;

    public BugControlClassificationBuilder(){
        bugControlsWithDescriptionHashMap = new HashMap<Integer, BugControl>();
    }

    public void createBugControlsWithDescription() throws ParserConfigurationException, SAXException, IOException {

        BugControlConfig proactives = new BugControlConfig();

        ArrayList<String[]> proactives_list = proactives.loadConfigFile();

        for (String[] proactive : proactives_list){

            BugControl bugControlWithDescription = this.createBugControlWithDescription(proactive[0], proactive[1], proactive[2]);

            int key = Integer.parseInt(proactive[0].substring(1));

            bugControlsWithDescriptionHashMap.put(key, bugControlWithDescription);
        }
    }

    public HashMap<Integer, BugControl> getBugControlsWithDescription() throws IOException, SAXException, ParserConfigurationException {

        if (bugControlsWithDescriptionHashMap.isEmpty()){
            this.createBugControlsWithDescription();
        }
        return bugControlsWithDescriptionHashMap;
    }

    private BugControl createBugControlWithDescription(String id, String name, String description){

        BugControl control = new BugControl();
        control.setId(id);
        control.setName(name);
        control.setDescription(description);

        return control;
    }
}
