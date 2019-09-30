package minjava.frameworks.micronaut;

import io.micronaut.runtime.Micronaut;
import io.micronaut.tracing.brave.instrument.http.BraveTracingClientFilter;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}