package com.leetcode.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leetcode.model.discuss.DiscussTopics;
import com.leetcode.model.discuss.Topic;
import com.leetcode.model.problem.detail.Problem;
import com.leetcode.model.problem.list.ProblemStatusList;
import com.leetcode.model.problem.list.TopicTag;
import com.leetcode.model.response.APIResponse;
import com.leetcode.service.ProblemService;
import org.apache.http.client.CookieStore;
import org.apache.http.entity.StringEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.leetcode.util.HttpUtil.*;
import static com.leetcode.util.RequestParamUtil.*;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Override
    public APIResponse getProblem(String uri) {
        CookieStore cookieStore = getCookies(uri);
        String titleSlug = getTitleSlug(uri);
        StringEntity requestBody = buildProblemReqBody(titleSlug);
        String res = post(uri, cookieStore, requestBody);
        APIResponse error;
        if ((error = getErrorIfFailed(res)) != null) return error;
        JSONObject j = JSONObject.parseObject(res).getJSONObject("data");
        j = j.getJSONObject("question");
        Problem p = JSON.parseObject(j.toString(), Problem.class);
        return new APIResponse(p);
    }

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

    @Override
    public APIResponse getProblemList(String uri) {
        String res = get(uri);
        ProblemStatusList statusList = JSON.parseObject(res, ProblemStatusList.class);
        return new APIResponse(statusList);
    }

    @Override
    public APIResponse getTags(String uri) {
        String html = getHtmlContent(uri);
        if (html == null) return new APIResponse(500, "Request failed. Please try again.");
        Document doc = Jsoup.parse(html);
        Elements tagElements = doc.select("div[id=current-topic-tags]").select("a");
        List<TopicTag> topicTags = getTags(tagElements);
        Elements companyElements = doc.select("div[id=current-company-tags]").select("a");
        List<TopicTag> companyTags = getTags(companyElements);
        Map<String, Object> tags = new HashMap<>();
        tags.put("topicTags", topicTags);
        tags.put("companyTags", companyTags);
        return new APIResponse(tags);
    }

    private List<TopicTag> getTags(Elements tagElements) {
        if(tagElements == null) return new ArrayList<>();
        List<TopicTag> tags = new ArrayList<>();
        for (Element e : tagElements) {
            TopicTag tag = new TopicTag();
            Elements tagNames = e.select("span[class=text-sm text-gray]");
            tag.setTagName(tagNames.size() != 0 ? tagNames.first().text() : null);
            Elements tagNum = e.select("span[class=badge text-sm]");
            tag.setTagNum(tagNames.size() != 0 ? Integer.valueOf(tagNum.first().text()) : null);
            tag.setTagUri(e.attr("href"));
            tags.add(tag);
        }
        return tags;
    }

    private String buildDiscussUri(String uri, int page, String orderBy, String query) {
        return uri + "?currentPage=" + page + "&" +
                "orderBy=" + orderBy + "&" +
                "query=" + query;
    }

}
