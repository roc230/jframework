package com.roc.jframework.web.accountmgr.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;

/**
 * 员工
 */
public class Employee implements Serializable {

    private String id;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    private String name;

    @ManyToMany
    private List<Station> stations;
}
