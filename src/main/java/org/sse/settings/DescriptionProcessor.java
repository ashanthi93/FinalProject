package org.sse.settings;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.process.DocumentPreprocessor;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class DescriptionProcessor {

    private DescriptionProcessor(){}

    /**
     *
     *
     * @param paragraph
     * @return
     */
    public static List<String> getSentences(String paragraph){

        Reader reader = new StringReader(paragraph);

        DocumentPreprocessor documentPreprocessor = new DocumentPreprocessor(reader);

        List<String> sentenceList = new ArrayList<String>();

        for (List<HasWord> sentence : documentPreprocessor){

            String sentenceString = SentenceUtils.listToString(sentence);
            sentenceList.add(sentenceString);
        }

        return sentenceList;
    }
}
