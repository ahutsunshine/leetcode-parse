package com.leetcode.service;

import com.leetcode.model.response.APIResponse;

public interface LoginService {
    APIResponse login(String username, String pwd);

    APIResponse signUp(String username, String email, String password1, String password2);
}
