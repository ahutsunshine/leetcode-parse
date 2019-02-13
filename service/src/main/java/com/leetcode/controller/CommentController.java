package com.leetcode.controller;

import com.leetcode.model.comment.CommentBody;
import com.leetcode.model.response.APIResponse;
import com.leetcode.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
    private final CommentService service;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    @RequestMapping(path = "/comments", method = RequestMethod.POST)
    public APIResponse createComment(@RequestBody CommentBody request) {
        return service.createComment(request);
    }

    @RequestMapping(path = "/comments", method = RequestMethod.PUT)
    public APIResponse updateComment(@RequestBody CommentBody request) {
        return service.updateComment(request);
    }

    @RequestMapping(path = "/comments", method = RequestMethod.DELETE)
    public APIResponse deleteComment(@RequestBody CommentBody request) {
        return service.deleteComment(request);
    }
}
