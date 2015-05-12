package com.ntu.igts.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("sensitiveword")
public class SensitiveWord extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1181080028496268314L;

    @JsonProperty("word")
    private String word;
    @JsonProperty("activeyn")
    private String activeYN = "Y";

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getActiveYN() {
        return activeYN;
    }

    public void setActiveYN(String activeYN) {
        this.activeYN = activeYN;
    }

}
