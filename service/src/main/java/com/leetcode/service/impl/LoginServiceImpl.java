package com.leetcode.service.impl;

import com.leetcode.model.response.APIResponse;
import com.leetcode.service.LoginService;
import com.leetcode.util.LoginUtil;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "login", keyGenerator = "cacheKeyGenerator")
public class LoginServiceImpl implements LoginService {
    @Override
    @Cacheable(unless = "#result.code == null || #result.code != 200")
    public APIResponse login(String user, String pwd) {
        return LoginUtil.login(user, pwd);
    }
}
