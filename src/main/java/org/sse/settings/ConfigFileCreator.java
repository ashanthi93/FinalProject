package org.sse.settings;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigFileCreator {

    private Document document;
    private Element rootElement;

    public ConfigFileCreator(){}

    public void createFile(){

        document = DocumentHelper.createDocument();
    }

    public void createRootElement(String tagName){

        rootElement = document.addElement(tagName);
    }

    public Element createChildElement(String tagName){

        Element element = DocumentHelper.createElement(tagName);
        return element;
    }

    public Element createChildElement(String tagName, String value){

        Element element = DocumentHelper.createElement(tagName);
        element.setText(value);

        return element;
    }

    public void addToRoot(Element element){

        rootElement.add(element);
    }

    public void writeFile(String fileName) throws IOException {

        File file = new File("src/main/resources/configurations/" + fileName);
        String pathName = file.getAbsolutePath();

        FileWriter fileWriter = new FileWriter(pathName);

        XMLWriter xmlWriter = new XMLWriter(fileWriter);
        xmlWriter.write(document);
        xmlWriter.close();
    }
}
