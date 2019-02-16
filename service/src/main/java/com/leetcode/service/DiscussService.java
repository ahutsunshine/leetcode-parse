package com.leetcode.service;

import com.leetcode.model.discuss.TopicReqBody;
import com.leetcode.model.response.APIResponse;

public interface DiscussService {
    APIResponse getDiscussions(String uri, int page, String orderBy,
                               String query, int pageSize, int questionId);

    APIResponse getDiscussTopic(String problemUri, String discussUri, int topicId);

    APIResponse createTopic(TopicReqBody req);

    APIResponse updateTopic(TopicReqBody req);

    APIResponse deleteTopic(TopicReqBody req);
}
