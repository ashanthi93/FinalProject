package org.sse.settings.config.source.mapping;

import org.dom4j.DocumentException;
import org.sse.settings.config.source.BugModelConfig;
import org.sse.settings.config.source.control.BugControlConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MappingCreator {

    private static HashMap<String, List<String>> mapping;
    private static HashMap<String, String> bugCategoryIdAndName;
    private static HashMap<String, String> bugControlIdAndName;

    static {
        try {
            mapping = new HashMap<String, List<String>>();
            bugCategoryIdAndName = BugModelConfig.loadBugCategoryIdsAndNames();
            bugControlIdAndName = BugControlConfig.loadControlIdsAndNames();

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    private MappingCreator() {
    }

    /**
     * @param mappingByNames
     * @throws IOException
     */
    public static void create(HashMap<String, List<String>> mappingByNames) throws IOException {

        for (String bugCategoryName : mappingByNames.keySet()) {

            String bugCategoryId = getBugCategoryId(bugCategoryName);

            List<String> bugControlNames = mappingByNames.get(bugCategoryName);
            List<String> bugControlIds = new ArrayList<String>();

            for (String bugControlName : bugControlNames) {
                bugControlIds.add(getBugControlId(bugControlName));
            }

            mapping.put(bugCategoryId, bugControlIds);
        }

        MappingConfig.createFile(mapping);
    }

    /**
     * @param bugCategoryName
     * @return
     */
    private static String getBugCategoryId(String bugCategoryName) {

        for (String bugCategoryId : bugCategoryIdAndName.keySet()) {

            if (bugCategoryIdAndName.get(bugCategoryId).equalsIgnoreCase(bugCategoryName)) {
                return bugCategoryId;
            }
        }

        throw new RuntimeException("Invalid Bug Category Name");
    }

    /**
     * @param bugControlName
     * @return
     */
    private static String getBugControlId(String bugControlName) {

        for (String bugControlId : bugControlIdAndName.keySet()) {

            if (bugControlIdAndName.get(bugControlId).equalsIgnoreCase(bugControlName)) {
                return bugControlId;
            }
        }

        throw new RuntimeException("Invalid bug Control Name");
    }
}
