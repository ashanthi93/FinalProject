package org.sse.reportparser.design;

import org.dom4j.DocumentException;
import org.sse.design.model.Threat;

import java.io.File;
import java.util.List;

public abstract class ThreatReportParser {

    private File threatModelingFile;

    public void setThreatModelingFile(File threatModelingFile) {
        this.threatModelingFile = threatModelingFile;
    }

    public File getThreatModelingFile() {
        return threatModelingFile;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public abstract boolean validateFile();

    /**
     *
     * @return
     * @throws Exception
     */
    public abstract String extractName() throws DocumentException;

    /**
     *
     * @return
     * @throws Exception
     */
    public abstract List<Threat> extractThreats() throws DocumentException;
}
