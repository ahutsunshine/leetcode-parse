package com.leetcode.controller;

import com.leetcode.model.discuss.DiscussPageReqBody;
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
    private final DiscussService service;

    @Autowired
    public DiscussController(DiscussService service) {
        this.service = service;
    }

    @RequestMapping(path = "/discussions", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getDiscussions(@RequestBody DiscussPageReqBody req) {
        APIResponse res = service.getDiscussions(req);
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getTopic(String problemUri, String topicUri, int topicId) {
        APIResponse res = service.getTopic(problemUri, topicUri, topicId);
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> createTopic(@RequestBody TopicReqBody req) {
        APIResponse res = service.createTopic(req);
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.PUT)
    public ResponseEntity<APIResponse> updateTopic(@RequestBody TopicReqBody req) {
        APIResponse res = service.updateTopic(req);
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.DELETE)
    public ResponseEntity<APIResponse> deleteTopic(@RequestBody TopicReqBody req) {
        APIResponse res = service.deleteTopic(req);
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/image", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> uploadImage(String uri, String refer, String cookie,
                                                   MultipartFile file) {
        APIResponse res = service.uploadImage(uri, refer, cookie, file);
        return ResponseEntity.status(res.getCode()).body(res);
    }
}
