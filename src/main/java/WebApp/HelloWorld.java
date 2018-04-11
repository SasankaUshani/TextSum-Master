package WebApp;

import Database.PostgreSQLJDBC;
import Main.ExecuteTextSum;
import Models.Sentence;
import SummarizerAlgorithm.Api_Client;
import SummarizerAlgorithm.SentenceScoreCalculator;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.json.Json;
//import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;


import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/getSummarizedNews")
public class HelloWorld {

//    @Produces("text/plain")
//    public String getClichedMessage() {
//        return "Coming from restAPI test test test";
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClichedMessage() throws IOException, InterruptedException, JSONException {

//      start
        ArrayList newsObject;
        String title = "initial title";
        JSONObject jsonObject = new JSONObject();
        try {
            ExecuteTextSum executeTextSum = new ExecuteTextSum();
            newsObject = executeTextSum.execute();

            title = newsObject.get(1).toString();
            jsonObject.put("title", title);
        } catch (IOException e) {

        }

//        end



//        JsonObject personObject = Json.createObjectBuilder()
//                .add("title", title)
//                .add("date", "10/04/2018")
//                .add("image", "https://timedotcom.files.wordpress.com/2018/03/donald-trump-trade-wars-change-mind.jpg")
//                .add("description", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.\n"
//                )
//                .add("phoneNumber",
//                        Json.createArrayBuilder().add("00-000-0000")
//                                .add("11-111-1111")
//                                .add("11-111-1112")
//                                .build()
//                )
//                .build();

        return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(jsonObject.toString()).build();
    }

//    @GET
//    @Path("/getScoredSentences")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getScoredSentences(){
//
//    }


}