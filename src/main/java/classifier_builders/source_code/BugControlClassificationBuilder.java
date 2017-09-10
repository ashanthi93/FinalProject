package classifier_builders.source_code;

import data_models.source_code.BugControl;
import org.xml.sax.SAXException;
import knowedge_model.settings.bugmodel_configs.OWASPProactiveConfig;

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

        OWASPProactiveConfig proactives = new OWASPProactiveConfig();

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
