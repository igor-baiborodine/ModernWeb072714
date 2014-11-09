package com.farata.course.mwd.auction.model;

import java.math.BigDecimal;
import java.util.Date;


public class Product {

  private BigDecimal minimalPrice;
  private BigDecimal reservedPrice;
  private Date auctionEndTime;
  private Integer quantity;
  private Integer watchers;
  private Long id;
  private String description;
  private String thumb;
  private String title;

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

  public Date getAuctionEndTime() {
    return auctionEndTime;
  }

  public void setAuctionEndTime(final Date auctionEndTime) {
    this.auctionEndTime = auctionEndTime;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(final Integer quantity) {
    this.quantity = quantity;
  }

  public Integer getWatchers() {
    return watchers;
  }

  public void setWatchers(final Integer watchers) {
    this.watchers = watchers;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }

  public String getThumb() {
    return thumb;
  }

  public void setThumb(final String thumb) {
    this.thumb = thumb;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Product{" +
           "minimalPrice=" + minimalPrice +
           ", reservedPrice=" + reservedPrice +
           ", auctionEndTime=" + auctionEndTime +
           ", quantity=" + quantity +
           ", watchers=" + watchers +
           ", id=" + id +
           ", description='" + description + '\'' +
           ", thumb='" + thumb + '\'' +
           ", title='" + title + '\'' +
           '}';
  }
}
