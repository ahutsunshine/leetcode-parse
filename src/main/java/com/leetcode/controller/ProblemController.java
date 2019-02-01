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
    private static final String GRAPHQL_URL = "https://leetcode.com/graphql";

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @RequestMapping(path = "/problems", method = RequestMethod.POST)
    public APIResponse getProblem(String uri,
                                  @RequestParam(defaultValue = GRAPHQL_URL) String dataUri) {
        return problemService.getProblem(uri, dataUri);
    }
}
