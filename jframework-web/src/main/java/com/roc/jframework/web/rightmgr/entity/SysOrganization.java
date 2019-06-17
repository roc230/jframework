package com.roc.jframework.web.rightmgr.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 组织机构
 */
@Entity
public class SysOrganization implements Serializable {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private OrganizationType type;

    private String name;

    private Boolean enable = true;

    @ManyToOne
    private SysOrganization parent;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
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

    public SysOrganization getParent() {
        return parent;
    }

    public void setParent(SysOrganization parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
