package com.ntu.igts.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("indent")
public class Indent extends BaseModel implements Serializable {


    /**
     * 
     */
    private static final long serialVersionUID = 8457910703962529707L;
    @JsonProperty("indentnumber")
    private String indentNumber; // e.g. 201502031423969130883 = year + month + date + date.getTime()
    @JsonProperty("userid")
    private String userId;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("indentaddress")
    private String indentAddress;
    @JsonProperty("paytype")
    private String payType;
    @JsonProperty("status")
    private String status;
    @JsonProperty("indenttime")
    private Date indentTime;
    @JsonProperty("paytime")
    private Date payTime;
    @JsonProperty("delivertime")
    private Date deliverTime;
    @JsonProperty("dealcompletetime")
    private Date dealCompleteTime;
    @JsonProperty("commodityprice")
    private double commodityPrice;
    @JsonProperty("commodityid")
    private String commodityId;
    @JsonProperty("carriage")
    private double carriage;
    @JsonProperty("indentprice")
    private double indentPrice;
    @JsonProperty("phonenumber")
    private String phoneNumber;
    @JsonProperty("indentmessage")
    private String indentmessage;
    @JsonProperty("commodity")
    private Commodity commodity;
    @JsonProperty("sellerid")
    private String sellerId;

    public String getIndentNumber() {
        return indentNumber;
    }

    public void setIndentNumber(String indentNumber) {
        this.indentNumber = indentNumber;
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

    public String getIndentAddress() {
        return indentAddress;
    }

    public void setIndentAddress(String indentAddress) {
        this.indentAddress = indentAddress;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getIndentTime() {
        return indentTime;
    }

    public void setIndentTime(Date indentTime) {
        this.indentTime = indentTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Date getDealCompleteTime() {
        return dealCompleteTime;
    }

    public void setDealCompleteTime(Date dealCompleteTime) {
        this.dealCompleteTime = dealCompleteTime;
    }

    public double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public double getCarriage() {
        return carriage;
    }

    public void setCarriage(double carriage) {
        this.carriage = carriage;
    }

    public double getIndentPrice() {
        return indentPrice;
    }

    public void setIndentPrice(double indentPrice) {
        this.indentPrice = indentPrice;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
}
