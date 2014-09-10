package auction.service

import spock.lang.Specification
import spock.lang.Title

/**
 * @author Igor Baiborodine
 */
@Title("BiddingService Specification")
class BiddingServiceSpec extends Specification {

    def "should find bid with highest amount"() {

        setup:
        def biddingService = new BiddingService();

        def productId = 1;
        def userId = 1;
        def highestBidAmount = new BigDecimal("30.0");

        def bid = biddingService.createBid(productId, new BigDecimal("10.0"), 1, userId);
        biddingService.addBid(productId, bid);

        def bid2 = biddingService.createBid(productId, highestBidAmount, 1, userId);
        biddingService.addBid(productId, bid2);

        def bid3 = biddingService.createBid(productId, new BigDecimal("20.0"), 1, userId);
        biddingService.addBid(productId, bid3);

        def bidWithHighestAmount = biddingService.findBidWithHighestAmount(productId);

        expect:
        bidWithHighestAmount.getAmount().compareTo(highestBidAmount) == 0;
    }

    def "should return null for empty current bids"() {

        setup:
        def biddingService = new BiddingService();
        def bidWithHighestAmount = biddingService.findBidWithHighestAmount(1);

        expect:
        bidWithHighestAmount == null;
    }
}
