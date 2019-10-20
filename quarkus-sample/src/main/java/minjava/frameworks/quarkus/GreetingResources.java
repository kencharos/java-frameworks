package minjava.frameworks.quarkus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.annotation.Counted;

@Path("/greeting")
public class GreetingResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name = "call_greeting")
    public Greeting greet() {
        return new Greeting("quarkus", "this is Quarkus sample");
    }
}