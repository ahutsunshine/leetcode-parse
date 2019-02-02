package com.leetcode;

import com.alibaba.fastjson.JSON;
import com.leetcode.model.problem.Problem;
import org.apache.http.client.CookieStore;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import static com.leetcode.util.HttpUtil.*;
import static com.leetcode.util.RequestParamUtil.buildProblemReqBody;

public class Test {
    public static void main(String[] args) {
        String uri = "https://leetcode.com/problems/trapping-rain-water";
        try {
            CookieStore cookieStore = getCookies(uri);
            StringEntity stringEntity = buildProblemReqBody("trapping-rain-water");
            String url = "https://leetcode.com/graphql";
            String res = post(url, cookieStore, stringEntity);
            JSONObject j = new JSONObject(res);
            j = j.getJSONObject("data").getJSONObject("question");
            Problem p = JSON.parseObject(j.toString(), Problem.class);
            System.out.println("-------------------------------------------------------");
            System.out.println(res);
            System.out.println("-------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(getTitleSlug(uri));
    }

    public static String getTitleSlug(String uri) {
        if(StringUtils.isEmpty(uri)) return null;
        return uri.replace("leetcode.com","")
                .replace("problems","")
                .replace("https:","")
                .replace("http:","")
                .replace("/","");
    }
}
