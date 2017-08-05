package source;

import source.classification.model.A1BugCategory;
import source.classification.model.A2BugCategory;

import java.io.File;

/**
 * Created by Ashi on 8/1/2017.
 */
public class BugAnalyzer { //If we want to make this class static then make class final

    private A1BugCategory A1; //if static class then static variables and methods
    private A2BugCategory A2;

    public BugAnalyzer(){ //if static class then private constructor

    }

    public void readFile(File xmlFile){ // !!! same as in HTMLReader. Is this method necessary in this class

    }

    public void collectBugs(){

    }

    public void generateBugReport(){ // !!! Isn't this method calling createBugReport() method in BugReportBuilder Class??

    }

}
