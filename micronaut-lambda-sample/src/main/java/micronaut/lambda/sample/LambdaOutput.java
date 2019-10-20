package micronaut.lambda.sample;

public class LambdaOutput {

    private final String message;

    public LambdaOutput(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

