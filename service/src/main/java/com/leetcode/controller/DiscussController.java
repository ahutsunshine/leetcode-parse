package com.leetcode.controller;

import com.leetcode.common.PageReqBody;
import com.leetcode.model.discuss.TopicReqBody;
import com.leetcode.model.response.APIResponse;
import com.leetcode.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class DiscussController {
    private static final int CREATED = 201;
    private final DiscussService service;

    @Autowired
    public DiscussController(DiscussService service) {
        this.service = service;
    }

    @RequestMapping(path = "/discussions/topics", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getTopics(@RequestBody PageReqBody req) {
        APIResponse res = service.getTopics(req);
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getTopic(int topicId, String cookies) {
        APIResponse res = service.getTopic(topicId, cookies);
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> createTopic(@RequestBody TopicReqBody req) {
        APIResponse res = service.createTopic(req);
        int code = res.getCode() == 200 ? CREATED : res.getCode();
        res.setCode(code);
        return ResponseEntity.status(code).body(res);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.PUT)
    public ResponseEntity<APIResponse> updateTopic(@RequestBody TopicReqBody req) {
        APIResponse res = service.updateTopic(req);
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.DELETE)
    public ResponseEntity<APIResponse> deleteTopic(@RequestBody TopicReqBody req) {
        APIResponse res = service.deleteTopic(req);
        if (res.getCode() == 200) return ResponseEntity.noContent().build();
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/image", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> uploadImage(String uri, String refer, String cookies,
                                                   MultipartFile file) {
        APIResponse res = service.uploadImage(uri, refer, cookies, file);
        int code = res.getCode() == 200 ? CREATED : res.getCode();
        res.setCode(code);
        return ResponseEntity.status(code).body(res);
    }
}
