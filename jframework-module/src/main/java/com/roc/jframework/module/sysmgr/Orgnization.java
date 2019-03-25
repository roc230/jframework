package com.roc.jframework.module.sysmgr;

public class Orgnization {

    /**
     * ID
     */
    private String id;

    /**
     * 部门编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 部门LOGO
     */
    private String icon;

    /**
     * 组织类别
     */
    private OrgType orgType;

    /**
     * 序码
     */
    private Integer order;

    /**
     * 部门描述
     */
    private String description;

    /**
     * 父级部门
     */
    private Orgnization father;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public OrgType getOrgType() {
        return orgType;
    }

    public void setOrgType(OrgType orgType) {
        this.orgType = orgType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Orgnization getFather() {
        return father;
    }

    public void setFather(Orgnization father) {
        this.father = father;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
