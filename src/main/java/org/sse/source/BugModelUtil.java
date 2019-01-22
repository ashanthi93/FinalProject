package org.sse.source;

import org.sse.source.model.Bug;
import org.sse.source.model.BugCollection;

import java.util.List;

public class BugModelUtil {

    private BugModelUtil(){}

    public static List<Bug> getAllBugs(BugCollection bugCollection){

        return (bugCollection.getBugList());
    }
}
