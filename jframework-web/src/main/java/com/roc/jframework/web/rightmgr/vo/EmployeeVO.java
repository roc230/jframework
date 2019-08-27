package com.roc.jframework.web.rightmgr.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class EmployeeVO implements Serializable {
    private String id;
    private String name;
    private String[][] orgIds;
    private String orgNames;
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date birthday;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date joinTime;
    private String sex;
    private String account;

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

    public String[][] getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(String[][] orgIds) {
        this.orgIds = orgIds;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOrgNames() {
        return orgNames;
    }

    public void setOrgNames(String orgNames) {
        this.orgNames = orgNames;
    }
}
