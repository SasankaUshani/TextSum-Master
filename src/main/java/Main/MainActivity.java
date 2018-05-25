package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Database.PostgreSQLJDBC;
import SummarizerAlgorithm.Api_Client;

import SummarizerAlgorithm.SentenceScoreCalculator;
import Models.Sentence;
import TrendingNews.TrendingNewsObserver;

/**
 * Created by SasankaKudagoda on 01/19/18.
 */
public class MainActivity {
    public static void main(String[] args) throws IOException, InterruptedException {



        ArrayList<StringBuilder> descriptions = new ArrayList();
            StringBuilder stringBuilder = new StringBuilder("J.C. Penney CEO Marvin Ellison is quitting the 116-year-old department store chain after three years to become CEO of home-improvement retailer Lowe's, and on Tuesday he called it a \"gut-wrenching decision.\" \n" +
                    "\n" +
                    "Ellison made a 3.5-minute video for employees in which he talked about them and the progress the company has made. He said that the Lowe's job was \"a once-in-a-lifetime opportunity\" for him to become CEO of a $70 billion-a-year company. He also said that he didn't seek out the job. \n" +
                    "\n" +
                    "Ellison said he understands if employees are disappointed in him for leaving, but he believes that Penney has improved, modernized and has a strong leadership team. \n" +
                    "\n");
            descriptions.add(stringBuilder);
            SentenceScoreCalculator sentenceScoreCalculator = new SentenceScoreCalculator(descriptions.get(0));
            List<Sentence> scoredSenetences = sentenceScoreCalculator.getScoredSenetences();
            int incrementer = 0;
            StringBuilder selectedSenetences = new StringBuilder();

            for (Sentence sentence : scoredSenetences) {
                if (incrementer == 3) {
                    break;
                } else {
//                    summerized news as a paragraph
                    selectedSenetences.append(sentence.getSentenceValue() + "\n");

                    incrementer++;
                }


            }

            System.out.println("********************************");
            System.out.println("Summary " + selectedSenetences);


    }
}



