package com.jingluu.admin.auth.entity;

import java.io.Serializable;
import java.util.Date;

public class AuthPermission implements Serializable {
    private Long id;

    /**
     * 0表示没有上一级 
     */
    private Long parentId;

    /**
     * 权限名称 
     */
    private String name;

    /**
     * 权限吗 
     */
    private String code;

    /**
     * 功能url 
     */
    private String url;

    /**
     * 类型： 1 - 菜单； 2 - 操作 
     */
    private Integer type;

    /**
     * 1 - 可用；0 - 不可用 
     */
    private Integer enabled;

    private Date createdTime;

    private Date lastUpdateTime;

    /**
     * 序号 
     */
    private Integer order;

    /**
     * 权限描述 
     */
    private String description;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}