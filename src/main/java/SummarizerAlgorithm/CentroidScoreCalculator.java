package SummarizerAlgorithm;


/**
 * Created by SasankaKudagoda on 3/17/18.
 */

public class CentroidScoreCalculator {
//
//    private static final double threshold = 0.1;
//    private static List<Sentence> sentences;
//    private static HashMap<String, Word> words;
//
//
    //    public void getCentroidScoresList(List<Sentence> sentences, HashMap<String, Word> words) {
//        this.sentences = sentences;
//        this.words = words;
//        calculateTFIDFScore();
//        constructCentroidCluster();
//
//    }
//
//
//    /**
//     * Calculate tfidf value for each word(without stop word) in document
//     */
//    private void calculateTFIDFScore() {
//        for (Sentence sentence : sentences) {
//            for (Word word : sentence.getWords()) {
//                words.get(word.getValue()).setTf_idf_value(words.get(word.getValue()).getTf_idf_value()
//                        + words.get(word.getValue()).getTf_idf_value());
//
//
//            }
//        }
//    }
//
//    /**
//     *
//     * */
//    private void constructCentroidCluster() {
//        for (String word : words.keySet()) {
//            if (words.get(word).getTf_idf_value() > threshold) {
//                words.get(word).setCentorid(words.get(word).getTf_idf_value());
//            } else {
//                words.get(word).setCentorid(0);
//            }
//
//        }
//    }
//
//    /**
//     *
//     * */
//    private void calculateSenetenceScore() {
//        for (Sentence sentence : sentences) {
//            for (Word word : sentence.getWords()) {
//                sentence.setScore(sentence.getScore() +
//                        (word.getCentorid() * sentence.getWords().get(sentence.getWords().indexOf(word)).getCount()));
//
//            }
//        }
//    }


}

