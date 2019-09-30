package micronaut.lambda.sample;

import io.micronaut.core.annotation.*;

@Introspected
public class LambdaInput {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

