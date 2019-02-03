package com.leetcode.service;

import com.leetcode.model.response.APIResponse;

public interface ProblemService {
    APIResponse getProblem(String uri);

    APIResponse getProblemList(String uri);

    APIResponse getTopLikedProblems();

    APIResponse getInterviewProblems();

    APIResponse getTags(String uri);
}
