package com.jingluu.admin.auth.dao;

import com.jingluu.admin.auth.entity.AuthRole;

import java.util.List;

public interface AuthRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthRole record);

    AuthRole selectByPrimaryKey(Integer id);

    List<AuthRole> selectAll();

    int updateByPrimaryKey(AuthRole record);
}