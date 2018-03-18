package com.jingluu.admin.auth.dao;

import com.jingluu.admin.auth.entity.AuthUser;

import java.util.List;

public interface AuthUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthUser authUser);

    AuthUser selectByPrimaryKey(Long id);

    List<AuthUser> selectAll();

    List<AuthUser> selectList(AuthUser authUser);

    int updateByPrimaryKey(AuthUser authUser);
}