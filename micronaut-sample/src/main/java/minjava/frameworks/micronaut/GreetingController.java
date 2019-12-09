package minjava.frameworks.micronaut;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

@Controller
public class GreetingController {

    private final Counter counter;

    private final GreetingClient client;


    public GreetingController(MeterRegistry meterRegistry, GreetingClient client) {
        this.counter = meterRegistry.counter("call_greeting");
        this.client = client;
    }

    @Get("/greeting")
    public Greeting greeting() {
        counter.increment();
        return new Greeting("micronaut", "this is micronaut service");
    }

    @Get("/greetings")
    public Greetings collectGreeting() {
        Greetings other =  client.collectGreetings();
        System.out.println(other);
        return other.add(greeting());

    }

    @Get("/greetingsRx")
    public Single<Greetings> collectGreetingRx() {
        return client.collectGreetingsRx()
                     .map(other -> other.add(greeting()));

    }

}
