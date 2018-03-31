package com.jingluu.admin.auth.dao;

import com.jingluu.admin.auth.entity.AuthUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AuthUser authUser);

    AuthUser selectByPrimaryKey(Long id);

    List<AuthUser> selectAll();

    Integer count(AuthUser authUser);

    List<AuthUser> selectList(@Param("user") AuthUser authUser,@Param("offset") Integer offset,@Param("size") Integer size);

    int updateByPrimaryKey(AuthUser authUser);
}