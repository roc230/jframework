package com.roc.jframework.web.webcrawler.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 小说网站
 */
@Entity
@Table(name = "nc_novel_site")
public class NovelSite implements Serializable {

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name", length = 48)
    private String name;

    @Column(name = "url", length = 255)
    private String url;

    @Column(name = "record_time")
    private Date recordTime;

    @Column(name = "enable", length = 1, columnDefinition = "char")
    private Boolean enable = true;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public static final class Builder{
        private String id;
        private String name;
        private String url;
        private Date recordTime;
        private Boolean enable = true;
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder url(String url){
            this.url = url;
            return this;
        }
        public Builder recordTime(Date recordTime){
            this.recordTime = recordTime;
            return this;
        }
        public Builder enable(Boolean enable){
            this.enable = enable;
            return this;
        }
        public NovelSite build(){
            NovelSite novelSite = new NovelSite();
            novelSite.setId(this.id);
            novelSite.setName(this.name);
            novelSite.setUrl(this.url);
            novelSite.setRecordTime(this.recordTime);
            novelSite.setEnable(this.enable);
            return novelSite;
        }
    }

}
