package com.ntu.igts.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("slice")
public class Slice extends BaseModel implements Serializable {

    private static final long serialVersionUID = -7737152560739189189L;

    @JsonProperty("imageid")
    private String imageId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("displaysequence")
    private int displaySequence;
    @JsonProperty("commodityid")
    private String commodityId;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDisplaySequence() {
        return displaySequence;
    }

    public void setDisplaySequence(int displaySequence) {
        this.displaySequence = displaySequence;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

}
