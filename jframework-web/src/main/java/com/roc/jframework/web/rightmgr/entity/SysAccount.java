package com.roc.jframework.web.rightmgr.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class SysAccount implements Serializable {

    @Id
    private String id;

    private String loginName;

    @ManyToMany
    @JoinTable(
            name = "sys_account_role",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private List<SysRole> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
