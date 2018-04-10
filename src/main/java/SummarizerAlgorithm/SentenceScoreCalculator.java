package SummarizerAlgorithm;


import Comparator.SentenceComparator;
import Extractor.SentenceExtractor;
import Models.Sentence;
import Models.Word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by SasankaKudagoda on 3/28/18.
 */

public class SentenceScoreCalculator {
    private List<String[]> allWordsInDocuments = null;
    private List<String> uniqueWords = null;
    private List<String> allSentences = null;
    private StringBuilder documents = null;
    private SentenceExtractor sentenceExtractor;
    private double[][] cosineMaxtrics = null;
    private ArrayList<Word> words;
    private double max_occarance = 0;
    private double[] degrees = null;
    private final double treshhold = 0.1;
    private double[] lexScore;

    public SentenceScoreCalculator(StringBuilder documents) throws IOException, InterruptedException {
        this.documents = documents;

        sentenceExtractor = new SentenceExtractor();
        init();
    }


    private void init() throws IOException, InterruptedException {
//        for (int i = 0; i < documents.size(); i++) {
        sentenceExtractor.splitSentence(documents);
//        }
        this.allWordsInDocuments = sentenceExtractor.getAllWordsInDescription();
        this.uniqueWords = sentenceExtractor.getUniqueWords();
        this.allSentences = sentenceExtractor.getAllSentencesAsList();
        this.cosineMaxtrics = new double[allSentences.size()][allSentences.size()];
        this.degrees = new double[allSentences.size()];
        //calculateMaxOccarunce();

        TFIDFCalculator tfidfCalculator = new TFIDFCalculator(uniqueWords, allWordsInDocuments, documents);
        words = tfidfCalculator.calculateTF();
        tfidfCalculator.calculateIDF();

        LexScoreCalculator lexScoreCalculator = new LexScoreCalculator(allSentences, treshhold, cosineMaxtrics, degrees, lexScore, allWordsInDocuments, words);
        lexScore = lexScoreCalculator.calculateLexRankScore();
    }

    private double[] getLexScore() {
        return this.lexScore;
    }

    private List<String> getAllSentence() {
        return this.allSentences;
    }

    public List<Sentence> getScoredSenetences() {
        ArrayList<Sentence> sentences = new ArrayList<Sentence>();
        for (int i = 0; i < allSentences.size(); i++) {
            sentences.add(new Sentence(allSentences.get(i), lexScore[i]));
        }
        SentenceComparator sentenceComparator = new SentenceComparator();
        Collections.sort(sentences, sentenceComparator);
        return sentences;
    }


}