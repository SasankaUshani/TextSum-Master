package SummarizerAlgorithm;

import Models.Word;

import java.util.*;

/**
 * Created by SasankaKudagoda on 3/15/18.
 */
public class TFIDFCalculator {
    private List<String> uniqueWords;
    private List<String[]> allWordsInDocuments;
    private StringBuilder documents;

    private ArrayList<Word> words;

    TFIDFCalculator(List<String> uniqueWords, List<String[]> allWordsInDocuments, StringBuilder documents) {
        words = new ArrayList<Word>();
        this.uniqueWords = uniqueWords;
        this.allWordsInDocuments = allWordsInDocuments;
        this.documents = documents;
    }
    TFIDFCalculator(){

    }

    public ArrayList<Word> calculateTF() {
        double tf_value = 0.00;
        for (String word : uniqueWords) {
            Word wordObj = new Word();
            wordObj.setValue(word);
            tf_value = calculateOccarunce(word) / allWordsInDocuments.size();
            wordObj.setTf_value(tf_value);
            words.add(wordObj);
        }
        return words;
    }

    public void calculateIDF() {
        double total_sentence = 0;
        double idf_value = 0;
        for (Word word : words) {
            total_sentence = calculateSentenceOccarunce(word.getValue());
            idf_value = 1 + Math.log(4.0 / total_sentence);
            word.setIdf_value(idf_value);
        }
    }

    private double calculateOccarunce(String word) {
        double count = 0;
        for (String[] sentence : allWordsInDocuments) {
            for (String w : sentence) {
                if (w.equalsIgnoreCase(word)) {
                    count++;
                }
            }
        }
        return count;
    }

    private double calculateSentenceOccarunce(String word) {
        double count = 0;
//        for (StringBuilder document : documents) {

            if (documents.toString().contains(word)) {
                count++;
            }
//        }
        return count;
    }

    public double calculateTotalTF_IDF_Square(String[] sentence, ArrayList<Word>words) {
        double sentenceVal = 0.00;
        for (String word : sentence) {
            Word wordObj = new Word();
            wordObj.setValue(word);
            if (words.contains(wordObj)) {
                wordObj = words.get(words.indexOf(wordObj));
                sentenceVal += Math.pow((wordObj.getTf_value() * wordObj.getIdf_value()), 2);
            }
        }
        return sentenceVal;
    }

}



