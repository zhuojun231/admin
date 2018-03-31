package com.jingluu.admin.auth.controller;

import com.jingluu.admin.auth.service.UserService;
import com.jingluu.admin.auth.vo.AuthUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public String list(){
        return "user/user-list";
    }

    @RequestMapping("/findByPagination")
    @ResponseBody
    public Object findByPagination(AuthUserVO user,Integer pageNo,Integer size){
        return userService.findList(user,pageNo,size);
    }

}
