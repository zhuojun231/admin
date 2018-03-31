package com.jingluu.admin.auth.service.impl;

import com.jingluu.admin.auth.dao.AuthUserMapper;
import com.jingluu.admin.auth.entity.AuthUser;
import com.jingluu.admin.auth.service.UserService;
import com.jingluu.admin.auth.vo.AuthUserVO;
import com.jingluu.admin.auth.vo.Pagination;
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

    @Override
    public AuthUserVO findOne(AuthUserVO authUserVO){
        List<AuthUserVO> list = this.findList(authUserVO);
        return list == null ? null : list.get(0);
    }

    @Override
    public AuthUserVO findByUsername(String username){
        AuthUserVO vo = new AuthUserVO();
        vo.setUsername(username);
        return this.findOne(vo);
    }

    public List<AuthUserVO> findList(AuthUserVO authUserVO){
        List<AuthUserVO> users = new ArrayList<>();

        AuthUser authUser = new AuthUser();
        authUser.setUsername(authUserVO.getUsername());
        authUser.setPassword(authUserVO.getPassword());
        authUser.setEnabled(authUserVO.getEnabled());
        authUser.setNickname(authUserVO.getNickname());

        List<AuthUser> list = userMapper.selectList(authUser,null,null);

        if(list == null || list.isEmpty()){
            return null;
        }

        return toVO(list);
    }

    public Pagination findList(AuthUserVO authUserVO, Integer pageNo, Integer size){
        Pagination<AuthUserVO> pagination = new Pagination<>();

        AuthUser authUser = new AuthUser();
        authUser.setUsername(authUserVO.getUsername());
        authUser.setPassword(authUserVO.getPassword());
        authUser.setEnabled(authUserVO.getEnabled());
        authUser.setNickname(authUserVO.getNickname());

        //总记录数
        Integer total  = userMapper.count(authUser);;
        if(total == null || total == 0){
            return pagination;
        }

        //分页数据
        pagination = new Pagination<>(pageNo,size,total);
        List<AuthUserVO> users = new ArrayList<>();
        List<AuthUser> list = userMapper.selectList(authUser,pagination.getOffset(),size);

        if(list == null || list.isEmpty()){
            return null;
        }

        pagination.setItems(toVO(list));
        return pagination;
    }

    public List<AuthUserVO> toVO(List<AuthUser> list){
        List<AuthUserVO> users = new ArrayList<>();
        for (AuthUser user : list){
            users.add(toVO(user));
        }
        return users;
    }

    public AuthUserVO toVO(AuthUser user){
        AuthUserVO vo = new AuthUserVO();
        vo.setId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setPassword(user.getPassword());
        vo.setNickname(user.getNickname());
        vo.setEnabled(user.getEnabled());
        vo.setCreatedTime(user.getCreatedTime());

        return vo;
    }
}
