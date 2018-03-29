package com.jingluu.admin.auth.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jingluu.admin.auth.controller.ResponseBean;
import com.jingluu.admin.util.WebUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 扩展登录认证过滤器
 */
@Component("customFormAuthenticationFilter")
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    /**
     * 拒绝访问监听事件
     * (调用org.apache.shiro.web.filter.AccessControlFilter的isAccessAllowed方法返回false后，调用该方法)
     * @param request
     * @param response
     * @return true,进入下一个过滤器；false，就此止住，直接处理返回结果
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("CustomFormAuthenticationFilter#onAccessDenied");

        //此处可以自定义其他登录认证的逻辑，比如验证码校验...

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if(WebUtils.isAsync(httpServletRequest)){
            //如果是AJAX请求
            ResponseBean responseBean = new ResponseBean();
            responseBean.fail("请登录");

            ObjectMapper objectMapper = new ObjectMapper();
            String error = objectMapper.writeValueAsString(responseBean);

            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write(error);

            //不进入下一个过滤器，直接返回错误结果
            return false;
        }

        //非AJAX请求(跳到登录页面)
        return super.onAccessDenied(request, response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue);
    }
}
