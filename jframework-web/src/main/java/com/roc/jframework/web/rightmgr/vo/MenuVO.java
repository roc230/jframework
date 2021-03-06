package com.roc.jframework.web.rightmgr.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuVO implements Serializable {
    private String id;

    private String name;

    private String type;

    private String url;

    private String icon;

    private String parentId;

    private String parentName;

    private Boolean enable = true;

    private String description;

    private String systemId;

    private Integer index;

    private String label;

    private List<MenuVO> children = new ArrayList<>();

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<MenuVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVO> children) {
        this.children = children;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void addChild(MenuVO menuVO){
        if(this.children == null){
            this.children = new ArrayList<>();
        }
        this.children.add(menuVO);
    }

    public static final class Builder{
        private String id;
        private String name;
        private String type;
        private String url;
        private String icon;
        private String parentId;
        private String parentName;
        private Boolean enable = true;
        private String description;
        private String systemId;
        private Integer index;
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder type(String type){
            this.type = type;
            return this;
        }
        public Builder url(String url){
            this.url = url;
            return this;
        }
        public Builder icon(String icon){
            this.icon = icon;
            return this;
        }
        public Builder parentId(String parentId){
            this.parentId = parentId;
            return this;
        }
        public Builder enable(Boolean enable){
            this.enable = enable;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder systemId(String systemId){
            this.systemId = systemId;
            return this;
        }
        public Builder index(Integer index){
            this.index = index;
            return this;
        }
        public Builder parentName(String parentName){
            this.parentName = parentName;
            return this;
        }
        public MenuVO build(){
            MenuVO menuVO = new MenuVO();
            menuVO.setId(this.id);
            menuVO.setName(this.name);
            menuVO.setType(this.type);
            menuVO.setUrl(this.url);
            menuVO.setIcon(this.icon);
            menuVO.setParentId(this.parentId);
            menuVO.setEnable(this.enable);
            menuVO.setDescription(this.description);
            menuVO.setSystemId(this.systemId);
            menuVO.setIndex(this.index);
            menuVO.setLabel(this.name);
            menuVO.setParentName(this.parentName);
            return menuVO;
        }
    }

}
