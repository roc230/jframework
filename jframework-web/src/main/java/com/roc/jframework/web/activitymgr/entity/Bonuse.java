package com.roc.jframework.web.activitymgr.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 活动奖励
 */
@Entity
@Table(name = "am_bonuse")
public class Bonuse implements Serializable {

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "name", length = 24)
    private String name;

    @Column(name = "description", length = 128)
    private String description;

    @Column(name = "count")
    private Integer count;

    @Column(name = "unit")
    private String unit;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
