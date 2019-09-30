package minjava.frameworks.micronaut;

import java.util.concurrent.Flow.Publisher;

import io.micrometer.core.instrument.MeterRegistry;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

@Controller
public class SampleController {

    private final MeterRegistry meterRegistry;

    private final SampleClient client;

    public SampleController(MeterRegistry meterRegistry, SampleClient client) {
        this.meterRegistry = meterRegistry;
        this.client = client;
    }

    @Get("/greeting")
    public Greetings greeting() {
        meterRegistry.counter("call_greeting").increment();
        return new Greetings(new Greeting("micronaut-sample", "this micronaut greeting"));
    }


    @Get("/callRemote")
    public Single<Greetings> callRemote() {
        return client.callRemote()
                .map(other -> other.add(greeting()));

    }


}
