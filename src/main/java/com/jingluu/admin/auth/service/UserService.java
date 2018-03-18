package com.jingluu.admin.auth.service;

import com.jingluu.admin.auth.vo.AuthUserVO;

public interface UserService {
    AuthUserVO login(String username,String password);
}
