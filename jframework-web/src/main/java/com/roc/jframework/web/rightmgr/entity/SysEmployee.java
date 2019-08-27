package com.roc.jframework.web.rightmgr.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class SysEmployee implements Serializable {

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    private String name;

    private String sex;

    private Date birthday;

    private Date joinTime;

    private Boolean enable = true;

    /**
     * 员工的系统帐号
     */
    @OneToOne
    @JoinColumn(name = "account_id")
    private SysAccount account;

    /**
     * 公司
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private SysOrganization company;

    /**
     * 部门
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private SysOrganization department;

    /**
     * 岗位
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id")
    private SysOrganization station;

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

    public SysOrganization getCompany() {
        return company;
    }

    public void setCompany(SysOrganization company) {
        this.company = company;
    }

    public SysOrganization getDepartment() {
        return department;
    }

    public void setDepartment(SysOrganization department) {
        this.department = department;
    }

    public SysOrganization getStation() {
        return station;
    }

    public void setStation(SysOrganization station) {
        this.station = station;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }
}
