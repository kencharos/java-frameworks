# Helidon Quickstart MP Example

This example implements a simple Hello World REST service using MicroProfile

## Build and run

With JDK8+
```bash
mvn package
java -jar target/helidon-mp-sample.jar
```

## Exercise the application

```
curl -X GET http://localhost:8083/greeting
```

