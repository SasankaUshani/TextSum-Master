package WebApp;

import Models.Sentence;
import SummarizerAlgorithm.Api_Client;
import SummarizerAlgorithm.SentenceScoreCalculator;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Time;
import java.util.*;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/getSummarizedNews")
public class RESTController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClichedMessage() throws JSONException, IOException, InterruptedException {


        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // task to run goes here
                System.out.println("**********************Hello !!!");
            }
        };
        Timer timer = new Timer();
        long delay = 0;
        long intevalPeriod = 5 * 10000;
        // schedules the task to be run in an interval
        timer.scheduleAtFixedRate(task, delay,
                intevalPeriod);




        ArrayList sumarizedNews = new ArrayList();
        JSONObject jsonObject = new JSONObject();
        sumarizedNews = getNews();
        for (int x = 0; x < sumarizedNews.size(); x++) {
            jsonObject.put("news" + x, sumarizedNews.get(x));
        }


        ArrayList response = new ArrayList();
        response = getNewsURL();
        ArrayList additionalDetails = (ArrayList) response.get(1);
        for(int i=0;i<sumarizedNews.size();i++) {
            additionalDetails = (ArrayList) response.get(1);
            jsonObject.put("newsDetails"+i, additionalDetails.get(i));
        }

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(jsonObject.toString()).build();

    }

    private  void print(){
        System.out.println("*********** Called **************");
    }
    private ArrayList<JsonObject> createJsonObject(ArrayList titles, ArrayList images, ArrayList source, ArrayList dates, ArrayList authors) {
        ArrayList<JsonObject> jsonArrayList = new ArrayList();
        for (int k = 0; k < titles.size(); k++) {
            JsonObject additionalDetails = Json.createObjectBuilder()
                    .add("title", titles.get(k).toString())
                    .add("image", images.get(k).toString())
                    .add("source", source.get(k).toString())
                    .add("date", dates.get(k).toString())
                    .add("author", authors.get(k).toString())
                    .build();
            jsonArrayList.add(additionalDetails);
        }
        return jsonArrayList;
    }

    private ArrayList getNewsURL() throws IOException {
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
        ArrayList titles = newsData.get("TITLE");
        ArrayList images = newsData.get("IMAGE");
        ArrayList source = newsData.get("SOURCE");
        ArrayList dates = newsData.get("DATE");
        ArrayList authors = newsData.get("AUTHOR");

        ArrayList<JsonObject> additionalDetails =  createJsonObject(titles, images, source, dates, authors);
       ArrayList returnObject = new ArrayList();
       returnObject.add(urls);
       returnObject.add(additionalDetails);
        return returnObject;
    }

    private String gettitle() {
        return "returned from the outside method";
    }


    private ArrayList getNews() throws IOException, InterruptedException {

        ArrayList summarizedNews = new ArrayList();
        Api_Client api_client = new Api_Client();

        ArrayList response = new ArrayList();
        response = getNewsURL();
        response = (ArrayList) response.get(0);
        ArrayList<StringBuilder> descriptions = api_client.getHTMLContent(response);
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

            summarizedNews.add(selectedSenetences);
        }

        return summarizedNews;
    }
}
