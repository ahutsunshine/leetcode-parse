package com.leetcode.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leetcode.model.problem.detail.Problem;
import com.leetcode.model.problem.list.ProblemStatusList;
import com.leetcode.model.problem.list.TopicTag;
import com.leetcode.model.response.APIResponse;
import com.leetcode.service.ProblemService;
import org.apache.http.entity.StringEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.leetcode.util.HttpUtil.*;
import static com.leetcode.util.RequestParamUtil.buildProblemReqBody;

@Service
@CacheConfig(cacheNames = "problem", keyGenerator = "cacheKeyGenerator")
public class ProblemServiceImpl implements ProblemService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProblemServiceImpl.class);

    private static final String TOP_100_LIKED_URI = "https://leetcode.com/api/problems/favorite_lists/top-100-liked-questions/";
    private static final String TOP_INTERVIEW_URI = "https://leetcode.com/api/problems/favorite_lists/top-interview-questions/";
    private static final String FILTER_URI = "https://leetcode.com/problems/api/filter-questions/";
    private static final String ALL_PROBLEMS_URL = "https://leetcode.com/api/problems/all/";

    @Override
    @Cacheable(unless = "#result.code == null || #result.code != 200")
    public APIResponse getProblem(String uri) {
//        CookieStore cookieStore = getCookies(uri);
        String titleSlug = getTitleSlug(uri);
        StringEntity requestBody = buildProblemReqBody(titleSlug);
        String res = post(uri, requestBody);
        APIResponse error;
        if ((error = getErrorIfFailed(res)) != null) return error;
        JSONObject j = JSONObject.parseObject(res).getJSONObject("data");
        j = j.getJSONObject("question");
        Problem p = JSON.parseObject(j.toString(), Problem.class);
        return new APIResponse(p);
    }

    @Override
    @Cacheable(unless = "#result.code == null || #result.code != 200")
    public APIResponse getAllProblems() {
        return getProblems(ALL_PROBLEMS_URL);
    }

    @Override
    @Cacheable(unless = "#result.code == null || #result.code != 200")
    public APIResponse getTopLikedProblems() {
        return getProblems(TOP_100_LIKED_URI);
    }

    @Override
    @Cacheable(unless = "#result.code == null || #result.code != 200")
    public APIResponse getInterviewProblems() {
        return getProblems(TOP_INTERVIEW_URI);
    }

    @Override
    @Cacheable(unless = "#result.code == null || #result.code != 200")
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

    @Override
    @Cacheable(unless = "#result.code == null || #result.code != 200")
    public APIResponse filterProblems(String key) {
        String res = get(FILTER_URI + key);
        if (res.startsWith("[")) { // that's array
            res = res.replace("[", "").replace("]", "");
            List<Integer> ids = new ArrayList<>();
            for (String id : res.split(",")) {
                ids.add(Integer.valueOf(id));
            }
            return new APIResponse(ids);
        }
        return JSON.parseObject(res, APIResponse.class);
    }

    private APIResponse getProblems(String uri) {
        String res = get(uri);
        ProblemStatusList statusList = JSON.parseObject(res, ProblemStatusList.class);
        // determine whether response is normal
        if (statusList == null || statusList.getNumTotal() == null) {
            return JSON.parseObject(res, APIResponse.class);
        }
        return new APIResponse(statusList);
    }

    private List<TopicTag> getTags(Elements tagElements) {
        if (tagElements == null) return new ArrayList<>();
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

}
