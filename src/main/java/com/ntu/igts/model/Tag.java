package com.ntu.igts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("tag")
public class Tag extends BaseModel implements Serializable {

    private static final long serialVersionUID = -2616479631469825693L;

    @JsonProperty("name")
    private String name;
    @JsonProperty("standardname")
    private String standardName;
    @JsonProperty("parentid")
    private String parentId;
    @JsonProperty("tags")
    private List<Tag> tags = new ArrayList<Tag>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
