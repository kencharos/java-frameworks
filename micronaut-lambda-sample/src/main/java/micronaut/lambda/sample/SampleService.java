package micronaut.lambda.sample;

import javax.inject.Singleton;

@Singleton
public class SampleService {

    public String greet(String name) {
        return "Hello! " + name;
    }
}
