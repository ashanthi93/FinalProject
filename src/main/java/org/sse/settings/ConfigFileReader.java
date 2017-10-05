package org.sse.settings;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

public class ConfigFileReader {

    private Document document;

    public ConfigFileReader(){

    }

    /**
     *
     * @param fileName
     * @throws DocumentException
     */
    public void readFile(String fileName) throws DocumentException {

        File file = new File("src/main/resources/configurations/" + fileName);
        String pathName = file.getAbsolutePath();

        SAXReader saxReader = new SAXReader();

        document = saxReader.read(pathName);
    }

    /**
     *
     * @param xPath
     * @return
     */
    public List<Node> getNodes(String xPath){

        List<Node> nodeList = document.selectNodes(xPath);

        return nodeList;
    }

    /**
     *
     * @param xPath
     * @return
     */
    public Node getNode(String xPath){

        Node node = document.selectSingleNode(xPath);

        return node;
    }
}
