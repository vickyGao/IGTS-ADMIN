package com.ntu.igts.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("bill")
public class Bill extends BaseModel implements Serializable {

    private static final long serialVersionUID = 7529131224929254388L;

    @JsonProperty("content")
    private String content;
    @JsonProperty("amount")
    private int amount; // pay: '-', achieve: '+'
    @JsonProperty("userid")
    private String userId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
