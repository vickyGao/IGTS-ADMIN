package com.ntu.igts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("message")
public class Message extends BaseModel implements Serializable {

    private static final long serialVersionUID = 2122366791637631002L;

    @JsonProperty("commodityid")
    private String commodityId;
    @JsonProperty("content")
    private String content;
    @JsonProperty("floor")
    private int floor;
    @JsonProperty("parentid")
    private String parentId;
    @JsonProperty("userid")
    private String userId;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("messages")
    private List<Message> messages = new ArrayList<Message>();

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
