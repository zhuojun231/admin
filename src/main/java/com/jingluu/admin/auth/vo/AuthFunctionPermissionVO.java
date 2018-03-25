package com.jingluu.admin.auth.vo;

import java.io.Serializable;
import java.util.List;

public class AuthFunctionPermissionVO implements Serializable {
    private static final long serialVersionUID = -2757980195174544391L;

    private String url;
    private List<String> roles;
    private List<String> permissions;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}