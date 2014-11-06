package com.farata.course.mwd.auction.service;

import com.farata.course.mwd.auction.entity.Bid;
import com.farata.course.mwd.auction.entity.Product;
import com.farata.course.mwd.auction.entity.User;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Igor Baiborodine
 */
@Singleton
public class BidService {

    private static final Logger logger = LoggerFactory.getLogger(BidService.class);

    private List<Bid> bids = Lists.newArrayList();
    private ProductService productService;
    private UserService userService;
    private QueueService queueService;
    private EmailService emailService;

    @Inject
    public void setProductService(final ProductService productService) {
        this.productService = productService;
    }

    @Inject
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    @Inject
    public void setQueueService(final QueueService queueService) {
        this.queueService = queueService;
    }

    @Inject
    public void setEmailService(final EmailService emailService) {
        this.emailService = emailService;
    }

    void setBids(List<Bid> bids) {
        this.bids = Lists.newArrayList(bids);
    }

    public Bid bidOnProduct(int productId, BigDecimal bidAmount, int quantity, int userId) {

        Bid newBid = createBid(productId, bidAmount, quantity, userId);
        newBid.setWinning(false);

        if (bidAmountLessThanMinimalPrice(newBid)) {
            emailService.sendSorryEmail(newBid);
            return null;
        }
        Bid bidWithHighestAmount = findBidWithHighestAmount(productId);

        if (bidAmountMoreThanHighestBidAmount(newBid, bidWithHighestAmount)) {
            Set<User> otherBidders = findOtherBidders(newBid.getProductId(), newBid.getUserId());
            emailService.sendOverBidEmail(newBid, otherBidders);
        }

        if (bidAmountEqualToOrGreaterThanReservedPrice(newBid)) {
            newBid.setWinning(true);
            emailService.sendWinningEmail(newBid);
        }
        bids.add(newBid);
        queueService.sendBidToQueue(newBid);

        return newBid;
    }

    public Set<User> findOtherBidders(int productId, int userId) {

        Set<User> currentBidders = findCurrentBidders(productId);
        return currentBidders.stream()
                .filter(bidder -> bidder.getId() != userId)
                .collect(Collectors.toSet());
    }

    public Set<User> findCurrentBidders(int productId) {

        return findCurrentBids(productId).stream()
                .map(Bid::getUser)
                .collect(Collectors.toSet());
    }

    public List<Bid> findCurrentBids(int productId) {

        return bids.stream()
                .filter(bid -> bid.getProductId() == productId)
                .collect(Collectors.toList());
    }

    public Bid findBidWithHighestAmount(int productId) {

        List<Bid> currentBids = findCurrentBids(productId);
        if (currentBids.isEmpty()) {
            return null;
        }
        Comparator<Bid> byBidAmountAsc = (o1, o2) -> o1.getAmount().compareTo(o2.getAmount());

        return currentBids.stream().max(byBidAmountAsc).get();
    }

    public int getBidsCount() {
        return bids.size();
    }

    public Bid findBidById(int id) {

        List<Bid> filteredBids = bids.stream()
                .filter(bid -> bid.getId() == id)
                .collect(Collectors.toList());
        Bid bid = (filteredBids.size() == 1) ? filteredBids.get(0) : null;
        logger.info("Found for id[{}] bid[{}]", id, bid);

        return bid;
    }

    private boolean bidAmountLessThanMinimalPrice(final Bid newBid) {

        return newBid.getAmount().compareTo(newBid.getProduct().getMinimalPrice()) == -1;
    }

    private boolean bidAmountMoreThanHighestBidAmount(final Bid newBid, final Bid bidWithHighestAmount) {

        return (bidWithHighestAmount != null) && newBid.getAmount().compareTo(bidWithHighestAmount.getAmount()) == 1;
    }

    private boolean bidAmountEqualToOrGreaterThanReservedPrice(final Bid bid) {

        Product product = bid.getProduct();
        return product.getReservedPrice().compareTo(bid.getAmount()) == -1
                || product.getReservedPrice().compareTo(bid.getAmount()) == 0;
    }

    private Product findProduct(int productId) {

        return productService.findProductById(productId);
    }

    private User findUser(int userId) {

        return userService.findUserById(userId);
    }

    private Bid createBid(int productId, BigDecimal bidAmount, int quantity, int userId) {

        Bid bid = new Bid(findProduct(productId), bidAmount, quantity, findUser(userId));
        bid.setId(bids.size() + 1);

        return bid;
    }

}
