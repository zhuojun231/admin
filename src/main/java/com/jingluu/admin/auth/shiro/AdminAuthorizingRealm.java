package com.jingluu.admin.auth.shiro;

import com.jingluu.admin.auth.service.RoleService;
import com.jingluu.admin.auth.service.UserService;
import com.jingluu.admin.auth.vo.AuthPermissionVO;
import com.jingluu.admin.auth.vo.AuthRoleVO;
import com.jingluu.admin.auth.vo.AuthUserVO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义Realm，获取用户、角色、权限信息
 */
@Component("adminAuthorizingRealm")
public class AdminAuthorizingRealm  extends AuthorizingRealm{
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CredentialsMatcher credentialsMatcher;


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();

        //获取用户登录账号
        String username = (String) super.getAvailablePrincipal(principalCollection);

        //查询用户信息
        AuthUserVO user = this.userService.findByUsername(username);
        if(user != null){
            //查询用户拥有的角色和权限
            List<AuthRoleVO> roles = roleService.findUserRoleList(user.getId());
            if(roles != null && !roles.isEmpty()){
                for (AuthRoleVO role : roles){
                    //角色
                    auth.addRole(role.getCode());

                    //操作权限
                    List<AuthPermissionVO> functions = role.getPermissions();
                    if(functions != null && !functions.isEmpty()){
                        for (AuthPermissionVO permission : functions){
                            auth.addStringPermission(permission.getCode());
                        }
                    }
                }
            }
        }
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
            throw new UnknownAccountException("未知账户");
        }

        if(user.getEnabled().intValue() == 0){
            throw new LockedAccountException("账号锁定");
        }

        //SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,user.getPassword(),super.getName());

        //返回认证信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,user.getPassword(),super.getName());
        //加密算法（ShiroConfig中的CredentialsMatcher实例已经设定）、salt必须与加密密码时的加密算法、salt一致，否则认证失败
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(ShiroFilterUtils.CREDENTIAL_SALT));

        return authenticationInfo;
    }

    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        return this.credentialsMatcher;
    }
}
