package com.roc.jframework.web.webcrawler.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "nc_category")
public class Category implements Serializable {

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name", length = 24)
    private String name;

    @Column(name = "enable")
    private Boolean enable = true;

    @Column(name = "record_time")
    private Date recordTime;

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

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public static final class Builder{
        private String id;
        private String name;
        private Boolean enable = true;
        private Date recordTime;
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
        public Builder recordTime(Date recordTime){
            this.recordTime = recordTime;
            return this;
        }
        public Category build(){
            Category category = new Category();
            category.setId(this.id);
            category.setName(this.name);
            category.setEnable(this.enable);
            category.setRecordTime(this.recordTime);
            return category;
        }
    }

}
