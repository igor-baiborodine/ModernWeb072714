package com.farata.course.mwd.auction.resource;

import com.farata.course.mwd.auction.entity.Bid;
import com.farata.course.mwd.auction.service.BidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

import static java.lang.String.format;
import static javax.ws.rs.core.Response.Status.*;

@Path("bid")
@Produces(MediaType.APPLICATION_JSON)
public class BidResource {

    private static final Logger logger = LoggerFactory.getLogger(BidResource.class);

    private BidService bidService;

    @Inject
    public void setBidService(final BidService bidService) {
        this.bidService = bidService;
    }

    @GET
    @Path("/{id}/")
    public Response getBid(@PathParam("id") int id, @Context HttpHeaders headers) {

        String userAgent = headers.getRequestHeader("user-agent").get(0);
        logger.info("Received request for bid id[{}] from[{}]", id, userAgent);

        Bid bid = bidService.findBidById(id);

        if (bid != null) {
            return Response.ok(bid.getJsonObject()).build();
        } else {
            return Response.status(NOT_FOUND).build();
        }
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response placeBid(@FormParam("productId") int productId,
                             @FormParam("bidAmount") String bidAmount,
                             @FormParam("quantity") int quantity,
                             @FormParam("userId") int userId) {

        logger.info("Received request to place bid with[productId={}, bidAmount={}, quantity={}, userId{}]",
                productId, bidAmount, quantity, userId);
        Bid bid = bidService.bidOnProduct(productId, new BigDecimal(bidAmount), quantity, userId);
        if (bid != null) {
            return Response.ok(bid.getJsonObject()).build();
        } else {
            return Response.status(BAD_REQUEST)
                    .entity(format("New bid[$%s] is less than minimal price for product!", bidAmount))
                    .type(MediaType.TEXT_PLAIN).build();
        }
    }

}
