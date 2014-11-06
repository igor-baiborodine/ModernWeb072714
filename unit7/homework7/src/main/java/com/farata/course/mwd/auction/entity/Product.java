package com.farata.course.mwd.auction.entity;

import com.google.common.base.MoreObjects;

import javax.json.Json;
import javax.json.JsonObject;
import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

    private Integer id;
    private String title;
    @XmlElement(name = "thumb")
    private String thumbnail;
    private String url;
    private String description;
    private int quantity;   // How many items the seller has
    private LocalDateTime auctionEndTime;
    private BigDecimal minimalPrice;     // Don't sell unless the bid is more than min price
    private BigDecimal reservedPrice;   // If a bidder offers reserved price, the auction is closed
    private String timeleft;
    private int watchers;
    private boolean featured;

    public Product() {}

    public Product(Integer id, String title, String thumbnail, String url, String description,
        int quantity,
        LocalDateTime auctionEndTime, BigDecimal minimalPrice, BigDecimal reservedPrice,
        String timeleft, int watchers) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.url = url;
        this.description = description;
        this.quantity = quantity;
        this.auctionEndTime = auctionEndTime;
        this.minimalPrice = minimalPrice;
        this.reservedPrice = reservedPrice;
        this.timeleft = timeleft;
        this.watchers = watchers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeleft() {
        return timeleft;
    }

    public void setTimeleft(String timeleft) {
        this.timeleft = timeleft;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(final int watchers) {
        this.watchers = watchers;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(final boolean featured) {
        this.featured = featured;
    }

    @XmlTransient
    public JsonObject getJsonObject() {

        return Json.createObjectBuilder()
                .add("id", id)
                .add("title", title)
                .add("thumbnail", thumbnail)
                .add("url", url)
                .add("description", description)
                .add("quantity", quantity)
                .add("auctionEndTime", String.valueOf(auctionEndTime))
                .add("minimalPrice", minimalPrice)
                .add("reservedPrice", reservedPrice)
                .add("timeleft", timeleft)
                .add("watchers", watchers)
                .add("featured", featured)
                .build();
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        final Product product = (Product) o;

        return id.equals(product.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("description", description)
                .add("featured", featured)
                .add("quantity", quantity)
                .add("thumbnail", thumbnail)
                .add("url", url)
                .add("auctionEndTime", auctionEndTime)
                .add("minimalPrice", minimalPrice)
                .add("reservedPrice", reservedPrice)
                .add("timeleft", timeleft)
                .add("watchers", watchers)
                .toString();
    }
}
