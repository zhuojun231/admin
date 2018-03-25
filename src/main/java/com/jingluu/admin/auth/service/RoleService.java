package com.jingluu.admin.auth.service;

import com.jingluu.admin.auth.vo.AuthRoleVO;

import java.util.List;

public interface RoleService {
    List<AuthRoleVO> findUserRoleList(Long userId);
}
