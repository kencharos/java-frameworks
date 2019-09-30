package micronaut.lambda.sample;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class LambdaInputFunctionTest {

    @Inject
    MicronautLambdaSampleClient client;

    @Test
    public void testFunction() throws Exception {
    	LambdaInput body = new LambdaInput();
    	body.setName("micronaut-lambda-sample");
        assertEquals("micronaut-lambda-sample", client.apply(body).blockingGet().getName());
    }
}
