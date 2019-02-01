package com.leetcode.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leetcode.model.problem.Problem;
import com.leetcode.model.response.APIResponse;
import com.leetcode.service.ProblemService;
import org.apache.http.client.CookieStore;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Service;

import static com.leetcode.util.HttpUtil.*;

@Service
public class ProblemServiceImpl implements ProblemService {
    @Override
    public APIResponse getProblem(String cookieUri, String dataUri) {
        CookieStore cookieStore = getCookies(cookieUri);
        String titleSlug = getTitleSlug(cookieUri);
        StringEntity requestBody = buildRequestBody(titleSlug);
        String res = post(dataUri, cookieStore, requestBody);
        APIResponse error;
        if ((error = getErrorIfFailed(res)) != null) {
            return error;
        }
        JSONObject j = JSONObject.parseObject(res);
        j = j.getJSONObject("data").getJSONObject("question");
        Problem p = JSON.parseObject(j.toString(), Problem.class);
        return new APIResponse(p);
    }
}
