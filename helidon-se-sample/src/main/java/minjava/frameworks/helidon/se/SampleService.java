/*
 * Copyright (c) 2018, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package minjava.frameworks.helidon.se;

import javax.ws.rs.client.Client;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.MetricRegistry;

import io.helidon.config.Config;
import io.helidon.metrics.RegistryFactory;

/**
 * Sample REST Endpoint
 */

public class SampleService {

    private final String nextEndpoint;

    private final Client client;

    private final Counter counter;

    SampleService(Config config, Client client) {

        this.nextEndpoint = config.get("app.sample.next").asString().get();
        this.client = client;

        RegistryFactory metricsRegistry = RegistryFactory.getInstance();
        MetricRegistry appRegistry = metricsRegistry.getRegistry(MetricRegistry.Type.APPLICATION);
        counter = appRegistry.counter("call_greeting");
    }

    public Greetings greet() {

        counter.inc(); // increment custom metrics counter;
        return new Greetings(new Greeting("helidon-se", "hello this is helidon SE"));
    }

    public Greetings callOther() {
        
        Greetings other = client.target(nextEndpoint)
                                .request()
                                .buildGet()
                                .invoke(Greetings.class);

        return other.add(greet());
    }

}
