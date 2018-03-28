package com.jingluu.admin.auth.shiro;

import com.jingluu.admin.auth.service.PermissionService;
import com.jingluu.admin.auth.vo.AuthFunctionPermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 过滤器链定义
 */
@Component("filterChainDefinition")
public class FilterChainDefinition {

    @Autowired
    private PermissionService permissionService;

    /**
     * 获取功能鉴权的过滤器链
     * @return
     */
    public Map<String,String> getFilterChainDefinitionMap(){
        //过滤器链
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        //登录可以匿名访问，无需认证
        filterChainDefinitionMap.put("/login", ShiroFilterUtils.FILTER_NAME_ANON);
        filterChainDefinitionMap.put("/doLogin", ShiroFilterUtils.FILTER_NAME_ANON);

        //功能和角色、权限对应列表
        List<AuthFunctionPermissionVO> funcPerms = permissionService.findFunctionPermissionList();
        if (funcPerms != null && !funcPerms.isEmpty()) {
            for (AuthFunctionPermissionVO funcPerm : funcPerms) {
                //功能URL
                String url = funcPerm.getUrl();
                if (url == null || "".equals(url.trim())){
                    continue;
                }

                //访问该功能所需的角色
                List<String> roles = funcPerm.getRoles();
                //格式化为Shiro角色配置字符串（roles[role1,role2,...]）
                String roleFilterDefinition = ShiroFilterUtils.formatRoles(roles);

                //访问该功能所需的权限
                List<String> permissions = funcPerm.getPermissions();
                //格式化为Shiro权限配置字符串（perms[perm1,perm2,...]）
                String permsFilterDefinition = ShiroFilterUtils.formatPerms(permissions);

                //最终组合为：/path=authc,roles[role1,role2,...],perms[perm1,perm2,...]
                String filterChainDefinition = ShiroFilterUtils.FILTER_NAME_AUTHC;
                if (!roleFilterDefinition.equals("")) {
                    filterChainDefinition += "," + roleFilterDefinition;
                }
                if (!permsFilterDefinition.equals("")) {
                    filterChainDefinition += "," + filterChainDefinition;
                }
                filterChainDefinitionMap.put(url, permsFilterDefinition);
            }
        }

        //其他URL默认均可匿名访问
        filterChainDefinitionMap.put("/**", ShiroFilterUtils.FILTER_NAME_ANON);

        return filterChainDefinitionMap;

    }
}
