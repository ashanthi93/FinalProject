package org.sse.reportparser.source.concrete;

import org.sse.reportparser.source.BugReportParser;
import org.sse.source.model.Bug;

import java.util.List;

public class ZonarBugReportParser extends BugReportParser{

    public ZonarBugReportParser(){}

    public boolean validateFile() throws Exception {
        return false;
    }

    public String extractName() throws Exception {
        return null;
    }

    public List<Bug> extractBugs() throws Exception {
        return null;
    }
}
