package com.jingluu.admin.auth.shiro;

import com.jingluu.admin.auth.service.UserService;
import com.jingluu.admin.auth.vo.AuthUserVO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义Realm，获取用户信息、角色信息、权限信息，并对用户进行认证和授权
 */
@Component("adminAuthorizingRealm")
public class AdminAuthorizingRealm  extends AuthorizingRealm{
    @Autowired
    private UserService userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();

        //添加当前用户所拥有到角色
        auth.addRole("admin");

        //添加当前用户所拥有到权限
        auth.addStringPermission("user:add");

        return auth;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        String username = token.getUsername();

        AuthUserVO user = userService.findByUsername(username);

        if(null == user){
            throw new UnknownAccountException("账号或密码有误");
        }

        if(user.getEnabled().intValue() == 0){
            throw new LockedAccountException("账号锁定");
        }

        return new SimpleAuthenticationInfo(username,user.getPassword(),super.getName());
    }
}
