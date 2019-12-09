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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/greetings")
@ApplicationScoped
public class GreetingsResource {

    private final GreetingService service;
    private final int greetingId;

    @Inject
    public GreetingsResource(GreetingService service, @ConfigProperty(name = "greeting.id") int greetingId) {
        this.service = service;
        this.greetingId = greetingId;
    }

    /**
     * curl http://localhost:8083/greetings
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Greetings greeting() {
        String message = service.getMessage(greetingId);
        return new Greetings(new Greeting("helidon-mp", message));
    }

}
