package micronaut.lambda.sample;

import java.util.Map;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class LambdaOutput {

    private final String name;

    private final String message;

    public LambdaOutput(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}

