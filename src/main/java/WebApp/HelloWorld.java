package WebApp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;


// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorld {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content


        return "Coming from restAPI";
    }


//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getClichedMessage() {
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("name","Sasanka");
//
//        // Return some cliched textual content
//        String text = "Finally it worked bitch";
//
//        return Response.status(200).entity(jsonObject.toString()).build();
//    }


}