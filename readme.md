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


#### build

```
mvn clean package
java -jar target/helidon-se-sample.jar
```

run at 1100ms.

all jar ziped size : 10.9 MB

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


#### build

```
mvn clean package
java -jar target/helidon-mp-sample.jar
```

run at 4300 ms.
zipped app jar size: 23MB (include hikariCP extension)

## Micronaut

v 1.2.3

use micronaut-cli (mn) 

if use IDE, set annotation processor!

```bash
mn create-app minjava.frameworks.micronaut.micronaut-sample
```

run on port 8082

### build

```
./gradlew shadowJar
java -jar build/libs/micronaut-sample-0.1-all.jar
```

run at 1800ms.
oneJar size : 14.8 MB


### micronaut AWS Lambda


#### build

```
./gradlw shadowJar
```


upload micronaut-lambda-sample-0.1-all.jar to AWS Lambda,
with handler name `io.micronaut.function.aws.MicronautRequestStreamHandler`


one jar size: 9.5MB.

|memory | use memory | cold | hot |
|----|---|---|---|
| 128| outOfMemory metaspace | - | - |
| 192| 130MS | 20000 ms  | 1.3 ms | 
| 256| 55MB-111MB | 13000 ms - 20000 ms  | 1.3 ms | 
| 512 | 138 MB|  9800 ms | 1.31 ms | 


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