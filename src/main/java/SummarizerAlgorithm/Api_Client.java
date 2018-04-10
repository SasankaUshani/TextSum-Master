package SummarizerAlgorithm;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;

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

    public static ArrayList<StringBuilder> getHTMLContent(ArrayList newsUrl) throws IOException, InterruptedException {
        ArrayList<StringBuilder> descriptionList = new ArrayList();
        Document document;
        for (int i = 0; i < newsUrl.size(); i++) {

            //create jsoup connection with user agent
            Connection.Response response = Jsoup.connect((String) newsUrl.get(i))
                    .ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .timeout(12000)
                    .followRedirects(true)
                    .execute();

            document = response.parse();
            System.out.println("url - " + newsUrl.get(i));
            String title = document.title();
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
            System.out.println(descriptionList.get(0));
        }

        return descriptionList;

    }

    public static ArrayList<String> getJsonContent(StringBuilder stringBuilder) throws IOException {
        JsonParser jsonParser = new JsonParser();
        JsonObject responseObj = (JsonObject) jsonParser.parse(stringBuilder.toString());
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("News"));
        ArrayList<String> urlList = new ArrayList<>();
        for (int i = 0; i < responseObj.get("articles").getAsJsonArray().size(); i++) {

            String newsURL = responseObj.get("articles").getAsJsonArray().get(i).getAsJsonObject().get("url").getAsString();
//            System.out.println("Json : " + newsURL);
            fileWriter.write(newsURL);
            fileWriter.write(" - ");
            fileWriter.newLine();
            urlList.add(newsURL);
        }
        fileWriter.close();

        return urlList;
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


    public static void synonym(String word) throws IOException {
        StringBuilder builder =
                httpClient("http://words.bighugelabs.com/api/2/" + SYNONYM_API_KEY + "/" + word + "/json", null);
        JsonParser jsonParser = new JsonParser();
        try {
            JsonObject responseObj = (JsonObject) jsonParser.parse(builder.toString());


            JsonObject verbObj = responseObj.get("verb").getAsJsonObject();
            JsonArray synArray = verbObj.get("syn").getAsJsonArray();

            for (int i = 0; i < synArray.size(); i++) {
                System.out.println("synonym " + i + " :" + synArray.get(i));

            }

        } catch (NullPointerException e) {
        }
    }
}