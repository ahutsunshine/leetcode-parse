package com.leetcode.controller;

import com.leetcode.model.comment.CommentReqBody;
import com.leetcode.model.response.APIResponse;
import com.leetcode.service.CommentService;
import com.leetcode.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
    private final CommentServiceImpl service;

    @Autowired
    public CommentController(CommentServiceImpl service) {
        this.service = service;
    }

    @RequestMapping(path = "/comments", method = RequestMethod.POST)
    public APIResponse createComment(@RequestBody CommentReqBody request) {
        return service.createComment(request);
    }

    @RequestMapping(path = "/comments", method = RequestMethod.PUT)
    public APIResponse updateComment(@RequestBody CommentReqBody request) {
        return service.updateComment(request);
    }

    @RequestMapping(path = "/comments", method = RequestMethod.DELETE)
    public APIResponse deleteComment(@RequestBody CommentReqBody request) {
        return service.deleteComment(request);
    }
}
