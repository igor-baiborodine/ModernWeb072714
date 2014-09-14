package com.farata.course.mwd.auction.service;

import com.farata.course.mwd.auction.data.DataEngine;
import com.farata.course.mwd.auction.entity.Product;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
@Produces("application/json")
public class ProductService {

    DataEngine dataEngine;

    @Inject
    public void setDataEngine(DataEngine dataEngine) {
        this.dataEngine = dataEngine;
    }

    @GET
    public List<Product> getAllProducts() {
        return dataEngine.findAllProducts();
    }

    @GET
    @Path("/featured")
    public Response getFeaturedProducts() {
        final JsonObjectBuilder jsonResult = itemsWithHeading("Featured products");
        return Response.ok(jsonResult.build()).build();

    }

    /**
     * TODO Dumb search implementation. Provide implementation in DataEngine
     *
     * @return
     */
    @GET
    @Path("/search")
    public Response getSearchResults() {
        final JsonObjectBuilder jsonResult = itemsWithHeading("Search results");
        return Response.ok(jsonResult.build()).build();

    }

    private JsonObjectBuilder itemsWithHeading(String heading) {
        JsonArrayBuilder itemsBuilder = Json.createArrayBuilder();
        dataEngine.findAllFeaturedProducts().forEach(product -> {
            itemsBuilder.add(product.getJsonObject());

        });

        return Json.createObjectBuilder()
            .add("heading", heading)
            .add("items", itemsBuilder);
    }

    @GET
    @Path("/{id}/")
    public Response getProductById(@PathParam("id") int productId, @Context HttpHeaders headers) {

        String userAgent = headers.getRequestHeader("user-agent").get(0);
        System.out.println("Got request form " + userAgent);
        Product product = dataEngine.findProductById(productId);


        if (product != null) {
            System.out.println("findProduct method has returned " + product
                .getTitle());
            return Response.ok(product.getJsonObject()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}/jaxb")
    public Product getProductByIdWithBinding(@PathParam("id") int productId,
        @Context HttpHeaders headers) {

        String userAgent = headers.getRequestHeader("user-agent").get(0);
        System.out.println("Got request form " + userAgent);
        Product product = dataEngine.findProductById(productId);


        if (product != null) {
            System.out.println("findProduct method has returned " + product
                .getTitle());
            return product;
        } else {
            return null;
        }
    }

}