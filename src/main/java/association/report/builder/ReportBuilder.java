package association.report.builder;

import association.report.AssociationReport;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public abstract class ReportBuilder {

    /**
     *
     *
     * @param associationReport
     * @return
     * @throws JsonProcessingException
     */
    public abstract String convertReport(AssociationReport associationReport) throws JsonProcessingException;

    /**
     *
     *
     * @param associationReport
     * @param filePath
     * @throws IOException
     */
    public abstract void convertReportToFile(AssociationReport associationReport, String filePath) throws IOException;
}
