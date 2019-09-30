
## extension

update pom or mvn

pom
```xml
    <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-rest-client</artifactId>
    </dependency>
    <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-resteasy-jsonb</artifactId>
    </dependency>
```

mvn

```
./mvnw quarkus:add-extension -Dextension=rest-client
./mvnw quarkus:add-extension -Dextension=resteasy-jsonb

```
