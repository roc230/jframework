package com.roc.jframework.web.accountmgr.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "rm_account")
public class Account implements Serializable {

    /**
     * ID
     */
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    private String id;

    /**
     * 登陆名
     */
    @Column(name = "login_name", length = 36)
    private String loginName;

    /**
     * 密码
     */
    @Column(name = "password", length = 36)
    private String password;

    /**
     * 记录时间(注册时间)
     */
    @Column(name = "record_time")
    private Date recordTime;

    /**
     * 是否已冻结
     */
    @Column(name = "frozen", length = 1, columnDefinition = "char")
    private Boolean frozen = false;

    /**
     * 逻辑删除标记
     */
    @Column(name = "enable", length = 1, columnDefinition = "char")
    private Boolean enable = true;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Boolean getFrozen() {
        return frozen;
    }

    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
