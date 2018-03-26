package com.jingluu.admin.auth.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AuthRoleVO implements Serializable {
    private String id;

    /**
     * 角色名称 
     */
    private String name;

    private String code;

    /**
     * 1 - 可用；0 - 不可用 
     */
    private Integer enabled;

    private Date createdTime;

    private Date lastUpdateTime;

    /**
     * 角色描述 
     */
    private String description;

    List<AuthPermissionVO> permissions;

    private static final long serialVersionUID = 1L;

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
        this.name = name == null ? null : name.trim();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public List<AuthPermissionVO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<AuthPermissionVO> permissions) {
        this.permissions = permissions;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}