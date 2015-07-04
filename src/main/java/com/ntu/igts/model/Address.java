package com.ntu.igts.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("address")
public class Address extends BaseModel implements Serializable {


    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @JsonProperty("addresscountry")
    private String addressCountry;
    @JsonProperty("addressprovince")
    private String addressProvince;
    @JsonProperty("addresscity")
    private String addressCity;
    @JsonProperty("addressdistrict")
    private String addressDistrict;
    @JsonProperty("addressdetail")
    private String addressDetail;
    @JsonProperty("postcode")
    private String postcode;
    @JsonProperty("phonenumber")
    private String phoneNumber;
    @JsonProperty("userid")
    private String userId;

    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressDistrict() {
        return addressDistrict;
    }

    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
