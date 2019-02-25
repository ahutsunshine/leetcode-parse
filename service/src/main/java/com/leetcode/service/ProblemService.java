package com.leetcode.service;

import com.leetcode.model.response.APIResponse;

public interface ProblemService {
    APIResponse getProblem(String titleSlug);

    APIResponse getAllProblems();

    APIResponse getTopLikedProblems();

    APIResponse getInterviewProblems();

    APIResponse getTags();

    APIResponse filterProblems(String key);
}
