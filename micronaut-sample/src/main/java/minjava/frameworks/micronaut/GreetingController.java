package minjava.frameworks.micronaut;

import io.micrometer.core.instrument.MeterRegistry;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

@Controller
public class GreetingController {

    private final MeterRegistry meterRegistry;

    private final GreetingClient client;

    public GreetingController(MeterRegistry meterRegistry, GreetingClient client) {
        this.meterRegistry = meterRegistry;
        this.client = client;
    }

    @Get("/greeting")
    public Greeting greeting() {
        meterRegistry.counter("call_greeting").increment();
        return new Greeting("micronaut", "this is micronaut service");
    }

    @Get("/greetings")
    public Greetings collectGreeting() {
        Greetings other =  client.collectGreetings();
        return other.add(greeting());

    }

    @Get("/greetingsRx")
    public Single<Greetings> collectGreetingRx() {
        return client.collectGreetingsRx()
                     .map(other -> other.add(greeting()));

    }

}
