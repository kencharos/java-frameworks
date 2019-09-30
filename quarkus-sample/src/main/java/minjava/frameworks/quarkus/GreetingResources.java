package minjava.frameworks.quarkus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/greeting")
public class GreetingResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting greet() {
        return new Greeting("quarkus", "this is quarkus service");
    }
}