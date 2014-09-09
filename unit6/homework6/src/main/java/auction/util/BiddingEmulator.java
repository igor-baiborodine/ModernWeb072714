package auction.util;

import auction.service.BiddingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
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
                    logger.info("Completed auction!");
                    System.exit(0);
                }
                count++;
                logger.info("Bid[{}], bidding on product...", count);
                // TODO: implement me
                currentAuctionTime = currentAuctionTime.plusSeconds(1);
            }
        }
    }

    public static void main(String args[]) {

        logger.info("Starting auction...");

        new BiddingEmulator(new BiddingService(), LocalDateTime.now().plusSeconds(10));
    }
}