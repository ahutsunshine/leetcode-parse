package com.leetcode.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leetcode.model.discuss.DiscussTopics;
import com.leetcode.model.discuss.Topic;
import com.leetcode.model.response.APIResponse;
import com.leetcode.service.DiscussService;
import org.apache.http.client.CookieStore;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;

import static com.leetcode.util.HttpUtil.*;
import static com.leetcode.util.HttpUtil.getErrorIfFailed;
import static com.leetcode.util.RequestParamUtil.buildDiscussReqBody;
import static com.leetcode.util.RequestParamUtil.buildDiscussTopicsReqBody;

@Service
public class DiscussServiceImpl implements DiscussService {
    @Override
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

    private String buildDiscussUri(String uri, int page, String orderBy, String query) {
        return uri + "?currentPage=" + page + "&" +
                "orderBy=" + orderBy + "&" +
                "query=" + query;
    }
}
