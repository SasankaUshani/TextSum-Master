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

        final String NEWS_API_KEY = "a31a93a9948a4b93a326671db56b4785";
//
        Api_Client api_client = new Api_Client();
        String sources = "abc-news,bbc-sport,cbc-news";
        String keyword = "dengue";
        String type = "top-headlines"; // or --> everything
        String pageSize = "1";
        String category = "business";
        String endpoint = "https://newsapi.org/v2/" + type + "?country=us&category=" + category + "&" + "apiKey=a31a93a9948a4b93a326671db56b4785";


        //        uncomment this to fetch news
//        StringBuilder response = api_client.httpClient(endpoint, NEWS_API_KEY);
//        HashMap<String, ArrayList<String>> newsData = api_client.getJsonContent(response);
//        ArrayList urls = newsData.get("URL");


//        ArrayList urls = new ArrayList();
//        urls.add("http://fortune.com/2018/04/12/wework-naked-hub-china-buy/");
//        urls.add("http://www.mcall.com/news/local/allentown/mc-nws-allentown-post-office-moving-20180409-story.html");
//        urls.add("https://www.bloomberg.com/news/features/2018-04-12/tesla-workers-claim-racial-bias-and-abuse-at-electric-car-factory");
//        urls.add("https://www.reuters.com/article/us-sky-m-a-fox-walt-disney/uk-takeover-panel-rules-disney-must-offer-to-buy-all-of-sky-idUSKBN1HJ188");
//        urls.add("https://www.bloomberg.com/news/articles/2018-04-12/british-airways-owner-is-said-to-consider-bid-for-norwegian-air");
//        urls.add("http://www.newsweek.com/houston-gas-station-owner-shoots-customer-slim-jim-beef-882600");
//        urls.add("https://www.cnbc.com/2018/04/12/japan-rare-earths-huge-deposit-of-metals-found-in-pacific.html");
//        urls.add("https://www.reuters.com/article/us-flipkart-online-m-a-walmart/exclusive-walmart-close-to-buying-majority-of-indias-flipkart-deal-likely-by-end-june-sources-idUSKBN1HJ0YJ");
//        urls.add("https://www.reuters.com/article/us-global-oil/oil-markets-tense-on-middle-east-crisis-u-s-china-trade-spat-idUSKBN1HJ01E");
//        urls.add("https://www.digitaltrends.com/cool-tech/airbus-advanced-inspection-drone/");
//        urls.add("https://www.wsj.com/articles/airlines-know-you-hate-the-airport-and-are-trying-to-do-something-about-it-1523479889");
//        urls.add("https://www.bloomberg.com/news/articles/2018-04-11/toys-r-us-receives-bids-of-over-1-billion-for-asian-business");
//        urls.add("http://fortune.com/2018/04/11/soylent-meal-replacement-drinks-walmart/");
//        urls.add("https://www.bloomberg.com/news/articles/2018-04-11/jpmorgan-sued-over-cash-advance-fees-for-cryptocurrency-buys");
//        urls.add("http://www.sacbee.com/news/state/california/article208596279.html");
//        ArrayList<StringBuilder> descriptions = api_client.getHTMLContent(urls);
//        System.out.println("HTML!!!!");
//        System.out.println(descriptions.get(0));




//        for (int i = 0; i < descriptions.size(); i++) {
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


//        }
//        TrendingNewsObserver trendingNewsObserver = new TrendingNewsObserver();
//        trendingNewsObserver.calculateWordOccurance(descriptions);
    }
}

