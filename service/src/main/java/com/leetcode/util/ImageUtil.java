package com.leetcode.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leetcode.model.response.APIResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.leetcode.util.CommonUtil.decode;
import static com.leetcode.util.HttpUtil.getToken;

public class ImageUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtil.class);

    private static HttpUriRequest buildRequest(String uri, String refer,
                                                String cookies, HttpEntity params) {
        cookies = decode(cookies);
        HttpUriRequest req = RequestBuilder.post(uri)
                .setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                //boundary is necessary
                .setHeader(HttpHeaders.CONTENT_TYPE, "multipart/form-data;boundary=----WbKitFormBoundarysfevHGSzVFcFIb9e")
                .setHeader(HttpHeaders.REFERER, refer)
                .setHeader("Cookie", cookies)
                .setHeader("X-CSRFToken", getToken(cookies))
                .setEntity(params)
                .build();
        LOGGER.info("Executing request {}", req.getRequestLine());
        return req;
    }

    private static HttpEntity buildImageFormData(MultipartFile file) throws IOException {
        InputStreamBody bin = new InputStreamBody(file.getInputStream(), ContentType.create("image/*"), file.getName());
        return MultipartEntityBuilder.create()
                .setCharset(Charset.forName("utf-8"))
                .setBoundary("----WbKitFormBoundarysfevHGSzVFcFIb9e") //boundary is necessary
                .addPart("image", bin)
                .build();
    }

    public static APIResponse upload(String uri, String refer, String cookies, MultipartFile file) throws IOException {
        HttpEntity formData = buildImageFormData(file);
        HttpUriRequest request = buildRequest(uri, refer, cookies, formData);
        try (CloseableHttpClient httpClient = HttpClients.custom().build();
             CloseableHttpResponse res = httpClient.execute(request)) {
            String content = EntityUtils.toString(res.getEntity(), "UTF-8");
            LOGGER.info("Response content: {}", content);
            JSONObject status = JSON.parseObject(content);
            if (res.getStatusLine().getStatusCode() == 200) {
                return new APIResponse(status.get("url"));
            }
            Object error = status.get("detail");
            return new APIResponse(400, error != null ? error.toString() : "Upload failure");
        } catch (Exception e) {
            LOGGER.error("Exception occurs.", e);
        }
        return new APIResponse(500, "Upload failure.  Please try again");
    }

}
