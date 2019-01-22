package org.sse.settings.config.design;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.sse.settings.ConfigFileCreator;
import org.sse.settings.ConfigFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThreatModelConfig {

    private static String parentTag = "threat-model";
    private static String threatTypeTag = "threat-type";
    private static String idTag = "id";
    private static String nameTag = "name";

    private static String fileName = "ThreatModel.xml";

    private ThreatModelConfig(){}

    /**
     *
     * @param threatDetails           id, name, security control
     * @throws IOException
     */
    public static void createConfigFile(ArrayList<String[]> threatDetails) throws IOException {

        ConfigFileCreator configFileCreator = new ConfigFileCreator();
        configFileCreator.createFile();

        configFileCreator.createRootElement(parentTag);

        /* create threat-type tags */
        for (String[] threatDetail : threatDetails){

            /* create threat-type tag */
            Element threatTypeElement = configFileCreator.createChildElement(threatTypeTag);

            Element idElement = configFileCreator.createChildElement(idTag, threatDetail[0]);
            Element nameElement = configFileCreator.createChildElement(nameTag, threatDetail[1]);

            threatTypeElement.add(idElement);
            threatTypeElement.add(nameElement);
            /* end of threat-type tag */

            configFileCreator.addToRoot(threatTypeElement);
        }
        /* end of threat-type tags */

        configFileCreator.writeFile(fileName);
    }

    /**
     *
     * @return
     * @throws DocumentException
     */
    public static ArrayList<String[]> loadConfigFile() throws DocumentException {

        ArrayList<String[]> threatList = new ArrayList<String[]>();

        ConfigFileReader configFileReader = new ConfigFileReader();
        configFileReader.readFile(fileName);

        List<Node> nodeList = configFileReader.getNodes("//" + parentTag + "/" + threatTypeTag);

        for (Node node : nodeList){

            String[] threatDetail = new String[2];

            threatDetail[0] = node.valueOf(idTag);
            threatDetail[1] = node.valueOf(nameTag);

            threatList.add(threatDetail);
        }

        return threatList;
    }

    /**
     *
     * @return
     * @throws DocumentException
     */
    public static HashMap<String,String> loadThreatCategoryIdsAndNames() throws DocumentException {

        HashMap<String,String> threatIdsAndNames = new HashMap<String, String>();

        ArrayList<String[]> threatDetails = loadConfigFile();

        for (String[] threatDetail : threatDetails){
            threatIdsAndNames.put(threatDetail[0],threatDetail[1]);
        }

        return threatIdsAndNames;
    }
}
