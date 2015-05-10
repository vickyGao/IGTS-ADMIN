package com.ntu.igts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("admin")
public class Admin extends BaseModel implements Serializable {

    private static final long serialVersionUID = -2592865393997208669L;

    @JsonProperty("adminname")
    private String adminName;
    @JsonProperty("adminpassword")
    private String adminPassword;
    @JsonProperty("roles")
    private List<Role> roles = new ArrayList<Role>();

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
