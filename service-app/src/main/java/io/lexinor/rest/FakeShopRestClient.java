package io.lexinor.rest;

import io.lexinor.domain.Product;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.List;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "fakeshop")
public interface FakeShopRestClient {

  @GET
  @Path("products")
  Uni<List<Product>> toto();
}
