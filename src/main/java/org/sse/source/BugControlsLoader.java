package org.sse.source;

import org.dom4j.DocumentException;
import org.sse.source.model.BugControl;
import org.sse.settings.config.source.control.BugControlConfig;

import java.util.HashMap;
import java.util.List;

public class BugControlsLoader {

    private static HashMap<Integer, BugControl> bugControlsWithDescriptionHashMap;

    static {
        bugControlsWithDescriptionHashMap = new HashMap<>();
    }

    private BugControlsLoader(){

    }

    /**
     *
     * @return
     * @throws DocumentException
     */
    public static HashMap<Integer, BugControl> getBugControlsWithDescription() throws DocumentException {

        if (bugControlsWithDescriptionHashMap.isEmpty()){
            createBugControlsWithDescription();
        }
        return bugControlsWithDescriptionHashMap;
    }

    public static String getVersionName() throws DocumentException {

        return (BugControlConfig.getVersionTag());
    }

    /**
     *
     * @throws DocumentException
     */
    private static void createBugControlsWithDescription() throws DocumentException {

        List<BugControl> bugControlList = BugControlConfig.loadConfigFile();

        for (BugControl bugControl : bugControlList){
            int key = Integer.parseInt(bugControl.getId().substring(1));
            bugControlsWithDescriptionHashMap.put(key, bugControl);
        }
    }
}
