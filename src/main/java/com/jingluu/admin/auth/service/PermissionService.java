package com.jingluu.admin.auth.service;

import com.jingluu.admin.auth.vo.AuthFunctionPermissionVO;
import com.jingluu.admin.auth.vo.AuthPermissionVO;

import java.util.List;

public interface PermissionService {
    List<AuthPermissionVO> findUserMenuList(Long userId);
    List<AuthFunctionPermissionVO> findFunctionPermissionList();
}
