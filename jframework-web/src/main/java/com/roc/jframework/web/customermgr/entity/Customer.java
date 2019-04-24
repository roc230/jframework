package com.roc.jframework.web.customermgr.entity;

import com.roc.jframework.web.Address;
import com.roc.jframework.web.accountmgr.entity.Account;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "cm_customer")
public class Customer implements Serializable {

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    private String id;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = true)
    private Account account;

    @Column(name = "name", length = 36)
    private String name;

    @Column(name = "nick_name", length = 36)
    private String nickName;

    @Column(name = "email", length = 36)
    private String email;

    @Column(name = "sex", length = 2)
    private String sex;

    @Column(name = "birthday")
    private Date birthday;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
