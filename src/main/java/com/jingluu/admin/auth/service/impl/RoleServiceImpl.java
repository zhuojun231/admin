package com.jingluu.admin.auth.service.impl;

import com.jingluu.admin.auth.dao.AuthRoleMapper;
import com.jingluu.admin.auth.dao.AuthUserMapper;
import com.jingluu.admin.auth.entity.AuthRole;
import com.jingluu.admin.auth.service.RoleService;
import com.jingluu.admin.auth.vo.AuthRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    public AuthRoleMapper roleMapper;


    @Override
    public List<AuthRoleVO> findUserRoleList(Long userId) {
        return null;
    }
}
