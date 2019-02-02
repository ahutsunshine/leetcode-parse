package com.leetcode.service;

import com.leetcode.model.response.APIResponse;

public interface ProblemService {
    APIResponse getProblem(String uri);

    APIResponse getDiscussions(String uri, int page, String orderBy,
                               String query, int pageSize, int questionId);

    APIResponse getDiscussTopic(String problemUri, String discussUri, int topicId);
}
