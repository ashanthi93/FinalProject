package design;

import design.classification.model.*;
import java.io.File;

/**
 * Created by Ashi on 8/1/2017.
 */
public class ThreatAnalyzer { //If we want to make this class static then make class final

    private SpoofingThreatCategory spoofing; //if static class then static variables and methods
    private TamperingThreatCategory tampering;
    private RepudiationThreatCategory repudiation;
    private InformationDisclosureThreatCategory infDisclosure;
    private DosThreatCategory dos;
    private EopThreatCategory eop;

    public ThreatAnalyzer(){ //if static class then private constructor

    }

    public void readFile(File htmFile){ // !!! same as in HTMLReader. Is this method necessary in this class

    }

    public void collectThreats(){

    }

    public void generateThreatReport(){ // !!! Isn't this method calling createThreatReport() method in ThreatReportBuilder Class??

    }

}
