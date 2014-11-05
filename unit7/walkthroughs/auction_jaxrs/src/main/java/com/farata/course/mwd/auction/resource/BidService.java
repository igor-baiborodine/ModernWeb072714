package com.farata.course.mwd.auction.resource;

import com.farata.course.mwd.auction.entity.Bid;

import javax.annotation.Resource;
import javax.jms.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.math.BigDecimal;
import java.util.logging.Logger;

@Path("bid")
@Produces("application/json")
public class BidService {

    private static final Logger log = Logger.getLogger(BidService.class.getName());

    // Set up all the default values
    private static final String DEFAULT_MESSAGE = "Hello, World!";
    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "jms/queue/test";
    private static final String DEFAULT_MESSAGE_COUNT = "57";
    private static final String DEFAULT_USERNAME = "quickstartUser";
    private static final String DEFAULT_PASSWORD = "quickstartPwd1!";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";

    @Resource(lookup ="java:/ConnectionFactory")
    ConnectionFactory connectionFactory;

    @Resource(lookup = "queue/test")
    Queue testQueue;


    // TODO: Provide actual implementation
    @GET
    @Path("/{id}/")
    public Bid getBid(@PathParam("id") int id, @Context HttpHeaders headers) {
        return new Bid(id, new BigDecimal(42));
    }

    // TODO: Provide actual implementation
    @POST
    public Bid placeBid(/*@Valid Bid bid*/) {

        sendBidToQueue(); // Send a message to the queue

        return new Bid();

    }

    private void sendBidToQueue(){

        try (JMSContext context =
               connectionFactory.createContext(DEFAULT_USERNAME, DEFAULT_PASSWORD)) {
            log.info("\n Sending Hello Bid message from BidService to the queue");

            JMSProducer producer = context.createProducer();

            producer.send(testQueue, "Hello Bid!");
        }
    }
}
