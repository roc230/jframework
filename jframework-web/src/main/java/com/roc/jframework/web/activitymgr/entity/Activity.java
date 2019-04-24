package com.roc.jframework.web.activitymgr.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 活动
 */
@Entity
@Table(name = "am_activity")
public class Activity implements Serializable {

    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "name", length = 24)
    private String name;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @ManyToMany
    @JoinTable(name = "am_activity_bonuse",
            joinColumns = {@JoinColumn(name = "activity_id", referencedColumnName = "id")},
            inverseJoinColumns ={@JoinColumn(name = "bonuse_id", referencedColumnName = "id")})
    private List<Bonuse> bonuses;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Bonuse> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<Bonuse> bonuses) {
        this.bonuses = bonuses;
    }
}
