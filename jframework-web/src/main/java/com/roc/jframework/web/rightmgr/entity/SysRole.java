package com.roc.jframework.web.rightmgr.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 */
@Entity
public class SysRole implements Serializable {

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    private String name;

    @Column(nullable = false)
    private Boolean enable = true;

    /**
     * 操作人
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id")
    private SysAccount operator;

    /**
     * 最近操作时间
     */
    private Date latestOperateTime;

    /**
     * 权限集合
     */
    @ManyToMany
    @JoinTable(name = "sys_role_permission",
        joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")}
    )
    private List<SysPermission> permissions;

    @ManyToMany(mappedBy = "roles")
    private List<SysAccount> accounts;

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

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<SysAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<SysAccount> accounts) {
        this.accounts = accounts;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public SysAccount getOperator() {
        return operator;
    }

    public void setOperator(SysAccount operator) {
        this.operator = operator;
    }

    public Date getLatestOperateTime() {
        return latestOperateTime;
    }

    public void setLatestOperateTime(Date latestOperateTime) {
        this.latestOperateTime = latestOperateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static final class Builder{
        private String id;
        private String name;
        private Boolean enable = true;
        private SysAccount operator;
        private Date latestOperateTime;
        private List permissions;
        private List accounts;
        private String description;
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder enable(Boolean enable){
            this.enable = enable;
            return this;
        }
        public Builder operator(SysAccount operator){
            this.operator = operator;
            return this;
        }
        public Builder latestOperateTime(Date latestOperateTime){
            this.latestOperateTime = latestOperateTime;
            return this;
        }
        public Builder permissions(List permissions){
            this.permissions = permissions;
            return this;
        }
        public Builder accounts(List accounts){
            this.accounts = accounts;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }
        public SysRole build(){
            SysRole sysRole = new SysRole();
            sysRole.setId(this.id);
            sysRole.setName(this.name);
            sysRole.setEnable(this.enable);
            sysRole.setOperator(this.operator);
            sysRole.setLatestOperateTime(this.latestOperateTime);
            sysRole.setPermissions(this.permissions);
            sysRole.setAccounts(this.accounts);
            sysRole.setDescription(this.description);
            return sysRole;
        }
    }

}
