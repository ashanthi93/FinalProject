package org.sse.reportparser;

import org.sse.reportparser.design.ThreatReportParser;
import org.sse.reportparser.design.concrete.MsThreatReportParser;
import org.sse.reportparser.source.BugReportParser;
import org.sse.reportparser.source.concrete.ZonarBugReportParser;

public class ReportParserFactory {

    private ReportParserFactory(){}

    /**
     *
     * @return
     */
    public static ThreatReportParser getThreatReportParser(){

        ThreatReportParser threatReportParser = new MsThreatReportParser();

        return threatReportParser;
    }

    /**
     *
     * @return
     */
    public static BugReportParser getBugReportParser(){

        BugReportParser bugReportParser = new ZonarBugReportParser();

        return bugReportParser;
    }
}
