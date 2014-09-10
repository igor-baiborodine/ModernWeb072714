package auction.service;

import auction.model.Bid;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Igor Baiborodine
 */
public class BiddingServiceTest {

    private BiddingService biddingService;

    @Before
    public void setUp() {
        biddingService = new BiddingService();
    }

    @After
    public void tearDown() {
        biddingService = null;
    }

    @Test
    public void findBidWithHighestAmount_shouldFindBidWithHighestAmount() {

        int productId = 1;
        int userId = 1;
        BigDecimal highestBidAmount = new BigDecimal("30.0");

        Bid bid = biddingService.createBid(productId, new BigDecimal("10.0"), 1, userId);
        biddingService.addBid(productId, bid);

        Bid bid2 = biddingService.createBid(productId, highestBidAmount, 1, userId);
        biddingService.addBid(productId, bid2);

        Bid bid3 = biddingService.createBid(productId, new BigDecimal("20.0"), 1, userId);
        biddingService.addBid(productId, bid3);

        Bid bidWithHighestAmount = biddingService.findBidWithHighestAmount(productId);
        assertTrue(bidWithHighestAmount.getAmount().compareTo(highestBidAmount) == 0);
    }

    @Test
    public void findBidWithHighestAmount_shouldReturnNullForEmptyCurrentBids() {

        Bid bidWithHighestAmount = biddingService.findBidWithHighestAmount(1);
        assertThat(bidWithHighestAmount, nullValue());
    }
}
