Java Frameworks
---------------

+ Helidon SE
+ Helidon MP
+ Micronaut
+ quarkus

## pre requirement

+ jdk 11 (11.0.5.hs-adpt )
    + notice, compiler level set to jdk8
+ maven
+ gradle 

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


## Micronaut

v 1.2.3 -> v1.2.6

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

run at 1740ms.
oneJar size : 16.2 MB


### micronaut AWS Lambda

```bash
mn create-function minjava.frameworks.micronaut.micronaut-lambda-sample
```

fix micronaut-aws version 1.3.3 for running AWS Lambda java 11 runtime.

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

v 0.23.1 -> v 1.0.0.Final

```bash
mvn io.quarkus:quarkus-maven-plugin:1.0.0.Final:create \
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

+ install graalvm (19.2.1 is Ok in 1.0.0 Final)
+ set JAVA_HOME, GRAALVM_HOME
+ add native-image by `gu install native-image`

```
./mvnw clean -P native package
./traget/quarkus-sample-1.0-SNAPSHOT-runner
```

logs 

```
[INFO] [io.quarkus.deployment.pkg.steps.NativeImageBuildStep] Running Quarkus native-image plugin on OpenJDK 64-Bit Server VM
[INFO] [io.quarkus.deployment.pkg.steps.NativeImageBuildStep] /Users/--/.sdkman/candidates/java/19.2.1-grl/bin/native-image -J-Dsun.nio.ch.maxUpdateArraySize=100 -J-Djava.util.logging.manager=org.jboss.logmanager.LogManager -J-Dvertx.logger-delegate-factory-class-name=io.quarkus.vertx.core.runtime.VertxLogDelegateFactory -J-Dvertx.disableDnsResolver=true -J-Dio.netty.leakDetection.level=DISABLED -J-Dio.netty.allocator.maxOrder=1 --initialize-at-build-time= -H:InitialCollectionPolicy=com.oracle.svm.core.genscavenge.CollectionPolicy$BySpaceAndTime -jar quarkus-sample-1.0-SNAPSHOT-runner.jar -J-Djava.util.concurrent.ForkJoinPool.common.parallelism=1 -H:FallbackThreshold=0 -H:+ReportExceptionStackTraces -H:-AddAllCharsets -H:EnableURLProtocols=http,https --enable-all-security-services -H:+JNI --no-server -H:-UseServiceLoaderFeature -H:+StackTrace quarkus-sample-1.0-SNAPSHOT-runner
[quarkus-sample-1.0-SNAPSHOT-runner:48656]    classlist:   7,954.60 ms
[quarkus-sample-1.0-SNAPSHOT-runner:48656]        (cap):   3,359.94 ms
[quarkus-sample-1.0-SNAPSHOT-runner:48656]        setup:   5,133.19 ms
04:14:59,253 INFO  [org.jbo.threads] JBoss Threads version 3.0.0.Final
[quarkus-sample-1.0-SNAPSHOT-runner:48656]   (typeflow):  27,480.22 ms
[quarkus-sample-1.0-SNAPSHOT-runner:48656]    (objects):  15,754.88 ms
[quarkus-sample-1.0-SNAPSHOT-runner:48656]   (features):     829.36 ms
[quarkus-sample-1.0-SNAPSHOT-runner:48656]     analysis:  46,038.16 ms
[quarkus-sample-1.0-SNAPSHOT-runner:48656]     (clinit):     911.21 ms
[quarkus-sample-1.0-SNAPSHOT-runner:48656]     universe:   2,412.26 ms
[quarkus-sample-1.0-SNAPSHOT-runner:48656]      (parse):   5,162.33 ms
[quarkus-sample-1.0-SNAPSHOT-runner:48656]     (inline):  10,605.25 ms
[quarkus-sample-1.0-SNAPSHOT-runner:48656]    (compile):  42,685.36 ms
[quarkus-sample-1.0-SNAPSHOT-runner:48656]      compile:  60,694.33 ms
[quarkus-sample-1.0-SNAPSHOT-runner:48656]        image:   2,967.48 ms
[quarkus-sample-1.0-SNAPSHOT-runner:48656]        write:   1,100.40 ms
[quarkus-sample-1.0-SNAPSHOT-runner:48656]      [total]: 127,191.63 ms
[INFO] [io.quarkus.deployment.QuarkusAugmentor] Quarkus augmentation completed in 130947ms
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  02:24 min
[INFO] Finished at: 2019-11-27T04:16:51+09:00
[INFO] ------------------------------------------------------------------------

```

### native (with Docker)

```bash
./mvnw package -Pnative -Dquarkus.native.container-build=true
# generate target/quarkus-sample-1.0-SNAPSHOT-runner
# pack docker
docker build -t qurkussample -f Dockerfile.native .
docker run -p 8083:8083 qurkussample
```

42.5MB
run at 0.012 

### changes in 1.0.1

+ pom.xml, dockerfile
+ extension install "opentracing" -> "smallrye-opentracing"
+ annotaion "@SubtractTest" -> "@NativeImageTest"
+ graalVM version "19.1" -> "19.2"
+ docker build option, `-Dnative-image.docker-build=true` -> `-Dquarkus.native.container-build=true`


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