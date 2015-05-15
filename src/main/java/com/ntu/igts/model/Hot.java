package com.ntu.igts.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("hot")
public class Hot extends BaseModel implements Serializable {

    private static final long serialVersionUID = 140637532042408604L;

    @JsonProperty("imageid")
    private String imageId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("displaysequence")
    private int displaySequence;
    @JsonProperty("commodityid")
    private String commodityId;
    @JsonProperty("image")
    private Image image;
    @JsonProperty("commodity")
    private Commodity commodity;

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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

}
