package minjava.frameworks.quarkus;

import java.util.concurrent.CompletionStage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * RestClient, set url in application.properties
 */
@Path("/greetings")
@RegisterRestClient
public interface GreetingClient {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Greetings fetchGreetings();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    CompletionStage<Greetings> fetchGreetingsAsync();
}
