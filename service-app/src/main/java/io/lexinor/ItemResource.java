package io.lexinor;

import io.lexinor.domain.Product;
import io.lexinor.rest.FakeShopRestClient;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("item")
public class ItemResource {

    @RestClient
    FakeShopRestClient fakeShopClient;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance products(List<Product> products);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Uni<TemplateInstance> item() {

        return Uni.createFrom().item(fakeShopClient.toto())
            .onItem().transformToUni(listUni -> listUni
                .onItem().transformToUni(products -> Uni.createFrom().item(Templates.products(products))));
    }
}
