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
 * 扩展登录认证过滤器
 */
@Component("customRolesAuthorizationFilter")
public class CustomRolesAuthorizationFilter extends RolesAuthorizationFilter {

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
