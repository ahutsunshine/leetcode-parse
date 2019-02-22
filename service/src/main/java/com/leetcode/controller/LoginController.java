package com.leetcode.controller;

import com.leetcode.model.response.APIResponse;
import com.leetcode.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class LoginController {
    private static final int CREATED = 201;
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getProblem(String username, String pwd) {
        APIResponse res = loginService.login(username, pwd);
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> signUp(String username, String email, String password1, String password2) {
        APIResponse res = loginService.signUp(username, email, password1, password2);
        int code = res.getCode() == 200 ? CREATED : res.getCode();
        res.setCode(code);
        return ResponseEntity.status(code).body(res);
    }

    @RequestMapping(path = "/password/reset", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> resetPassword(String email) {
        APIResponse res = loginService.resetPassword(email);
        return ResponseEntity.status(res.getCode()).body(res);
    }
}
