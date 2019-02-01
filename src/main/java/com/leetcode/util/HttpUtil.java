package com.leetcode.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leetcode.model.response.APIResponse;
import com.leetcode.model.response.LeetcodeErrorMessage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.*;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;


public class HttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    public static CookieStore getCookies(String uri) {
        CookieStore cookieStore = new BasicCookieStore();
        HttpUriRequest request = buildGetRequest(uri);
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore).build();
             CloseableHttpResponse response = httpClient.execute(request)) {
            return cookieStore;
        } catch (IOException e) {
            LOGGER.error("IOException ", e);
        }
        return null;
    }

    public static String post(String uri, CookieStore cookieStore, HttpEntity params) {
        String token = getCsrfToken(cookieStore);
        HttpUriRequest request = buildPostRequest(uri, token, params);
        APIResponse response;
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore).build();
             CloseableHttpResponse res = httpClient.execute(request)) {
            if (res == null) {
                LOGGER.error("HttpResponse is null, please check cookie, header and params!");
                response = new APIResponse(400, "Please check request params");
                return JSON.toJSONString(response);
            }
            int statusCode = res.getStatusLine().getStatusCode();
            //返回200仍可能是错误信息,需要处理
            if (statusCode == 200) {
                return EntityUtils.toString(res.getEntity(), "UTF-8");
            }
            LOGGER.error("status:{},message:{}", res.getEntity().getContent());
            if (String.valueOf(statusCode).startsWith("4")) {
                response = new APIResponse(403, "Forbidden 403. CSRF verification failed. Request aborted.");
            } else {
                response = new APIResponse(400, "Request failed.");
            }
            return JSON.toJSONString(response);
        } catch (Exception e) {
            LOGGER.error("Exception occurs.", e);
            return JSON.toJSONString(new APIResponse(500, "Exception occurs"));
        }
    }

    /**
     * 判断返回数据是否为空，空则表示有出错信息
     * @param res get or post result string
     * @return LeetcodeErrorMessage error message
     */
    public static APIResponse getErrorIfFailed(String res) {
        LeetcodeErrorMessage msg = JSON.parseObject(res, LeetcodeErrorMessage.class);
        if (msg.getCode() != null) { // means it's APIResponse
            return JSON.parseObject(res, APIResponse.class);
        }
        if (isErrorMessage(msg)) {
            String info = msg.getErrors().size() > 0 ?
                    msg.getErrors().get(0).getMessage() : "Request failed.";
            return new APIResponse(400, info);
        }
        return null;
    }

    private static boolean isErrorMessage(LeetcodeErrorMessage msg) {
        Map<String, Object> data = msg.getData();
        // means it's error message
        if (data == null) return true;
        for (String key : data.keySet()) {
            if (data.get(key) == null) return true;
        }
        return false;
    }

    private static HttpUriRequest buildGetRequest(String uri) {
        return RequestBuilder.get(uri)
                .setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                .setHeader(":authority", "leetcode.com")
                .setHeader(":method", "GET")
                .setHeader(":scheme", "https")
                .build();
    }

    private static HttpUriRequest buildPostRequest(String uri, String token, HttpEntity params) {
        return RequestBuilder.post(uri)
                .setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setHeader(HttpHeaders.REFERER, uri)
                .setHeader(":authority", "leetcode.com")
                .setHeader(":method", "POST")
                .setHeader(":scheme", "https")
                .setHeader("x-csrftoken", token)
                .setEntity(params)
                .build();
    }

    private static String getCsrfToken(CookieStore cookieStore) {
        if (cookieStore == null) return null;
        String token = null;
        for (Cookie cookie : cookieStore.getCookies()) {
            if (cookie.getName().equalsIgnoreCase("csrftoken")) {
                token = cookie.getValue();
            }
        }
        return token;
    }

    public static String getTitleSlug(String uri) {
        if(StringUtils.isEmpty(uri)) return null;
        return uri.replace("leetcode.com","")
                .replace("problems","")
                .replace("https:","")
                .replace("http:","")
                .replace("/","");
    }

    public static StringEntity buildRequestBody(String operationName, String variables, String query) {
        JSONObject json = new JSONObject();
        json.put("operationName", operationName);
        json.put("variables", variables);
        json.put("query", query);
        return new StringEntity(json.toString(), "UTF-8");
    }

    public static StringEntity buildRequestBody(String titleSlug) {
        String operationName = "questionData";
        JSONObject variables = new JSONObject();
        variables.put("titleSlug", titleSlug);
        String query = "query questionData($titleSlug: String!) {\n" +
                "question(titleSlug: $titleSlug) {\n" +
                "questionId\n" +
                "questionFrontendId\n" +
                "boundTopicId\n" +
                "title\n" +
                "titleSlug\n" +
                "content\n" +
                "difficulty\n" +
                "likes\n" +
                "dislikes\n" +
                "isLiked\n" +
                "similarQuestions\n" +
                "contributors {\n" +
                "username\n" +
                "profileUrl\n" +
                "avatarUrl\n" +
                "}\n" +
                "topicTags {\n" +
                "name\n" +
                "slug\n" +
                "translatedName\n" +
                "}\n" +
                "codeSnippets {\n" +
                "lang\n" +
                "langSlug\n" +
                "code\n" +
                "}\n" +
                "stats\n" +
                "hints\n" +
                "solution {\n" +
                "id\n" +
                "canSeeDetail\n" +
                "}\n" +
                "status\n" +
                "sampleTestCase\n" +
                "metaData\n" +
                "}\n" +
                "}";
        return buildRequestBody(operationName, variables.toString(), query);
    }
}
