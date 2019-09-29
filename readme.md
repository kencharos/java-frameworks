Java Frameworks
---------------

+ Helidon SE
+ Helidon MP
+ Micronaut
+ quarkus

## pre requirement

+ jdk 11
    + notice, compiler level set to jdk8
+ maven
+ gradle 

## helidon

### SE 

v 1.3.0

```bash
mvn archetype:generate -DinteractiveMode=false \
    -DarchetypeGroupId=io.helidon.archetypes \
    -DarchetypeArtifactId=helidon-quickstart-se \
    -DarchetypeVersion=1.3.0 \
    -DgroupId=minjava.frameworks \
    -DartifactId=helidon-se-sample \
    -Dpackage=minjava.frameworks.helidon.se
```

run on port 8080


### MP 

v 1.3.0

```bash
mvn archetype:generate -DinteractiveMode=false \
    -DarchetypeGroupId=io.helidon.archetypes \
    -DarchetypeArtifactId=helidon-quickstart-mp \
    -DarchetypeVersion=1.3.0 \
    -DgroupId=minjava.frameworks \
    -DartifactId=helidon-mp-sample \
    -Dpackage=minjava.frameworks.helidon.mp
```

run on port 8081

## Micronaut

v 1.2.2

use micronaut-cli (mn) 

```bash
mn create-app minjava.frameworks.micronaut.micronaut-sample
```


run on port 8082

## quarkus

v 0.22

```bash
mvn io.quarkus:quarkus-maven-plugin:0.22.0:create \
    -DprojectGroupId=minjava.frameworks \
    -DprojectArtifactId=quarkus-sample \
    -DclassName="minjava.frameworks.quarkus.HelloResources" \
    -Dpath="/hello"
```

`./mvnw compile quarkus:dev`


run on port 8083

## monitoring

in monitoring dir, zipkin, prometheus, grafana setup with docker.
run `docker-compose up -d` in monitoring.