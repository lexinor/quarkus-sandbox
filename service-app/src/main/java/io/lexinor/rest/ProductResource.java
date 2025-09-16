package io.lexinor.rest;

import io.lexinor.db.Users;
import io.lexinor.domain.Product;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("api")
public class ProductResource {

    @RestClient
    FakeShopRestClient fakeShopClient;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance products(List<Product> products);
    }

    @GET
    @Path("/product")
    @Produces(MediaType.TEXT_HTML)
    public Uni<TemplateInstance> products() {
        return Uni.createFrom().item(fakeShopClient.toto())
            .onItem().transformToUni(listAsUni -> listAsUni
                .onItem().transformToUni(products -> Uni.createFrom().item(Templates.products(products))));
    }

    @GET
    @Path("users")
    public Uni<List<Users>> users() {
        return Uni.createFrom().item(Users.listAll());
    }

    @POST
    @Path("users")
    public Boolean users(Users users) {
        System.out.println("ProductResource.users");
        return true;
    }
}
