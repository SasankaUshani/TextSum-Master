package WebApp;

import Database.PostgreSQLJDBC;
import Models.Sentence;
import SummarizerAlgorithm.AbstractiveSummarizer;
import SummarizerAlgorithm.Api_Client;
import SummarizerAlgorithm.SentenceScoreCalculator;
import TrendingNews.TrendingNewsObserver;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/getSummarizedNews")
public class RESTController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClichedMessage() throws JSONException, IOException, InterruptedException, SQLException {
///**************uncomment for the timer HERE

//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                // task to run goes here
//                print();
//            }
//        };
//        Timer timer = new Timer();
//        long delay = 0;
//        long intevalPeriod = 50 * 10000;
//        // schedules the task to be run in an interval
//        timer.scheduleAtFixedRate(task, delay,
//                intevalPeriod);
//
//        TimerTask task2 = new TimerTask() {
//            @Override
//            public void run() {
//                print2();
//            }
//        };
//        Timer timer2 = new Timer();
//        long delay2 = 100;
//        long intevalPeriod2 = 100 * 100000;
//        // schedules the task to be run in an interval
//        timer2.scheduleAtFixedRate(task2, 0,
//                60000);

///**************uncomment for the timer TO HERE


        JSONObject jsonObject = new JSONObject();
//        fetch news from api
//        getNews();

//      retreive news from the database
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        ArrayList<ArrayList> response = postgreSQLJDBC.retreiveNews();
        ArrayList<JsonObject> additionalDetails = createJsonObject(response);

//        for (int i = 0; i < additionalDetails.size(); i++) {
//            jsonObject.put("newsDetails" + i, additionalDetails.get(i));
//        }


        jsonObject.put("news",additionalDetails);

//      abstractive summary
//        String abstractiveSummary = generateAbtractiveSummarization();
//        jsonObject.put("AbstractiveSummary", abstractiveSummary);
////       generate graph
        HashMap trendingNews = generateGraph();
        jsonObject.put("trendingNews",trendingNews);


        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(jsonObject.toString()).build();

    }

    private String generateAbtractiveSummarization() throws IOException {
        AbstractiveSummarizer abstractiveSummarizer = new AbstractiveSummarizer();
        return abstractiveSummarizer.generateAbstractiveSummary();
    }

    private HashMap generateGraph() {
        TrendingNewsObserver trendingNewsObserver = new TrendingNewsObserver();
        HashMap trendingNews = trendingNewsObserver.calculateWordOccurance();
        return trendingNews;
    }

    private void print() {
        System.out.println("**********************Hello  FROM 1111111111!!!");
    }

    private void print2() {
        System.out.println("**********************Hello  FOMR 2222222222!!!");
    }

    private ArrayList<JsonObject> createJsonObject(ArrayList<ArrayList> otherDetails) {
        ArrayList<JsonObject> jsonArrayList = new ArrayList();


        ArrayList<String> summary = otherDetails.get(0);
        ArrayList<String> titles = otherDetails.get(1);
        ArrayList<String> urls = otherDetails.get(2);
        ArrayList<String> sources = otherDetails.get(3);
        ArrayList<String> images = otherDetails.get(4);
        ArrayList<String> dates = otherDetails.get(5);
        ArrayList<String> authors = otherDetails.get(6);


//        create the json object
        for (int k = 0; k < titles.size(); k++) {
            JsonObject additionalDetails = Json.createObjectBuilder()
                    .add("title", titles.get(k).toString())
                    .add("url", urls.get(k).toString())
                    .add("image", images.get(k).toString())
                    .add("source", sources.get(k).toString())
                    .add("date", dates.get(k).toString())
                    .add("author", authors.get(k).toString())
                    .add("summary", summary.get(k).toString())
                    .build();
            jsonArrayList.add(additionalDetails);
        }
        return jsonArrayList;
    }

    private HashMap<String, ArrayList<String>> getNewsURL() throws IOException {
        Api_Client api_client = new Api_Client();
        String sources = "abc-news,bbc-sport,cbc-news";
        String keyword = "dengue";
        String type = "top-headlines"; // or --> everything
        String pageSize = "1";
        String category = "business";
        String endpoint = "http://newsapi.org/v2/" + type + "?country=us&category=" + category + "&" + "apiKey=a31a93a9948a4b93a326671db56b4785";

        StringBuilder response = api_client.httpClient(endpoint, null);

        HashMap<String, ArrayList<String>> newsData = api_client.getJsonContent(response);

        ArrayList urls = newsData.get("URL");

        ArrayList returnObject = new ArrayList();
        returnObject.add(urls);
        returnObject.add(newsData);
        return newsData;
    }


    private ArrayList getNews() throws IOException, InterruptedException, SQLException {

        ArrayList summarizedNews = new ArrayList();
        Api_Client api_client = new Api_Client();

        HashMap<String, ArrayList<String>> newsData = new HashMap();
        newsData = getNewsURL();
        ArrayList urls = newsData.get("URL");

        ArrayList<StringBuilder> descriptions = api_client.getHTMLContent(urls);
        for (int i = 0; i < descriptions.size(); i++) {
//            call to the main summarizing algorithm
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
            summarizedNews.add(selectedSenetences);
        }
        ArrayList url = newsData.get("URL");
        ArrayList titles = newsData.get("TITLE");
        ArrayList images = newsData.get("IMAGE");
        ArrayList source = newsData.get("SOURCE");
        ArrayList dates = newsData.get("DATE");
        ArrayList authors = newsData.get("AUTHOR");

//        save to database
        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
        postgreSQLJDBC.saveToDatabase(url, summarizedNews, titles, images, source, dates, authors);
        return summarizedNews;
    }
}
