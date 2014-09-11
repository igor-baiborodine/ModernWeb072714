package auction.model;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Yakov Fain
 * @author Igor Baiborodine
 */
public class Bid {

    private int id;
    private Product product;
    private BigDecimal amount;
    private int desiredQuantity; // How many items the user wants
    private User user;
    private LocalDateTime bidTime;
    private boolean isWinning;

    public Bid() {}

    public Bid(final Product product, final BigDecimal amount, final int desiredQuantity, final User user) {

        this.product = product;
        this.amount = amount;
        this.desiredQuantity = desiredQuantity;
        this.user = user;
        this.bidTime = LocalDateTime.now();
        this.isWinning = false;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public int getProductId() {
        return product.getId();
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public int getDesiredQuantity() {
        return desiredQuantity;
    }

    public void setDesiredQuantity(final int desiredQuantity) {
        this.desiredQuantity = desiredQuantity;
    }

    public User getUser() {
        return user;
    }

    public int getUserId() {
        return user.getId();
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public LocalDateTime getBidTime() {
        return bidTime;
    }

    public void setBidTime(final LocalDateTime bidTime) {
        this.bidTime = bidTime;
    }

    public boolean isWinning() {
        return isWinning;
    }

    public void setWinning(final boolean isWinning) {
        this.isWinning = isWinning;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Bid)) return false;

        final Bid bid = (Bid) o;

        return id == bid.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("product", product)
                .add("amount", amount)
                .add("desiredQuantity", desiredQuantity)
                .add("user", user)
                .add("bidTime", bidTime)
                .add("isWinning", isWinning)
                .toString();
    }
}
