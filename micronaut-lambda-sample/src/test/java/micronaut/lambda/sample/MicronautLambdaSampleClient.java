package micronaut.lambda.sample;

import io.micronaut.function.client.FunctionClient;
import io.micronaut.http.annotation.Body;
import io.reactivex.Single;
import javax.inject.Named;

@FunctionClient
public interface MicronautLambdaSampleClient {

    @Named("micronaut-lambda-sample")
    Single<LambdaInput> apply(@Body LambdaInput body);

}
