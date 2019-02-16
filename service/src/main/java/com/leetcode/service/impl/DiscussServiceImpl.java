package com.leetcode.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leetcode.common.ResponseStatus;
import com.leetcode.model.discuss.DiscussTopics;
import com.leetcode.model.discuss.Topic;
import com.leetcode.model.discuss.TopicReqBody;
import com.leetcode.model.response.APIResponse;
import com.leetcode.service.DiscussService;
import org.apache.http.client.CookieStore;
import org.apache.http.entity.StringEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import static com.leetcode.util.HttpUtil.*;
import static com.leetcode.util.RequestParamUtil.*;

@Service
@CacheConfig(cacheNames = "discuss", keyGenerator = "cacheKeyGenerator")
public class DiscussServiceImpl implements DiscussService {

    private static final String UPDATE_TOPIC_OPERATION = "updateTopic";
    private static final String CREATE_TOPIC_OPERATION = "createTopicForQuestion";
    private static final String DELETE_TOPIC_OPERATION = "deleteTopic";

    @Override
    @Cacheable(unless = "#result.code == null || #result.code != 200")
    public APIResponse getDiscussions(String uri, int page, String orderBy,
                                      String query, int pageSize, int questionId) {
        if (page < 0) return new APIResponse(400, "Negative page index is not supported.");
        if (pageSize <= 0 || pageSize > 512) pageSize = 15;
        int skip = 0;
        CookieStore cookieStore = getCookies(uri.replace("discuss", ""));
        uri = buildDiscussUri(uri, page, orderBy, query);
        StringEntity requestBody = buildDiscussReqBody(orderBy, query, skip,
                String.valueOf(pageSize), questionId);
        String res = post(uri, cookieStore, requestBody);
        APIResponse error = getErrorIfFailed(res);
        if (error != null) return error;
        JSONObject j = JSONObject.parseObject(res).getJSONObject("data");
        j = j.getJSONObject("questionTopicsList");
        DiscussTopics topicsList = JSON.parseObject(j.toString(), DiscussTopics.class);
        return new APIResponse(topicsList);
    }

    @Override
    @Cacheable(unless = "#result.code == null || #result.code != 200")
    public APIResponse getDiscussTopic(String problemUri, String discussUri, int topicId) {
        CookieStore cookieStore = getCookies(problemUri);
        StringEntity body = buildDiscussTopicsReqBody(topicId);
        String res = post(discussUri, cookieStore, body);
        APIResponse error = getErrorIfFailed(res);
        if (error != null) return error;
        JSONObject j = JSONObject.parseObject(res);
        j = j.getJSONObject("data").getJSONObject("topic");
        Topic topic = JSON.parseObject(j.toString(), Topic.class);
        return new APIResponse(topic);
    }

    @Override
    public APIResponse createTopic(TopicReqBody req) {
        APIResponse error = checkParams(req, CREATE_TOPIC_OPERATION);
        if (error != null) return error;
        StringEntity entity = buildCreateTopicReqBody(req);
        return topicPost(req, entity, CREATE_TOPIC_OPERATION);
    }

    @Override
    public APIResponse updateTopic(TopicReqBody req) {
        APIResponse error = checkParams(req, UPDATE_TOPIC_OPERATION);
        if (error != null) return error;
        StringEntity entity = buildUpdateTopicReqBody(req);
        return topicPost(req, entity, UPDATE_TOPIC_OPERATION);
    }

    @Override
    public APIResponse deleteTopic(TopicReqBody req) {
        APIResponse error = checkParams(req, DELETE_TOPIC_OPERATION);
        if (error != null) return error;
        StringEntity requestBody = buildDeleteTopicReqBody(req);
        return topicPost(req, requestBody, DELETE_TOPIC_OPERATION);
    }

    private APIResponse topicPost(TopicReqBody req, StringEntity entity, String operation) {
        CookieStore cookieStore = req.getCookieStore();
        String res = post(req.getUri(), cookieStore, entity);
        APIResponse e;
        if ((e = getErrorIfFailed(res)) != null) return e;
        JSONObject data = JSONObject.parseObject(res).getJSONObject("data");
        data = data.getJSONObject(operation);
        ResponseStatus status = JSON.parseObject(data.toString(), ResponseStatus.class);
        return new APIResponse(status);
    }

    private APIResponse checkParams(TopicReqBody req, String operation) {
//        if (StringUtils.isEmpty(req.getUri())) {
//            return new APIResponse(400, "Refer uri is required.");
//        }
        if (CollectionUtils.isEmpty(req.getCookies())) {
            return new APIResponse(400, "User cookie is required.");
        }
        if (req.getId() == null) {
            return new APIResponse(400, "Id is required.");
        }
        if (StringUtils.isEmpty(req.getTitle()) && !operation.equals(DELETE_TOPIC_OPERATION)) {
            return new APIResponse(400, "Title is required.");
        }
        if (StringUtils.isEmpty(req.getContent()) && !operation.equals(DELETE_TOPIC_OPERATION)) {
            return new APIResponse(400, "Content is required.");
        }
        return null;
    }

    private String buildDiscussUri(String uri, int page, String orderBy, String query) {
        return uri + "?currentPage=" + page + "&" +
                "orderBy=" + orderBy + "&" +
                "query=" + query;
    }
}
