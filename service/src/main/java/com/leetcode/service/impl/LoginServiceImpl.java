package com.leetcode.service.impl;

import com.leetcode.model.response.APIResponse;
import com.leetcode.service.LoginService;
import com.leetcode.util.LoginUtil;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public APIResponse login(String user, String pwd) {
        return LoginUtil.login(user, pwd);
    }
}
