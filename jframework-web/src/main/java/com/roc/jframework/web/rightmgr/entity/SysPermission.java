package com.roc.jframework.web.rightmgr.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class SysPermission implements Serializable {

    @Id
    private String id;
    private String name;
    private String url;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    private SysMenu menu;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SysMenu getMenu() {
        return menu;
    }

    public void setMenu(SysMenu menu) {
        this.menu = menu;
    }
}
