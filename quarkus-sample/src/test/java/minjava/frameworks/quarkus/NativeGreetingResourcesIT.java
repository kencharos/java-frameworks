package minjava.frameworks.quarkus;


import io.quarkus.test.junit.NativeImageTest;

// ./mvnw verify -Pnative for test with native
@NativeImageTest
public class NativeGreetingResourcesIT extends GreetingResourcesTest {

}
