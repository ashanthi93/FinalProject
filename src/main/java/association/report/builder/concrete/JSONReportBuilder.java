package association.report.builder.concrete;

import association.report.AssociationReport;
import association.report.builder.ReportBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONReportBuilder extends ReportBuilder{

    /**
     *
     *
     * @param associationReport
     * @return
     * @throws JsonProcessingException
     */
    public String convertReport(AssociationReport associationReport) throws JsonProcessingException {

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonOutput = jsonMapper.writeValueAsString(associationReport);

        jsonOutput = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(associationReport);
        System.out.println(jsonOutput);

        return jsonOutput;
    }

    /**
     *
     *
     * @param associationReport
     * @param filePath
     * @throws IOException
     */
    public void convertReportToFile(AssociationReport associationReport, String filePath) throws IOException {

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.writeValue(new File(filePath), associationReport);
    }
}
