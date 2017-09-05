package source.classification;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public interface BugClassificationModel {

    /**
     *
     */
    public void createBugCategories() throws ParserConfigurationException, SAXException, IOException;

    /**
     *
     * @return
     */
    public HashMap<String, BugCategory> getBugCategories() throws IOException, SAXException, ParserConfigurationException;
}
