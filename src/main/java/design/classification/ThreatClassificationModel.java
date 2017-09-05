package design.classification;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public interface ThreatClassificationModel {

    /**
     *
     */
    public void createThreatCategories() throws ParserConfigurationException, SAXException, IOException;

    /**
     *
     *
     * @return
     */
    public HashMap<String,ThreatCategory> getThreatCategories() throws IOException, SAXException, ParserConfigurationException;
}
