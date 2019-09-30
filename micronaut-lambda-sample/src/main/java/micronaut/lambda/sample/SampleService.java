package micronaut.lambda.sample;

import javax.inject.Singleton;

@Singleton
public class SampleService {

    public String getMessage() {
        return "this is AWS Lambda sample";
    }

}
