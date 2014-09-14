package com.farata.course.mwd.auction.entity;

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

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", thumbnail='").append(thumbnail).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", auctionEndTime=").append(auctionEndTime);
        sb.append(", minimalPrice=").append(minimalPrice);
        sb.append(", reservedPrice=").append(reservedPrice);
        sb.append(", timeleft='").append(timeleft).append('\'');
        sb.append(", watchers=").append(watchers);
        sb.append('}');
        return sb.toString();
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
            .build();
    }
}
