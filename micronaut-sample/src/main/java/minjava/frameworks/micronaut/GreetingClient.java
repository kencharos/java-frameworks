package minjava.frameworks.micronaut;


import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

@Client("${sample.next}")
public interface GreetingClient {

    @Get("/greeting")
    Greetings collectGreetings();

    @Get("/greeting")
    Single<Greetings> collectGreetingsRx();
}
