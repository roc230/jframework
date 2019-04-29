package com.roc.jframework.web.webcrawler.entity;

import com.roc.jframework.basic.utils.ListUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "nc_web_novel")
public class WebNovel implements Serializable {

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name="id", length = 36)
    private String id;

    @Column(name = "name")
    private String name;

    /**
     * 别名
     */
    @Column(name = "alias", length = 64)
    private String alias;

    @Column(name = "author", length = 64)
    private String author;

    @Column(name = "brief", length = 256)
    private String brief;

    @Column(name = "click_count")
    private Integer clickCount;

    @Column(name = "words")
    private Integer words;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "nc_category_novel",
            joinColumns = {@JoinColumn(name = "novel_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")}
            )
    private List<Category> category;

    @Column(name = "cover_img", length = 255)
    private String coverImg;

    @Column(name = "url", length = 255)
    private String url;

    @Column(name = "enable", length = 1, columnDefinition = "char", nullable = false)
    private Boolean enable = true;

    @Column(name = "record_time")
    private Date recordTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 12)
    private Status status;

    @Column(name = "owner", length = 36)
    private String owner;

    @ManyToOne
    @JoinColumn(name = "novel_site_id")
    private NovelSite novelSite;

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Integer getWords() {
        return words;
    }

    public void setWords(Integer words) {
        this.words = words;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public NovelSite getNovelSite() {
        return novelSite;
    }

    public void setNovelSite(NovelSite novelSite) {
        this.novelSite = novelSite;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        WebNovel n = (WebNovel)obj;
        return false;
    }

    public static final class Builder{
        private String id;
        private String name;
        private String alias;
        private String author;
        private String brief;
        private Integer clickCount;
        private Integer words;
        private List category;
        private String coverImg;
        private String url;
        private Boolean enable = true;
        private Date recordTime;
        private Status status;
        private String owner;
        private NovelSite novelSite;
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder alias(String alias){
            this.alias = alias;
            return this;
        }
        public Builder author(String author){
            this.author = author;
            return this;
        }
        public Builder brief(String brief){
            this.brief = brief;
            return this;
        }
        public Builder clickCount(Integer clickCount){
            this.clickCount = clickCount;
            return this;
        }
        public Builder words(Integer words){
            this.words = words;
            return this;
        }
        public Builder category(List category){
            this.category = category;
            return this;
        }
        public Builder category(Category... categories){
            if(this.category == null){
                this.category = ListUtils.newArrayList();
            }
            this.category.add(Arrays.asList(categories));
            return this;
        }
        public Builder coverImg(String coverImg){
            this.coverImg = coverImg;
            return this;
        }
        public Builder url(String url){
            this.url = url;
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
        public Builder status(Status status){
            this.status = status;
            return this;
        }
        public Builder owner(String owner){
            this.owner = owner;
            return this;
        }
        public Builder novelSite(NovelSite novelSite){
            this.novelSite = novelSite;
            return this;
        }
        public WebNovel build(){
            WebNovel webNovel = new WebNovel();
            webNovel.setId(this.id);
            webNovel.setName(this.name);
            webNovel.setAlias(this.alias);
            webNovel.setAuthor(this.author);
            webNovel.setBrief(this.brief);
            webNovel.setClickCount(this.clickCount);
            webNovel.setWords(this.words);
            webNovel.setCategory(this.category);
            webNovel.setCoverImg(this.coverImg);
            webNovel.setUrl(this.url);
            webNovel.setEnable(this.enable);
            webNovel.setRecordTime(this.recordTime);
            webNovel.setStatus(this.status);
            webNovel.setOwner(this.owner);
            webNovel.setNovelSite(this.novelSite);
            return webNovel;
        }
    }

}
