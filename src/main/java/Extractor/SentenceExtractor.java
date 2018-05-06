package Extractor;

import Preprocessor.JunkWordsRemover;
import Preprocessor.StopWordsRemover;

import java.io.IOException;
import java.util.*;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SasankaKudagoda on 3/20/18.
 */
public class SentenceExtractor {
    StringBuilder description;
    List<String[]> allWordsInDescription = new ArrayList<>(); //words in the document
    List<String> uniqueWords = new ArrayList<>(); // unique words in description
    List<String[]> allSentences = new ArrayList<>();
    List<String> allSentencesAsList = new ArrayList<>();
    List<String> stopWords = null;
    List<String> junkText = null;

    public void splitSentence(StringBuilder descriptions) throws IOException, InterruptedException {
        junkText = new JunkWordsRemover().getJunkWords();
        stopWords = new StopWordsRemover().getStopWords();

        int documentCount = 0;
        description = descriptions;

        if (description != null) {
            Pattern re = Pattern.compile("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)",
                    Pattern.MULTILINE | Pattern.COMMENTS);

            Matcher reMatcher = re.matcher(description);
//            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/sasankakudagoda/Desktop/IIT/TextSum/FYP/src/main/java/TextFiles/TrendingNews.txt"));


            while (reMatcher.find()) {
                Boolean addToSentence = false;
                String[] tokenizedTerms = reMatcher.group().
                        replaceAll("[\\W&&[^\\s]]", "").split("\\W+");//to get individual terms
                String sentence = reMatcher.group().toString();
//                uncomment here to remove junk  FROM HERE
//                for (int x = 0; x < junkText.size(); x++) {
//                    if (sentence.equalsIgnoreCase(junkText.get(x))) {
//                        System.out.println("junk text have " + junkText.get(x));
//                        addToSentence = false;
//                        break;
//                    } else {
//                        System.out.println("no junk tesxt found");
//                        addToSentence = true;
//                    }
//                }

//                if (addToSentence) {
//                    System.out.println("added");
//                    allSentences.add(tokenizedTerms);
//                    allSentencesAsList.add(sentence);
//                } else {
//                    System.out.println("not added " + sentence);
//                }
                //                uncomment here to remove junk TO HERE

                allSentences.add(tokenizedTerms);
                allSentencesAsList.add(sentence);
                for (String term : tokenizedTerms) {
//                        avoid duplicate entry & stop words
                    if (!uniqueWords.contains(term) && !stopWords.contains(term.toLowerCase())) {
                        uniqueWords.add(term);
                    }
                }

                allWordsInDescription.add(tokenizedTerms);


            }


        } else {
            System.out.println("Description is null");
        }
        documentCount++;


    }

//    }


    public List<String[]> getAllWordsInDescription() {
        return this.allWordsInDescription;
    }

    public List<String> getUniqueWords() {
        return this.uniqueWords;
    }

    public List<String> getAllSentencesAsList() {
        return this.allSentencesAsList;
    }
}
