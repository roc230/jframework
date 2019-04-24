package com.roc.jframework.web.accountmgr.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * 组织架构
 */
public class Organization implements Serializable {

    private String id;

    private OrgType type;

    private String name;

    private String description;

    private Boolean enable = true;

    @ManyToOne
    @JoinColumn(name = "father_id")
    private Organization father;
}
