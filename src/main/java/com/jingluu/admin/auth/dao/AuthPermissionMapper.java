package com.jingluu.admin.auth.dao;

import com.jingluu.admin.auth.entity.AuthPermission;

import java.util.List;

public interface AuthPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthPermission record);

    AuthPermission selectByPrimaryKey(Long id);

    List<AuthPermission> selectAll();

    int updateByPrimaryKey(AuthPermission record);
}