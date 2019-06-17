package com.roc.jframework.web.rightmgr.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class SysEmployee implements Serializable {

    @Id
    private String id;

    private String name;

    private Boolean enable = true;

    /**
     * 员工的系统帐号
     */
    @OneToOne
    @JoinColumn(name = "account_id")
    private SysAccount account;

    /**
     * 所属组织
     */
    @OneToOne
    @JoinColumn(name = "organization_id")
    private SysOrganization organization;

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

    public SysAccount getAccount() {
        return account;
    }

    public void setAccount(SysAccount account) {
        this.account = account;
    }
}
