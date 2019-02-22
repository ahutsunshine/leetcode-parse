package com.leetcode.controller;

import com.leetcode.model.response.APIResponse;
import com.leetcode.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProblemController {

    private static final int CREATED = 201;
    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @RequestMapping(path = "/problems/detail", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getProblem(String uri) {
        APIResponse res = problemService.getProblem(uri);
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/problems", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getAllProblems() {
        APIResponse res = problemService.getAllProblems();
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/problems/top100", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getTopLikedProblems() {
        APIResponse res = problemService.getTopLikedProblems();
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/problems/interview", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getInterviewProblems() {
        APIResponse res = problemService.getInterviewProblems();
        return ResponseEntity.status(res.getCode()).body(res);
    }

    @RequestMapping(path = "/tags", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> getTags(String uri) {
        APIResponse res = problemService.getTags(uri);
        return ResponseEntity.status(res.getCode() == 200 ? CREATED : res.getCode()).body(res);
    }

    @RequestMapping(value = "/problems/filtration/{key}", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> filterProblems(@PathVariable("key") String key) {
        APIResponse res = problemService.filterProblems(key);
        return ResponseEntity.status(res.getCode()).body(res);
    }

}
