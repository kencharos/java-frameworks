package minjava.frameworks.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourcesTest {

    @Test
    public void testGreetingEndpoint() {
        given()
                .when().get("/greeting")
                .then()
                .statusCode(200)
                .body(is("{\"message\":\"this is Quarkus sample\",\"name\":\"quarkus\"}"));
    }
}
