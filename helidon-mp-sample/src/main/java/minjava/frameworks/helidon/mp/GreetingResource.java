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

package minjava.frameworks.helidon.mp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Counted;

/**
 * Sample Endpoing get and post
 */
@Path("/greeting")
@ApplicationScoped
public class GreetingResource {

    private final GreetingService service;
    private final int greetingId;

    @Inject
    public GreetingResource(GreetingService service, @ConfigProperty(name = "greeting.id") int greetingId) {
        this.service = service;
        this.greetingId = greetingId;
    }

    /**
     * curl http://localhost:8083/greeting
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name="call_greeting") // custom metrics
    public Greeting greeting() {
        String message = service.getMessage(greetingId);
        return new Greeting("helidon-mp", message);
    }

    /**
     * curl -X POST http://localhost:8083/greeting -H "content-type: application/json" -d '{"id":1, "message":"<your preffer>"}'
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateMessage(GreetUpdate body) {

        service.updateMessage(greetingId, body.getMessage());
    }
}
