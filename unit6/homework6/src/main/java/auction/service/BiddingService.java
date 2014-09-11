package auction.service;

import auction.model.Bid;
import auction.model.Product;
import auction.model.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Igor Baiborodine
 */
public class BiddingService {

    private static final Logger logger = LoggerFactory.getLogger(BiddingService.class);

    private Map<Integer, Product> products;
    private Map<Integer, User> users;
    private Map<Integer, List<Bid>> productBids;
    private Map<Integer, Set<User>> productBidders;

    public BiddingService() {

        products = createProducts();
        users = createUsers();
        productBids = Maps.newHashMap();
        productBidders = Maps.newHashMap();
    }

    public boolean bidOnProduct(int productId, BigDecimal bidAmount, int quantity, int userId) {

        Bid newBid = createBid(getProduct(productId), bidAmount, quantity, getUser(userId));
        newBid.setWinning(false);

        if (bidAmountLessThanMinimalPrice(newBid)) {
            sendSorryEmail(newBid);
        } else {
            Bid bidWithHighestAmount = findBidWithHighestAmount(productId);

            if (bidAmountMoreThanHighestBidAmount(newBid, bidWithHighestAmount)) {
                Set<User> otherBidders = findOtherBidders(newBid);
                sendOverBidEmail(newBid, otherBidders);
            }
            if (bidAmountEqualToOrGreaterThanReservedPrice(newBid)) {
                newBid.setWinning(true);
                sendWinningEmail(newBid);
            }
            addBid(newBid.getProductId(), newBid);
            addCurrentBidder(newBid.getProductId(), newBid.getUser());
        }

        return newBid.isWinning();
    }

    private boolean bidAmountLessThanMinimalPrice(final Bid newBid) {

        return newBid.getAmount().compareTo(newBid.getProduct().getMinimalPrice()) == -1;
    }

    private boolean bidAmountMoreThanHighestBidAmount(final Bid newBid, final Bid bidWithHighestAmount) {

        return (bidWithHighestAmount != null) && newBid.getAmount().compareTo(bidWithHighestAmount.getAmount()) == 1;
    }

    private boolean bidAmountEqualToOrGreaterThanReservedPrice(final Bid newBid) {

        Product product = newBid.getProduct();
        return product.getReservedPrice().compareTo(newBid.getAmount()) == -1
                || product.getReservedPrice().compareTo(newBid.getAmount()) == 0;
    }

    private void sendSorryEmail(final Bid newBid) {

        Product product = newBid.getProduct();
        logger.info("Sending SORRY email to [{}]: new bid[${}] is less than minimal price[${}] for  product[{}]",
                newBid.getUser().toShortString(), newBid.getAmount(), product.getMinimalPrice(), product.getTitle());
    }

    private void sendOverBidEmail(final Bid newBid, final Set<User> otherBidders) {

        otherBidders.forEach(bidder -> logger.info("Sending OVERBID email to [{}]: new bid[${}] was received for product[{}]",
                bidder.toShortString(), newBid.getAmount(), newBid.getProduct().getTitle()));
    }

    private void sendWinningEmail(final Bid newBid) {

        Product product = newBid.getProduct();
        logger.info("Sending WINNING email to [{}]: new bid[${}] is equal to or greater than" +
                        " reserved price[${}] for  product[{}]", newBid.getUser().toShortString(),
                newBid.getAmount(), product.getReservedPrice(), product.getTitle());
    }

    private Set<User> findOtherBidders(final Bid newBid) {

        Set<User> bidders = getCurrentBidders(newBid.getProductId());
        return bidders.stream()
                .filter(bidder -> !bidder.equals(newBid.getUser()) && bidder.isGetOverbidNotifications())
                .collect(Collectors.toSet());
    }

    public Bid findBidWithHighestAmount(int productId) {

        List<Bid> currentBids = getCurrentBids(productId);
        if (currentBids.isEmpty()) {
            return null;
        }

//        Java 7
//        Comparator<Bid> byBidAmount = new Comparator<Bid>() {
//            @Override
//            public int compare(final Bid o1, final Bid o2) {
//                return o1.getAmount().compareTo(o2.getAmount());
//            }
//        };

//        Comparator<Bid> byBidAmountDesc = (o1, o2) -> o2.getAmount().compareTo(o1.getAmount());
//        List<Bid> sortedCurrentBids = currentBids.stream().sorted(byBidAmountDesc).collect(Collectors.toList());
//        return sortedCurrentBids.get(0);
        Comparator<Bid> byBidAmountAsc = (o1, o2) -> o1.getAmount().compareTo(o2.getAmount());
        return currentBids.stream().max(byBidAmountAsc).get();
    }

    public Product getProduct(int productId) {
        return products.get(productId);
    }

    public User getUser(int userId) {
        return users.get(userId);
    }

    public int getAllBidsCount() {
        return productBids.isEmpty() ? 0 : (int) productBids.values().stream().count();
    }

    public List<Bid> getCurrentBids(int productId) {
        return productBids.get(productId) == null ? Lists.newArrayList() : productBids.get(productId);
    }

    public Set<User> getCurrentBidders(int productId) {
        return productBidders.get(productId) == null ? Sets.newHashSet() : productBidders.get(productId);
    }

    public Bid createBid(int productId, BigDecimal bidAmount, int quantity, int userId) {

        return createBid(getProduct(productId), bidAmount, quantity, getUser(userId));
    }

    public void addBid(int productId, Bid bid) {

        List<Bid> currentBids = getCurrentBids(productId);

        currentBids.add(bid);
        productBids.put(productId, currentBids);
    }

    private void addCurrentBidder(int productId, User bidder) {

        Set<User> currentBidders = getCurrentBidders(productId);
        currentBidders.add(bidder);
        productBidders.put(productId, currentBidders);
    }

    private Map<Integer, Product> createProducts() {

        Map<Integer, Product> products = Maps.newHashMapWithExpectedSize(2);

        Product product = new Product();
        product.setId(1);
        product.setTitle("Item 1");
        product.setThumb("01.jpg");
        product.setDescription("Item 1: Lorem ipsum dolor sit amet, consectetur adipisicing elit.");
        product.setQuantity(15);
        product.setAuctionEndTime(LocalDateTime.now().plusSeconds(10));
        product.setWatchers(5);
        product.setMinimalPrice(new BigDecimal("40.0"));
        product.setReservedPrice(new BigDecimal("200.0"));

        products.put(product.getId(), product);

        Product product2 = new Product();
        product2.setId(2);
        product2.setTitle("Item 2");
        product2.setThumb("02.jpg");
        product2.setDescription("Item 2: Lorem ipsum dolor sit amet, consectetur adipisicing elit.");
        product2.setQuantity(5);
        product2.setAuctionEndTime(LocalDateTime.now().plusSeconds(10));
        product2.setWatchers(3);
        product2.setMinimalPrice(new BigDecimal("20.0"));
        product2.setReservedPrice(new BigDecimal("100.0"));

        products.put(product2.getId(), product2);

        return products;
    }

    private Map<Integer, User> createUsers() {

        Map<Integer, User> users = Maps.newHashMap();

        User user = new User(1, "philip.fry", "philip.fry@planet-express.com", true);
        users.put(user.getId(), user);

        User user2 = new User(2, "bender.rodriguez", "bender.rodriguez@planet-express.com", true);
        users.put(user2.getId(), user2);

        User user3 = new User(3, "amy.wong", "amy.wong@planet-express.com", true);
        users.put(user3.getId(), user3);

        return users;
    }

    private Bid createBid(Product product, BigDecimal bidAmount, int quantity, User user) {

        Bid bid = new Bid(product, bidAmount, quantity, user);
        bid.setId(getAllBidsCount() + 1);

        return bid;
    }

}
