package com.jingluu.admin.auth.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AuthUserVO implements Serializable {
    private Long id;

    /**
     * 账号 
     */
    private String username;

    /**
     * 密码 
     */
    private String password;

    /**
     * 用户昵称 
     */
    private String nickname;

    /**
     * 1 - 可用；0 - 不可用 
     */
    private Integer enabled;

    private Date createdTime;

    private Date lastUpdateTime;

    private List<AuthPermissionVO> menuList;

    /**
     * 最后最后登陆时间 
     */
    private Date lastLoginTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
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

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public List<AuthPermissionVO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<AuthPermissionVO> menuList) {
        this.menuList = menuList;
    }
}