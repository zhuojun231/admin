package com.jingluu.admin.auth.service.impl;

import com.jingluu.admin.auth.dao.AuthPermissionMapper;
import com.jingluu.admin.auth.dao.AuthUserMapper;
import com.jingluu.admin.auth.entity.AuthPermission;
import com.jingluu.admin.auth.entity.AuthUser;
import com.jingluu.admin.auth.service.PermissionService;
import com.jingluu.admin.auth.service.UserService;
import com.jingluu.admin.auth.vo.AuthPermissionVO;
import com.jingluu.admin.auth.vo.AuthUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    public AuthPermissionMapper permissionMapper;

    @Override
    public List<AuthPermissionVO> findUserMenuList(Long userId) {
        List<AuthPermissionVO> resultList = new ArrayList<>();

        List<AuthPermission> permissions = permissionMapper.selectUserMenuList(userId);

        if(null != permissions && !permissions.isEmpty()){
            for(AuthPermission permission : permissions){
                resultList.add(toVO(permission));
            }
        }
        return resultList;
    }

    private AuthPermissionVO toVO(AuthPermission permission){
        AuthPermissionVO vo = null;
        if(null != permission){
            vo = new AuthPermissionVO();
            vo.setId(permission.getId().toString());
            vo.setParentId(permission.getParentId().toString());
            vo.setName(permission.getName());
            vo.setCode(permission.getCode());
            vo.setUrl(permission.getUrl());
            vo.setType(permission.getType());
            vo.setEnabled(permission.getEnabled());
            vo.setCreatedTime(permission.getCreatedTime());
            vo.setDescription(permission.getDescription());

            List<AuthPermission> permissions = permission.getSubPermissions();

            if(permissions != null && !permissions.isEmpty()){
                List<AuthPermissionVO> voList = new ArrayList<>();
                for (AuthPermission p : permissions) {
                     voList.add(this.toVO(p)) ;
                }
                vo.setSubPermissions(voList);
            }
        }
        return vo;
    }



}
