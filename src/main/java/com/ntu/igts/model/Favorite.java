package com.ntu.igts.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("favorite")
public class Favorite extends BaseModel implements Serializable {

    private static final long serialVersionUID = -8876661467254356108L;

    @JsonProperty("commodityid")
    private String commodityId;
    @JsonProperty("userid")
    private String userId;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
