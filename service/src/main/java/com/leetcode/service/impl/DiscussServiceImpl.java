package com.leetcode.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leetcode.common.PageReqBody;
import com.leetcode.model.discuss.DiscussTopics;
import com.leetcode.model.discuss.Topic;
import com.leetcode.model.discuss.TopicReqBody;
import com.leetcode.model.response.APIResponse;
import com.leetcode.service.DiscussService;
import com.leetcode.util.ImageUtil;
import org.apache.http.client.CookieStore;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLDecoder;

import static com.leetcode.util.CommonUtil.*;
import static com.leetcode.util.HttpUtil.*;
import static com.leetcode.util.RequestParamUtil.*;

@Service
@CacheConfig(cacheNames = "discuss", keyGenerator = "cacheKeyGenerator")
public class DiscussServiceImpl implements DiscussService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscussServiceImpl.class);
    private static final String UPDATE_TOPIC_OPERATION = "updateTopic";
    private static final String CREATE_TOPIC_OPERATION = "createTopicForQuestion";
    private static final String DELETE_TOPIC_OPERATION = "deleteTopic";

    @Override
    @Cacheable(unless = "#result.code == null || #result.code != 200")
    public APIResponse getDiscussions(PageReqBody req) {
        APIResponse errorStatus = checkPageParam(req);
        if (errorStatus != null) return errorStatus;
        StringEntity requestBody = buildDiscussReqBody(req);
        CookieStore cookieStore = getCookies(req.getUri());
        String res = post(req.getUri(), cookieStore, requestBody);
        APIResponse error = getErrorIfFailed(res);
        if (error != null) return error;
        JSONObject j = JSONObject.parseObject(res).getJSONObject("data");
        j = j.getJSONObject("questionTopicsList");
        DiscussTopics topicsList = JSON.parseObject(j.toString(), DiscussTopics.class);
        return new APIResponse(topicsList);
    }

    @Override
    @Cacheable(unless = "#result.code == null || #result.code != 200")
    public APIResponse getTopic(String problemUri, int topicId) {
        CookieStore cookieStore = getCookies(problemUri);
        StringEntity body = buildDiscussTopicsReqBody(topicId);
        String res = post(problemUri, cookieStore, body);
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

    @Override
    public APIResponse uploadImage(String uri, String refer, String cookie, MultipartFile file) {
        try {
            if (cookie == null) return new APIResponse(400, "Cookie cannot be empty");
            cookie = URLDecoder.decode(cookie, "UTF-8");
            if (!isCookieValid(cookie)) {
                return new APIResponse(400, "User cookie is invalid");
            }
            return ImageUtil.upload(uri, refer, cookie, file);
        } catch (Exception e) {
            LOGGER.error("Exception occurs. ", e);
        }
        return new APIResponse(500, "Upload failure. Please try again");
    }

    private APIResponse topicPost(TopicReqBody req, StringEntity entity, String operation) {
        String res = post(req.getUri(), req.getCookies(), entity);
        APIResponse e;
        if ((e = getErrorIfFailed(res)) != null) return e;
        return getResponseStatus(operation, res);
    }

    private APIResponse checkParams(TopicReqBody req, String operation) {
        if (!isCookieValid(req.getCookies())) {
            return new APIResponse(400, "User cookie is invalid");
        }
        if (req.getId() == null) {
            return new APIResponse(400, "Id is required");
        }
        if (StringUtils.isEmpty(req.getTitle()) && !operation.equals(DELETE_TOPIC_OPERATION)) {
            return new APIResponse(400, "Title is required");
        }
        if (StringUtils.isEmpty(req.getContent()) && !operation.equals(DELETE_TOPIC_OPERATION)) {
            return new APIResponse(400, "Content is required");
        }
        return null;
    }
}
