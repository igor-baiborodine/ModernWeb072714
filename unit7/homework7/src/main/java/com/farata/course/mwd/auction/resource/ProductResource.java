package com.farata.course.mwd.auction.resource;

import com.farata.course.mwd.auction.service.ProductService;
import com.farata.course.mwd.auction.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status.*;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    private static final Logger logger = LoggerFactory.getLogger(ProductResource.class);

    private ProductService productService;

    @Inject
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GET
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    @GET
    @Path("/featured")
    public Response getFeaturedProducts() {

        logger.info("Received request for featured products...");
        List<Product> products = productService.findProductsByFeatured(true);
        final JsonObjectBuilder jsonResult = itemsWithHeading("Featured products", products);

        return Response.ok(jsonResult.build()).build();
    }

    @GET
    @Path("/search")
    public Response getSearchResults() {

        logger.info("Received request for search results...");
        List<Product> products = productService.findProductsByFeatured(false);
        final JsonObjectBuilder jsonResult = itemsWithHeading("Search results", products);

        return Response.ok(jsonResult.build()).build();
    }

    private JsonObjectBuilder itemsWithHeading(String heading, List<Product> products) {

        JsonArrayBuilder itemsBuilder = Json.createArrayBuilder();
        products.forEach(product -> itemsBuilder.add(product.getJsonObject()));

        return Json.createObjectBuilder()
            .add("heading", heading)
            .add("items", itemsBuilder);
    }

    @GET
    @Path("/{id}/")
    public Response getProductById(@PathParam("id") int productId, @Context HttpHeaders headers) {

        String userAgent = headers.getRequestHeader("user-agent").get(0);
        logger.info("Received request for product id[{}] from[{}]", productId, userAgent);

        Product product = productService.findProductById(productId);

        if (product != null) {
            return Response.ok(product.getJsonObject()).build();
        } else {
            return Response.status(NOT_FOUND).build();
        }
    }

}