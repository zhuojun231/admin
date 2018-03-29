package com.jingluu.admin.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jingluu.admin.auth.controller.ResponseBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {

    /**
     * 是否是AJAX请求
     * @param request
     * @return true，是
     */
    public static boolean isAsync(HttpServletRequest request){
        String xmlHttpRequest = request.getHeader("X-Requested-With");
        return !StringUtils.isEmpty(xmlHttpRequest) && "XMLHttpRequest".equals(xmlHttpRequest.trim());
    }

    public static void sendErrorAsync(HttpServletResponse response,String errorMessage){
        //如果是AJAX请求
        ResponseBean responseBean = new ResponseBean();
        responseBean.fail(errorMessage);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String error = objectMapper.writeValueAsString(responseBean);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(error);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
