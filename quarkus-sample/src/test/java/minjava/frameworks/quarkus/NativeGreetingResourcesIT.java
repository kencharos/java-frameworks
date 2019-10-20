package minjava.frameworks.quarkus;


import io.quarkus.test.junit.SubstrateTest;

// ./mvnw package -Pnative for test with native
@SubstrateTest
public class NativeGreetingResourcesIT extends GreetingResourcesTest {

}
