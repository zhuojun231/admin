package com.jingluu.admin.auth.service.impl;

import com.jingluu.admin.auth.dao.AuthUserMapper;
import com.jingluu.admin.auth.entity.AuthUser;
import com.jingluu.admin.auth.service.UserService;
import com.jingluu.admin.auth.vo.AuthUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public AuthUserMapper userMapper;

    @Override
    public AuthUserVO login(String username, String password) {
        AuthUserVO vo = new AuthUserVO();
        vo.setUsername(username);
        vo.setPassword(password);
        return this.findOne(vo);
    }

    public AuthUserVO findOne(AuthUserVO authUserVO){
        List<AuthUserVO> list = this.findList(authUserVO);
        return list == null ? null : list.get(0);
    }

    public List<AuthUserVO> findList(AuthUserVO authUserVO){
        List<AuthUserVO> users = new ArrayList<>();

        AuthUser authUser = new AuthUser();
        authUser.setUsername(authUserVO.getUsername());
        authUser.setPassword(authUserVO.getPassword());
        authUser.setEnabled(authUserVO.getEnabled());
        authUser.setNickname(authUserVO.getNickname());
        List<AuthUser> list = userMapper.selectList(authUser);

        if(list == null || list.isEmpty()){
            return null;
        }

        for (AuthUser user : list){
            AuthUserVO vo = new AuthUserVO();
            vo.setId(user.getId());
            vo.setUsername(user.getUsername());
            vo.setPassword(user.getPassword());
            vo.setNickname(user.getNickname());
            users.add(vo);
        }

        return users;
    }
}
