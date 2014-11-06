package com.farata.course.mwd.auction.entity;

import com.google.common.base.MoreObjects;

import javax.json.Json;
import javax.json.JsonObject;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Bid {

    // TODO: Use Bean Validation for other properties
    private int id;
    private Product product;
    private BigDecimal amount;
    private int desiredQuantity; // How many items the user wants
    private User user;
    private LocalDateTime bidTime;
    private boolean isWinning;

    public Bid(final Product product, final BigDecimal amount, final int desiredQuantity, final User user) {

        this.product = product;
        this.amount = amount;
        this.desiredQuantity = desiredQuantity;
        this.user = user;
        this.bidTime = LocalDateTime.now();
        this.isWinning = false;
    }

    public Bid() {}

    @XmlTransient
    public JsonObject getJsonObject() {

        return Json.createObjectBuilder()
                .add("id", id)
                .add("product", getProductId())// product.getJsonObject())
                .add("amount", amount)
                .add("desiredQuantity", desiredQuantity)
                .add("user", getUserId())//user.getJsonObject())
                .add("bidTime", bidTime.toString())
                .add("isWinning", isWinning)
                .build();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getDesiredQuantity() {
        return desiredQuantity;
    }

    public void setDesiredQuantity(int desiredQuantity) {
        this.desiredQuantity = desiredQuantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getBidTime() {
        return bidTime;
    }

    public void setBidTime(LocalDateTime bidTime) {
        this.bidTime = bidTime;
    }

    public boolean isWinning() {
        return isWinning;
    }

    public void setWinning(boolean isWinning) {
        this.isWinning = isWinning;
    }

    public int getProductId() {
        return product.getId();
    }

    public int getUserId() {
        return user.getId();
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
