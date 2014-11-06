package com.farata.course.mwd.auction.service;

import com.farata.course.mwd.auction.entity.Bid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class QueueService {

    private final static Logger logger = LoggerFactory.getLogger(QueueService.class);

    // Set up all the default values
    private static final String DEFAULT_MESSAGE = "Hello, World!";
    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "jms/queue/test";
    private static final String DEFAULT_MESSAGE_COUNT = "57";
    private static final String DEFAULT_USERNAME = "quickstartUser";
    private static final String DEFAULT_PASSWORD = "quickstartPwd1!";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:8080";

//    @Resource(lookup ="java:/ConnectionFactory")
//    ConnectionFactory connectionFactory;
//
//    @Resource(lookup = "queue/test")
//    Queue testQueue;

    public void sendBidToQueue(Bid bid) {

//        try (JMSContext context = connectionFactory.createContext(DEFAULT_USERNAME, DEFAULT_PASSWORD)) {

            logger.info("Sending 'Hello Bid!' message from BidService to the queue");

//            JMSProducer producer = context.createProducer();
//            producer.send(testQueue, "Hello Bid!");
//        }
    }

}
