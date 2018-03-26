package com.jingluu.admin.auth.service.impl;

import com.jingluu.admin.auth.dao.AuthRoleMapper;
import com.jingluu.admin.auth.dao.AuthUserMapper;
import com.jingluu.admin.auth.entity.AuthPermission;
import com.jingluu.admin.auth.entity.AuthRole;
import com.jingluu.admin.auth.service.RoleService;
import com.jingluu.admin.auth.vo.AuthPermissionVO;
import com.jingluu.admin.auth.vo.AuthRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    public AuthRoleMapper roleMapper;


    @Override
    public List<AuthRoleVO> findUserRoleList(Long userId) {
        List<AuthRoleVO> resultList = new ArrayList<>();
        List<AuthRole> roleList = roleMapper.selectUserRoleList(userId);
        if(null != roleList && !roleList.isEmpty()){
            for (AuthRole role : roleList) {
                resultList.add(toVO(role));
            }
        }
        return resultList;
    }

    private AuthRoleVO toVO(AuthRole role){
        AuthRoleVO vo = null;
        if(null != role){
            //角色
            vo = new AuthRoleVO();
            vo.setId(role.getId().toString());
            vo.setName(role.getName());
            vo.setCode(role.getCode());

            //角色权限
            List<AuthPermission> permissions = role.getPermissions();
            if(permissions != null && !permissions.isEmpty()){
                List<AuthPermissionVO> voList = new ArrayList<>();
                for (AuthPermission permission : permissions) {
                    AuthPermissionVO permissionVO = new AuthPermissionVO();
                    permissionVO.setId(permission.getId().toString());
                    permissionVO.setName(permission.getName());
                    permissionVO.setCode(permission.getCode());
                    voList.add(permissionVO) ;
                }
                vo.setPermissions(voList);
            }
        }
        return vo;
    }
}
