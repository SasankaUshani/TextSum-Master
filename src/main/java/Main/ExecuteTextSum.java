package Main;

import Database.PostgreSQLJDBC;
import Models.Sentence;
import SummarizerAlgorithm.Api_Client;
import SummarizerAlgorithm.SentenceScoreCalculator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;
import org.codehaus.jettison.json.JSONException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExecuteTextSum {
    public static void main(String[] args) throws IOException, JSONException {
        ArrayList<String> arrayList = new ArrayList<>();

//        PostgreSQLJDBC postgreSQLJDBC = new PostgreSQLJDBC();
//        postgreSQLJDBC.createConnection();
//        postgreSQLJDBC.createTable();
//        return postgreSQLJDBC.retreiveNews();
//        System.out.println(arrayList.get(0));


        String text = "The cricket match between Sri Lanka and England was held last Saturday at Colombo. Both teams gave a great fight till the last innings and Kumar Sangakkar managed to score 153. Kumar Sangakkar became the man of the match. England won the match by 30 runs.";
        Api_Client api_client = new Api_Client();
        List entityList = api_client.getExtractedEntities(text);
        replaceTemplate(entityList);


    }

    private static void replaceTemplate(List entityList) throws IOException {
        Path file = Paths.get("/Users/sasankakudagoda/Desktop/IIT/TextSum/TextSum Master/src/main/java/Template/Cricket.txt");
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);

        JsonParser jsonParser = new JsonParser();

        JsonObject jsonObject = (JsonObject) jsonParser.parse(entityList.get(1).toString());
        String country1 = String.valueOf(jsonObject.get("text"));
        String firstLine = lines.get(0).replace("country1", country1);

        jsonObject = (JsonObject) jsonParser.parse(entityList.get(2).toString());
        String country2 = String.valueOf(jsonObject.get("text"));
        firstLine = firstLine.replace("country2", country2);

        jsonObject = (JsonObject) jsonParser.parse(entityList.get(3).toString());
        String location = String.valueOf(jsonObject.get("text"));
        firstLine = firstLine.replace("location", location);


        jsonObject = (JsonObject) jsonParser.parse(entityList.get(0).toString());
        String manOftheMatch = String.valueOf(jsonObject.get("text"));
        String seconLine = lines.get(1).replace("person", manOftheMatch);

        firstLine = firstLine+". "+seconLine;
        System.out.println();
        System.out.println();
        System.out.println(" - - - Input text - - - ");
        System.out.println("The cricket match between Sri Lanka and England was held last Saturday at Colombo. Both teams gave a great fight till the last innings and Kumar Sangakkar managed to score 153. Kumar Sangakkar became the man of the match. England won the match by 30 runs.");
        System.out.println(" - - - Abstractive summary - - - ");
        System.out.println(firstLine);

    }
}
