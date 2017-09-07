package prolog;

import org.jpl7.Query;
import org.xml.sax.SAXException;
import settings.owasp_configs.OWASPMappingConfig;
import settings.owasp_configs.OWASPProactiveConfig;
import settings.owasp_configs.OWASPT10Config;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AssociationLoader {

    public void callProlog() throws IOException, SAXException, ParserConfigurationException {

        PrintWriter writer = new PrintWriter("src/main/resources/prolog/knowledgeBase.pl");
        writer.print("");

        ArrayList<String[]> owasp = new OWASPT10Config().loadConfigFile();
        HashMap<String, String[]> owaspMapping = new OWASPMappingConfig().loadConfigFile();

        for (int i = 0; i < owasp.size(); i++) {
            String[] data = owasp.get(i);
            String proactives = "";
            String[] mapping = owaspMapping.get(data[0]);
            for (int j = 0; j < mapping.length; j++) {
                if(j>0) {
                    proactives = proactives + "," + mapping[j].toLowerCase();
                }else{
                    proactives = proactives + mapping[j].toLowerCase();
                }

            }
            System.out.println();
            String s = "owasp_top10(" + data[0].toLowerCase() + "," + "name(\"" + data[1] + "\")" + "," + "["  +proactives + "]).";
            System.out.println(s);
            writer.println(s);

        }

        writer.println();
        writer.println();
        writer.println("owasp(Bug_type):-\n" +
                "\towasp_top10(\n" +
                "\t\tBug_type,\n" +
                "\t\t_,\n" +
                "\t\tY\n" +
                "\t),\n" +
                "\tlength(Y,A),\n" +
                "\ttestloop(0,A,Y).\n" +
                "\n" +
                "testloop(N, Length, List):- \n" +
                "\tN<Length, \n" +
                "\tnth0(N,List,B),\n" +
                "\tget_proactive_description(B), nl, \n" +
                "\tM is N+1, \n" +
                "\ttestloop(M,Length,List).\n" +
                "\n" +
                "get_proactive_description(Name):-\n" +
                "\towasp_top10_proactive(\n" +
                "\t\tName,\n" +
                "\t\t_,\n" +
                "\t\tY\n" +
                "\t\t),\n" +
                "\twrite(Y).\n" +
                "\n" +
                "remove_frame(A):-\n" +
                "\towasp_top10_proactive(\n" +
                "\t\tA,\n" +
                "\t\t_,\n" +
                "\t\t_\n" +
                "\t),\n" +
                "\tretract(owasp_top10_proactive(A,_,_)).");
        writer.close();

        String s1 = String.format("consult('src/main/resources/prolog/knowledgeBase.pl').");
        Query q1 = new Query(s1);
        System.out.println("Query Loaded " + (q1.hasSolution() ? "Success" : "Failed"));

        String s2 = "owasp_top10(A,B,C).";
        Query q2 = new Query(s2);

        q2.open();
        while(q2.hasMoreSolutions()){
            Map solution = q2.nextSolution();
            System.out.println(solution.get("A"));
        }

    }

    public void getOwaspMappingData() throws ParserConfigurationException, SAXException, IOException {

        ArrayList<String[]> proactive = new OWASPProactiveConfig().loadConfigFile();
        for (int i = 0; i < proactive.size(); i++) {
            String data [] = proactive.get(i);

        }
    }

}
