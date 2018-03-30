package com.jingluu.admin.auth.shiro;

import com.jingluu.admin.util.WebUtils;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 扩展PermissionsAuthorizationFilter过滤器，加入AJAX判断与消息返回
 */
@Component("customPermissionsAuthorizationFilter")
public class CustomPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if(WebUtils.isAsync(httpServletRequest)){
            //如果是AJAX请求
            WebUtils.sendErrorAsync(httpServletResponse,"权限不足");
            return false;
        }

        return super.onAccessDenied(request, response, mappedValue);
    }

    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
         return super.isAccessAllowed(request,response,mappedValue);
    }
}
