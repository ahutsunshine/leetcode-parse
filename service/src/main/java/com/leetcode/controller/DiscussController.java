package com.leetcode.controller;

import com.leetcode.model.discuss.DiscussPageReqBody;
import com.leetcode.model.discuss.TopicReqBody;
import com.leetcode.model.response.APIResponse;
import com.leetcode.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public APIResponse getDiscussions(@RequestBody DiscussPageReqBody req) {
        return service.getDiscussions(req);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.GET)
    public APIResponse getTopic(String problemUri, String topicUri, int topicId) {
        return service.getTopic(problemUri, topicUri, topicId);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.POST)
    public APIResponse createTopic(@RequestBody TopicReqBody req) {
        return service.createTopic(req);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.PUT)
    public APIResponse updateTopic(@RequestBody TopicReqBody req) {
        return service.updateTopic(req);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.DELETE)
    public APIResponse deleteTopic(@RequestBody TopicReqBody req) {
        return service.deleteTopic(req);
    }

    @RequestMapping(path = "/image", method = RequestMethod.POST)
    public APIResponse uploadImage(String uri, String refer, String cookie,
                                   MultipartFile file) {
        return service.uploadImage(uri, refer, cookie, file);
    }
}
