package com.ntu.igts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.ntu.igts.utils.StringUtil;

@JsonRootName("commodity")
public class Commodity extends BaseModel implements Serializable {


    /**
     * 
     */
    private static final long serialVersionUID = -1328032410096440445L;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("activeyn")
    private String activeYN = "Y";
    @JsonProperty("carriage")
    private double carriage;
    @JsonProperty("price")
    private double price;
    @JsonProperty("releasedate")
    private Date releaseDate;
    @JsonProperty("district")
    private String district;
    @JsonProperty("collectionnumber")
    private int collectionNumber = 0;
    @JsonProperty("userid")
    private String userId;
    @JsonProperty("status")
    private String status = StringUtil.EMPTY;
    @JsonProperty("tags")
    private List<Tag> tags = new ArrayList<Tag>();
    @JsonProperty("covers")
    private List<Cover> covers = new ArrayList<Cover>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActiveYN() {
        return activeYN;
    }

    public void setActiveYN(String activeYN) {
        this.activeYN = activeYN;
    }

    public double getCarriage() {
        return carriage;
    }

    public void setCarriage(double carriage) {
        this.carriage = carriage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getCollectionNumber() {
        return collectionNumber;
    }

    public void setCollectionNumber(int collectionNumber) {
        this.collectionNumber = collectionNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Cover> getCovers() {
        return covers;
    }

    public void setCovers(List<Cover> covers) {
        this.covers = covers;
    }
}
