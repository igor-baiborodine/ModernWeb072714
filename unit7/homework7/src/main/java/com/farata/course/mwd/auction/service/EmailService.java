package com.farata.course.mwd.auction.service;

import com.farata.course.mwd.auction.entity.Bid;
import com.farata.course.mwd.auction.entity.Product;
import com.farata.course.mwd.auction.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.Set;

/**
 * @author Igor Baiborodine
 */
@Singleton
public class EmailService {

    private final static Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendSorryEmail(final Bid bid) {

        Product product = bid.getProduct();
        logger.info("Sending SORRY email to [{}]: new bid[${}] is less than minimal price[${}] for  product[{}]",
                bid.getUser().toShortString(), bid.getAmount(), product.getMinimalPrice(), product.getTitle());
    }

    public void sendOverBidEmail(final Bid bid, final Set<User> otherBidders) {

        otherBidders.stream()
                .forEach(bidder -> logger.info("Sending OVERBID email to [{}]: new bid[${}] was received for product[{}]",
                        bidder.toShortString(), bid.getAmount(), bid.getProduct().getTitle()));
    }

    public void sendWinningEmail(final Bid bid) {

        Product product = bid.getProduct();
        logger.info("Sending WINNING email to [{}]: new bid[${}] is equal to or greater than" +
                        " reserved price[${}] for  product[{}]", bid.getUser().toShortString(),
                bid.getAmount(), product.getReservedPrice(), product.getTitle());
    }

}
