package auction.model;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Yakov Fain
 * @author Igor Baiborodine
 */
public class Product {

    private int id;
    private String title;
    private String thumb;
    private String description;
    private int quantity;   // How many items the seller has
    private LocalDateTime auctionEndTime;
    private int watchers;
    private BigDecimal minimalPrice;     // Don't sell unless the bid is more than min price
    private BigDecimal reservedPrice;   // If a bidder offers reserved price, the auction is closed

    public Product() {}

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(final String thumb) {
        this.thumb = thumb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getAuctionEndTime() {
        return auctionEndTime;
    }

    public void setAuctionEndTime(final LocalDateTime auctionEndTime) {
        this.auctionEndTime = auctionEndTime;
    }

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(final int watchers) {
        this.watchers = watchers;
    }

    public BigDecimal getMinimalPrice() {
        return minimalPrice;
    }

    public void setMinimalPrice(final BigDecimal minimalPrice) {
        this.minimalPrice = minimalPrice;
    }

    public BigDecimal getReservedPrice() {
        return reservedPrice;
    }

    public void setReservedPrice(final BigDecimal reservedPrice) {
        this.reservedPrice = reservedPrice;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        final Product product = (Product) o;

        return id == product.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {

        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("thumb", thumb)
                .add("description", description)
                .add("quantity", quantity)
                .add("auctionEndTime", auctionEndTime)
                .add("watchers", watchers)
                .add("minimalPrice", minimalPrice)
                .add("reservedPrice", reservedPrice)
                .toString();
    }
}
