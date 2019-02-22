package com.leetcode.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BasicController {
    @RequestMapping(path = "/version", method = RequestMethod.GET)
    public String greeting() {
        return "0.1.0";
    }
}
