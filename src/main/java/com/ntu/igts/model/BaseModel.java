package com.ntu.igts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseModel {

    @JsonProperty("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
