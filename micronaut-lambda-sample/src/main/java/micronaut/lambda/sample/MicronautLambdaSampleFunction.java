package micronaut.lambda.sample;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.discovery.cloud.ComputeInstanceMetadata;
import io.micronaut.function.executor.FunctionInitializer;
import io.micronaut.function.FunctionBean;
import javax.inject.*;
import java.io.IOException;
import java.util.function.Function;

@FunctionBean("micronaut-lambda-sample")
public class MicronautLambdaSampleFunction extends FunctionInitializer implements Function<LambdaInput, LambdaOutput> {

    @Inject
    SampleService service;

    @Override
    public LambdaOutput apply(LambdaInput msg) {

        return new LambdaOutput(msg.getName(), service.getMessage());
    }

    /**
     * This main method allows running the function as a CLI application using: echo '{}' | java -jar function.jar 
     * where the argument to echo is the JSON to be parsed.
     */
    public static void main(String...args) throws IOException {
        MicronautLambdaSampleFunction function = new MicronautLambdaSampleFunction();
        function.run(args, (context)-> function.apply(context.get(LambdaInput.class)));
    }    
}

