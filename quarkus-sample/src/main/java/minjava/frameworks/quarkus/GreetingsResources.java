package minjava.frameworks.quarkus;

import java.util.Arrays;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/greetings")
public class GreetingsResources {

    @Inject
    @RestClient
    GreetingClient client;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<Greetings> collectGreeting() {
        Greeting my = new Greeting("quarkus", "this is quarkus service");

        return client.fetchGreetingAsync()
                .thenApply(other -> new Greetings(Arrays.asList(other, my)));
    }
}