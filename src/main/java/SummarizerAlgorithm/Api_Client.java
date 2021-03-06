package SummarizerAlgorithm;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by SasankaKudagoda on 01/18/18.
 */

public class Api_Client {
    final static String SYNONYM_API_KEY = "777f01ece8859262a3ffe3413206df51";
    final static String AlCHMEY_API_KEY = "IsXLt8lcA0PZLaSSrIY2SfgBGim6tesxqnxQNvqPYDSm";

    public static ArrayList<StringBuilder> getHTMLContent(ArrayList newsUrl) throws IOException, InterruptedException {

        ArrayList<StringBuilder> descriptionList = new ArrayList();
        Document document;
        for (int i = 0; i < 15; i++) {
            System.out.println(newsUrl.get(i));
            try {
                Connection.Response response = Jsoup.connect((String) newsUrl.get(i))
                        .ignoreContentType(true)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                        .referrer("http://www.google.com")
                        .timeout(12000)
                        .followRedirects(true)
                        .execute();


                document = response.parse();
                Elements paragraphs = document.body().select("p");
                StringBuilder content = new StringBuilder();
                for (Element element : paragraphs) {
                    //removing advertisement tag
                    if (!element.text().equals("Advertisement") &&
                            !element.text().contains("|") &&
                            !element.text().contains(":") &&
                            !(element.text().trim().split(" ").length == 1) &&
                            !(element.text().trim().contains("Home Page"))) {
                        content.append(element.text());
                        content.append("\n");
                    }
                }
                descriptionList.add(content);
            } catch (Exception e) {

            }
        }

        return descriptionList;

    }

    public static HashMap<String, ArrayList<String>> getJsonContent(StringBuilder stringBuilder) throws IOException {
        JsonParser jsonParser = new JsonParser();
        JsonObject responseObj = (JsonObject) jsonParser.parse(stringBuilder.toString());

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("News"));
        ArrayList<String> urlList = new ArrayList<>();
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> sourceList = new ArrayList<>();
        ArrayList<String> imageList = new ArrayList<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<String> authorList = new ArrayList<>();
        HashMap<String, ArrayList<String>> newsData = new HashMap<>();


        for (int i = 0; i < responseObj.get("articles").getAsJsonArray().size(); i++) {


            String title;
            String source;
            String imageURL;
            String datePublished;
            String author;

            String newsURL = responseObj.get("articles").getAsJsonArray().get(i).getAsJsonObject().get("url").getAsString();


            if (!responseObj.get("articles").getAsJsonArray().get(i).getAsJsonObject().get("title").isJsonNull()) {
                title = responseObj.get("articles").getAsJsonArray().get(i).getAsJsonObject().get("title").getAsString();
            } else {
                title = "Unknown";
            }
            if (!responseObj.get("articles").getAsJsonArray().get(i).getAsJsonObject().get("source").getAsJsonObject().get("name").isJsonNull()) {
                source = responseObj.get("articles").getAsJsonArray().get(i).getAsJsonObject().get("source").getAsJsonObject().get("name").getAsString();

            } else {
                source = "Unknown";
            }
            if (!responseObj.get("articles").getAsJsonArray().get(i).getAsJsonObject().get("urlToImage").isJsonNull()) {
                imageURL = responseObj.get("articles").getAsJsonArray().get(i).getAsJsonObject().get("urlToImage").getAsString();

            } else {
                imageURL = "https://www.staticwhich.co.uk/static/images/products/no-image/no-image-available.png";
            }
            if (!responseObj.get("articles").getAsJsonArray().get(i).getAsJsonObject().get("publishedAt").isJsonNull()) {
                datePublished = responseObj.get("articles").getAsJsonArray().get(i).getAsJsonObject().get("publishedAt").getAsString();

            } else {
                datePublished = "23/05/2018";
            }
            if (!responseObj.get("articles").getAsJsonArray().get(i).getAsJsonObject().get("author").isJsonNull()) {
                author = responseObj.get("articles").getAsJsonArray().get(i).getAsJsonObject().get("author").getAsString();

            } else {
                author = "Unknown";
            }

            urlList.add(newsURL);
            titleList.add(title);
            sourceList.add(source);
            imageList.add(imageURL);
            dateList.add(datePublished);
            authorList.add(author);


        }
        fileWriter.close();
        newsData.put("URL", urlList);
        newsData.put("TITLE", titleList);
        newsData.put("SOURCE", sourceList);
        newsData.put("IMAGE", imageList);
        newsData.put("DATE", dateList);
        newsData.put("AUTHOR", authorList);


        return newsData;
    }

    public static StringBuilder httpClient(String endpoint, String authenticationKey) throws IOException {
        URL url = new URL(endpoint);
        URLConnection connection = url.openConnection();
        if (authenticationKey != null) {
            String accountKeyEnc = Base64.getEncoder().encodeToString((authenticationKey + ":"
                    + authenticationKey).getBytes());
            connection.setRequestProperty("Authorization", "Basic " + accountKeyEnc);
        }
        String line;
        StringBuilder response = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                response.append(line);

            }
        } catch (FileNotFoundException e) {
            System.out.println("Not found");
        }

        return response;
    }


    public static AnalysisResults getExtractedEntities(String phrase) throws IOException {
        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                "2018-03-16",
                "e0d43a04-6901-4cbc-8652-bd6dad5886cb",
                "eYrsjmHIcY3s"
        );


        EntitiesOptions entitiesOptions = new EntitiesOptions.Builder()
                .emotion(true)
                .sentiment(true)
                .limit(10)
                .build();
        KeywordsOptions keywordsOptions = new KeywordsOptions.Builder()
                .emotion(false)
                .sentiment(true)
                .limit(20)
                .build();

        Features features = new Features.Builder()
                .entities(entitiesOptions)
                .keywords(keywordsOptions)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(phrase)
                .features(features)
                .build();

        AnalysisResults response = service
                .analyze(parameters)
                .execute();
        return response;
    }


}