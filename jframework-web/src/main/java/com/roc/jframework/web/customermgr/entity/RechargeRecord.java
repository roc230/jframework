package com.roc.jframework.web.customermgr.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class RechargeRecord implements Serializable {

    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Date time;

    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_type", length = 12)
    private CurrencyType currencyType;
}
