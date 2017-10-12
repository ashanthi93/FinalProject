package org.sse.source;

import org.dom4j.DocumentException;
import org.sse.source.model.BugControl;
import org.sse.settings.config.source.control.BugControlConfig;

import java.util.HashMap;
import java.util.List;

public class BugControlsLoader {

    HashMap<Integer, BugControl> bugControlsWithDescriptionHashMap;

    public BugControlsLoader(){
        bugControlsWithDescriptionHashMap = new HashMap<Integer, BugControl>();
    }

    /**
     *
     * @return
     * @throws DocumentException
     */
    public HashMap<Integer, BugControl> getBugControlsWithDescription() throws DocumentException {

        if (bugControlsWithDescriptionHashMap.isEmpty()){
            this.createBugControlsWithDescription();
        }
        return bugControlsWithDescriptionHashMap;
    }

    /**
     *
     * @throws DocumentException
     */
    private void createBugControlsWithDescription() throws DocumentException {

        List<BugControl> bugControlList = BugControlConfig.loadConfigFile();

        for (BugControl bugControl : bugControlList){
            int key = Integer.parseInt(bugControl.getId().substring(1));
            bugControlsWithDescriptionHashMap.put(key, bugControl);
        }
    }
}
