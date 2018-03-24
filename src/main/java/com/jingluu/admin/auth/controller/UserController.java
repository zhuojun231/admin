package com.jingluu.admin.auth.controller;

import com.jingluu.admin.auth.service.UserService;
import com.jingluu.admin.auth.vo.AuthUserVO;
import constants.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public Object login(String username, String password,String verificationCode, HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        HttpSession session = request.getSession();

        String vCode = (String) session.getAttribute("verificationCode");

        if(username == null || "".equals(username.trim())
                || password == null || "".equals(password.trim())){
            responseBean.fail("请输入账号和密码");
            return responseBean;
        }

        if(verificationCode == null || "".equals(verificationCode.trim())){
            responseBean.fail("请输入验证码");
            return responseBean;
        }

        if(!vCode.equals(verificationCode.trim())){
            responseBean.fail("请正确输入验证码");
            return responseBean;
        }

        AuthUserVO userVO = userService.login(username,password);

        if(userVO == null){
            responseBean.fail("账号或密码有误");
        }else {
            session.setAttribute(WebConstants.CURRENT_USER,userVO);
            responseBean.setData(userVO);
        }



        return responseBean;
    }

    /**
     * 退出
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        //使当前Session无效
        request.getSession().invalidate();
        return "redirect:login";
    }
}
