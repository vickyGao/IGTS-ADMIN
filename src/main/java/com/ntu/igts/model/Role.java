package com.ntu.igts.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Role extends BaseModel implements Serializable {

    private static final long serialVersionUID = 7981046572876956082L;

    @JsonProperty("rolename")
    private String roleName;
    @JsonProperty("rolestandardname")
    private String roleStandardName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleStandardName() {
        return roleStandardName;
    }

    public void setRoleStandardName(String roleStandardName) {
        this.roleStandardName = roleStandardName;
    }

}
