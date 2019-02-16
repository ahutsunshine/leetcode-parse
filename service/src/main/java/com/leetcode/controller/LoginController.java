package com.leetcode.controller;

import com.leetcode.model.response.APIResponse;
import com.leetcode.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public APIResponse getProblem(String username, String pwd) {
        return loginService.login(username, pwd);
    }
}
