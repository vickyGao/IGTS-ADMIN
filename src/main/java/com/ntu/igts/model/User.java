package com.ntu.igts.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("user")
public class User extends BaseModel implements Serializable {

    private static final long serialVersionUID = 5852161501955587332L;

    @JsonProperty("username")
    private String userName;
    @JsonProperty("password")
    private String password;
    @JsonProperty("realname")
    private String realName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phonenumber")
    private String phoneNumber;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("age")
    private int age;
    @JsonProperty("activeyn")
    private String activeYN = "Y";
    @JsonProperty("sellerlevel")
    private int sellerLevel;
    @JsonProperty("buyerlevel")
    private int buyerLevel;
    @JsonProperty("sellerexp")
    private int sellerExp;
    @JsonProperty("buyerexp")
    private int buyerExp;
    @JsonProperty("idnumber")
    private String idNumber; // id card number

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getActiveYN() {
        return activeYN;
    }

    public void setActiveYN(String activeYN) {
        this.activeYN = activeYN;
    }

    public int getSellerLevel() {
        return sellerLevel;
    }

    public void setSellerLevel(int sellerLevel) {
        this.sellerLevel = sellerLevel;
    }

    public int getBuyerLevel() {
        return buyerLevel;
    }

    public void setBuyerLevel(int buyerLevel) {
        this.buyerLevel = buyerLevel;
    }

    public int getSellerExp() {
        return sellerExp;
    }

    public void setSellerExp(int sellerExp) {
        this.sellerExp = sellerExp;
    }

    public int getBuyerExp() {
        return buyerExp;
    }

    public void setBuyerExp(int buyerExp) {
        this.buyerExp = buyerExp;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

}
