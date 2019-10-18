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

NOTE: CDI拡張を抜いた場合

run at 3500 ms.
zipped app jar size: 21.4 MB.

CDIの初期化がやはり重め。


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

v 0.23.1

```bash
mvn io.quarkus:quarkus-maven-plugin:0.22.0:create \
    -DprojectGroupId=minjava.frameworks \
    -DprojectArtifactId=quarkus-sample \
    -DclassName="minjava.frameworks.quarkus.HelloResources" \
    -Dpath="/hello"
```

`./mvnw compile quarkus:dev`

run on port 8083

### build

JVM

```
./mvnw package
 java -jar target/quarkus-sample-1.0-SNAPSHOT-runner.jar 
```

run on 1.2s
jar all size 15.5MB


### native (with graalVM local)

set upg graalvm native image.

+ install graalvm
+ set JAVA_HOME, GRAALVM_HOME
+ add native-image `gu install native-image`

```
./mvnw clean -P native package
./traget/quarkus-sample-1.0-SNAPSHOT-runner
```

logs 

```
[INFO] [io.quarkus.creator.phase.nativeimage.NativeImagePhase] Running Quarkus native-image plugin on OpenJDK 64-Bit Server VM
[INFO] [io.quarkus.creator.phase.nativeimage.NativeImagePhase] /Users/--/.sdkman/candidates/java/19.2.0.1-grl/bin/native-image -J-Dsun.nio.ch.maxUpdateArraySize=100 -J-Djava.util.logging.manager=org.jboss.logmanager.LogManager -J-Dvertx.logger-delegate-factory-class-name=io.quarkus.vertx.core.runtime.VertxLogDelegateFactory -J-Dio.netty.noUnsafe=true -J-Dio.netty.leakDetection.level=DISABLED -J-Dvertx.disableDnsResolver=true --initialize-at-build-time= -H:InitialCollectionPolicy=com.oracle.svm.core.genscavenge.CollectionPolicy$BySpaceAndTime -jar quarkus-sample-1.0-SNAPSHOT-runner.jar -J-Djava.util.concurrent.ForkJoinPool.common.parallelism=1 -H:FallbackThreshold=0 -H:+ReportExceptionStackTraces -H:+PrintAnalysisCallTree -H:-AddAllCharsets -H:EnableURLProtocols=http,https --enable-all-security-services -H:+JNI --no-server -H:-UseServiceLoaderFeature -H:+StackTrace
[quarkus-sample-1.0-SNAPSHOT-runner:5530]    classlist:   9,468.41 ms
[quarkus-sample-1.0-SNAPSHOT-runner:5530]        (cap):   3,552.72 ms
[quarkus-sample-1.0-SNAPSHOT-runner:5530]        setup:   5,948.83 ms
14:23:37,293 INFO  [org.jbo.threads] JBoss Threads version 3.0.0.Final
[quarkus-sample-1.0-SNAPSHOT-runner:5530]   (typeflow):  40,134.34 ms
[quarkus-sample-1.0-SNAPSHOT-runner:5530]    (objects):  20,866.10 ms
[quarkus-sample-1.0-SNAPSHOT-runner:5530]   (features):   1,115.80 ms
[quarkus-sample-1.0-SNAPSHOT-runner:5530]     analysis:  64,738.23 ms
Printing call tree to /--/java-frameworks/quarkus-sample/target/reports/call_tree_quarkus-sample-1.0-SNAPSHOT-runner_20191001_142454.txt
Printing list of used classes to /--/java-frameworks/quarkus-sample/target/reports/used_classes_quarkus-sample-1.0-SNAPSHOT-runner_20191001_142458.txt
Printing list of used packages to /--/java-frameworks/quarkus-sample/target/reports/used_packages_quarkus-sample-1.0-SNAPSHOT-runner_20191001_142458.txt
[quarkus-sample-1.0-SNAPSHOT-runner:5530]     (clinit):     876.25 ms
[quarkus-sample-1.0-SNAPSHOT-runner:5530]     universe:   2,825.18 ms
[quarkus-sample-1.0-SNAPSHOT-runner:5530]      (parse):   6,398.34 ms
[quarkus-sample-1.0-SNAPSHOT-runner:5530]     (inline):   9,834.44 ms
[quarkus-sample-1.0-SNAPSHOT-runner:5530]    (compile): 110,642.99 ms
[quarkus-sample-1.0-SNAPSHOT-runner:5530]      compile: 137,298.65 ms
[quarkus-sample-1.0-SNAPSHOT-runner:5530]        image:   9,858.41 ms
[quarkus-sample-1.0-SNAPSHOT-runner:5530]        write:   2,038.06 ms
[quarkus-sample-1.0-SNAPSHOT-runner:5530]      [total]: 252,634.41 ms
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  04:25 min
[INFO] Finished at: 2019-10-01T14:27:32+09:00
[INFO] ------------------------------------------------------------------------

```

### native (with Docker)

```bash
mvn package -Pnative -Dnative-image.docker-build=true
# generate target/quarkus-sample-1.0-SNAPSHOT-runner
# pack docker
docker build -t qurkussample -f Dockerfile.native .
docker run -p 8083:8083 qurkussample
```

43.5MB
run at 0.012 

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