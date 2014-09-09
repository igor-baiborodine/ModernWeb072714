package auction.model;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by yfain11 on 4/4/14.
 */
public class Product {

    public int id;
    public String title;
    public String thumb;
    public String description;
    public int quantity;   // How many items the seller has
    public LocalDateTime auctionEndTime;
    public int watchers;
    public BigDecimal minimalPrice;     // Don't sell unless the bid is more than min price
    public BigDecimal reservedPrice;   // If a bidder offers reserved price, the auction is closed

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

        if (id != product.id) return false;

        return true;
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
