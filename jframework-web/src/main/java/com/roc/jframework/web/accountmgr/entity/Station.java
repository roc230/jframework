package com.roc.jframework.web.accountmgr.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * 岗位
 */
public class Station implements Serializable {

    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;


}
