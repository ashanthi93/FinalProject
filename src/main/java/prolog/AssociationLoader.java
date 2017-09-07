package prolog;

import org.jpl7.Query;
import org.xml.sax.SAXException;
import settings.bugmodel_configs.OWASPMappingConfig;
import settings.bugmodel_configs.OWASPProactiveConfig;
import settings.bugmodel_configs.OWASPT10Config;
import settings.threatmodel_configs.STRIDEAttackerConfig;
import settings.threatmodel_configs.STRIDEDefensiveConfig;
import settings.threatmodel_configs.STRIDEDefensiveMappingConfig;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AssociationLoader {

    public void callProlog() throws IOException, SAXException, ParserConfigurationException {

        PrintWriter writer = new PrintWriter("src/main/resources/prolog/knowledgeBase.pl");
        writer.print(""); // clear the existing file.

// write stride frames to the prolog file
        ArrayList<String[]> stride = new STRIDEAttackerConfig().loadConfigFile();
        HashMap<String, String[]> strideMapping = new STRIDEDefensiveMappingConfig().loadConfigFile();
        for (int i = 0; i < stride.size(); i++) {
            String data [] = stride.get(i);
            String defensive = "";
            //System.out.println(data[1] + ", " + data[2] + ", " + data[3] );
            String[] mapping = strideMapping.get(data[0]);
            for (int j = 0; j < mapping.length; j++) {
                if(j>0) {
                    defensive = defensive + "," + mapping[j].toLowerCase();
                }else{
                    defensive = defensive + mapping[j].toLowerCase();
                }

            }
            String s = "stride(\n" + data[1].toLowerCase().replace(' ', '_') + ",\n" + "security_control(\"" + data[2] + "\")" +  ",\n" + "[" + defensive + "]).";
            writer.println(s);
            writer.println();
        }

        writer.println();
        writer.println();

// write asf data to prolog file
        ArrayList<String[]> asf = new STRIDEDefensiveConfig().loadConfigFile();
        for (int i = 0; i < asf.size(); i++) {
            String data[] = asf.get(i);
            String s = "stride_defensive(\n" + data[0].toLowerCase() + ",\n" + "name(\"" + data[1] + "\")).";
            //System.out.println(s);
            writer.println(s);
            writer.println();
        }
        writer.println();
        writer.println();


// write owasp top 10 to the prolog file
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
            String s = "owasp_top10(\n" + data[0].toLowerCase() + ",\n" + "name(\"" + data[1] + "\")" + ",\n" + "["  +proactives + "]).";
            writer.println(s);
            writer.println();

        }
        writer.println();
        writer.println();
        
// write owasp proactives to the prolog file
        ArrayList<String[]> proactive = new OWASPProactiveConfig().loadConfigFile();
        for (int i = 0; i < proactive.size(); i++) {
            String data [] = proactive.get(i);
            String s = "owasp_top10_proactive(\n" + data[0].toLowerCase() + ",\n" + "name(\"" + data[1] + "\"), \n" +"\"" + data[0].toLowerCase() + " description\"\n" + ")." ;
            writer.println(s);
            writer.println();

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

        String s2 = "owasp('a1').";
        Query q2 = new Query(s2);

        q2.open();
        q2.getSolution();
        /*while(q2.hasMoreSolutions()){
            Map solution = q2.nextSolution();
            System.out.println(solution.get("A"));
        }*/

    }



}
