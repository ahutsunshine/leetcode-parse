package com.leetcode.service;

import com.leetcode.model.response.APIResponse;

public interface TopService {

    APIResponse getTopLikedProblems();

    APIResponse getInterviewProblems();
}
