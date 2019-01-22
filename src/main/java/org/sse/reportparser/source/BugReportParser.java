package org.sse.reportparser.source;

import org.sse.source.model.Bug;

import java.io.File;
import java.util.List;

public abstract class BugReportParser {

    protected File bugReportFile;

    public void setBugReportFile(File bugReportFile) {
        this.bugReportFile = bugReportFile;
    }

    public File getBugReportFile() {
        return bugReportFile;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public abstract boolean validateFile() throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    public abstract String extractName() throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    public abstract List<Bug> extractBugs() throws Exception;
}
