package com.leetcode.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leetcode.model.response.APIResponse;
import com.leetcode.model.response.LeetcodeErrorMessage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
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

import static com.leetcode.util.CommonUtil.decode;


public class HttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
    private static final String GRAPHQL_URL = "https://leetcode.com/graphql";

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

    public static String get(String uri) {
        HttpUriRequest request = buildGetRequest(uri);
        return getResponseStatus(request, null);
    }

    public static String getHtmlContent(String uri) {
        HttpUriRequest request = buildGetRequest(uri);
        try (CloseableHttpClient httpClient = HttpClients.custom().build();
             CloseableHttpResponse res = httpClient.execute(request)) {
            if (res != null) {
                return EntityUtils.toString(res.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            LOGGER.error("Exception occurs.", e);
        }
        return null;
    }

    public static String post(String uri, CookieStore cookieStore, HttpEntity params) {
        HttpUriRequest request = buildPostRequest(uri, params);
        return getResponseStatus(request, cookieStore);
    }

    public static String post(String uri, HttpEntity params) {
        HttpUriRequest request = buildPostRequest(uri, params);
        return getResponseStatus(request, null);
    }

    public static String post(HttpEntity params) {
        HttpUriRequest request = buildPostRequest(params);
        return getResponseStatus(request, null);
    }

    public static String post(String uri, String cookies, HttpEntity params) {
        HttpUriRequest request = buildPostRequest(uri, cookies, params);
        return getResponseStatus(request, null);
    }

    public static String post(HttpEntity params, String cookies) {
        HttpUriRequest request = buildPostRequest(null, cookies, params);
        return getResponseStatus(request, null);
    }

    private static String getResponseStatus(HttpUriRequest request, CookieStore cookieStore) {
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore).build();
             CloseableHttpResponse res = httpClient.execute(request)) {
            return processResponse(res);
        } catch (Exception e) {
            LOGGER.error("Exception occurs.", e);
            return JSON.toJSONString(new APIResponse(500, "Exception occurs. Please try again"));
        }
    }

    static String getToken(String cookies) {
        if (cookies == null) return null;
        String[] values = cookies.split(";");
        for (String val : values) {
            String[] data = val.split("=");
            if (data.length != 2) { // incorrect cookie}
                LOGGER.error("Cookie:{} is invalid.", cookies);
                return null;
            }
            //remove blank space
            if (data[0].replace(" ", "").equals("csrftoken")) return data[1];
        }
        return null;
    }

    private static String processResponse(CloseableHttpResponse res) throws IOException {
        APIResponse response;
        if (res == null) {
            LOGGER.error("HttpResponse is null, please check cookie, header and params!");
            response = new APIResponse(400, "Please check request params");
            return JSON.toJSONString(response);
        }
        int statusCode = res.getStatusLine().getStatusCode();
        String content = EntityUtils.toString(res.getEntity(), "UTF-8");
        LOGGER.info("Response content : {}", content);
        //Return 200 may still be an error message that needs to be processed
        if (statusCode == 200) {
            return content;
        }
        String error = res.getStatusLine().getReasonPhrase();
        LOGGER.error("status:{},message:{}", statusCode, error);
        error = StringUtils.isEmpty(error) ? "Request failed" : error;
        response = new APIResponse(statusCode, error);
        return JSON.toJSONString(response);
    }

    /**
     * Determine whether returned data is null or not,
     * and null indicates that there is an error message
     *
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
                    msg.getErrors().get(0).getMessage() : "Request failed";
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

    private static HttpUriRequest buildPostRequest(String uri, HttpEntity params) {
        return RequestBuilder.post(GRAPHQL_URL)
                .setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setHeader(HttpHeaders.REFERER, uri)
                .setHeader(":authority", "leetcode.com")
                .setHeader(":method", "POST")
                .setHeader(":scheme", "https")
                .setEntity(params)
                .build();
    }

    private static HttpUriRequest buildPostRequest(HttpEntity params) {
        return RequestBuilder.post(GRAPHQL_URL)
                .setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setHeader(":authority", "leetcode.com")
                .setHeader(":method", "POST")
                .setHeader(":scheme", "https")
                .setEntity(params)
                .build();
    }

    private static HttpUriRequest buildPostRequest(String uri, String cookies, HttpEntity params) {
        cookies = decode(cookies);
        return RequestBuilder.post(GRAPHQL_URL)
                .setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setHeader(HttpHeaders.REFERER, uri)
                .setHeader("Cookie", cookies)
                .setHeader("x-csrftoken", getToken(cookies))
                .setHeader(":authority", "leetcode.com")
                .setHeader(":method", "POST")
                .setHeader(":scheme", "https")
                .setEntity(params)
                .build();
    }

    static String getCsrfToken(CookieStore cookieStore) {
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
        if (StringUtils.isEmpty(uri)) return null;
        return uri.replace("leetcode.com", "")
                .replace("problems", "")
                .replace("https:", "")
                .replace("http:", "")
                .replace("/", "")
                .replace("discuss", "");
    }

    static StringEntity buildRequestBody(String operationName, String variables, String query) {
        JSONObject json = new JSONObject();
        json.put("operationName", operationName);
        json.put("variables", variables);
        json.put("query", query);
        return new StringEntity(json.toString(), "UTF-8");
    }


}
