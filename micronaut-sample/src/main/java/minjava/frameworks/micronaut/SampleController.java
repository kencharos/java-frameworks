package minjava.frameworks.micronaut;

import io.micrometer.core.instrument.MeterRegistry;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/greeting")
public class SampleController {

    private final MeterRegistry meterRegistry;

    public SampleController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Get
    public Greetings greeting() {
        meterRegistry.counter("call_greeting").increment();
        return new Greetings(new Greeting("micronaut-sample", "this micronaut greeting"));
    }





}
