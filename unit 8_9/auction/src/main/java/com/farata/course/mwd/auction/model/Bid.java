package com.farata.course.mwd.auction.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Bid {
  private Long id;
  private BigDecimal amount;
  private Boolean isWinning;
  private Integer desiredQuantity;
  private LocalDateTime bidTime;
  private Long productId;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(final BigDecimal amount) {
    this.amount = amount;
  }

  public Boolean getIsWinning() {
    return isWinning;
  }

  public void setIsWinning(final Boolean isWinning) {
    this.isWinning = isWinning;
  }

  public Integer getDesiredQuantity() {
    return desiredQuantity;
  }

  public void setDesiredQuantity(final Integer desiredQuantity) {
    this.desiredQuantity = desiredQuantity;
  }

  public LocalDateTime getBidTime() {
    return bidTime;
  }

  public void setBidTime(final LocalDateTime bidTime) {
    this.bidTime = bidTime;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(final Long productId) {
    this.productId = productId;
  }

  @Override
  public String toString() {
    return "Bid{" +
           "id=" + id +
           ", amount=" + amount +
           ", isWinning=" + isWinning +
           ", desiredQuantity=" + desiredQuantity +
           ", bidTime=" + bidTime +
           ", productId=" + productId +
           '}';
  }
}
