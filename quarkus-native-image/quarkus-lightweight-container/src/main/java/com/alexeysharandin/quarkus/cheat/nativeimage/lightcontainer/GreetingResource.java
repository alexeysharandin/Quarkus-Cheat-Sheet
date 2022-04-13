package com.alexeysharandin.quarkus.cheat.nativeimage.lightcontainer;


import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.Uni;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/hello")
public class GreetingResource {
    void onLoad(@Observes StartupEvent event) {
        System.out.println("GreetingResource.OnLoad");
        System.out.println("Run http://localhost:8080/hello/reactive to check reactive rest");
        System.out.println("Run http://localhost:8080/hello/static to check non-reactive rest");
    }

    @GET
    @Path("reactive")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> helloReactive() {
        return Uni.createFrom().item("Hello from Uni");
    }

    @GET
    @Path("static")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from String";
    }
}