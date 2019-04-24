package com.roc.jframework.web.bookmgr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bm_book")
public class Book implements Serializable {

    /**
     * ID
     */
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    private String id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 中文名
     */
    @Column(name = "cname")
    private String cname;

    /**
     * 作者
     */
    @Column(name = "author")
    private String author;

    /**
     * 题材
     */
    @Transient
    private List<Material> materials;

    /**
     * 逻辑删除标记
     */
    @Column(name = "enable")
    private Boolean enable = true;

    /**
     * 录入时间
     */
    @Column(name = "record_time")
    private Date recorddTime;

    /**
     * 上线时间
     */
    @Column(name = "online_time")
    private Date onlineTime;

    /**
     * 最近更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Date getRecorddTime() {
        return recorddTime;
    }

    public void setRecorddTime(Date recorddTime) {
        this.recorddTime = recorddTime;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
