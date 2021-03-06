package com.roc.jframework.web.rightmgr.vo;

import java.io.Serializable;
import java.util.Date;

public class RoleVO implements Serializable {

    private String id;
    private String name;
    private Boolean enable;
    private String[] permissionIds;
    private String[] permissionNames;
    private Date latestUpdateTime;
    private String operatorId;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String[] getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String[] permissionIds) {
        this.permissionIds = permissionIds;
    }

    public Date getLatestUpdateTime() {
        return latestUpdateTime;
    }

    public void setLatestUpdateTime(Date latestUpdateTime) {
        this.latestUpdateTime = latestUpdateTime;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String[] getPermissionNames() {
        return permissionNames;
    }

    public void setPermissionNames(String[] permissionNames) {
        this.permissionNames = permissionNames;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
