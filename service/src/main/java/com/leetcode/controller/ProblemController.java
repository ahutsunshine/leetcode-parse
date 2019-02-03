package com.leetcode.controller;

import com.leetcode.model.response.APIResponse;
import com.leetcode.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "/problems", method = RequestMethod.GET)
    public APIResponse getProblemList(String uri) {
        return problemService.getProblemList(uri);
    }

    @RequestMapping(path = "/problems/top100", method = RequestMethod.GET)
    public APIResponse getTopLikedProblems() {
        return problemService.getTopLikedProblems();
    }

    @RequestMapping(path = "/problems/interview", method = RequestMethod.GET)
    public APIResponse getInterviewProblems() {
        return problemService.getInterviewProblems();
    }

    @RequestMapping(path = "/tags", method = RequestMethod.POST)
    public APIResponse getTags(String uri) {
        return problemService.getTags(uri);
    }

}
