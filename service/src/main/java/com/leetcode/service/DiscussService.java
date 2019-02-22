package com.leetcode.service;

import com.leetcode.common.PageReqBody;
import com.leetcode.model.discuss.TopicReqBody;
import com.leetcode.model.response.APIResponse;
import org.springframework.web.multipart.MultipartFile;

public interface DiscussService {
    APIResponse getTopics(PageReqBody req);

    APIResponse getTopic(int topicId, String cookies);

    APIResponse createTopic(TopicReqBody req);

    APIResponse updateTopic(TopicReqBody req);

    APIResponse deleteTopic(TopicReqBody req);

    APIResponse uploadImage(String uri, String refer, String cookies, MultipartFile file);
}
