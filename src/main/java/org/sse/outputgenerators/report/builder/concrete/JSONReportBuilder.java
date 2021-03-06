package org.sse.outputgenerators.report.builder.concrete;

import org.sse.outputgenerators.report.builder.ReportBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONReportBuilder extends ReportBuilder{

    /**
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public String convertReport(Object object) throws JsonProcessingException {

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonOutput = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

        return jsonOutput;
    }

    /**
     *
     *
     * @param object
     * @param filePath
     * @throws IOException
     */
    public void convertReportToFile(Object object, String filePath) throws IOException {

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.writeValue(new File(filePath), object);
    }
}
