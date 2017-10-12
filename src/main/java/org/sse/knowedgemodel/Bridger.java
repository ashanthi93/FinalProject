package org.sse.knowedgemodel;

import org.dom4j.DocumentException;
import org.sse.source.model.BugControl;
import org.sse.design.model.ThreatControl;
import org.sse.settings.config.design.control.ThreatControlConfig;
import org.sse.settings.config.source.control.BugControlConfig;

import java.util.List;

public class Bridger {

    private static List<ThreatControl> threatControlList;
    private static List<BugControl> bugControlList;

    static {
        try {
            threatControlList = ThreatControlConfig.loadConfigFile();
            bugControlList = BugControlConfig.loadConfigFile();

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    private Bridger(){}
}
