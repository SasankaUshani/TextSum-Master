package SummarizerAlgorithm;

import Models.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SasankaKudagoda on 3/23/18.
 */
public class LexScoreCalculator {
    private List<String> allSentences;
    private double treshhold;
    private double[][] cosineMaxtrics;
    private double[] degrees;
    private  double[]lexScore;
    List<String[]> allWordsInDocuments;
    ArrayList<Word> words;

    LexScoreCalculator(List<String> allSentences, double treshhold, double[][] cosineMaxtrics, double[] degrees, double[]lexScore, List<String[]> allWordsInDocuments, ArrayList<Word>words){
     this.allSentences = allSentences;
     this.treshhold = treshhold;
     this.cosineMaxtrics = cosineMaxtrics;
     this.degrees = degrees;
     this. lexScore = lexScore;
     this.allWordsInDocuments = allWordsInDocuments;
     this.words = words;
    }

    public double[] calculateLexRankScore() {
        CosineSimilarity cosineSimilarity = new CosineSimilarity(allWordsInDocuments,words);
        for (int i = 0; i < allSentences.size(); i++) {
            for (int j = 0; j < allSentences.size(); j++) {
                cosineMaxtrics[i][j] = cosineSimilarity.compute_cosine(i, j);
                if (cosineMaxtrics[i][j] > treshhold) {
                    cosineMaxtrics[i][j] = 1.0;
                    degrees[i] += 1;
                } else {
                    cosineMaxtrics[i][j] = 0.0;
                }
            }
        }

        for (int i = 0; i < allSentences.size(); i++) {
            for (int j = 0; j < allSentences.size(); j++) {
                if (degrees[i] == 0) {
                    degrees[i] = 1.0;
                }
                cosineMaxtrics[i][j] = cosineMaxtrics[i][j] / degrees[i];
            }
        }
        powerMethod(0.85);
        return lexScore;
    }
    private double[] powerMethod(double dampFactor) {
        double magDiff = 0.85;
        double size = (double) allSentences.size();
        lexScore = new double[allSentences.size()];
        double[] lexScoreNext = new double[(int) size];
        for (int i = 0; i < allSentences.size(); i++) {
            lexScore[i] = 1 / size;
        }
        while (magDiff > treshhold) {
            for (int i = 0; i < allSentences.size(); i++) {
                lexScoreNext[i] = dampFactor / size
                        + (1 - dampFactor)
                        * Vector.dotProduct(cosineMaxtrics[i], lexScore);
            }
            magDiff = Vector.difference(lexScoreNext, lexScore);
            Vector.printVector(lexScoreNext);
            System.arraycopy(lexScoreNext, 0, lexScore, 0, (int) size);
        }
       return lexScore;
    }

}
