package auction.service;

import auction.model.Bid;
import auction.model.Product;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Igor Baiborodine
 *
 */
public class BiddingService {

    private Map<Integer, Product> products;
    private List<Bid> bids;

    public BiddingService() {

        products = createProducts();
        bids = Lists.newArrayList();
    }

    public static void main() {

    }

    public void bidOnProduct(int productId, BigDecimal bidAmount, int quantity, int userId) {


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
        product.setMinimalPrice(new BigDecimal("10.0"));
        product.setReservedPrice(new BigDecimal("200.0"));

        products.put(product.getId(), product);

        Product product2 = new Product();
        product.setId(2);
        product.setTitle("Item 2");
        product.setThumb("02.jpg");
        product.setDescription("Item 2: Lorem ipsum dolor sit amet, consectetur adipisicing elit.");
        product.setQuantity(5);
        product.setAuctionEndTime(LocalDateTime.now().plusSeconds(10));
        product.setWatchers(3);
        product.setMinimalPrice(new BigDecimal("5.0"));
        product.setReservedPrice(new BigDecimal("100.0"));

        products.put(product2.getId(), product2);

        return products;
    }

}
