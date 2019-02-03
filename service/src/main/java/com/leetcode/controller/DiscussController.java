package com.leetcode.controller;

import com.leetcode.model.response.APIResponse;
import com.leetcode.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DiscussController {
    private final DiscussService service;

    @Autowired
    public DiscussController(DiscussService service) {
        this.service = service;
    }

    @RequestMapping(path = "/discussions", method = RequestMethod.POST)
    public APIResponse getDiscussions(String uri, int page,
                                      @RequestParam(defaultValue = "most_votes") String orderBy,
                                      @RequestParam(defaultValue = "") String query,
                                      @RequestParam(defaultValue = "15") int pageSize,
                                      int questionId) {
        return service.getDiscussions(uri, page, orderBy, query, pageSize, questionId);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.POST)
    public APIResponse getDiscussTopic(String problemUri, String discussUri, int topicId) {
        return service.getDiscussTopic(problemUri, discussUri, topicId);
    }

}
