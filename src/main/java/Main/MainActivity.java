package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        final String NEWS_API_KEY = "a31a93a9948a4b93a326671db56b4785";

        Api_Client api_client = new Api_Client();
        String sources = "abc-news,bbc-sport,cbc-news";
        String keyword = "dengue";
        String type = "top-headlines"; // or --> everything
        String pageSize = "1";
        String category = "business";
        String endpoint = "https://newsapi.org/v2/" + type + "?country=us&category=" + category + "&" + "apiKey=a31a93a9948a4b93a326671db56b4785";


        //        uncomment this to fetch news
        StringBuilder response = api_client.httpClient(endpoint, NEWS_API_KEY);
        HashMap<String,ArrayList<String>> newsData = api_client.getJsonContent(response);
        ArrayList urls = newsData.get("URL");
        ArrayList<StringBuilder> descriptions = api_client.getHTMLContent(urls);

        ArrayList titles = newsData.get("TITLE");
        for (int i = 0; i < descriptions.size(); i++) {
            SentenceScoreCalculator sentenceScoreCalculator = new SentenceScoreCalculator(descriptions.get(i));
            List<Sentence> scoredSenetences = sentenceScoreCalculator.getScoredSenetences();
            int incrementer = 0;
            StringBuilder selectedSenetences = new StringBuilder();

            for (Sentence sentence : scoredSenetences) {
                if (incrementer == 20) {
                    break;
                } else {
//                    summerized news as a paragraph
                    selectedSenetences.append(sentence.getSentenceValue() + "\n");
                    incrementer++;
                }

            }

            System.out.println("********************************");
            System.out.println("Summary : " + selectedSenetences);


            PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
            postgreSQLJDBC.createConnection();
//            postgreSQLJDBC.createTable();
            String title = titles.get(i).toString();
            System.out.println("title :"+i+" : "+title);
//            postgreSQLJDBC.saveNews(selectedSenetences,title);
//            postgreSQLJDBC.retreiveNews(1);
//            System.out.println("----------------------------------------");

//            URL url = new URL("http://www.google.com/search?q=mkyong");
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            System.out.println("HHTP ");
//            System.out.println(con);
        }
        TrendingNewsObserver trendingNewsObserver = new TrendingNewsObserver();
        trendingNewsObserver.calculateWordOccurance(descriptions);
    }
}
