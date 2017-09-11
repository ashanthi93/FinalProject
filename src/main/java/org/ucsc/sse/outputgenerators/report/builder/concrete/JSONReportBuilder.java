package org.ucsc.sse.outputgenerators.report.builder.concrete;

import org.ucsc.sse.outputgenerators.report.builder.ReportBuilder;
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
        String jsonOutput = jsonMapper.writeValueAsString(object);

        jsonOutput = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        System.out.println(jsonOutput);

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
