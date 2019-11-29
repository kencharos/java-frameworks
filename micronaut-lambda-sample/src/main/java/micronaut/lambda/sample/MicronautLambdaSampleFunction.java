package micronaut.lambda.sample;

import io.micronaut.function.executor.FunctionInitializer;
import io.micronaut.function.FunctionBean;
import javax.inject.*;
import java.util.function.Function;

@FunctionBean("micronaut-lambda-sample")
public class MicronautLambdaSampleFunction
        extends FunctionInitializer
        implements Function<LambdaInput, LambdaOutput> {

    @Inject
    SampleService service;

    @Override
    public LambdaOutput apply(LambdaInput input) {
        var result = service.greet(input.getName());
        return new LambdaOutput(result);
    }
}
