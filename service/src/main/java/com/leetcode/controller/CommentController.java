package com.leetcode.controller;

import com.leetcode.common.PageReqBody;
import com.leetcode.model.comment.CommentReqBody;
import com.leetcode.model.response.APIResponse;
import com.leetcode.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
    private static final int CREATED = 201;
    private final CommentService service;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    @RequestMapping(path = "/topics/comments", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getComments(@RequestBody PageReqBody req) {
        APIResponse res = service.getComments(req);
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/comments", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> createComment(@RequestBody CommentReqBody request) {
        APIResponse res = service.createComment(request);
        int code = res.getCode() == 200 ? CREATED : res.getCode();
        res.setCode(code);
        return ResponseEntity.status(code).body(res);
    }

    @RequestMapping(path = "/comments", method = RequestMethod.PUT)
    public ResponseEntity<APIResponse> updateComment(@RequestBody CommentReqBody request) {
        APIResponse res = service.updateComment(request);
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/comments", method = RequestMethod.DELETE)
    public ResponseEntity<APIResponse> deleteComment(@RequestBody CommentReqBody request) {
        APIResponse res = service.deleteComment(request);
        if(res.getCode() == 200) return ResponseEntity.noContent().build();
        return ResponseEntity.status(res.getCode()).body(res);
    }
}
