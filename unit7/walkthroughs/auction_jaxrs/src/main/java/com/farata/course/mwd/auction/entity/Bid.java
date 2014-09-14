package com.farata.course.mwd.auction.entity;

import javax.json.JsonObject;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private int id;
    private Product product;
    private BigDecimal amount;
    private int desiredQuantity; // How many items the user wants
    private User user;
    private LocalDateTime bidTime;
    private boolean isWinning;

    public Bid(int id, Product product, BigDecimal amount, int desiredQuantity,
        User user, LocalDateTime bidTime, boolean isWinning) {
        this.id = id;
        this.product = product;
        this.amount = amount;
        this.desiredQuantity = desiredQuantity;
        this.user = user;
        this.bidTime = bidTime;
        this.isWinning = isWinning;
    }

    public Bid(int id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public Bid(){}

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("Bid{");
        sb.append("id=").append(id);
        sb.append(", product=").append(product);
        sb.append(", amount=").append(amount);
        sb.append(", desiredQuantity=").append(desiredQuantity);
        sb.append(", user=").append(user);
        sb.append(", bidTime=").append(bidTime);
        sb.append(", isWinning=").append(isWinning);
        sb.append('}');
        return sb.toString();
    }

    // TODO: Implement getJSonObjet
    @XmlTransient
    public JsonObject getJsonObject() {
        return null;
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
}
