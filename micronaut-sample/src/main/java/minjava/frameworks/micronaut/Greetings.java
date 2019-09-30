package minjava.frameworks.micronaut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Greetings {

    private List<Greeting> greetings = new ArrayList<>();

    public Greetings(){}

    public Greetings(List<Greeting> greetings) {
        this.greetings = greetings;
    }
    public Greetings(Greeting greeting) {
        this(Arrays.asList(greeting));
    }

    public void setGreetings(List<Greeting> greetings) {
        this.greetings = greetings;
    }

    public List<Greeting> getGreetings() {
        return greetings;
    }

    public Greetings add(Greetings g) {
        List<Greeting> added = Stream.concat(greetings.stream(), g.greetings.stream())
                                     .collect(Collectors.toList());

        return new Greetings(added);
    }
}
