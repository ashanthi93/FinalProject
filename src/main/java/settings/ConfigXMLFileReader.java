package settings;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by CHAM PC on 8/17/2017.
 */
public class ConfigXMLFileReader {


    public Document createFile(String fileName) throws ParserConfigurationException, IOException, SAXException {

        String pathName = "src/main/resources/configurations/" + fileName;
        File xmlFIle = new File(pathName);

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(xmlFIle);

        return document;
    }

}
