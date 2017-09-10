package org.ucsc.sse.outputgenerators.report.builder;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public abstract class ReportBuilder {

    /**
     *
     *
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public abstract String convertReport(Object object) throws JsonProcessingException;

    /**
     *
     *
     * @param object
     * @param filePath
     * @throws IOException
     */
    public abstract void convertReportToFile(Object object, String filePath) throws IOException;
}
