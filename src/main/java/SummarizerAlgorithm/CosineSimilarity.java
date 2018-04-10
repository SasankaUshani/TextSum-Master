
package SummarizerAlgorithm;

import Models.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SasankaKudagoda on 3/21/18.
 */

public class CosineSimilarity {
    List<String[]> allWordsInDocuments;
    ArrayList<Word> words;
    TFIDFCalculator tfidfCalculator = new TFIDFCalculator();
    CosineSimilarity(List<String[]> allWordsInDocuments,ArrayList<Word>words){
        this.allWordsInDocuments = allWordsInDocuments;
        this.words = words;
    }

    public double compute_cosine(int sentence1Index, int sentence2Index) {
        ArrayList<String> commonWords = getCommonWords(sentence1Index, sentence2Index);
        double numerator = 0.0;
        for (String word : commonWords) {
            Word wordObj = new Word();
            wordObj.setValue(word);
            if (words.contains(wordObj)) {
                wordObj = words.get(words.indexOf(wordObj));
                numerator += Math.pow(wordObj.getTf_value() * wordObj.getTf_value() * wordObj.getIdf_value(), 2);
            }
        }
        String[] sentence1 = allWordsInDocuments.get(sentence1Index);
        double sentence1Val = tfidfCalculator.calculateTotalTF_IDF_Square(sentence1,words);

        String[] sentence2 = allWordsInDocuments.get(sentence2Index);
        double sentence2Val = tfidfCalculator.calculateTotalTF_IDF_Square(sentence2,words);

        if (sentence1Val > 0.0 && sentence2Val > 0.0) {
            return numerator / Math.sqrt(sentence1Val) * Math.sqrt(sentence2Val);
        } else {
            return 0.0;
        }
    }
    private ArrayList<String> getCommonWords(int index1, int index2) {
        String[] sentence1 = allWordsInDocuments.get(index1);
        String[] sentence2 = allWordsInDocuments.get(index2);
        ArrayList<String> commonWords = new ArrayList<String>();
        for (int i = 0; i < sentence1.length; i++) {
            for (int j = 0; j < sentence2.length; j++) {
                if (sentence1[i].trim().equalsIgnoreCase(sentence2[j].trim())) {
                    commonWords.add(sentence1[i]);
                }
            }
        }
        return commonWords;
    }



//    public double cosineSimilarity(Double[] docVector1, Double[] docVector2) {
//        double dotProduct = 0.0;
//        double magnitude1 = 0.0;
//        double magnitude2 = 0.0;
//        double cosineSimilarity = 0.0;
//
//        for (int i = 0; i < docVector1.length; i++) //docVector1 and docVector2 must be of same length
//        {
//            dotProduct += docVector1[i] * docVector2[i];  //a.b
//            magnitude1 += Math.pow(docVector1[i], 2);  //(a^2)
//            magnitude2 += Math.pow(docVector2[i], 2); //(b^2)
//        }
//
//        magnitude1 = Math.sqrt(magnitude1);//sqrt(a^2)
//        magnitude2 = Math.sqrt(magnitude2);//sqrt(b^2)
//
//        if (magnitude1 != 0.0 | magnitude2 != 0.0) {
//            cosineSimilarity = dotProduct / (magnitude1 * magnitude2);
//        } else {
//            return 0.0;
//        }
//        System.out.println("Cosine simlarity ---- ");
//        System.out.println(cosineSimilarity);
//        return cosineSimilarity;
//    }
}
