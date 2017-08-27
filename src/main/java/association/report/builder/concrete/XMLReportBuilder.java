package association.report.builder.concrete;

import association.report.AssociationReport;
import association.report.builder.ReportBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class XMLReportBuilder extends ReportBuilder {

    /**
     *
     *
     * @param associationReport
     * @return
     * @throws JsonProcessingException
     */
    public String convertReport(AssociationReport associationReport) throws JsonProcessingException {

        ObjectMapper xmlMapper = new XmlMapper();
        String xmlOutput = xmlMapper.writeValueAsString(associationReport);

        xmlOutput = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(associationReport);
        System.out.println(xmlOutput);

        return xmlOutput;
    }

    /**
     *
     *
     * @param associationReport
     * @param filePath
     * @throws IOException
     */
    public void convertReportToFile(AssociationReport associationReport, String filePath) throws IOException {

        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File(filePath), associationReport);
    }
}
