package com.leetcode.controller;

import com.leetcode.model.response.APIResponse;
import com.leetcode.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProblemController {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @RequestMapping(path = "/problems", method = RequestMethod.POST)
    public APIResponse getProblem(String uri) {
        return problemService.getProblem(uri);
    }

    @RequestMapping(path = "/discussions", method = RequestMethod.POST)
    public APIResponse getDiscussions(@RequestParam(required = true) String uri,
                                      @RequestParam(required = true) int page,
                                      @RequestParam(defaultValue = "most_votes") String orderBy,
                                      @RequestParam(defaultValue = "") String query,
                                      @RequestParam(defaultValue = "15") int pageSize,
                                      @RequestParam(required = true) int questionId) {
        return problemService.getDiscussions(uri, page, orderBy, query, pageSize, questionId);
    }

    @RequestMapping(path = "/topics", method = RequestMethod.POST)
    public APIResponse getDiscussTopic(String problemUri, String discussUri, int topicId) {
        return problemService.getDiscussTopic(problemUri, discussUri, topicId);
    }

    @RequestMapping(path = "/problems", method = RequestMethod.GET)
    public APIResponse getProblemList(String uri) {
        return problemService.getProblemList(uri);
    }
}
