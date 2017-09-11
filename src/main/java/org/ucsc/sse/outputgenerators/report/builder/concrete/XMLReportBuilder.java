package org.ucsc.sse.outputgenerators.report.builder.concrete;

import org.ucsc.sse.outputgenerators.report.builder.ReportBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

public class XMLReportBuilder extends ReportBuilder {

    /**
     *
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public String convertReport(Object object) throws JsonProcessingException {

        ObjectMapper xmlMapper = new XmlMapper();
        String xmlOutput = xmlMapper.writeValueAsString(object);

        xmlOutput = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        System.out.println(xmlOutput);

        return xmlOutput;
    }

    /**
     *
     *
     * @param object
     * @param filePath
     * @throws IOException
     */
    public void convertReportToFile(Object object, String filePath) throws IOException {

        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File(filePath), object);
    }
}
