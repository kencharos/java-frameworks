Java Frameworks
---------------

+ Micronaut (run on 8080 port)
+ quarkus   (run on 8081 port)
+ Helidon SE (run on 8082 port)
+ Helidon MP (run on 8083 port)

## NOTICE

書籍執筆時のソースコードは、BOOK_PUBLISHED タグの内容を参照してください。

## pre requirement

+ jdk 11 (11.0.5.hs-adpt )
+ maven
+ gradle 

## Micronaut

v 1.2.3 -> v1.2.6(BOOK) -> v1.3.1

use micronaut-cli (mn) 

if use IDE, set annotation processor!

```bash
mn create-app minjava.frameworks.micronaut.micronaut-sample
```

run on port 8080

### build

```
./gradlew shadowJar
java -jar build/libs/micronaut-sample-0.1-all.jar
```

run at 1740ms.
oneJar size : 16.2 MB


### micronaut AWS Lambda

```bash
mn create-function minjava.frameworks.micronaut.micronaut-lambda-sample
```

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
| 192| 153MB | 20247 ms  | 1.95 ms | 
| 256| 154MB | 14600 ms  | 1.80 ms | 
| 512 | 131 MB|  7000 ms | 1.80 ms | 


## quarkus

v 0.23.1 -> v 1.0.0.Final(BOOK) -> 1.2.0.Final
```bash
mvn io.quarkus:quarkus-maven-plugin:1.2.0.Final:create \
    -DprojectGroupId=minjava.frameworks \
    -DprojectArtifactId=quarkus-sample \
    -DclassName="minjava.frameworks.quarkus.GreetingResources" \
    -Dpath="/greeting"
```

`./mvnw compile quarkus:dev`

run on port 8083

### build

JVM

```
./mvnw package
 java -jar target/quarkus-sample-1.0-SNAPSHOT-runner.jar 
```

run on 1.2s ->  0.932s.
jar all size 13MB


### native (with graalVM local)

set upg graalvm native image.

+ install graalvm (19.3.1r11 is Ok in 1.2.0 Final)
+ set JAVA_HOME, GRAALVM_HOME
+ add native-image via `gu install native-image`

```
./mvnw clean -P native package
./traget/quarkus-sample-1.0-SNAPSHOT-runner
```

logs 

```
[INFO] [io.quarkus.deployment.pkg.steps.NativeImageBuildStep] Running Quarkus native-image plugin on GraalVM Version 19.3.1 CE
[INFO] [io.quarkus.deployment.pkg.steps.NativeImageBuildStep] <mysuerdir>/.sdkman/candidates/java/current/bin/native-image -J-Dsun.nio.ch.maxUpdateArraySize=100 -J-Djava.util.logging.manager=org.jboss.logmanager.LogManager -J-Dvertx.logger-delegate-factory-class-name=io.quarkus.vertx.core.runtime.VertxLogDelegateFactory -J-Dvertx.disableDnsResolver=true -J-Dio.netty.leakDetection.level=DISABLED -J-Dio.netty.allocator.maxOrder=1 --initialize-at-build-time= -H:InitialCollectionPolicy=com.oracle.svm.core.genscavenge.CollectionPolicy$BySpaceAndTime -jar quarkus-sample-1.0-SNAPSHOT-runner.jar -H:FallbackThreshold=0 -H:+ReportExceptionStackTraces -H:-AddAllCharsets -H:EnableURLProtocols=http,https --enable-all-security-services -H:+JNI --no-server -H:-UseServiceLoaderFeature -H:+StackTrace quarkus-sample-1.0-SNAPSHOT-runner
[quarkus-sample-1.0-SNAPSHOT-runner:77835]    classlist:   7,760.04 ms
[quarkus-sample-1.0-SNAPSHOT-runner:77835]        (cap):   2,462.42 ms
[quarkus-sample-1.0-SNAPSHOT-runner:77835]        setup:   4,008.87 ms
10:26:01,187 INFO  [org.jbo.threads] JBoss Threads version 3.0.0.Final
[quarkus-sample-1.0-SNAPSHOT-runner:77835]   (typeflow):  58,590.11 ms
[quarkus-sample-1.0-SNAPSHOT-runner:77835]    (objects):  32,168.55 ms
[quarkus-sample-1.0-SNAPSHOT-runner:77835]   (features):   1,401.01 ms
[quarkus-sample-1.0-SNAPSHOT-runner:77835]     analysis:  95,447.13 ms
[quarkus-sample-1.0-SNAPSHOT-runner:77835]     (clinit):   1,122.95 ms
[quarkus-sample-1.0-SNAPSHOT-runner:77835]     universe:   3,394.18 ms
[quarkus-sample-1.0-SNAPSHOT-runner:77835]      (parse):   6,703.64 ms
[quarkus-sample-1.0-SNAPSHOT-runner:77835]     (inline):  10,067.30 ms
[quarkus-sample-1.0-SNAPSHOT-runner:77835]    (compile):  70,932.95 ms
[quarkus-sample-1.0-SNAPSHOT-runner:77835]      compile:  92,806.42 ms
[quarkus-sample-1.0-SNAPSHOT-runner:77835]        image:   8,363.35 ms
[quarkus-sample-1.0-SNAPSHOT-runner:77835]        write:   1,792.09 ms
[quarkus-sample-1.0-SNAPSHOT-runner:77835]      [total]: 213,991.33 ms
[INFO] [io.quarkus.deployment.QuarkusAugmentor] Quarkus augmentation completed in 217702ms
```

### native (with Docker)

if use Java11, explicit graal version via `quarkus.native.builder-image` option.

```bash
./mvnw package -Pnative -Dquarkus.native.container-build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-native-image:19.3.1-java11
# generate target/quarkus-sample-1.0-SNAPSHOT-runner
# pack docker
docker build -t qurkussample -f Dockerfile.native .
docker run -p 8083:8083 qurkussample
```

48.1MB
run at 0.012 

### changes in 1.0.1

+ pom.xml, dockerfile
+ extension install "opentracing" -> "smallrye-opentracing"
+ annotaion "@SubtractTest" -> "@NativeImageTest"
+ graalVM version "19.1" -> "19.2"
+ docker build option, `-Dnative-image.docker-build=true` -> `-Dquarkus.native.container-build=true`


### changes in 1.2.0

+ pom.xml, dockerfile
+ GraalVM 19.2 -> 19.3.1r11


## helidon

### SE 

v 1.3.0 -> v 1.4.0

```bash
mvn archetype:generate -DinteractiveMode=false \
    -DarchetypeGroupId=io.helidon.archetypes \
    -DarchetypeArtifactId=helidon-quickstart-se \
    -DarchetypeVersion=1.4.0 \
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

run at 1236ms.

all jar ziped size : 12.9 MB

#### changes 1.4.0

metrics -> metrics2.
spinup 1100ms -> 1236ms

### MP 

v 1.3.0 -> 1.4.0

```bash
mvn archetype:generate -DinteractiveMode=false \
    -DarchetypeGroupId=io.helidon.archetypes \
    -DarchetypeArtifactId=helidon-quickstart-mp \
    -DarchetypeVersion=1.4.0 \
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

run at 3613 ms.
zipped app jar size: 24.1MB (include hikariCP extension)

NOTE: CDI拡張を抜いた場合

run at 3160 ms.
zipped app jar size: 21.5 MB.

CDIの初期化がやはり重め。

## spring boot 2.2

比較として。Micronautと同構成となる、 
ReactiveWeb, prometheus, 分散トレーシング(sleuth+zipkin)、HTTPクライアントを構築。

Jar size: 30MB
run at 5000ms

### set lazy-initialize

lazy-initializeを設定してみたがほぼ変わらず。
規模が大きくならないと効いてこない。

### Spring Cloudを抜く

SpringCloudSleuthを削除した場合、

Jar size: 21MB
run at 2800ms

## monitoring

in monitoring dir, zipkin, prometheus, grafana setup with docker.
run `docker-compose up -d` in monitoring.