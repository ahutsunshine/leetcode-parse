package com.leetcode.service.impl;

import com.leetcode.model.response.APIResponse;
import com.leetcode.service.LoginService;
import com.leetcode.util.LoginUtil;
import com.leetcode.util.SignUpUtil;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public APIResponse login(String username, String pwd) {
        return LoginUtil.login(username, pwd);
    }

    @Override
    public APIResponse signUp(String username, String email, String password1, String password2) {
        return SignUpUtil.signUp(username, email, password1, password2);
    }
}
