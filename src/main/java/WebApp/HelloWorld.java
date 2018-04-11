package WebApp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import com.google.gson.JsonObject;
import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;


// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorld {
    @GET
//    @Produces("text/plain")
//    public String getClichedMessage() {
//        return "Coming from restAPI test test test";
//    }


    @Produces(MediaType.APPLICATION_JSON)
    public Response getClichedMessage() {
        JsonObject personObject = Json.createObjectBuilder()
                .add("title", "Donuld trump decide to launch the neuclear bomb")
                .add("date", "10/04/2018")
                .add("image", "https://timedotcom.files.wordpress.com/2018/03/donald-trump-trade-wars-change-mind.jpg")
                .add("description", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.\n"
                )
                .add("phoneNumber",
                        Json.createArrayBuilder().add("00-000-0000")
                                .add("11-111-1111")
                                .add("11-111-1112")
                                .build()
                )
                .build();

        return Response.status(200).entity(personObject.toString()).build();
    }


}