package minjava.frameworks.sprinng.springsample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import io.micrometer.core.instrument.MeterRegistry;
import reactor.core.publisher.Mono;

@RestController
public class GreetingController {

    private final MeterRegistry metric;
    private final WebClient client;
    GreetingController(MeterRegistry metric, @Value("${next.endpoint}") String endpoint) {
        this.metric = metric;
        this.client = WebClient.create(endpoint);
    }

    @GetMapping("/greeting")
    public Greeting greet() {
        metric.counter("call_greeting").increment();
        return new Greeting("spring", "this is spring app");
    }

    @GetMapping("/greetings")
    public Mono<Greeting> callRemote() {
        return client.get().uri("/greeting").retrieve().bodyToMono(Greeting.class);
    }

}
