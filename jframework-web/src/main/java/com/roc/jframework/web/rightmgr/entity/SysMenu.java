package com.roc.jframework.web.rightmgr.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 菜单
 */
@Entity
public class SysMenu implements Serializable {

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    @Version
    private int version;

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

    /**
     * 序号，用于设置菜单顺序
     */
    @Column
    private Integer indexNo = 0;

    @ManyToOne
    private SysSystem system;

    @OneToOne(mappedBy = "menu", cascade = {CascadeType.ALL})
    private SysPermission permission;

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

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public SysSystem getSystem() {
        return system;
    }

    public void setSystem(SysSystem system) {
        this.system = system;
    }

    public SysPermission getPermission() {
        return permission;
    }

    public void setPermission(SysPermission permission) {
        this.permission = permission;
    }
}
