package com.roc.jframework.web.rightmgr.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 菜单
 */
@Entity
public class SysMenu implements Serializable {

    @Id
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MenuType type;

    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    private String url;

    private String icon;

    @ManyToOne
    private SysMenu parent;

    private Boolean enable = true;

    private String description;

    @ManyToOne
    private SysSystem system;

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

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public SysMenu getParent() {
        return parent;
    }

    public void setParent(SysMenu parent) {
        this.parent = parent;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
