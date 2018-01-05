package org.sse.outputgenerators.report.creator;

import org.sse.association.model.Association;
import org.sse.outputgenerators.report.model.AssociationReport;
import org.sse.design.model.ThreatCategory;
import org.xml.sax.SAXException;
import org.sse.source.model.BugCategory;
import org.sse.source.model.Bug;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AssociationReportCreator {

    private List<Association> associationList;

    public AssociationReportCreator(List<Association> associationList) {
        this.associationList = associationList;
    }

    /**
     * Returns AssociationReport object that can be serializable.
     *
     * @return the AssociationReport
     */
    public AssociationReport generateReport(String reportName) throws IOException, SAXException, ParserConfigurationException {

        AssociationReport associationReport = new AssociationReport();

        associationReport.setName(reportName);
        associationReport.setDate(this.getDate());

        associationReport.setAssociationList(associationList);

        return associationReport;
    }

    /**
     *
     *
     * @return
     */
    private String getDate(){

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date = new Date();

        return (dateFormat.format(date));
    }
}
