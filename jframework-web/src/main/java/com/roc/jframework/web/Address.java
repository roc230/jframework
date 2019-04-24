package com.roc.jframework.web;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 地址
 */
@Entity
@Table(name = "t_address")
public class Address implements Serializable {

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "country", length = 24)
    private String country;

    @Column(name = "province", length = 24)
    private String province;

    @Column(name = "city", length = 24)
    private String city;

    @Column(name = "district", length = 24)
    private String district;

    @Column(name = "county", length = 24)
    private String county;

    @Column(name = "detail", length = 255)
    private String detail;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
