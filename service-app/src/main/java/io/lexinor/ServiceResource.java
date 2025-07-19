package io.lexinor;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("service")
public class ServiceResource {

    @GET
    @Path("async")
    public Uni<String> async() {
        return Uni.createFrom().item("Hello from Quarkus REST");
    }
}
