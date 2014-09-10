package auction.util;

import auction.service.BiddingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Igor Baiborodine
 */
public class BiddingEmulator {

    private static final Logger logger = LoggerFactory.getLogger(BiddingEmulator.class);

    private final LocalDateTime endAuctionTime;
    private final Timer timer;
    private final BiddingService biddingService;

    public BiddingEmulator(BiddingService biddingService, LocalDateTime endAuctionTime) {

        this.biddingService = biddingService;
        this.endAuctionTime = endAuctionTime;
        this.timer = new Timer();
        this.timer.schedule(new BiddingTask(), 500, 1000);
    }

    private class BiddingTask extends TimerTask {

        LocalDateTime currentAuctionTime = LocalDateTime.now();
        int count = 0;

        public void run() {

            while (currentAuctionTime.isBefore(LocalDateTime.now())) {

                if (currentAuctionTime.isAfter(endAuctionTime)) {
                    logger.info("Completed auction without winner!");
                    System.exit(0);
                }
                count++;

                int userId = getRandomIntInRange(1, 3);
                int productId = 1; //getRandomIntInRange(1, 2);
                int quantity = getRandomIntInRange(1, 20);
                BigDecimal bidAmount = new BigDecimal(String.valueOf(getRandomIntInRange(1, 300)));

                logger.info("Placing bid[{}][productId={}, amount=${}, quantity={}, userId={}]",
                        count, productId, bidAmount, quantity, userId);
                if (!biddingService.bidOnProduct(productId, bidAmount, quantity, userId)) {
                    logger.info("Completed auction with winner[{}] for product[{}] with highest bid[${}]!",
                            userId, productId, bidAmount);
                    System.exit(0);
                }
                currentAuctionTime = currentAuctionTime.plusSeconds(1);
            }
        }

    }

    public static void main(String args[]) {

        logger.info("Starting auction...");

        new BiddingEmulator(new BiddingService(), LocalDateTime.now().plusSeconds(10));
    }

    private int getRandomIntInRange(int min, int max) {

        return min + new Random().nextInt(max - min + 1);
    }
}